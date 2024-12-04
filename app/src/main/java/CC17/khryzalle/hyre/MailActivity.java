package CC17.khryzalle.hyre;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MailActivity extends AppCompatActivity implements ChatAdapter.OnChatClickListener {

    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mail);

        setupViews();
        loadChats();
    }

    private void setupViews() {
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(this);
        chatRecyclerView.setAdapter(chatAdapter);
    }

    private void loadChats() {
        List<ChatItem> chats = new ArrayList<>();
        chats.add(new ChatItem("John Doe", "Hey, are you available for an interview?", "10:30 AM"));
        chats.add(new ChatItem("Jane Smith", "Thank you for your application", "Yesterday"));
        chatAdapter.setChats(chats);
    }

    @Override
    public void onChatClick(ChatItem chat) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("userName", chat.getUserName());
        startActivity(intent);
    }
} 