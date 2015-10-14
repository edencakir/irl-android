package tr.edu.iyte.irl.irl.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import java.util.Collections;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_news, container, false);
        findViews();
        initialize();
        return v;
    }

    private void findViews() {
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
    }
    private void initialize() {
        getNews();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //maybe set these after network operation.
        //data below is test only
        NewsItem item = new NewsItem("Roboleague Basliyor", "IZTECH RoboLeague 2014, Türkiye’nin dört bir yanından sayıları 1000’in üzerinde her yaştan robot meraklılarının katılımıyla 26-27 Ekim 2014 tarihlerinde İYTE Kampüsü’nde (Makine Mühendisliği Binası ve Kütüphane Gösteri Merkezinde) gerçekleşmiştir", "");
        NewsItem item2 = new NewsItem("Gecen Sene Biz", "Türkiye’de ilk defa gerçekleştiren ikinci ana kategoride (Tasarla-Yap-Yarıştır) 15 ekip 24 saat boyunca belirlenen bir tema çerçevesinde robotlarını tasarlamışlardır.",
                "http://i.ytimg.com/vi/cHCb1-Gv2nI/maxresdefault.jpg");
        ArrayList<NewsItem> list = new ArrayList<>();
        list.add(item);
        list.add(item2);
        Collections.reverse(list);
        newsAdapter = new NewsFragmentAdapter(getActivity(), list);
        recyclerView.setAdapter(newsAdapter);
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
                    List<NewsItem> list = response.getEventFeedList();
                    Log.d("jwp", "" + list.get(2).getTitle());
                }
                else
                    Toast.makeText(getActivity(), "Error 101: Connection error", Toast.LENGTH_SHORT).show();
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
