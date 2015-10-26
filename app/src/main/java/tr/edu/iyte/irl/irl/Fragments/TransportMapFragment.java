package tr.edu.iyte.irl.irl.Fragments;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Enes on 8/4/15.
 */
public class TransportMapFragment extends com.google.android.gms.maps.SupportMapFragment {
    private final LatLng IYTE = new LatLng(38.318331, 26.640255);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        GoogleMap googleMap = getMap();
        googleMap.addMarker(new MarkerOptions().position(IYTE).title("IYTE"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(IYTE, 8));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2500, null);

    }

}
