package is.brana.unnur.objects.gui.drawpath;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.nineoldandroids.animation.ObjectAnimator;
import is.brana.unnur.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thibaultguegan on 26/05/2014.
 */
public class HouseView extends View {

    private static final String LOG_TAG = "StateView";

    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private final SvgHelper mSvg = new SvgHelper(mPaint);
    private int mSvgResource;

    private final Object mSvgLock = new Object();
    private List<SvgHelper.SvgPath> mPaths = new ArrayList<SvgHelper.SvgPath>(0);
    private Thread mLoader;

    private float mPhase;
    private float mFadeFactor;
    private int mDuration;
    private float mParallax = 1.0f;
    private float mOffsetY;

    private ObjectAnimator mSvgAnimator;

    public HouseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HouseView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mPaint.setStyle(Paint.Style.STROKE);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HouseView, defStyle, 0);
        try {
            if (a != null) {
                mPaint.setStrokeWidth(a.getFloat(R.styleable.HouseView_strokeWidth, 1.0f));
                mPaint.setColor(a.getColor(R.styleable.HouseView_strokeColor, 0xff000000));
                mPhase = a.getFloat(R.styleable.HouseView_phase, 0.0f);
                mDuration = a.getInt(R.styleable.HouseView_duration, 4000);
                mFadeFactor = a.getFloat(R.styleable.HouseView_fadeFactor, 10.0f);
            }
        } finally {
            if (a != null) a.recycle();
        }
    }

    public float getPhase() {
        return mPhase;
    }

    public void setPhase(float phase) {
        mPhase = phase;
        synchronized (mSvgLock) {
            updatePathsPhaseLocked();
        }
        invalidate();
    }

    public int getSvgResource() {
        return mSvgResource;
    }

    public void setSvgResource(int svgResource) {
        mSvgResource = svgResource;
    }

    @Override
    protected void onSizeChanged(final int w, final int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (mLoader != null) {
            try {
                mLoader.join();
            } catch (InterruptedException e) {
                Log.e(LOG_TAG, "Unexpected error", e);
            }
        }

        mLoader = new Thread(new Runnable() {
            @Override
            public void run() {
                mSvg.load(getContext(), mSvgResource);
                synchronized (mSvgLock) {
                    mPaths = mSvg.getPathsForViewport(
                            w - getPaddingLeft() - getPaddingRight(),
                            h - getPaddingTop() - getPaddingBottom());
                    updatePathsPhaseLocked();
                }
            }
        }, "SVG Loader");
        mLoader.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        synchronized (mSvgLock) {
            canvas.save();
            canvas.translate(getPaddingLeft(), getPaddingTop() + mOffsetY);
            final int count = mPaths.size();
            for (int i = 0; i < count; i++) {
                SvgHelper.SvgPath svgPath = mPaths.get(i);

                // We use the fade factor to speed up the alpha animation
                int alpha = (int) (Math.min((1.0f - mPhase) * mFadeFactor, 1.0f) * 255.0f);
                svgPath.paint.setAlpha((int) (alpha * mParallax));

                canvas.drawPath(svgPath.path, svgPath.paint);
            }
            canvas.restore();
        }
    }

    private void updatePathsPhaseLocked() {

        final int count = mPaths.size();
        for (int i = 0; i < count; i++) {
            SvgHelper.SvgPath svgPath = mPaths.get(i);
            svgPath.paint.setPathEffect(createPathEffect(svgPath.length, mPhase, 0.0f));
        }
    }

    private static PathEffect createPathEffect(float pathLength, float phase, float offset) {
        return new DashPathEffect(new float[] { pathLength, pathLength },
                Math.max(phase * pathLength, offset));
    }

    public void startAnimation()
    {
        if (mSvgAnimator == null) {

            mSvgAnimator = ObjectAnimator.ofFloat(this, "phase", mPhase, 0.0f);
            mSvgAnimator.setDuration(mDuration);
            mSvgAnimator.start();
        }

        invalidate();

    }
}
