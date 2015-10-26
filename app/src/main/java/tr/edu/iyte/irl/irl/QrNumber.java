package tr.edu.iyte.irl.irl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QrNumber extends AppCompatActivity {
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_number);

        button = (Button) findViewById(R.id.button_check);
        editText = (EditText) findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editText.getText().toString();

                Intent qrIntent = new Intent(getApplicationContext(), ScanResultActivity.class);
                qrIntent.putExtra("qrurl", code);
                startActivity(qrIntent);

                QrNumber.this.finish();
            }
        });

    }

}
