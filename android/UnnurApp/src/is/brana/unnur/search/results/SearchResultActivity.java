package is.brana.unnur.search.results;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import is.brana.unnur.R;
import is.brana.unnur.detail.DetailActivity;
import is.brana.unnur.interfaces.LocationDetailImageClick;
import is.brana.unnur.objects.Accomodation;
import is.brana.unnur.objects.gui.HeaderHolder;
import is.brana.unnur.utils.Keys;

import java.util.ArrayList;

/**
 * Created by thibaultguegan on 14/06/2014.
 */
public class SearchResultActivity extends Activity implements LocationDetailImageClick{
    private static final String TAG = SearchResultActivity.class.getName();

    private HeaderHolder headerHolder;
    private ArrayList<Accomodation> accomodations;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_result);

        headerHolder = new HeaderHolder(this, getResources().getString(R.string.search));
        headerHolder.setBtnLeft(getResources().getDrawable(R.drawable.btn_cross));

        if(getIntent().getExtras() != null) {
            accomodations = getIntent().getExtras().getParcelableArrayList(Keys.KEY_ACCOMODATIONS);

            Log.d(TAG, accomodations.toString());

            ListView listView = (ListView) findViewById(android.R.id.list);

            SearchResultAdapter searchResultAdapter = new SearchResultAdapter(this, R.layout.accomodation_search_item, accomodations, this);

            listView.setAdapter(searchResultAdapter);
        }
    }

    public void onClickMenu(View v)
    {
        closeResultActivity();
    }

    @Override
    public void onBackPressed()
    {
        closeResultActivity();
    }

    private void closeResultActivity()
    {
        finish();

        overridePendingTransition(R.anim.standstill, R.anim.push_down_in);
    }

    @Override
    public void onClick(Accomodation accomodation) {
        Intent intentDetail = new Intent(this, DetailActivity.class);
        intentDetail.putExtra(Keys.KEY_ID, accomodation.getId());
        intentDetail.putExtra(Keys.KEY_REGION, accomodation.getRegion());
        intentDetail.putExtra(Keys.KEY_ADDRESS, accomodation.getRegion());

        startActivity(intentDetail);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}