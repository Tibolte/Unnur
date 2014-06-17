package is.brana.unnur.detail.map;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import is.brana.unnur.R;
import is.brana.unnur.objects.gui.HeaderHolder;
import is.brana.unnur.utils.Keys;

/**
 * Created by thibaultguegan on 26/05/2014.
 */
public class MapActivity extends FragmentActivity {

    private static final int MAP_ZOOM = 15;

    // Google Map
    private GoogleMap googleMap;

    private float latitude;
    private float longitude;
    private String name;

    private HeaderHolder headerHolder;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);

        if(getIntent().getExtras() != null) {
            latitude = getIntent().getFloatExtra(Keys.KEY_LATITUDE, 0.0f);
            longitude = getIntent().getFloatExtra(Keys.KEY_LONGITUDE, 0.0f);
            name = getIntent().getStringExtra(Keys.KEY_NAME);

            headerHolder = new HeaderHolder(this, name);
            headerHolder.setBtnLeft(getResources().getDrawable(R.drawable.btn_cross));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {

            FragmentManager fmanager = getSupportFragmentManager();
            Fragment fragment = fmanager.findFragmentById(R.id.map);
            SupportMapFragment supportmapfragment = (SupportMapFragment)fragment;

            googleMap = supportmapfragment.getMap();

            // create marker
            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(name);

            try
            {
                // Changing marker icon
                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_icon));
            }
            catch(NullPointerException ex)
            {

            }

            CameraPosition cameraPosition = new CameraPosition.Builder().target(
                    new LatLng(latitude, longitude)).zoom(MAP_ZOOM).build();

            googleMap.setMyLocationEnabled(true);

            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


            // adding marker
            googleMap.addMarker(marker);

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Oups! Nous n'avons pas pu créer la carte!", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    public void onClickMenu(View v)
    {
        closeMapActivity();
    }

    @Override
    public void onBackPressed()
    {
        closeMapActivity();
    }

    private void closeMapActivity()
    {
        finish();

        overridePendingTransition(R.anim.standstill, R.anim.push_down_in);
    }
}