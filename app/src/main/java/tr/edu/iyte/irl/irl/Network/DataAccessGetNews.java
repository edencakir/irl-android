package tr.edu.iyte.irl.irl.Network;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

import tr.edu.iyte.irl.irl.Model.RequestGetNews;
import tr.edu.iyte.irl.irl.Model.ResponseGetNews;
import tr.edu.iyte.irl.irl.Utility.AppController;
import tr.edu.iyte.irl.irl.Utility.GsonRequest;

/**
 * Created by Enes on 7/26/15.
 */
public class DataAccessGetNews {
    private final String url = "http://irl.iyte.edu.tr/getnews.php";

    public void executeGetNews(RequestGetNews requestGetNews, Response.Listener<ResponseGetNews>
            responseListener, Response.ErrorListener errorListener, String tag) {
        Map<String, String> parameters = new HashMap<>();

        parameters.put("news", "get");

        GsonRequest<ResponseGetNews> request = new GsonRequest<>(
                Request.Method.POST, url, ResponseGetNews.class,
                parameters, responseListener, errorListener);

        AppController.getInstance().addToRequestQueue(request, tag);
    }
}
