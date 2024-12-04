package CC17.khryzalle.hyre;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextInputEditText nameInput, phoneInput, locationInput, bioInput;
    private Uri selectedImageUri;

    private final ActivityResultLauncher<String> pickImage = registerForActivityResult(
        new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                selectedImageUri = uri;
                profileImage.setImageURI(uri);
            }
        }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);

        initializeViews();
        setupListeners();
        loadCurrentData();
    }

    private void initializeViews() {
        profileImage = findViewById(R.id.profileImage);
        nameInput = findViewById(R.id.nameInput);
        phoneInput = findViewById(R.id.phoneInput);
        locationInput = findViewById(R.id.locationInput);
        bioInput = findViewById(R.id.bioInput);
    }

    private void setupListeners() {
        ImageButton backButton = findViewById(R.id.backButton);
        Button saveButton = findViewById(R.id.saveButton);
        Button changePhotoButton = findViewById(R.id.changePhotoButton);

        backButton.setOnClickListener(v -> finish());
        
        saveButton.setOnClickListener(v -> {
            // TODO: Save profile changes
            Toast.makeText(this, "Profile updated!", Toast.LENGTH_SHORT).show();
            finish();
        });

        changePhotoButton.setOnClickListener(v -> 
            pickImage.launch("image/*"));
    }

    private void loadCurrentData() {
        // TODO: Load current user data
        nameInput.setText("John Doe");
        phoneInput.setText("+1 234 567 890");
        locationInput.setText("New York, USA");
        bioInput.setText("Professional software developer with 5 years of experience.");
    }
} 