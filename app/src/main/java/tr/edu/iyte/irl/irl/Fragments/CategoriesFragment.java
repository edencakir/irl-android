package tr.edu.iyte.irl.irl.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import tr.edu.iyte.irl.irl.DetailsActivity;
import tr.edu.iyte.irl.irl.R;
import tr.edu.iyte.irl.irl.RulesActivity;
import tr.edu.iyte.irl.irl.Utility.Constants;

/**
 * Created by Enes on 7/26/15.
 */
public class CategoriesFragment extends Fragment implements View.OnClickListener {
    private View v;
    private Intent detailActivity;
    private ImageView cizgi, sumo, mini, coklumini, yangin, arazi, cop, serbest, tasarla;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_categories, container, false);
        findViews();
        initialize(); //to add bundle before setting listener, i moved it above listeners.
        setListeners();
        return v;
    }

    private void findViews() {
        cizgi = (ImageView) v.findViewById(R.id.cizgi);
        sumo = (ImageView) v.findViewById(R.id.sumo);
        mini = (ImageView) v.findViewById(R.id.mini);
        coklumini = (ImageView) v.findViewById(R.id.coklumini);
        yangin = (ImageView) v.findViewById(R.id.yangin);
        arazi = (ImageView) v.findViewById(R.id.arazi);
        cop = (ImageView) v.findViewById(R.id.cop);
        serbest = (ImageView) v.findViewById(R.id.serbest);
        tasarla = (ImageView) v.findViewById(R.id.tasarla);
    }

    private void initialize() {
        detailActivity = new Intent(getActivity(), DetailsActivity.class);
    }

    private void setListeners() {
        cizgi.setOnClickListener(this);
        sumo.setOnClickListener(this);
        mini.setOnClickListener(this);
        coklumini.setOnClickListener(this);
        yangin.setOnClickListener(this);
        arazi.setOnClickListener(this);
        cop.setOnClickListener(this);
        serbest.setOnClickListener(this);
        tasarla.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cizgi:
                detailActivity.putExtra(Constants.CATEGORY_KEY, "cizgi");
                break;
            case R.id.sumo:
                detailActivity.putExtra(Constants.CATEGORY_KEY, "sumo");
                break;
            case R.id.mini:
                detailActivity.putExtra(Constants.CATEGORY_KEY, "mini");
                break;
            case R.id.coklumini:
                detailActivity.putExtra(Constants.CATEGORY_KEY, "coklumini");
                break;
            case R.id.yangin:
                detailActivity.putExtra(Constants.CATEGORY_KEY, "yangin");
                break;
            case R.id.arazi:
                detailActivity.putExtra(Constants.CATEGORY_KEY, "arazi");
                break;
            case R.id.cop:
                detailActivity.putExtra(Constants.CATEGORY_KEY, "cop");
                break;
            case R.id.serbest:
                detailActivity.putExtra(Constants.CATEGORY_KEY, "serbest");
                break;
            case R.id.tasarla:
                detailActivity.putExtra(Constants.CATEGORY_KEY, "tasarla");
                break;
        }

        startActivity(detailActivity);

    }
}
