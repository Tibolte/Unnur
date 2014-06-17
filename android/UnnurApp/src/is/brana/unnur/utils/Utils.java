package is.brana.unnur.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thibaultguegan on 16/05/2014.
 */
public class Utils {

    public static final int CAT_ACCOMODATIONS = 0;
    public static final int CAT_FAVORITES = 1;
    public static final int CAT_SEARCH = 2;
    public static final int CAT_WATCH = 3;


    private static boolean fontsInitialized_ = false;
    private static final Map<String, Typeface> fonts_ = new HashMap<String, Typeface>();

    public static final String ROBOTO_BOLD = "fonts/Roboto-Bold.ttf";
    public static final String ROBOTO_MEDIUM = "fonts/Roboto-Medium.ttf";
    public static final String ROBOTO_REGULAR = "fonts/Roboto-Regular.ttf";
    public static final String ROBOTO_LIGHT = "fonts/Roboto-Light.ttf";
    public static final String ROBOTO_THIN = "fonts/Roboto-Thin.ttf";

    public static Typeface getFont(final Context ctx, final String fontKey)
    {
        if (!fontsInitialized_)
        {
            initFonts(ctx.getApplicationContext());
        }

        return fonts_.get(fontKey);
    }

    private static synchronized void initFonts(final Context context)
    {
        if (fontsInitialized_)
        {
            return;
        }
        try
        {
            fonts_.put(ROBOTO_BOLD, Typeface.createFromAsset(context.getAssets(), ROBOTO_BOLD));
            fonts_.put(ROBOTO_MEDIUM, Typeface.createFromAsset(context.getAssets(), ROBOTO_MEDIUM));
            fonts_.put(ROBOTO_REGULAR, Typeface.createFromAsset(context.getAssets(), ROBOTO_REGULAR));
            fonts_.put(ROBOTO_LIGHT, Typeface.createFromAsset(context.getAssets(), ROBOTO_LIGHT));
            fonts_.put(ROBOTO_THIN, Typeface.createFromAsset(context.getAssets(), ROBOTO_THIN));
        } catch (Throwable e)
        {
            Log.e("FontInit", "Failed to load fonts", e);
        } finally
        {
            fontsInitialized_ = true;
        }
    }

    public static String getAppVersion(final Context ctx) throws PackageManager.NameNotFoundException {

        String appVersion = ctx.getPackageManager()
                .getPackageInfo(ctx.getPackageName(), 0).versionName;

        return appVersion;
    }


    /*public static void showAppMsgAlert(String msg, Activity activity) {

        AppMsg appMsg = AppMsg.makeText(activity, msg, AppMsg.STYLE_ALERT);

        appMsg.setAnimation(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        appMsg.show();
    }*/

}
