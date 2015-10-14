package tr.edu.iyte.irl.irl.Network;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import tr.edu.iyte.irl.irl.Model.RequestGetNews;
import tr.edu.iyte.irl.irl.Model.ResponseGetNews;
import tr.edu.iyte.irl.irl.Utility.AppController;
import tr.edu.iyte.irl.irl.Utility.Constants;
import tr.edu.iyte.irl.irl.Utility.GsonRequest;

/**
 * Created by Enes on 7/26/15.
 */
public class DataAccessGetNews {
    public void executeGetNews(RequestGetNews requestGetNews, Response.Listener<ResponseGetNews>
            responseListener, Response.ErrorListener errorListener, String tag) {
        Map<String, String> parameters = new HashMap<>();

        parameters.put("command", "300");
        parameters.put("secureKey", "15");
        parameters.put("ticketNumber", "1");

        GsonRequest<ResponseGetNews> request = new GsonRequest<>(
                Request.Method.POST, Constants.NEWS_URL, ResponseGetNews.class,
                parameters, responseListener, errorListener);

        AppController.getInstance().addToRequestQueue(request, tag);
    }
}
