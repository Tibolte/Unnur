package is.brana.unnur.accomodation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import is.brana.unnur.R;
import is.brana.unnur.client.UnnurClient;
import is.brana.unnur.detail.DetailActivity;
import is.brana.unnur.interfaces.AccomodationsListener;
import is.brana.unnur.interfaces.LocationDetailImageClick;
import is.brana.unnur.objects.Accomodation;
import is.brana.unnur.utils.Keys;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by thibaultguegan on 16/05/2014.
 */
public class AccomodationFragment extends Fragment implements LocationDetailImageClick, AccomodationsListener{
    private static final String TAG = AccomodationFragment.class.getName();

    private Activity activity;

    private ArrayList<Accomodation> items= new ArrayList<Accomodation>();
    private AccomodationAdapter accomodationAdapter;
    private SwipeRefreshLayout swipeLayout;
    private ListView mListView;

    private int limit;
    private boolean loading;
    private boolean loadMore;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        limit = 10;
        loading = false;
        loadMore = true;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.accomodations_list, null);

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                items.clear();
                loadListData(0);
            }
        });
        swipeLayout.setColorScheme(R.color.green_main,
                R.color.white,
                R.color.green_main,
                R.color.white);

        return view;
    }

    @Override
    public void onViewCreated(android.view.View view, android.os.Bundle savedInstanceState)
    {
        accomodationAdapter = new AccomodationAdapter(activity, R.id.empty, items, this);

        mListView = (ListView) view.findViewById(android.R.id.list);
        mListView.setAdapter(accomodationAdapter);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            int mLastFirstVisibleItem = 0;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i)
            {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                if (totalItemCount > 0)
                {
                    if (firstVisibleItem + visibleItemCount == totalItemCount && !loading && loadMore)
                    {
                        loadListData(totalItemCount);
                    }
                }


                Log.d(TAG, "firstVisibleItem: " + firstVisibleItem + ", visibleItemCount: " + visibleItemCount + ", totalItemCount: " + totalItemCount);

            }
        });

        //at first
        loadListData(0);
    }

    private void loadListData(int offset)
    {
        loading = true;
        swipeLayout.setRefreshing(true);

        String count = Integer.toString(limit);
        String offsetString = Integer.toString(offset);

        try {
            swipeLayout.setRefreshing(true);
            UnnurClient.getInstance(activity).getAccomodations(this, count, offsetString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAccomodationsSuccess(JSONArray array) {
        loading = false;

        Log.d(TAG, String.valueOf(array.length()));

        loadMore = array.length() == limit;

        for(int i = 0; i<array.length(); i++)
        {
            try {
                JSONObject object = array.getJSONObject(i);

                Accomodation accomodation = new Accomodation(object);

                items.add(accomodation);

            } catch (JSONException e) {
                e.printStackTrace();

                loadMore = false;

                swipeLayout.setRefreshing(false);

            }
        }

        swipeLayout.setRefreshing(false);

        accomodationAdapter.notifyDataSetChanged();

    }

    @Override
    public void onAccomodationsFailed() {
        swipeLayout.setRefreshing(false);

        loading = false;

        loadMore = false;
    }

    @Override
    public void onClick(Accomodation accomodation) {
        Intent intentDetail = new Intent(activity, DetailActivity.class);
        intentDetail.putExtra(Keys.KEY_ID, accomodation.getId());
        intentDetail.putExtra(Keys.KEY_REGION, accomodation.getRegion());
        intentDetail.putExtra(Keys.KEY_ADDRESS, accomodation.getRegion());

        startActivity(intentDetail);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}