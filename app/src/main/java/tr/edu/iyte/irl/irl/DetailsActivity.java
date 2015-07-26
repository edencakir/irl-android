package tr.edu.iyte.irl.irl;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import tr.edu.iyte.irl.irl.Utility.Constants;


public class DetailsActivity extends AppCompatActivity {
    //buralari hardcoded yapmak beni her ne kadar uzse de
    //network requestler databaseler icinde bogulmayip (zaman darligi)
    //islevsel bir uygulama cikarmak amaciyla
    //bu sekilde kotu bir kod yazdim.
    private int selection;

    // selection durumuna gore string-arrayden texti cekiyor gerekli yerlere koyuyorum.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        String type = intent.getStringExtra(Constants.CATEGORY_KEY);
        switch (type) {
            case "cizgi":
                selection = 0;
                break;
            case "sumo":
                selection = 1;
                break;
            case "mini":
                selection = 2;
                break;
            case "coklumini":
                selection = 3;
                break;
            case "yangin":
                selection = 4;
                break;
            case "arazi":
                selection = 5;
                break;
            case "cop":
                selection = 6;
                break;
            case "serbest":
                selection = 7;
                break;
            case "tasarla":
                selection = 8;
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
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
