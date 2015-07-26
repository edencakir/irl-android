package tr.edu.iyte.irl.irl.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tr.edu.iyte.irl.irl.Fragments.CategoriesFragment;
import tr.edu.iyte.irl.irl.Fragments.NewsFragment;

/**
 * Created by Enes on 7/26/15.
 */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] pageTitles = {"Duyurular", "Kategoriler"};

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
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
