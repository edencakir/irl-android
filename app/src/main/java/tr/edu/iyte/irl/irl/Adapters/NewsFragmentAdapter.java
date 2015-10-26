package tr.edu.iyte.irl.irl.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import tr.edu.iyte.irl.irl.Model.NewsItem;
import tr.edu.iyte.irl.irl.R;

/**
 * Created by Enes on 7/24/15.
 */
public class NewsFragmentAdapter extends RecyclerView.Adapter<NewsFragmentAdapter.ViewHolder> {
    private Context context;
    private List<NewsItem> items;

    //create constructor for adapter here.
    public NewsFragmentAdapter(Context context, List<NewsItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_news,
                viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        //we set the data here.
        NewsItem currentItem = items.get(i);
        if (currentItem != null) {
            //TODO: MAKE A BETTER STATEMENT HERE.
            if (currentItem.getTitle() != null && currentItem.getDescription() != null)
                viewHolder.title.setText(currentItem.getTitle());
            viewHolder.description.setText(currentItem.getDescription());
            if (!currentItem.getImageUrl().equals("")) {
                Picasso.with(context).load(currentItem
                        .getImageUrl()).resize(500, 200).centerCrop().into(viewHolder.image);
            } else {
                viewHolder.image.setImageResource(R.drawable.placeholder);
            }
        } else {
            Toast.makeText(context, "Error 103", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
