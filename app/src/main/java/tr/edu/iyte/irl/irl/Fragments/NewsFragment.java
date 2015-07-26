package tr.edu.iyte.irl.irl.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tr.edu.iyte.irl.irl.R;

/**
 * Created by Enes on 7/26/15.
 */
public class NewsFragment extends Fragment {
    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_news, container, false);
        findViews();
        return v;
    }

    private void findViews() {

    }
}
