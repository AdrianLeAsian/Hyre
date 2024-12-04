package CC17.khryzalle.hyre;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.NewsFeedViewHolder> {

    private List<NewsFeedItem> items = new ArrayList<>();

    public NewsFeedAdapter(List<NewsFeedItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public NewsFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_feed, parent, false);
        return new NewsFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsFeedViewHolder holder, int position) {
        NewsFeedItem item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<NewsFeedItem> newItems) {
        items = newItems;
        notifyDataSetChanged();
    }

    static class NewsFeedViewHolder extends RecyclerView.ViewHolder {
        TextView authorName, postType, postTitle, postContent;
        ImageView authorImage, postImage;

        NewsFeedViewHolder(@NonNull View itemView) {
            super(itemView);
            authorName = itemView.findViewById(R.id.authorName);
            postType = itemView.findViewById(R.id.postType);
            postTitle = itemView.findViewById(R.id.postTitle);
            postContent = itemView.findViewById(R.id.postContent);
            authorImage = itemView.findViewById(R.id.authorImage);
            postImage = itemView.findViewById(R.id.postImage);
        }

        void bind(NewsFeedItem item) {
            authorName.setText(item.getAuthorName());
            postType.setText(item.getPostType());
            postTitle.setText(item.getTitle());
            postContent.setText(item.getContent());

            if (item.getImageUri() != null) {
                postImage.setVisibility(View.VISIBLE);
                postImage.setImageURI(Uri.parse(item.getImageUri()));
            } else {
                postImage.setVisibility(View.GONE);
            }

            if (item.getFileUri() != null) {
                // Show file indicator or name
                // You might want to add a TextView or icon for files
            }
        }
    }
} 