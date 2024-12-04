package CC17.khryzalle.hyre;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        setupListeners();
    }

    private void setupListeners() {
        ImageButton backButton = findViewById(R.id.backButton);
        ImageButton editButton = findViewById(R.id.editProfileButton);

        backButton.setOnClickListener(v -> finish());
        
        editButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            startActivity(intent);
        });
    }
} 