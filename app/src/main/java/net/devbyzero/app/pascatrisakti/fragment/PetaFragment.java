package net.devbyzero.app.pascatrisakti.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devbyzero.app.pascatrisakti.PascaDB;
import net.devbyzero.app.pascatrisakti.R;

/**
 * Created by anta40 on 17-May-17.
 */

public class PetaFragment extends Fragment  {

    //SupportMapFragment mapFragment;
    TextView tv;

    public static PetaFragment newInstance(String param1, String param2){
        PetaFragment fragment = new PetaFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_generic, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Peta");

       // mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.trisakti_map);
       // mapFragment.getMapAsync(this);

        tv = (TextView) view.findViewById(R.id.teks_generik);

        tv.setText("This section is under construction");
        tv.setMovementMethod(new ScrollingMovementMethod());


        return view;
    }

    /*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.4233438, -122.0728817))
                .title("LinkedIn")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.4629101,-122.2449094))
                .title("Facebook")
                .snippet("Facebook HQ: Menlo Park"));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.3092293, -122.1136845))
                .title("Apple"));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.4233438, -122.0728817), 10));
    }*/
}
