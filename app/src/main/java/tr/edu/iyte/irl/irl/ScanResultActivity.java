package tr.edu.iyte.irl.irl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import tr.edu.iyte.irl.irl.Model.RequestGetRobotInfo;
import tr.edu.iyte.irl.irl.Model.ResponseGetRobotInfo;
import tr.edu.iyte.irl.irl.Model.Robot;
import tr.edu.iyte.irl.irl.Network.DataAccessGetRobotInfo;

public class ScanResultActivity extends AppCompatActivity {
    private DataAccessGetRobotInfo getRobotInfo;
    private Robot robot;
    private String returnedURL;
    private TextView tvNameDummy, tvRobotName, tvWeightDummy,
            tvRobotWeight, tvCategoryDummy, tvRobotCategory,
            tvTicketNoDummy, tvRobotTicketNo, tvTeamNameDummy, tvTeamName,
            tvTeamMailDummy, tvTeamMail, tvTeamNumDummy, tvTeamNum,
            tvRegistirationStatusDummy, tvRegistirationStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);
        Intent qrIntent = getIntent();
        returnedURL = qrIntent.getStringExtra("qrurl");

        findViews();
        initialize();
    }

    private void findViews() {
        tvNameDummy = (TextView) findViewById(R.id.nameDummy);
        tvRobotName = (TextView) findViewById(R.id.robotName);
        tvWeightDummy = (TextView) findViewById(R.id.weightDummy);
        tvRobotWeight = (TextView) findViewById(R.id.robotWeight);
        tvCategoryDummy = (TextView) findViewById(R.id.categoryDummy);
        tvRobotCategory = (TextView) findViewById(R.id.robotCategory);
        tvTicketNoDummy = (TextView) findViewById(R.id.ticketNoDummy);
        tvRobotTicketNo = (TextView) findViewById(R.id.robotTicketNo);
        tvTeamNameDummy = (TextView) findViewById(R.id.teamNameDummy);
        tvTeamName = (TextView) findViewById(R.id.teamName);
        tvTeamMailDummy = (TextView) findViewById(R.id.teamMailDummy);
        tvTeamMail = (TextView) findViewById(R.id.teamMail);
        tvTeamNumDummy = (TextView) findViewById(R.id.teamNumDummy);
        tvTeamNum = (TextView) findViewById(R.id.teamNum);
        tvRegistirationStatus = (TextView) findViewById(R.id.registerationStatus);
        tvRegistirationStatusDummy = (TextView) findViewById(R.id.registerationStatusDummy);

    }

    private void initialize() {
        robot = new Robot();
        getScanResult();
    }

    private void getScanResult() {
        getRobotInfo = new DataAccessGetRobotInfo();
        RequestGetRobotInfo requestGetRobotInfo = new RequestGetRobotInfo();

        getRobotInfo.executeGetNews(requestGetRobotInfo, getResponseListenerGetRobotInfo(),
                getErrorListenerGetRobotInfo(), "getrobotinfo", returnedURL);
    }

    private Response.Listener<ResponseGetRobotInfo> getResponseListenerGetRobotInfo() {
        return new Response.Listener<ResponseGetRobotInfo>() {
            @Override
            public void onResponse(ResponseGetRobotInfo response) {
                if (response != null) {
                    if (robot.getName() == null) {
                        Toast.makeText(getApplicationContext(), "Hata", Toast.LENGTH_LONG).show();
                    }
                    robot = response.getRobot();
                    tvRobotName.setText(robot.getName());
                    tvRobotWeight.setText(robot.getWeight());
                    tvRobotCategory.setText(robot.getCategory());
                    tvRobotTicketNo.setText(robot.getTicketNo());
                    tvTeamName.setText(robot.getTeamName());
                    tvTeamNum.setText(robot.getTeamNum());
                    tvTeamMail.setText(robot.getTeamMail());
                    tvRegistirationStatus.setText(robot.getRegistirationStatus());
                } else {
                    Log.d("JWp", "" + response);
                    Toast.makeText(getApplicationContext(), "Error 101: Connection error", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private Response.ErrorListener getErrorListenerGetRobotInfo() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JWp", "" + error);
                Toast.makeText(getApplicationContext(), "Error 102: Connection error", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scan_result, menu);
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
