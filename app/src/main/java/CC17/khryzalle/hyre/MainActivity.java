package CC17.khryzalle.hyre;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        Button applicantButton = findViewById(R.id.applicantButton);
        Button recruiterButton = findViewById(R.id.recruiterButton);

        applicantButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ApplicantActivity.class);
            startActivity(intent);
        });

        recruiterButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RecruiterActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}