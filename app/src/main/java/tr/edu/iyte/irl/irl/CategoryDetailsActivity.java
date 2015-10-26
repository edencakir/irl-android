package tr.edu.iyte.irl.irl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import tr.edu.iyte.irl.irl.Utility.Constants;


public class CategoryDetailsActivity extends AppCompatActivity {

    //buralari hardcoded yapmak beni her ne kadar uzse de
    //network requestler databaseler icinde bogulmayip (zaman darligi)
    //islevsel bir uygulama cikarmak amaciyla
    //bu sekilde kotu bir kod yazdim.
    private int selection;

    // selection durumuna gore string-arrayden texti cekiyor gerekli
    // yerlere koyuyorum.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        String type = intent.getStringExtra(Constants.CATEGORY_KEY);
        WebView browser = (WebView) findViewById(R.id.webView);
        switch (type) {
            case "cizgi":
                selection = 0;
                browser.loadUrl("http://irl.iyte.edu.tr/kategoriler/cizgi-izleyen/");
                break;
            case "sumo":
                selection = 1;
                browser.loadUrl("http://irl.iyte.edu.tr/kategoriler/sumo/");
                break;
            case "mini":
                selection = 2;
                browser.loadUrl("http://irl.iyte.edu.tr/kategoriler/mini-sumo/");
                break;
            case "coklumini":
                selection = 3;
                browser.loadUrl("http://irl.iyte.edu.tr/kategoriler/coklu-mini-sumo/");
                break;
            case "yangin":
                selection = 4;
                browser.loadUrl("http://irl.iyte.edu.tr/kategoriler/yangin-sonduren/");
                break;
            case "arazi":
                selection = 5;
                browser.loadUrl("http://irl.iyte.edu.tr/kategoriler/arazi/");
                break;
            case "cop":
                selection = 6;
                browser.loadUrl("http://irl.iyte.edu.tr/kategoriler/cop-toplayan/");
                break;
            case "serbest":
                selection = 7;
                browser.loadUrl("http://irl.iyte.edu.tr/kategoriler/serbest-kategori/");
                break;
            case "tasarla":
                selection = 8;
                browser.loadUrl("http://irl.iyte.edu.tr/kategoriler/tasarla-yap-yaristir/");
                break;

        }
        browser.scrollBy(0, 1500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rules, menu);
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
