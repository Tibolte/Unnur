package is.brana.unnur.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import is.brana.unnur.R;
import is.brana.unnur.utils.Utils;

import java.util.ArrayList;

/**
 * Created by thibaultguegan on 16/05/2014.
 */
public class ListAdapterMenu extends ArrayAdapter<DrawerItem>
{
    private ArrayList<DrawerItem> items;
    private Context context;

    public ListAdapterMenu(Context context, int textViewResourceId, ArrayList<DrawerItem> items)
    {
        super(context, textViewResourceId, items);
        this.items = items;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;

        if(v == null)
        {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.menu_item, null);
        }

        bindItem(v, position);

        return v;
    }

    private void bindItem(View v, int position)
    {
        DrawerItem drawerItem = items.get(position);

        ViewHolder holder = new ViewHolder();
        holder.imgIcon = (ImageView)v.findViewById(R.id.img_icon);
        holder.txtMenu = (TextView)v.findViewById(R.id.txt_menu);
        //holder.imgArrow = (ImageView)v.findViewById(R.id.img_arrow);
        holder.rowContent = (RelativeLayout)v.findViewById(R.id.rowContent);

        holder.imgIcon.setImageDrawable(context.getResources().getDrawable(drawerItem.getImage()));
        holder.txtMenu.setText(context.getResources().getString(drawerItem.getMenu()));

        holder.txtMenu.setTypeface(Utils.getFont(context, Utils.ROBOTO_LIGHT));

        holder.rowContent.setTag(drawerItem.getPosition());
    }

    public static class ViewHolder
    {
        public ImageView imgIcon;
        public TextView txtMenu;
        public ImageView imgArrow;
        public RelativeLayout rowContent;
    }
}
