package tr.edu.iyte.irl.irl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ContactActivity extends AppCompatActivity {
    private EditText name, email, feedback;
    private Button button;
    private String ad, mail, donus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        findViews();
        initiate();
    }

    private void findViews() {
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        feedback = (EditText) findViewById(R.id.message);

        button = (Button) findViewById(R.id.button_send);
    }

    private void initiate() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, email.getText().toString());
                intent.putExtra(Intent.EXTRA_SUBJECT, "IRL 2015 GERIDONUS");
                intent.putExtra(Intent.EXTRA_TEXT, feedback.getText().toString());

                startActivity(Intent.createChooser(intent, "Send Email"));
                ad = name.getText().toString();
                mail = email.getText().toString();
                donus = feedback.getText().toString();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact, menu);
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
