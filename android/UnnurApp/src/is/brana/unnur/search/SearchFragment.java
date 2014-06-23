package is.brana.unnur.search;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import is.brana.unnur.R;
import is.brana.unnur.client.UnnurClient;
import is.brana.unnur.detail.map.MapActivity;
import is.brana.unnur.interfaces.SearchResultListener;
import is.brana.unnur.interfaces.SearchSettingsListener;
import is.brana.unnur.objects.Accomodation;
import is.brana.unnur.objects.AccomodationType;
import is.brana.unnur.objects.Area;
import is.brana.unnur.objects.ZipCode;
import is.brana.unnur.search.fragments.EditAccomodationTypeFragment;
import is.brana.unnur.search.fragments.EditAreaFragment;
import is.brana.unnur.search.fragments.EditFieldFragment;
import is.brana.unnur.search.listeners.*;
import is.brana.unnur.search.results.SearchResultActivity;
import is.brana.unnur.utils.Keys;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by thibaultguegan on 27/05/2014.
 */
public class SearchFragment extends Fragment implements EditAreaTouchListener, EditCategoryTouchListener, SearchSettingsListener, EditAreaChangeListener, EditCategoryChangeListener, EditSearchButtonListener, SearchResultListener{
    private static final String TAG = SearchFragment.class.getName();

    private FragmentActivity activity;

    private final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
    private final int ANIMATION_DURATION = 250;
    private int modeContainerMargin;

    private FrameLayout mMainContainer;
    private ProgressBar progressBar;

    private EditAreaFragment editAreaFragment;
    private EditAccomodationTypeFragment editAccomodationTypeFragment;
    private EditFieldFragment editFieldFragment;
    private FragmentManager fragmentManager;

    private boolean regionExpanded;
    private boolean categoryExpanded;
    ArrayList<Area> areas = new ArrayList<Area>();
    ArrayList<AccomodationType> accomodationTypes = new ArrayList<AccomodationType>();

