package is.brana.unnur.client;

import android.content.Context;
import android.util.Log;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;
import is.brana.unnur.interfaces.AccomodationDetailListener;
import is.brana.unnur.interfaces.AccomodationsListener;
import is.brana.unnur.interfaces.SearchResultListener;
import is.brana.unnur.interfaces.SearchSettingsListener;
import is.brana.unnur.utils.Urls;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by thibaultguegan on 19/05/2014.
 */
public class UnnurClient {

    private static final String TAG = UnnurClient.class.getName();

    private static UnnurClient uniqInstance;
    private static AsyncHttpClient client;
    private static Context context;

    public static synchronized UnnurClient getInstance(Context ctx)
    {
        if(uniqInstance == null)
        {
            uniqInstance = new UnnurClient();
            client = new AsyncHttpClient();
            context = ctx;
        }
        context = ctx;
        return uniqInstance;
    }

    public static void getAccomodations(final AccomodationsListener accomodationsListener, String count, String offset) throws JSONException
    {
        Header[] headers = {
                new BasicHeader("Content-type", "application/json")
        };

        String urlAccomodations = String.format(Urls.ACCOMODATIONS, offset, count);

        client.get(context, urlAccomodations, headers, null, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(String responseBody) {
                // Successfully got a response
                try {
                    JSONArray array = new JSONArray(responseBody);

                    accomodationsListener.onAccomodationsSuccess(array);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String responseBody, Throwable e)
            {
                // Response failed :(
                accomodationsListener.onAccomodationsFailed();
            }

        });
    }

    public static void getAccomodationDetail(final AccomodationDetailListener listener, Long id) throws JSONException
    {
        Header[] headers = {
                new BasicHeader("Content-type", "application/json")
        };

        String urlAccomodationDetail = String.format(Urls.ACCOMODATIONS_DETAIL, String.valueOf(id));

        client.get(context, urlAccomodationDetail, headers, null, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(String responseBody) {
                // Successfully got a response
                try {
                    JSONObject object = new JSONObject(responseBody);

                    listener.onAccomodationDetailSuccess(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String responseBody, Throwable e)
            {
                // Response failed :(
                listener.onAccomodationDetailFailed();
            }

        });
    }

    public static void getSearchSettings(final SearchSettingsListener listener) throws JSONException
    {
        Header[] headers = {
                new BasicHeader("Content-type", "application/json")
        };

        client.get(context, Urls.SEARCH_SETTINGS, headers, null, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(String responseBody) {
                // Successfully got a response
                try {
                    JSONObject object = new JSONObject(responseBody);

                    listener.onSearchSettingsSuccess(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String responseBody, Throwable e)
            {
                // Response failed :(
                listener.onSearchSettingsFailed();
            }

        });
    }

    public static void getSearchResults(final SearchResultListener listener, String priceMin, String priceMax, String sizeMin, String sizeMax, String roomMin, String roomMax, String zipCodes, String areaIds, String typeIds, String longTerm) throws JSONException
    {
        Header[] headers = {
                new BasicHeader("Content-type", "application/json")
        };

        String search = String.format(Urls.SEARCH, priceMin, priceMax, sizeMin, sizeMax, roomMin, roomMax, zipCodes, areaIds, typeIds, longTerm);

        client.get(context, search, headers, null, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(String responseBody) {
                // Successfully got a response
                try {
                    JSONArray array = new JSONArray(responseBody);

                    listener.onSearchResultSuccess(array);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String responseBody, Throwable e)
            {
                // Response failed :(
                Log.d(TAG, "failed");

                listener.onSearchResultFailed();
            }

        });
    }
}
