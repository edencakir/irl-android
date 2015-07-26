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
public class CategoriesFragment extends Fragment implements View.OnClickListener {
    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_categories, container, false);
        findViews();
        setListeners();
        return v;
    }

    private void findViews() {

    }

    private void setListeners() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cizgi:
        }
    }
}