    private final Rect mTmpRect = new Rect();

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dynamic_form, null);


        mMainContainer = (FrameLayout) view.findViewById(R.id.main_container);
        progressBar = (ProgressBar) view.findViewById(R.id.load_progressbar);

        fragmentManager = activity.getSupportFragmentManager();

        //replace form container
        editFieldFragment = new EditFieldFragment();
        editFieldFragment.setEditAreaTouchListener(this);
        editFieldFragment.setEditCategoryTouchListener(this);
        editFieldFragment.setEditSearchButtonListener(this);
        fragmentManager.beginTransaction().replace(R.id.form_container, editFieldFragment).commit();

        //replace edit mode container
        editAreaFragment = new EditAreaFragment();
        editAreaFragment.setEditAreaChangeListener(this);
        editAccomodationTypeFragment = new EditAccomodationTypeFragment();
        editAccomodationTypeFragment.setEditCategoryChangeListener(this);
        fragmentManager.beginTransaction().replace(R.id.edit_mode_container, editAreaFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.edit_mode_accomodation_type_container, editAccomodationTypeFragment).commit();

        //margin for edit mode container
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        modeContainerMargin = getResources().getDimensionPixelSize(R.dimen.margin_mode_container);

        try {
            UnnurClient.getInstance(activity).getSearchSettings(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onViewCreated(android.view.View view, android.os.Bundle savedInstanceState)
    {

    }

    @Override
    public void onEditRegionTouch(View v, View movableView, boolean animated) {
        if(!regionExpanded)
        {
            regionExpanded = true;

            v.getDrawingRect(mTmpRect);

            ObjectAnimator anim = ObjectAnimator.ofFloat(v, "translationY", - ( movableView.getTop()));
            anim.setInterpolator(ANIMATION_INTERPOLATOR);
            anim.setDuration(ANIMATION_DURATION);
            anim.start();

            slideInToTop(v, movableView, getView().findViewById(R.id.edit_mode_container));

        }
        else
        {
            regionExpanded = false;

            v.getDrawingRect(mTmpRect);

            Log.d(TAG, "Movable view top: " + String.valueOf(movableView.getTop()));
            Log.d(TAG, "Container view top: " + String.valueOf(v.getTop()));

            ObjectAnimator anim = ObjectAnimator.ofFloat(v, "translationY", 0);
            anim.setInterpolator(ANIMATION_INTERPOLATOR);
            anim.setDuration(ANIMATION_DURATION);
            anim.start();

            slideInToBottom(v, getView().findViewById(R.id.edit_mode_container));
        }
    }

    private void slideInToTop(View editContainer, View movableView, View v)
    {
        //View v = getView().findViewById(R.id.edit_mode_container);

        //put edit at the bottom
        Log.d(TAG, "container view height :" + String.valueOf(v.getHeight()));

        ObjectAnimator anim = ObjectAnimator.ofFloat(v, "translationY", mMainContainer.getHeight());
        anim.setDuration(0);
        anim.start();

        v.setVisibility(View.VISIBLE);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(v, "translationY", editContainer.getTop()+movableView.getHeight()),
                ObjectAnimator.ofFloat(v, "alpha", 0, 1)
        );
        set.setDuration(ANIMATION_DURATION);
        set.setInterpolator(ANIMATION_INTERPOLATOR);
        set.start();

    }

    private void slideInToBottom(View editContainer, View v)
    {
        //View v = getView().findViewById(R.id.edit_mode_container);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(v, "translationY", mMainContainer.getHeight()),
                ObjectAnimator.ofFloat(v, "alpha", 1, 0)
        );
        set.setDuration(ANIMATION_DURATION);
        set.setInterpolator(ANIMATION_INTERPOLATOR);
        set.start();
    }

    @Override
    public void onEditCategoryTouch(View v, View movableView, boolean animated) {
        if(!categoryExpanded)
        {
            categoryExpanded = true;

            v.getDrawingRect(mTmpRect);

            ObjectAnimator anim = ObjectAnimator.ofFloat(v, "translationY", - ( movableView.getTop()));
            anim.setInterpolator(ANIMATION_INTERPOLATOR);
            anim.setDuration(ANIMATION_DURATION);
            anim.start();

            slideInToTop(v, movableView, getView().findViewById(R.id.edit_mode_accomodation_type_container));
        }
        else
        {
            categoryExpanded = false;

            v.getDrawingRect(mTmpRect);

            Log.d(TAG, "Movable view top: " + String.valueOf(movableView.getTop()));
            Log.d(TAG, "Container view top: " + String.valueOf(v.getTop()));

            ObjectAnimator anim = ObjectAnimator.ofFloat(v, "translationY", 0);
            anim.setInterpolator(ANIMATION_INTERPOLATOR);
            anim.setDuration(ANIMATION_DURATION);
            anim.start();

            slideInToBottom(v, getView().findViewById(R.id.edit_mode_accomodation_type_container));
        }
    }

    @Override
    public void onSearchSettingsSuccess(JSONObject response) {
        try {
            Log.d(TAG, response.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray areaArray = response.getJSONArray("Areas");

            areas = new ArrayList<Area>();
            for(int i=0; i<areaArray.length(); i++)
            {
                JSONObject areaObject = areaArray.getJSONObject(i);
                Area area = new Area(areaObject);
                areas.add(area);
            }


            JSONArray accomodationsArray = response.getJSONArray("AccommodationTypes");

            ArrayList<AccomodationType> accomodations = new ArrayList<AccomodationType>();
            for(int i=0; i<accomodationsArray.length(); i++)
            {
                JSONObject accomodationObject = accomodationsArray.getJSONObject(i);
                AccomodationType accomodationType = new AccomodationType(accomodationObject);
                accomodations.add(accomodationType);
            }
            accomodationTypes = accomodations;

            Log.d(TAG, "Arraylist filled");
            mMainContainer.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            editAreaFragment.setAreas(areas);
            editAccomodationTypeFragment.setAccomodationTypes(accomodationTypes);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSearchSettingsFailed() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onEditAreaChanged(Map<Integer, List<Integer>> mCheckedStates) {
        Log.d(TAG, mCheckedStates.toString());

        List<Integer> listToDisplay = new ArrayList<Integer>();

        int limit = 7;

        boolean broken = false;

        for(Integer key: mCheckedStates.keySet())
        {
            Area area = areas.get(key);

            if(listToDisplay.size() >= limit)
            {
                broken = true;
                break;
            }

            //retrieve 7 zip code if possible
            ArrayList<ZipCode> zipCodes = area.getZipCodes();
            for(int i=0; i<zipCodes.size(); i++)
            {
                if(mCheckedStates.get(key).contains(i))
                {
                    //do stuff
                    ZipCode zipCode = zipCodes.get(i);
                    listToDisplay.add(zipCode.getZipCode());
                }

                if(listToDisplay.size() >= limit)
                {
                    broken = true;
                    break;
                }
            }
        }

        String txt = new String();
        for(int i=0; i<listToDisplay.size(); i++)
        {
            Integer zipCode = listToDisplay.get(i);

            if(i == 0)
            {
                txt = String.valueOf(zipCode);
            }
            else
            {
                txt = txt + ", " + String.valueOf(zipCode);
            }

        }

        if(broken)
            txt = txt + "...";

        if(listToDisplay.isEmpty())
            txt = activity.getResources().getString(R.string.empty_area);


        //Log.d(TAG,"Areas: " + areas.toString());
        Log.d(TAG,"listToDisplay: " + listToDisplay.toString());
        editFieldFragment.setSelectedArea(txt);
    }

    @Override
    public void onEditCategoryChanged(ArrayList<AccomodationType> accomodationTypes) {

        String txt = new String();

        for(int i=0; i<accomodationTypes.size(); i++)
        {
            if(i == 0)
            {
                txt = accomodationTypes.get(i).getName();
            }
            else
            {
                txt = txt + ", " + accomodationTypes.get(i).getName();
            }
        }

        if(accomodationTypes.isEmpty())
            txt = activity.getResources().getString(R.string.empty_category);

        editFieldFragment.setSelectedCategory(txt);
    }

    @Override
    public void onButtonSearchClicked() {
        progressBar.setVisibility(View.VISIBLE);

        Map<Integer, List<Integer>> checkedStates = editAreaFragment.getCheckedStates();
        List<AccomodationType> accomodations = editAccomodationTypeFragment.getAccomodationTypes();

        //AREAS
        String zipCodeString = "";
        if(checkedStates.isEmpty())
        {
            zipCodeString = "null";
        }
        else //build the string representing the areas
        {
            for(Integer key: checkedStates.keySet())
            {
                Area area = areas.get(key);

                ArrayList<ZipCode> zipCodes = area.getZipCodes();

                for(int i=0; i<zipCodes.size(); i++)
                {
                    if(checkedStates.get(key).contains(i))
                    {
                        Integer zipCode = zipCodes.get(i).getZipCode();

                        if(zipCodeString.length() == 0)
                        {
                            zipCodeString = String.valueOf(zipCode);
                        }
                        else
                        {
                            zipCodeString = zipCodeString + "-" + String.valueOf(zipCode);
                        }
                    }
                }

            }
        }

        //zipcodes, double check
        if(zipCodeString.length() == 0)
            zipCodeString = "null";
        Log.d(TAG, "search zip code string: " + zipCodeString);

        //ACCOMODATION TYPES
        String accomadationTypeString = "";
        if(accomodationTypes.isEmpty())
        {
            accomadationTypeString = "null";
        }
        else
        {
            for(AccomodationType accomodationType: accomodationTypes)
            {
                if(accomodations.contains(accomodationType))
                {
                    if(accomadationTypeString.length() == 0)
                    {
                        accomadationTypeString = String.valueOf(accomodationType.getId());
                    }
                    else
                    {
                        accomadationTypeString = accomadationTypeString + "-" + String.valueOf(accomodationType.getId());
                    }
                }
            }
        }

        //accomodations, double check
        if(accomadationTypeString.length() == 0)
            accomadationTypeString = "null";
        Log.d(TAG, "Search accomodations type string: " + accomadationTypeString);

        //AREAS
        String areaString = "";
        //TODO: Viktor provides Area Ids
        if(!checkedStates.isEmpty())
        {
            for(Integer key: checkedStates.keySet())
            {
                Area area = areas.get(key);

                if(areaString.length() == 0)
                {
                }
                else
                {
                }
            }
        }

        if(areaString.length() == 0)
            areaString = "null";

        //PRICE MIN
        //TODO: make it possible with slider
        String priceMinString = editFieldFragment.getMinPrice();

        //PRICE MAX
        String priceMaxString = editFieldFragment.getMaxPrice();

        //SIZE MIN
        String sizeMinString = editFieldFragment.getMinSquareSize();

        //SIZE MAX
        String sizeMaxString = editFieldFragment.getMaxSquareSize();

        //ROOM MIN
        String roomMinString = editFieldFragment.getMinRoom();

        //ROOM MAX
        String roomMaxString = editFieldFragment.getMaxRoom();

        //LONG TERM (default true for now)
        String longTermString = "True";

        //perform request
        try {
            UnnurClient.getInstance(activity).getSearchResults(this, priceMinString, priceMaxString, sizeMinString, sizeMaxString, roomMinString, roomMaxString, zipCodeString, areaString, accomadationTypeString, longTermString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSearchResultSuccess(JSONArray array) {
        progressBar.setVisibility(View.GONE);

        Log.d(TAG, String.valueOf(array.length()));

        ArrayList<Accomodation> accomodations = new ArrayList<Accomodation>();

        for(int i = 0; i<array.length(); i++)
        {
            try {
                JSONObject object = array.getJSONObject(i);

                Accomodation accomodation = new Accomodation(object);

                accomodations.add(accomodation);

            } catch (JSONException e) {
                e.printStackTrace();

            }
        }

        Log.d(TAG, "Accomodations arraylist: " + accomodations.toString());

        Intent intentSearch = new Intent(activity, SearchResultActivity.class);
        intentSearch.putParcelableArrayListExtra(Keys.KEY_ACCOMODATIONS, accomodations);
        startActivity(intentSearch);
        activity.overridePendingTransition(R.anim.push_down_out, R.anim.standstill);
    }

    @Override
    public void onSearchResultFailed() {
        progressBar.setVisibility(View.GONE);
    }
}