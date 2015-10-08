package tr.edu.iyte.irl.irl;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import tr.edu.iyte.irl.irl.Adapters.BaseFragmentPagerAdapter;

// which contains viewpager, and base to categories and news
public class BaseActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PagerSlidingTabStrip tabStrip;
    private BaseFragmentPagerAdapter adapter;
    private Toolbar toolbar;
    private ImageView buttonQR;
    private String qrURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        findViews();
        initialize();
        setListeners();
    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        buttonQR = (ImageView) findViewById(R.id.buttonQR);
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

    private void setListeners() {
        buttonQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQR();
            }
        });
    }

    private void scanQR() {
        try {
            Toast.makeText(getApplicationContext(), "QR reader initiated.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Please download Google's official QR reader", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.google.zxing.client.android")));
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {

                qrURL = intent.getStringExtra("SCAN_RESULT");

                Intent qrIntent = new Intent(this, ScanResultActivity.class);
                qrIntent.putExtra("qrurl", qrURL);
                startActivity(qrIntent);

            } else if (resultCode == Activity.RESULT_CANCELED) {
                // Handle cancel
                Toast.makeText(getApplicationContext(), "Scanning interrupted.", Toast.LENGTH_SHORT).show();
            }
        }

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
