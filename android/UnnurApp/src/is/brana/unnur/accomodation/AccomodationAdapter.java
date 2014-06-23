package is.brana.unnur.accomodation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import is.brana.unnur.R;
import is.brana.unnur.interfaces.LocationDetailImageClick;
import is.brana.unnur.objects.Accomodation;
import is.brana.unnur.objects.Image;
import is.brana.unnur.utils.Utils;

import java.util.ArrayList;

/**
 * Created by thibaultguegan on 16/05/2014.
 */
public class AccomodationAdapter extends ArrayAdapter<Accomodation> {

    private ArrayList<Accomodation> items;
    private Context context;
    private LocationDetailImageClick imageClick;

    private static final float TEXT_SIZE = 13.0f;

    public AccomodationAdapter(Context context, int textViewResourceId, ArrayList<Accomodation> items, LocationDetailImageClick imageClick)
    {
        super(context, textViewResourceId, items);
        this.items = items;
        this.context = context;
        this.imageClick = imageClick;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;

        if(v == null)
        {
            v = initView();
        }

        try
        {
            bindItem(v, position);
        }
        catch(IndexOutOfBoundsException ex)
        {

        }

        return v;
    }

    private View initView()
    {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.accomodation_item, null);

        ViewHolder holder = new ViewHolder();
        holder.image = (ImageView)v.findViewById(R.id.img_location);
        holder.txtRegion = (TextView)v.findViewById(R.id.txtRegion);
        holder.txtAddress = (TextView)v.findViewById(R.id.txtAddress);
        holder.txtRoomSize = (TextView)v.findViewById(R.id.txtRoomSize);
        holder.txtSquareSize = (TextView)v.findViewById(R.id.txtSquareSize);
        holder.txtPrice = (TextView)v.findViewById(R.id.txtPrice);


        holder.txtRegion.setTextSize(TEXT_SIZE);
        holder.txtAddress.setTextSize(TEXT_SIZE);
        holder.txtRoomSize.setTextSize(TEXT_SIZE);
        holder.txtSquareSize.setTextSize(TEXT_SIZE);
        holder.txtPrice.setTextSize(TEXT_SIZE);

        holder.txtRegion.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
        holder.txtAddress.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
        holder.txtRoomSize.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
        holder.txtSquareSize.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
        holder.txtPrice.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));

        v.setTag(holder);

        return v;
    }

    private void bindItem(View v, int position)
    {
        final Accomodation accomodation = items.get(position);

        ViewHolder holder = (ViewHolder) v.getTag();

        holder.txtRegion.setText(accomodation.getRegion() +" ");
        holder.txtAddress.setText(accomodation.getAddress());
        holder.txtRoomSize.setText(String.valueOf(accomodation.getRoomSize()) + " herb - ");
        holder.txtSquareSize.setText(String.valueOf(accomodation.getSquareSize())+ " fm");
        holder.txtPrice.setText(String.valueOf(accomodation.getValue()).substring(0, 3)+ " þús");

        if(!accomodation.getImages().isEmpty())
        {
            Picasso.with(context).load(accomodation.getImages().get(0).getImageUrl(accomodation.getId())).placeholder(R.drawable.placeholder_house).into(holder.image);
        }
        else
        {
            holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.placeholder_house));
        }

        holder.image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                imageClick.onClick(accomodation);
            }
        });

    }

    public static class ViewHolder
    {
        public ImageView image;
        public TextView txtRegion;
        public TextView txtAddress;
        public TextView txtRoomSize;
        public TextView txtSquareSize;
        public TextView txtPrice;
    }
}
