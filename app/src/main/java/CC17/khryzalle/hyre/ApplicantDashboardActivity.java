package CC17.khryzalle.hyre;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ApplicantDashboardActivity extends AppCompatActivity {

    private static final int CREATE_POST_REQUEST = 1;
    private RecyclerView newsFeedRecyclerView;
    private NewsFeedAdapter newsFeedAdapter;
    private List<NewsFeedItem> feedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_applicant_dashboard);

        feedItems = new ArrayList<>();
        setupRecyclerView();
        setupNavigation();
        loadSampleData();
    }

    private void setupNavigation() {
        ImageButton homeButton = findViewById(R.id.homeButton);
        ImageButton mailButton = findViewById(R.id.mailButton);
        ImageButton addPostButton = findViewById(R.id.addPostButton);
        ImageButton profileButton = findViewById(R.id.profileButton);
        ImageButton settingsButton = findViewById(R.id.settingsButton);

        homeButton.setOnClickListener(v -> 
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show());
        mailButton.setOnClickListener(v -> {
            Intent intent = new Intent(ApplicantDashboardActivity.this, MailActivity.class);
            startActivity(intent);
        });
        addPostButton.setOnClickListener(v -> {
            Intent intent = new Intent(ApplicantDashboardActivity.this, AddPostActivity.class);
            startActivityForResult(intent, CREATE_POST_REQUEST);
        });
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(ApplicantDashboardActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
        settingsButton.setOnClickListener(v -> 
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_POST_REQUEST && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            String type = data.getStringExtra("type");
            String imageUri = data.getStringExtra("imageUri");
            String fileUri = data.getStringExtra("fileUri");

            // Create new post and add it to the top of the list
            NewsFeedItem newPost = new NewsFeedItem(
                "You", // Or get the current user's name
                type,
                title,
                description,
                imageUri,
                fileUri
            );
            
            feedItems.add(0, newPost); // Add to the beginning of the list
            newsFeedAdapter.notifyItemInserted(0);
            newsFeedRecyclerView.scrollToPosition(0); // Scroll to the new post
        }
    }

    private void setupRecyclerView() {
        newsFeedRecyclerView = findViewById(R.id.newsFeedRecyclerView);
        newsFeedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsFeedAdapter = new NewsFeedAdapter(feedItems);
        newsFeedRecyclerView.setAdapter(newsFeedAdapter);
    }

    private void loadSampleData() {
        feedItems.add(new NewsFeedItem(
            "Steven Yean",
            "Discussion",
            "How to make Money As a Voice Actor",
            "To make money as a voice actor, establish your skills, create a professional demo reel, and actively seek out opportunities...",
            null,
            null
        ));
        newsFeedAdapter.notifyDataSetChanged();
    }
} 