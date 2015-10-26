package tr.edu.iyte.irl.irl.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import tr.edu.iyte.irl.irl.Adapters.NewsFragmentAdapter;
import tr.edu.iyte.irl.irl.Model.NewsItem;
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
    private RecyclerView recyclerView;
    private RecyclerView.Adapter newsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<NewsItem> newsList;
    private NewsItem tempItem;
    private BroadcastReceiver onNotice = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            newsList.clear();
            getNews();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_news, container, false);
        findViews();
        initialize();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(onNotice, new IntentFilter("refresh"));
        return v;
    }

    private void findViews() {
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
    }

    private void initialize() {
        newsList = new ArrayList<>();
        getNews();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
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
                if (response != null) {
                    newsList = response.getEventFeedList();
                    newsAdapter = new NewsFragmentAdapter(getActivity(), newsList);
                    Log.d("jwp", "list size is " + newsList.size());
                    recyclerView.setAdapter(newsAdapter);
                } else {
                    Log.d("JWp", "" + response);
                    Toast.makeText(getActivity(), "Error 101: Connection error", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private Response.ErrorListener getErrorListenerGetNews() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JWp", "" + error);
                Toast.makeText(getActivity(), "Error 102: Connection error", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
