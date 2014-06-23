package is.brana.unnur.search.results;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import is.brana.unnur.R;
import is.brana.unnur.objects.Accomodation;
import is.brana.unnur.utils.Utils;

import java.util.ArrayList;

/**
 * Created by thibaultguegan on 23/06/2014.
 */
public class SearchResultAdapter extends ArrayAdapter<Accomodation> {

    private ArrayList<Accomodation> items;
    private Context context;

    public SearchResultAdapter(Context context, int textViewResourceId, ArrayList<Accomodation> items)
    {
        super(context, textViewResourceId, items);
        this.items = items;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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

    private View initView() {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.accomodation_search_item, null);


        ViewHolder holder = new ViewHolder();

        holder.image = (ImageView) v.findViewById(R.id.img_location);
        holder.txtPrice = (TextView) v.findViewById(R.id.txtPrice);
        holder.txtStreet = (TextView) v.findViewById(R.id.txtStreet);
        holder.txtRoomSize = (TextView) v.findViewById(R.id.txtRoomSize);
        holder.txtSquareSize = (TextView) v.findViewById(R.id.txtSquareSize);
        holder.txtLongTerm = (TextView) v.findViewById(R.id.txtLongTerm);
        holder.txtDescription = (TextView) v.findViewById(R.id.txtDescription);

        holder.txtStreet.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
        holder.txtLongTerm.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
        holder.txtRoomSize.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
        holder.txtSquareSize.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
        holder.txtPrice.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));
        holder.txtDescription.setTypeface(Utils.getFont(context, Utils.ROBOTO_REGULAR));

        v.setTag(holder);

        return v;
    }

    private void bindItem(View v, int position)
    {
        final Accomodation accomodation = items.get(position);

        ViewHolder holder = (ViewHolder) v.getTag();

        holder.txtRoomSize.setText(String.valueOf(accomodation.getRoomSize()) + " herb");
        holder.txtSquareSize.setText(String.valueOf(accomodation.getSquareSize())+ " fm");
        holder.txtPrice.setText(String.valueOf(accomodation.getValue()).substring(0, 3)+ " þús");
        holder.txtStreet.setText(accomodation.getStreet());
        holder.txtLongTerm.setText(accomodation.getLongTerm());

        //TODO: description (ask to Viktor)

        if(!accomodation.getImages().isEmpty())
        {
            Picasso.with(context).load(accomodation.getImages().get(0).getImageUrl(accomodation.getId())).placeholder(R.drawable.placeholder_house).into(holder.image);
        }
        else
        {
            holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.placeholder_house));
        }
    }

    public static class ViewHolder
    {
        public ImageView image;
        public TextView txtPrice;
        public TextView txtStreet;
        public TextView txtRoomSize;
        public TextView txtSquareSize;
        public TextView txtLongTerm;
        public TextView txtDescription;
    }
}
