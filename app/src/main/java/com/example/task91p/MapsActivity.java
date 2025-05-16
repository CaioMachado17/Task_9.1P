package com.example.task91p;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task91p.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private DatabaseHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Map");

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        dbHelper = new DatabaseHelper(this);

        // Set up the map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        List<Advert> adverts = dbHelper.getAllAdverts();

        boolean firstMarker = true;

        for (Advert ad : adverts) {
            if (ad.latitude != 0.0 && ad.longitude != 0.0) {
                LatLng latLng = new LatLng(ad.latitude, ad.longitude);

                float color = ad.type.equalsIgnoreCase("Lost")
                    ? BitmapDescriptorFactory.HUE_RED
                    : BitmapDescriptorFactory.HUE_GREEN;

                mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(ad.type + ": " + ad.description)
                    .icon(BitmapDescriptorFactory.defaultMarker(color)));


                if (firstMarker) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
                    firstMarker = false;
                }
            } else {
                Toast.makeText(this, "No coordinates for: " + ad.location, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
