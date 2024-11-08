package com.palaniappan.thillainathan.cs3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText E1, E2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        b1 = findViewById(R.id.button);
        E1 = findViewById(R.id.Name);
        E2 = findViewById(R.id.Number);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the latest values from EditText fields
                String s1 = E1.getText().toString().trim();
                String s2 = E2.getText().toString().trim();


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(s1);

                // Update the value in Firebase
                myRef.setValue(s2);

            }
        });
      }
}
