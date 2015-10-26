package tr.edu.iyte.irl.irl.Network;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import tr.edu.iyte.irl.irl.Model.RequestGetRobotInfo;
import tr.edu.iyte.irl.irl.Model.ResponseGetRobotInfo;
import tr.edu.iyte.irl.irl.Utility.AppController;
import tr.edu.iyte.irl.irl.Utility.Constants;
import tr.edu.iyte.irl.irl.Utility.GsonRequest;

/**
 * Created by Enes on 10/15/15.
 */
public class DataAccessGetRobotInfo {
    public void executeGetNews(RequestGetRobotInfo requestGetRobotInfo, Response.Listener<ResponseGetRobotInfo>
            responseListener, Response.ErrorListener errorListener, String tag, String ticketNumber) {
        Map<String, String> parameters = new HashMap<>();

        parameters.put("command", "200");
        parameters.put("secureKey", "15");
        parameters.put("ticketNumber", "" + ticketNumber);

        GsonRequest<ResponseGetRobotInfo> request = new GsonRequest<>(
                Request.Method.POST, Constants.ROBOTINFO_URL, ResponseGetRobotInfo.class,
                parameters, responseListener, errorListener);

        AppController.getInstance().addToRequestQueue(request, tag);
    }
}
