package tr.edu.iyte.irl.irl;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import tr.edu.iyte.irl.irl.Adapters.BaseFragmentPagerAdapter;

// which contains viewpager, and base to categories and news
public class BaseActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PagerSlidingTabStrip tabStrip;
    private BaseFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        findViews();
        initialize();
    }

    private void findViews() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        tabStrip.setIndicatorColor(getResources().getColor(R.color.irl_blue));
    }

    private void initialize() {
        adapter = new BaseFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabStrip.setViewPager(viewPager);
        //set fonts for tabs here.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
