package CC17.khryzalle.hyre;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {

    private EditText messageInput;
    private TextView userNameText;
    private RecyclerView messagesRecyclerView;
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);

        setupViews();
        setupListeners();
        loadChatData();
        loadSampleMessages();
    }

    private void setupViews() {
        messageInput = findViewById(R.id.messageInput);
        userNameText = findViewById(R.id.userName);
        
        messagesRecyclerView = findViewById(R.id.messagesRecyclerView);
        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter();
        messagesRecyclerView.setAdapter(messageAdapter);
    }

    private void setupListeners() {
        ImageButton backButton = findViewById(R.id.backButton);
        ImageButton sendButton = findViewById(R.id.sendButton);

        backButton.setOnClickListener(v -> finish());

        sendButton.setOnClickListener(v -> {
            String messageText = messageInput.getText().toString().trim();
            if (!messageText.isEmpty()) {
                sendMessage(messageText);
                messageInput.setText("");
            }
        });
    }

    private void loadChatData() {
        String userName = getIntent().getStringExtra("userName");
        if (userName != null) {
            userNameText.setText(userName);
        }
    }

    private void sendMessage(String text) {
        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        Message message = new Message(text, currentTime, true);
        messageAdapter.addMessage(message);
        messagesRecyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);

        // Simulate received message after delay
        messagesRecyclerView.postDelayed(() -> {
            Message response = new Message("Thanks for your message!", currentTime, false);
            messageAdapter.addMessage(response);
            messagesRecyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
        }, 1000);
    }

    private void loadSampleMessages() {
        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        messageAdapter.addMessage(new Message("Hello!", "10:00", false));
        messageAdapter.addMessage(new Message("Hi there!", "10:01", true));
        messageAdapter.addMessage(new Message("How are you?", currentTime, false));
    }
} 