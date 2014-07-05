package is.brana.unnur.detail;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;
import is.brana.unnur.R;
import is.brana.unnur.interfaces.LocationDetailImageClick;
import is.brana.unnur.interfaces.LocationMapClick;
import is.brana.unnur.objects.Accomodation;
import is.brana.unnur.objects.AccomodationDetail;
import is.brana.unnur.objects.Image;
import is.brana.unnur.objects.gui.drawpath.HouseView;
import is.brana.unnur.utils.Urls;
import is.brana.unnur.utils.Utils;
import is.brana.unnur.utils.sidescroll.PageChangeListener;
import is.brana.unnur.utils.sidescroll.SwipeView;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by thibaultguegan on 21/05/2014.
 */
public class ListBaseDetailAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_CLOSE_BY = 1;
    private static final int TYPE_MAX_COUNT = TYPE_CLOSE_BY + 1;
    private static final float TEXT_SIZE = 18.0f;
    private static int TEXT_COLOR;

    private Context context;
    private DisplayMetrics metrics;
    private ArrayList<AccomodationDetail> accomodations = new ArrayList<AccomodationDetail>();

    private LocationDetailImageClick imageClick;
    private LocationMapClick mapClick;
    private LayoutInflater mInflater;

    private TreeSet<Integer> mCloseBySet = new TreeSet<Integer>();

    public ListBaseDetailAdapter(Context context,  DisplayMetrics metrics, LocationDetailImageClick imageClick, LocationMapClick mapClick)
    {
        this.context = context;
        this.metrics = metrics;
        this.imageClick = imageClick;
        this.mapClick = mapClick;

        TEXT_COLOR = context.getResources().getColor(R.color.label_grey);

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final AccomodationDetail accomodation) {
        accomodations.add(accomodation);
        //notifyDataSetChanged();
    }

    public void addCloseByItem(final AccomodationDetail accomodation) {
        accomodations.add(accomodation);

        mCloseBySet.add(accomodations.size() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        return mCloseBySet.contains(position) ? TYPE_CLOSE_BY: TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return accomodations.size();
    }

    @Override
    public AccomodationDetail getItem(int position) {
        return accomodations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        FirstCellHolder firstCellHolder = null;
        ViewHolder holder = null;

        int type = getItemViewType(position);

        final AccomodationDetail accomodation = getItem(position);

        if(v == null)
        {
            switch (type)
            {
                case TYPE_ITEM:
                    firstCellHolder = new FirstCellHolder();

                    v = mInflater.inflate(R.layout.location_detail_first_cell, null);

                    firstCellHolder.swipeView = (SwipeView) v.findViewById(R.id.scroll1);
                    firstCellHolder.spots = (LinearLayout) v.findViewById(R.id.flipspots1);
                    firstCellHolder.nBPhotoContainer = (RelativeLayout) v.findViewById(R.id.nBPhotoContainer);
                    firstCellHolder.txtNbPhoto = (TextView) v.findViewById(R.id.txtnBPhoto);
                    firstCellHolder.txtName = (TextView) v.findViewById(R.id.txtName);
                    firstCellHolder.txtAddress = (TextView) v.findViewById(R.id.txtAddress);
                    firstCellHolder.txtDescription = (TextView) v.findViewById(R.id.txtDescription);
                    firstCellHolder.txtCategories = (TextView) v.findViewById(R.id.txtCategories);
                    firstCellHolder.txtUser = (TextView) v.findViewById(R.id.txtUser);
                    firstCellHolder.txtPhone = (TextView) v.findViewById(R.id.txtPhone);
                    firstCellHolder.txtMail = (TextView) v.findViewById(R.id.txtMail);
                    firstCellHolder.greenBanner = (RelativeLayout) v.findViewById(R.id.greenBanner);
                    firstCellHolder.txtDate = (TextView) v.findViewById(R.id.txtDate);
                    firstCellHolder.txtDateDescription = (TextView) v.findViewById(R.id.txtDateDescription);
                    firstCellHolder.txtLongTerm = (TextView) v.findViewById(R.id.txtLongTerm);
                    firstCellHolder.txtLongTermDescription = (TextView) v.findViewById(R.id.txtLongTermDescription);
                    firstCellHolder.txtCategory = (TextView) v.findViewById(R.id.txtCategory);
                    firstCellHolder.txtCategoryDescription= (TextView) v.findViewById(R.id.txtCategoryDescription);
                    firstCellHolder.txtRoomSize = (TextView) v.findViewById(R.id.txtRoomSize);
                    firstCellHolder.txtroomSizeDescription = (TextView) v.findViewById(R.id.txtRoomSizeDescription);
                    firstCellHolder.txtSize = (TextView) v.findViewById(R.id.txtSize);
                    firstCellHolder.txtSizeDescription = (TextView) v.findViewById(R.id.txtSizeDescription);
                    firstCellHolder.txtHeat = (TextView) v.findViewById(R.id.txtHeat);
                    firstCellHolder.txtHeatDescription = (TextView) v.findViewById(R.id.txtHeatDescription);
                    firstCellHolder.txtElectricity = (TextView) v.findViewById(R.id.txtElectricity);
                    firstCellHolder.txtElectricityDescription = (TextView) v.findViewById(R.id.txtElectricityDescription);
                    firstCellHolder.txtHouseFund = (TextView) v.findViewById(R.id.txtHousefund);
                    firstCellHolder.txtHouseFundDescription = (TextView) v.findViewById(R.id.txtHousefundDescription);
                    firstCellHolder.txtPrepaid = (TextView) v.findViewById(R.id.txtPrePaid);
                    firstCellHolder.txtPrepaidDescription = (TextView) v.findViewById(R.id.txtPrePaidDescription);
                    firstCellHolder.txtGuarantee = (TextView) v.findViewById(R.id.txtGuarantee);
                    firstCellHolder.txtGuaranteeDescription = (TextView) v.findViewById(R.id.txtGuaranteeDescription);
                    firstCellHolder.txtInsurance = (TextView) v.findViewById(R.id.txtInsurance);
                    firstCellHolder.txtInsuranceDescription = (TextView) v.findViewById(R.id.txtInsuranceDescription);
                    firstCellHolder.txtValue = (TextView) v.findViewById(R.id.txtValue);
                    firstCellHolder.txtValueDescription = (TextView) v.findViewById(R.id.txtValueDescription);




                    firstCellHolder.txtNbPhoto.setTypeface(Utils.getFont(context, Utils.ROBOTO_LIGHT));
                    firstCellHolder.txtName.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtAddress.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtDescription.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtCategories.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtUser.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtMail.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtPhone.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtDate.setTypeface(Utils.getFont(context, Utils.ROBOTO_BOLD));
                    firstCellHolder.txtDateDescription.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtLongTerm.setTypeface(Utils.getFont(context, Utils.ROBOTO_BOLD));
                    firstCellHolder.txtLongTermDescription.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtCategory.setTypeface(Utils.getFont(context, Utils.ROBOTO_BOLD));
                    firstCellHolder.txtCategoryDescription.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));


                    firstCellHolder.txtRoomSize.setTypeface(Utils.getFont(context, Utils.ROBOTO_BOLD));
                    firstCellHolder.txtroomSizeDescription.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtSize.setTypeface(Utils.getFont(context, Utils.ROBOTO_BOLD));
                    firstCellHolder.txtSizeDescription.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtHeat.setTypeface(Utils.getFont(context, Utils.ROBOTO_BOLD));
                    firstCellHolder.txtHeatDescription.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtElectricity.setTypeface(Utils.getFont(context, Utils.ROBOTO_BOLD));
                    firstCellHolder.txtElectricityDescription.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtHouseFund.setTypeface(Utils.getFont(context, Utils.ROBOTO_BOLD));
                    firstCellHolder.txtHouseFundDescription.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtPrepaid.setTypeface(Utils.getFont(context, Utils.ROBOTO_BOLD));
                    firstCellHolder.txtPrepaidDescription.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtGuarantee.setTypeface(Utils.getFont(context, Utils.ROBOTO_BOLD));
                    firstCellHolder.txtGuaranteeDescription.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtInsurance.setTypeface(Utils.getFont(context, Utils.ROBOTO_BOLD));
                    firstCellHolder.txtInsuranceDescription.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
                    firstCellHolder.txtValue.setTypeface(Utils.getFont(context, Utils.ROBOTO_BOLD));
                    firstCellHolder.txtValueDescription.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));

                    firstCellHolder.txtRoomSize.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtroomSizeDescription.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtSize.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtSizeDescription.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtHeat.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtHeatDescription.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtElectricity.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtElectricityDescription.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtHouseFund.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtHouseFundDescription.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtPrepaid.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtPrepaidDescription.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtGuarantee.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtGuaranteeDescription.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtInsurance.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtInsuranceDescription.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtValue.setTextColor(TEXT_COLOR);
                    firstCellHolder.txtValueDescription.setTextColor(TEXT_COLOR);

                    firstCellHolder.txtName.setText(accomodation.getRegion());
                    firstCellHolder.txtAddress.setText(accomodation.getZipName());
                    firstCellHolder.txtDescription.setText(accomodation.getDescription());
                    firstCellHolder.txtCategories.setText(accomodation.getCategory());
                    firstCellHolder.txtUser.setText(accomodation.getUser());
                    firstCellHolder.txtMail.setText(accomodation.getMail());
                    firstCellHolder.txtPhone.setText(accomodation.getPhone());
                    firstCellHolder.txtDate.setText(accomodation.getStartDate());
                    firstCellHolder.txtCategory.setText(accomodation.getCategory());
                    firstCellHolder.txtLongTerm.setText(accomodation.getLongTerm());
                    firstCellHolder.txtroomSizeDescription.setText(accomodation.getRoomCountString());
                    firstCellHolder.txtSizeDescription.setText(accomodation.getSquareSizeString());
                    firstCellHolder.txtHeatDescription.setText(accomodation.getHeat());
                    firstCellHolder.txtElectricityDescription.setText(accomodation.getElectricity());
                    firstCellHolder.txtHouseFundDescription.setText(accomodation.getHouseFundString());
                    firstCellHolder.txtPrepaidDescription.setText(accomodation.getPrePaidString());
                    firstCellHolder.txtGuaranteeDescription.setText(accomodation.getGuaranteeString());
                    firstCellHolder.txtInsuranceDescription.setText(accomodation.getInsurance());
                    firstCellHolder.txtValueDescription.setText(accomodation.getValueString());

                    firstCellHolder.greenBanner.setBackgroundColor(context.getResources().getColor(R.color.green_main));

                    String url = String.format(Urls.MAP_URL, accomodation.getLat(), accomodation.getLon(), accomodation.getLat(), accomodation.getLon());

                    firstCellHolder.imgStaticMap = (ImageView) v.findViewById(R.id.staticmap);
                    Picasso.with(context).load(url).into(firstCellHolder.imgStaticMap);

                    firstCellHolder.imgStaticMap.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            mapClick.onClickMap(accomodation);
                        }
                    });

                    ViewGroup.LayoutParams lp;
                    lp = firstCellHolder.swipeView.getLayoutParams();
                    lp.width = (int) (metrics.widthPixels);
                    lp.height = (int) (lp.width * 0.7);
                    firstCellHolder.swipeView.setLayoutParams(lp);
                    firstCellHolder.swipeView.setSwipeThreshold(10);

                    ArrayList<Image> images = accomodation.getImages();

                    if(images.size() > 0)
                    {
                        int padding = context.getResources().getDimensionPixelSize(R.dimen.spot_padding);
                        int spotsize = context.getResources().getDimensionPixelSize(R.dimen.spot_size);
                        if(firstCellHolder.swipeView.getPageCount() == 0)
                        {
                            int i = 0;
                            ImageView[] spotList = new ImageView[images.size()];

                            firstCellHolder.txtNbPhoto.setText(String.valueOf(images.size()));

                            for(Image image: images)
                            {
                                KenBurnsView img = new KenBurnsView(context);
                                //img.setScaleType(ImageView.ScaleType.FIT_XY);
                                img.setLayoutParams(firstCellHolder.swipeView.getLayoutParams());

                                firstCellHolder.swipeView.addView(img);
                                Picasso.with(context).load(accomodation.getImages().get(i).getImageUrl(accomodation.getId())).placeholder(R.drawable.placeholder_house).into(img);

                                ImageView spot = new ImageView(context);
                                spot.setPadding(padding, padding, padding, padding);
                                if (i == 0)
                                    spot.setImageResource(R.drawable.plot_filled);
                                else
                                    spot.setImageResource(R.drawable.plot_empty);

                                spot.setLayoutParams(new LinearLayout.LayoutParams(spotsize, spotsize));

                                firstCellHolder.spots.addView(spot);

                                spotList[i] = spot;

                                i++;
                            }
                            firstCellHolder.swipeView.setOnPageChangedListener(new PageChangeListener(spotList));
                        }

                        v.setTag(firstCellHolder);
                    }
                    else
                    {
                        firstCellHolder.nBPhotoContainer.setVisibility(View.GONE);

                        //Set placeholder
                        ImageView img = new ImageView(context);
                        img.setImageDrawable(context.getResources().getDrawable(R.drawable.placeholder_house_centered));
                        img.setScaleType(ImageView.ScaleType.FIT_XY);
                        img.setLayoutParams(firstCellHolder.swipeView.getLayoutParams());

                        firstCellHolder.swipeView.addView(img);
                    }

                    //set circle position
                    firstCellHolder.circleContainer = (RelativeLayout) v.findViewById(R.id.circleContainer);
                    firstCellHolder.circleImage = (ImageView) v.findViewById(R.id.circleImage);

                    int y = firstCellHolder.swipeView.getLayoutParams().height;
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) firstCellHolder.circleContainer.getLayoutParams();
                    params.topMargin = y - context.getResources().getDimensionPixelSize(R.dimen.circle_diameter)/2;
                    firstCellHolder.circleContainer.setLayoutParams(params);

                    //set house animation
                    View animatedView = mInflater.inflate(R.layout.item_house, firstCellHolder.circleContainer, false);
                    HouseView houseView = (HouseView) animatedView.findViewById(R.id.house);
                    houseView.setSvgResource(R.raw.house);
                    firstCellHolder.circleContainer.addView(animatedView);
                    houseView.startAnimation();

                    break;
                case TYPE_CLOSE_BY:
                    break;
            }
        }
        else
        {
            if(v.getTag().getClass() == ViewHolder.class)
                holder = (ViewHolder) v.getTag();
            else
                firstCellHolder = (FirstCellHolder) v.getTag();
        }
        if(holder != null)
        {
            //do any changes if neeeded
        }

        return v;
    }

    public static class FirstCellHolder
    {
        public SwipeView swipeView;
        public LinearLayout spots;
        public RelativeLayout nBPhotoContainer;
        public TextView txtNbPhoto;
        public RelativeLayout circleContainer;
        public ImageView circleImage;
        public TextView txtName;
        public TextView txtAddress;
        public TextView txtDescription;
        public TextView txtCategories;
        public ImageView imgStaticMap;

        public RelativeLayout greenBanner;
        public TextView txtDate;
        public TextView txtDateDescription;
        public TextView txtLongTerm;
        public TextView txtLongTermDescription;
        public TextView txtCategory;
        public TextView txtCategoryDescription;

        public TextView txtRoomSize;
        public TextView txtroomSizeDescription;
        public TextView txtSize;
        public TextView txtSizeDescription;
        public TextView txtHeat;
        public TextView txtHeatDescription;
        public TextView txtElectricity;
        public TextView txtElectricityDescription;
        public TextView txtHouseFund;
        public TextView txtHouseFundDescription;
        public TextView txtPrepaid;
        public TextView txtPrepaidDescription;
        public TextView txtGuarantee;
        public TextView txtGuaranteeDescription;
        public TextView txtInsurance;
        public TextView txtInsuranceDescription;
        public TextView txtValue;
        public TextView txtValueDescription;

        public TextView txtUser;
        public TextView txtPhone;
        public TextView txtMail;
    }

    public static class ViewHolder
    {
        public ImageView image;
        public TextView txtName;
        public TextView txtDistanceValue;
    }
}
