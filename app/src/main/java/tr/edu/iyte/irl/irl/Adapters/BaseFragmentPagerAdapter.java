package tr.edu.iyte.irl.irl.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tr.edu.iyte.irl.irl.Fragments.CategoriesFragment;
import tr.edu.iyte.irl.irl.Fragments.NewsFragment;
import tr.edu.iyte.irl.irl.Fragments.TransportFragment;

/**
 * Created by Enes on 7/26/15.
 */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] pageTitles = {"Duyurular", "Kategorİler ", "Ulaşım"};

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NewsFragment();
            case 1:
                return new CategoriesFragment();
            case 2:
                return new TransportFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
