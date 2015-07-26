package tr.edu.iyte.irl.irl.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enes on 7/26/15.
 */
public class ResponseGetNews {
    @SerializedName("data")
    private List<NewsItem> news = new ArrayList<>();

    public ResponseGetNews() {
        super();
    }

    public List<NewsItem> getEventFeedList() {
        return this.news;
    }
}
