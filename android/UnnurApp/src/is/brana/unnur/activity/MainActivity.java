package is.brana.unnur.activity;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import is.brana.unnur.R;
import is.brana.unnur.accomodation.AccomodationFragment;
import is.brana.unnur.menu.DrawerItem;
import is.brana.unnur.menu.ListAdapterMenu;
import is.brana.unnur.objects.gui.HeaderHolder;
import is.brana.unnur.search.SearchFragment;
import is.brana.unnur.utils.Utils;

import java.util.ArrayList;

/**
 * Created by thibaultguegan on 16/05/2014.
 */
public class MainActivity extends FragmentActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private ListView mDrawerList;
    private LinearLayout listContainer;
    private ArrayList<DrawerItem> items = new ArrayList<DrawerItem>();

    private HeaderHolder headerHolder;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initMenu();

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        listContainer = (LinearLayout) findViewById(R.id.list_container);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ListAdapterMenu(this, R.layout.menu_item, items));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        headerHolder = new HeaderHolder(this, getResources().getString(R.string.accomodations));

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    public void onClickMenu(View v)
    {
        if(mDrawerLayout.isDrawerOpen(listContainer))
        {
            mDrawerLayout.closeDrawer(listContainer);
        }
        else
        {
            mDrawerLayout.openDrawer(listContainer);
        }
    }

    private void initMenu()
    {
        items.clear();

        items.add(new DrawerItem(R.string.accomodations, R.drawable.menu_icon_accomodations, Long.valueOf(Utils.CAT_ACCOMODATIONS)));
        items.add(new DrawerItem(R.string.favorites, R.drawable.menu_icon_favorites, Long.valueOf(Utils.CAT_FAVORITES)));
        items.add(new DrawerItem(R.string.search, R.drawable.menu_icon_search, Long.valueOf(Utils.CAT_SEARCH)));
        items.add(new DrawerItem(R.string.watches, R.drawable.menu_icon_wheel, Long.valueOf(Utils.CAT_WATCH)));

    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position)
        {
            case Utils.CAT_ACCOMODATIONS:
                AccomodationFragment accomodationFragment = new AccomodationFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, accomodationFragment).commit();
                headerHolder.setTitle(getResources().getString(R.string.accomodations));
                break;
            case Utils.CAT_SEARCH:
                SearchFragment searchFragment = new SearchFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, searchFragment).commit();
                headerHolder.setTitle(getResources().getString(R.string.search));
                break;
        }

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(listContainer);
    }
}