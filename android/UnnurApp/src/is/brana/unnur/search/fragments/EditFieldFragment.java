package is.brana.unnur.search.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import is.brana.unnur.R;
import is.brana.unnur.search.listeners.EditAreaTouchListener;
import is.brana.unnur.search.listeners.EditCategoryTouchListener;
import is.brana.unnur.search.listeners.EditSearchButtonListener;
import is.brana.unnur.utils.RangeSeekBar;
import is.brana.unnur.utils.Utils;

import java.util.List;

/**
 * Created by thibaultguegan on 03/06/2014.
 */
public class EditFieldFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private static final String TAG = EditFieldFragment.class.getName();

    private Activity activity;

    //price
    private SeekBar seekBarPrice;
    private RangeSeekBar rangeSeekBarPrice;
    private TextView txtPriceTitle, txtPriceMin, txtPriceMax;

    //size
    private SeekBar seekBarSize;
    private RangeSeekBar rangeSeekBarSize;
    private TextView txtSizeTitle, txtSizeMin, txtSizeMax;

    //room
    private SeekBar seekBarRoom;
    private RangeSeekBar rangeSeekBarRoom;
    private TextView txtRoomTitle, txtRoomMin, txtRoomMax;

    //Edit areas banner
    private TextView txtAreaTitle;
    private TextView txtSelectedAreas;

    //Edit category banner
    private TextView txtCategoryTitle;
    private TextView txtSelectedCategories;

    //Search button
    private Button btnSearch;

    private EditAreaTouchListener editAreaTouchListener;
    private EditCategoryTouchListener editCategoryTouchListener;
    private EditSearchButtonListener editSearchButtonListener;

    private RelativeLayout editRegionContainer;
    private RelativeLayout editCategoryContainer;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, null);

        //seekBarPrice = (SeekBar) view.findViewById(R.id.seekBarPrice);
        txtPriceTitle = (TextView) view.findViewById(R.id.txtPriceTitle);
        txtPriceMin = (TextView) view.findViewById(R.id.txtPriceMin);
        txtPriceMax = (TextView) view.findViewById(R.id.txtPriceMax);
        //seekBarSize = (SeekBar) view.findViewById(R.id.seekBarSize);
        txtSizeTitle = (TextView) view.findViewById(R.id.txtSizeTitle);
        txtSizeMin = (TextView) view.findViewById(R.id.txtSizeMin);
        txtSizeMax = (TextView) view.findViewById(R.id.txtSizeMax);
        //seekBarRoom = (SeekBar) view.findViewById(R.id.seekBarRoom);
        txtRoomTitle = (TextView) view.findViewById(R.id.txtRoomTitle);
        txtRoomMin = (TextView) view.findViewById(R.id.txtRoomMin);
        txtRoomMax = (TextView) view.findViewById(R.id.txtRoomMax);
        editRegionContainer = (RelativeLayout) view.findViewById(R.id.editRegionContainer);
        editCategoryContainer = (RelativeLayout) view.findViewById(R.id.editCategoryContainer);
        txtAreaTitle = (TextView) view.findViewById(R.id.txtRegionTitle);
        txtSelectedAreas = (TextView) view.findViewById(R.id.txtRegionSelected);
        txtCategoryTitle = (TextView) view.findViewById(R.id.txtCategoryTitle);
        txtSelectedCategories = (TextView) view.findViewById(R.id.txtCategorySelected);
        btnSearch = (Button) view.findViewById(R.id.btnSearch);

        txtPriceTitle.setTypeface(Utils.getFont(activity, Utils.ROBOTO_BOLD));
        txtPriceMin.setTypeface(Utils.getFont(activity, Utils.ROBOTO_REGULAR));
        txtPriceMax.setTypeface(Utils.getFont(activity, Utils.ROBOTO_REGULAR));
        txtSizeTitle.setTypeface(Utils.getFont(activity, Utils.ROBOTO_BOLD));
        txtSizeMin.setTypeface(Utils.getFont(activity, Utils.ROBOTO_REGULAR));
        txtSizeMax.setTypeface(Utils.getFont(activity, Utils.ROBOTO_REGULAR));
        txtRoomTitle.setTypeface(Utils.getFont(activity, Utils.ROBOTO_BOLD));
        txtRoomMin.setTypeface(Utils.getFont(activity, Utils.ROBOTO_REGULAR));
        txtRoomMax.setTypeface(Utils.getFont(activity, Utils.ROBOTO_REGULAR));
        txtAreaTitle.setTypeface(Utils.getFont(activity, Utils.ROBOTO_BOLD));
        txtCategoryTitle.setTypeface(Utils.getFont(activity, Utils.ROBOTO_BOLD));
        txtSelectedAreas.setTypeface(Utils.getFont(activity, Utils.ROBOTO_REGULAR));
        txtSelectedCategories.setTypeface(Utils.getFont(activity, Utils.ROBOTO_REGULAR));
        btnSearch.setTypeface(Utils.getFont(activity, Utils.ROBOTO_BOLD));

        //seekBarPrice.setOnSeekBarChangeListener(this);
        //seekBarSize.setOnSeekBarChangeListener(this);
        //seekBarRoom.setOnSeekBarChangeListener(this);

        //seekBarPrice.setProgress(seekBarPrice.getMax());
        //seekBarSize.setProgress(seekBarSize.getMax());
        //seekBarRoom.setProgress(seekBarRoom.getMax());

        txtSelectedAreas.setText(activity.getResources().getString(R.string.empty_area));
        txtSelectedCategories.setText(activity.getResources().getString(R.string.empty_category));

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editSearchButtonListener.onButtonSearchClicked();
            }
        });

        rangeSeekBarRoom = new RangeSeekBar<Integer>(0, 10, activity);
        rangeSeekBarRoom.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                // handle changed range values
                Log.i(TAG, "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
                int stepRoom = 1;

                //maxValue = (maxValue/stepRoom)*stepRoom;
                if(maxValue == 10)
                    txtRoomMax.setText("10+ herb.");
                else
                    txtRoomMax.setText(String.valueOf(maxValue) + " herb.");

                //minValue = (maxValue/stepRoom)*stepRoom;
                if(minValue == 10)
                    txtRoomMin.setText("10+ herb.");
                else
                    txtRoomMin.setText(String.valueOf(minValue) + " herb.");
            }
        });

        LinearLayout.LayoutParams rangeParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        rangeParams.setMargins(getResources().getDimensionPixelSize(R.dimen.seek_bar_room_margin_left), 0, 0, 0);
        rangeSeekBarRoom.setLayoutParams(rangeParams);

        rangeSeekBarSize = new RangeSeekBar<Integer>(0, 2000, activity);
        rangeSeekBarSize.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                // handle changed range values
                Log.i(TAG, "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
                int stepSize = 10;

                maxValue = (maxValue/stepSize)*stepSize;
                txtSizeMax.setText(String.valueOf(maxValue) + " m2");

                minValue = (minValue/stepSize)*stepSize;
                txtSizeMin.setText(String.valueOf(minValue) + " m2");
            }
        });

        LinearLayout.LayoutParams rangeParamsSize = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        rangeParamsSize.setMargins(getResources().getDimensionPixelSize(R.dimen.seek_bar_size_margin_left), 0, 0, 0);
        rangeSeekBarSize.setLayoutParams(rangeParamsSize);


        rangeSeekBarPrice = new RangeSeekBar<Integer>(0, 500000, activity);
        rangeSeekBarPrice.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                // handle changed range values
                Log.i(TAG, "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
                int stepPrice = 10000;
                maxValue = (maxValue/stepPrice)*stepPrice;

                String x = Integer.toString(maxValue);

                if(x.length() == 5)
                {
                    txtPriceMax.setText(x.substring(0,2) + "." + x.substring(2, x.length()) + " kr.");
                }
                else if(x.length() == 6)
                {
                    txtPriceMax.setText(x.substring(0,3) + "." + x.substring(3, x.length()) + " kr.");
                }
                else
                {
                    txtPriceMax.setText(String.valueOf(maxValue) + " kr.");
                }

                minValue = (minValue/stepPrice)*stepPrice;

                String y = Integer.toString(minValue);

                if(y.length() == 5)
                {
                    txtPriceMin.setText(y.substring(0,2) + "." + y.substring(2, y.length()) + " kr.");
                }
                else if(y.length() == 6)
                {
                    txtPriceMin.setText(y.substring(0,3) + "." + y.substring(3, y.length()) + " kr.");
                }
                else
                {
                    txtPriceMin.setText(String.valueOf(minValue) + " kr.");
                }
            }
        });

        LinearLayout.LayoutParams rangeParamsPrice = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        rangeParamsPrice.setMargins(getResources().getDimensionPixelSize(R.dimen.seek_bar_size_margin_left), 0, 0, 0);
        rangeSeekBarPrice.setLayoutParams(rangeParamsPrice);

        // add RangeSeekBar to pre-defined layout
        ViewGroup layoutRoom = (ViewGroup) view.findViewById(R.id.rangeRoomContainer);
        layoutRoom.addView(rangeSeekBarRoom);

        // add RangeSeekBar to pre-defined layout
        ViewGroup layoutSize = (ViewGroup) view.findViewById(R.id.rangeSizeContainer);
        layoutSize.addView(rangeSeekBarSize);

        // add RangeSeekBar to pre-defined layout
        ViewGroup layoutPrice = (ViewGroup) view.findViewById(R.id.rangePriceContainer);
        layoutPrice.addView(rangeSeekBarPrice);

        return view;
    }

    @Override
    public void onViewCreated(android.view.View view, android.os.Bundle savedInstanceState)
    {
        editRegionContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAreaTouchListener.onEditRegionTouch(getView(), editRegionContainer, true);
            }
        });

        editCategoryContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCategoryTouchListener.onEditCategoryTouch(getView(), editCategoryContainer, true);
            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        /*if(seekBar == seekBarPrice)
        {
            int stepPrice = 10000;
            progress = (progress/stepPrice)*stepPrice;

            String x = Integer.toString(progress);

            if(x.length() == 5)
            {
                txtPriceMax.setText(x.substring(0,2) + "." + x.substring(2, x.length()) + " kr.");
            }
            else if(x.length() == 6)
            {
                txtPriceMax.setText(x.substring(0,3) + "." + x.substring(3, x.length()) + " kr.");
            }
            else
            {
                txtPriceMax.setText(String.valueOf(progress) + " kr.");
            }
        }*/

        /*else if(seekBar == seekBarSize)
        {
            int stepSize = 10;
            progress = (progress/stepSize)*stepSize;
            txtSizeMax.setText(String.valueOf(progress) + " m2");
        }*/

        /*else if(seekBar == seekBarRoom)
        {
            int stepRoom = 1;
            progress = (progress/stepRoom)*stepRoom;
            if(progress == 10)
                txtRoomMax.setText("10+ herb.");
            else
                txtRoomMax.setText(String.valueOf(progress) + " herb.");
        }*/
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void setEditAreaTouchListener(EditAreaTouchListener editAreaTouchListener) {
        this.editAreaTouchListener = editAreaTouchListener;
    }

    public void setEditCategoryTouchListener(EditCategoryTouchListener editCategoryTouchListener) {
        this.editCategoryTouchListener = editCategoryTouchListener;
    }

    public void setEditSearchButtonListener(EditSearchButtonListener editSearchButtonListener) {
        this.editSearchButtonListener = editSearchButtonListener;
    }

    public void setSelectedArea(String text)
    {
        txtSelectedAreas.setText(text);
    }

    public void setSelectedCategory(String text)
    {
        txtSelectedCategories.setText(text);
    }

    public String getMinPrice()
    {
        return rangeSeekBarPrice.getSelectedMinValue().toString();
    }

    public String getMaxPrice()
    {
        //return Integer.toString(seekBarPrice.getProgress());
        return rangeSeekBarPrice.getSelectedMaxValue().toString();
    }

    public String getMinSquareSize()
    {
        return rangeSeekBarSize.getSelectedMinValue().toString();
    }

    public String getMaxSquareSize()
    {
        //return Integer.toString(seekBarSize.getProgress());
        return rangeSeekBarSize.getSelectedMaxValue().toString();
    }

    public String getMinRoom()
    {
        return rangeSeekBarRoom.getSelectedMinValue().toString();
    }

    public String getMaxRoom()
    {
        //return Integer.toString(seekBarRoom.getProgress());
        return rangeSeekBarRoom.getSelectedMaxValue().toString();
    }

}
