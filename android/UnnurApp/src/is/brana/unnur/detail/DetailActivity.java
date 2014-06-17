package is.brana.unnur.detail;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import is.brana.unnur.R;
import is.brana.unnur.client.UnnurClient;
import is.brana.unnur.detail.map.MapActivity;
import is.brana.unnur.interfaces.AccomodationDetailListener;
import is.brana.unnur.interfaces.LocationDetailImageClick;
import is.brana.unnur.interfaces.LocationMapClick;
import is.brana.unnur.objects.Accomodation;
import is.brana.unnur.objects.AccomodationDetail;
import is.brana.unnur.objects.gui.HeaderHolder;
import is.brana.unnur.utils.Keys;
import is.brana.unnur.utils.TypeFaceSpan;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by thibaultguegan on 21/05/2014.
 */
public class DetailActivity extends Activity implements LocationDetailImageClick, LocationMapClick, AccomodationDetailListener{
    private static final String TAG = DetailActivity.class.getName();

    private static final float ALPHA_MAX = 255.0f;
    private static final float SCROLL_MAX = 450.0f;

    private ProgressBar progressBar;
    private HeaderHolder headerHolder;

    private Long id;

    private ListBaseDetailAdapter listBaseDetailAdapter;
    private ListView listView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        progressBar = (ProgressBar) findViewById(R.id.load_progressbar);
        listView = (ListView) findViewById(android.R.id.list);

        //Get id
        if(getIntent().getExtras() != null)
        {
            id = getIntent().getLongExtra(Keys.KEY_ID, 0);

            headerHolder = new HeaderHolder(this, getIntent().getStringExtra(Keys.KEY_REGION));
            headerHolder.setBtnLeft(getResources().getDrawable(R.drawable.back_button));

            headerHolder.setBackgroundAlpha(0);

            try {
                UnnurClient.getInstance(this).getAccomodationDetail(this, id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        listView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i)
            {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {

                Log.d(TAG, "firstVisibleItem: " + firstVisibleItem + ", visibleItemCount: " + visibleItemCount + ", totalItemCount: " + totalItemCount);
                handleScroll();
            }
        });
    }

    private void handleScroll()
    {
        if(getScrollY() <= SCROLL_MAX)
        {
            int computedAlpha = (int) (ALPHA_MAX/SCROLL_MAX * getScrollY());

            Log.d(TAG, "Computed alha: "+ String.valueOf(computedAlpha));

            headerHolder.setBackgroundAlpha(computedAlpha);
        }
        else if(getScrollY() > SCROLL_MAX)
        {
            headerHolder.setBackgroundAlpha((int) ALPHA_MAX);
        }

        Log.d(TAG, String.valueOf(getScrollY()));
    }

    public int getScrollY() {
        View c = listView.getChildAt(0);
        if (c == null) {
            return 0;
        }

        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int top = c.getTop();

        return -top + firstVisiblePosition * c.getHeight() ;
    }

    public void onClickMenu(View v)
    {
        closeActivity();
    }

    @Override
    public void onBackPressed()
    {
        closeActivity();
    }

    @Override
    public void onClick(Accomodation accomodation) {

    }

    @Override
    public void onClickMap(AccomodationDetail accomodation) {
        Intent intentMap = new Intent(this, MapActivity.class);
        intentMap.putExtra(Keys.KEY_LATITUDE, accomodation.getLat());
        intentMap.putExtra(Keys.KEY_LONGITUDE, accomodation.getLon());
        intentMap.putExtra(Keys.KEY_NAME, accomodation.getRegion());
        startActivity(intentMap);
        overridePendingTransition(R.anim.push_down_out, R.anim.standstill);
    }

    public void closeActivity()
    {
        finish();

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onAccomodationDetailSuccess(JSONObject response) {

        AccomodationDetail accomodationDetail = new AccomodationDetail(response);

        Log.d(TAG, "accomodation detail created");

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        listBaseDetailAdapter = new ListBaseDetailAdapter(this, metrics, this, this);

        listBaseDetailAdapter.addItem(accomodationDetail);

        listView.setAdapter(listBaseDetailAdapter);

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onAccomodationDetailFailed() {
        progressBar.setVisibility(View.GONE);
    }
}