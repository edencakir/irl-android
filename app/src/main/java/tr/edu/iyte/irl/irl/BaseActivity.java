package tr.edu.iyte.irl.irl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import tr.edu.iyte.irl.irl.Adapters.BaseFragmentPagerAdapter;

// which contains viewpager, and base to categories and news
public class BaseActivity extends AppCompatActivity {
    public final static String VERSION_NUMBER = "1.0.5";
    static final String TAG = "JWP";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    GoogleCloudMessaging gcm;
    Context context;
    String regid;
    String SENDER_ID = "300954823314";
    private ViewPager viewPager;
    private PagerSlidingTabStrip tabStrip;
    private BaseFragmentPagerAdapter adapter;
    private Toolbar toolbar;
    private ImageView buttonQR, buttonContact;
    private String qrURL;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        context = this;
        findViews();
        if (checkPlayServices()) {
            new Register().execute();
        } else {
            Toast.makeText(getApplicationContext(), "The device is not supported!", Toast.LENGTH_LONG).show();
        }
        initialize();
        setListeners();
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                Toast.makeText(getApplicationContext(), "The device is not supported!", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        buttonQR = (ImageView) findViewById(R.id.buttonQR);
        buttonContact = (ImageView) findViewById(R.id.buttonContact);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        tabStrip.setIndicatorColor(getResources().getColor(R.color.irl_blue));
    }

    private void initialize() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("");
        alertDialogBuilder.setCancelable(true);
        // set dialog message
        alertDialogBuilder
                .setMessage("QR veya Numara")
                .setCancelable(false)
                .setPositiveButton("QR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        scanQR();
                    }
                })
                .setNegativeButton("Numara", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        startActivity(new Intent(getBaseContext(), QrNumber.class));
                    }
                });

        // create alert dialog
        alertDialog = alertDialogBuilder.create();

        // show it
        adapter = new BaseFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabStrip.setViewPager(viewPager);
        //set fonts for tabs here.
    }

    private void setListeners() {
        buttonQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });
        buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), ContactActivity.class));
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

    class Register extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {
            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(context);
                    regid = gcm.register(SENDER_ID);
                    //Log.d(TAG, regid);
                    //Log.e(TAG, prefs.getString("REG_ID", ""));
                }

                return regid;

            } catch (IOException ex) {
                Log.e("Error", ex.getMessage());
                return "Fails";

            }
        }

    }
}
