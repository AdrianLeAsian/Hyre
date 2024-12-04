package CC17.khryzalle.hyre;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class AddPostActivity extends AppCompatActivity {

    private TextInputEditText titleInput, descriptionInput;
    private ImageView imagePreview;
    private TextView fileNameText;
    private Uri selectedImageUri;
    private Uri selectedFileUri;

    private final ActivityResultLauncher<String> pickImage = registerForActivityResult(
        new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                selectedImageUri = uri;
                imagePreview.setImageURI(uri);
                imagePreview.setVisibility(View.VISIBLE);
                fileNameText.setVisibility(View.GONE);
            }
        }
    );

    private final ActivityResultLauncher<String> pickFile = registerForActivityResult(
        new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                selectedFileUri = uri;
                fileNameText.setText(uri.getLastPathSegment());
                fileNameText.setVisibility(View.VISIBLE);
                imagePreview.setVisibility(View.GONE);
            }
        }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_post);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        titleInput = findViewById(R.id.titleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        imagePreview = findViewById(R.id.imagePreview);
        fileNameText = findViewById(R.id.fileNameText);
    }

    private void setupListeners() {
        ImageButton backButton = findViewById(R.id.backButton);
        Button postButton = findViewById(R.id.postButton);
        Button attachFileButton = findViewById(R.id.attachFileButton);
        Button attachImageButton = findViewById(R.id.attachImageButton);

        backButton.setOnClickListener(v -> finish());
        
        postButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString().trim();
            String description = descriptionInput.getText().toString().trim();
            
            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create intent with post data
            Intent resultIntent = new Intent();
            resultIntent.putExtra("title", title);
            resultIntent.putExtra("description", description);
            resultIntent.putExtra("type", "Discussion");
            if (selectedImageUri != null) {
                resultIntent.putExtra("imageUri", selectedImageUri.toString());
            }
            if (selectedFileUri != null) {
                resultIntent.putExtra("fileUri", selectedFileUri.toString());
            }
            
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        attachFileButton.setOnClickListener(v -> 
            pickFile.launch("*/*"));

        attachImageButton.setOnClickListener(v -> 
            pickImage.launch("image/*"));
    }
} 