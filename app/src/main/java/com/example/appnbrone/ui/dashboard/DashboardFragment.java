package com.example.appnbrone.ui.dashboard;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appnbrone.databinding.FragmentDashboardBinding;

import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private static final String TAG = "MyActivity";
    private static final int PERMISSION_CODE = 100;
    String currentLocation;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        Log.d(TAG, "FRAGMENTTI KÄYNNISTYY");
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                makeUseOfNewLocation(location);
                Log.d(TAG, "TESTATAAN!!!! 1");
            }

            @Override
            public void onStatusChanged(String provider, int Status, Bundle extras) {
                Log.d(TAG, "TESTATAAN!!!! 2");
            }

            public void OnProviderEnabled(String provider) {
                Log.d(TAG, "TESTATAAN!!!! 3");
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }
        };

        LocationManager locationManager =
                (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Sijaintitietolupaa ei ole: KYSYTÄÄN LUPA!");
            String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(getActivity(), permissions, PERMISSION_CODE);
            Log.d(TAG, "SOS!!!!  2");
            return root;

        }
        Toast.makeText(getContext(), "Permission already granted", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "SOS!!!!  3");
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 10, locationListener);


        Log.d(TAG, "TÄTÄ EI ILMEISESTI KOSKAAN TULOSTETA!?!?");


        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        double latitude = lastLocation.getLatitude();
        double longitude = lastLocation.getLongitude();
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    1);
            Address address = addresses.get(0);
            currentLocation = address.getAddressLine(0);

        } catch (Exception e) {
            Log.e(getTag(), e.getMessage());
        }

        TextView latitudeText = binding.textView5;
        TextView longitudeText = binding.textView6;
        Log.d(TAG, ".............. Jälleen yksi loki testaus  NYT GPS:STÄ SAADUT TIEDOT KÄYTÖSSÄ.......");
        TextView addressText = binding.textView7;
        addressText.setText(currentLocation);

        latitudeText.setText("Latitude: " + latitude);
        longitudeText.setText("Longitude: " + longitude);


        final Button mapButton = binding.button2;
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude);

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");

                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);
            }
        });


        return root;


    }

    private void makeUseOfNewLocation(Location location) {
        LocationManager locationManager =
                (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location newLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        double latitude =newLocation.getLatitude();
        double longitude = newLocation.getLongitude();
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    1);
            Address address= addresses.get(0);
            currentLocation = address.getAddressLine(0);

        } catch (Exception e) {
            Log.e(getTag(), e.getMessage());
        }

        TextView latitudeText = binding.textView5;
        TextView longitudeText = binding.textView6;
        TextView addressText = binding.textView7;
        addressText.setText(currentLocation);

        latitudeText.setText("Latitude: " + latitude );
        longitudeText.setText("Longitude: " + longitude );
    }

/*
   // @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onStart() {
        super.onStart();
        String latitude;
        String longitude;

        LocationManager locationManager =
                (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, (LocationListener) this);
        lastLocation.getLatitude();
        lastLocation.getLongitude();
        TextView latitudeText = binding.textView5;
        TextView longitudeText = binding.textView6;

        TextView addressText = binding.textView7;

        addressText.setText("NOTTA PERKELE");
        latitudeText.setText("Latitude: " + lastLocation.getLatitude());
        longitudeText.setText("Longitude: " + lastLocation.getLongitude());



        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                //makeUseOfNewLocation(Location);
                try {
                    Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                    List<Address> addresses = geocoder.getFromLocation(
                            location.getLatitude(),
                            location.getLongitude(),
                            1);

                } catch (Exception e) {
                    Log.e(getTag(), e.getMessage());
                }



            }


            public void onStatusChanged(String provider, int status, Bundle extras) {
            }


            public void onProviderEnabled(@NonNull String provider) {
            }

            public void onProviderDisabled(@NonNull String provider) {
            }
        };







    }

*/


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;}
}