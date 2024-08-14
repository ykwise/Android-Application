package com.palaniappan.thillainathan.counterapp2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2;
    ImageButton btn1,btn2;
    int counter = 0;
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
        btn1 =(ImageButton) findViewById(R.id.B1);
        btn2 =(ImageButton) findViewById(R.id.BM);
        tv1= findViewById(R.id.counter_text);
        tv2 = findViewById(R.id.welcome_text);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            counter=counter+1;
            tv1.setText(""+counter);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter=counter-1;
                tv1.setText(""+counter);
            }
        });
    }
}