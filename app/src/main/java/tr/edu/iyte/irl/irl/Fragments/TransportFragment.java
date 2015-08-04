package tr.edu.iyte.irl.irl.Fragments;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

import tr.edu.iyte.irl.irl.R;

/**
 * Created by Enes on 8/4/15.
 */
public class TransportFragment extends Fragment {
    private View view;
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_transport, container, false);
        initializeMap();
        return view;
    }

    private void initializeMap() {
        TransportMapFragment mapFragment = new TransportMapFragment();
        android.support.v4.app.FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.map, mapFragment).commit();
    }

    @Override
    public void onDestroyView() {
        Fragment f = getFragmentManager().findFragmentById(R.id.map);
        if (f != null) {
            getChildFragmentManager().beginTransaction().remove(f).commit();
        }

        super.onDestroyView();
    }
}
