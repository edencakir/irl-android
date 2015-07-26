package tr.edu.iyte.irl.irl.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import tr.edu.iyte.irl.irl.Model.RequestGetNews;
import tr.edu.iyte.irl.irl.Model.ResponseGetNews;
import tr.edu.iyte.irl.irl.Network.DataAccessGetNews;
import tr.edu.iyte.irl.irl.R;

/**
 * Created by Enes on 7/26/15.
 */
public class NewsFragment extends Fragment {
    private View v;
    private DataAccessGetNews getNews;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_news, container, false);
        findViews();
        initialize();
        return v;
    }

    private void findViews() {

    }

    private void initialize() {
        getNews();

    }

    private void getNews() {
        getNews = new DataAccessGetNews();
        RequestGetNews requestGetNews = new RequestGetNews();

        getNews.executeGetNews(requestGetNews, getResponseListenerGetNews(),
                getErrorListenerGetNews(), "get news");
    }

    private Response.Listener<ResponseGetNews> getResponseListenerGetNews() {
        return new Response.Listener<ResponseGetNews>() {
            @Override
            public void onResponse(ResponseGetNews response) {
                if (response != null)
                    response.getEventFeedList();
                else
                    Toast.makeText(getActivity(), "Error 101: Connection error", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private Response.ErrorListener getErrorListenerGetNews() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error 102: Connection error", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
