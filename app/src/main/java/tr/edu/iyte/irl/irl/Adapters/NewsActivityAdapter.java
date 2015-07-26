package tr.edu.iyte.irl.irl.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tr.edu.iyte.irl.irl.Model.NewsItem;

/**
 * Created by Enes on 7/24/15.
 */
public class NewsActivityAdapter extends RecyclerView.Adapter<NewsActivityAdapter.ViewHolder> {
    private Context context;
    private ArrayList<NewsItem> items;

    //create constructor for adapter here.

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView test;

        public ViewHolder(View itemView) {
            super(itemView);
            //test = (TextView) itemView.findViewById(R.id. );
        }
    }
}
