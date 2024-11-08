package com.palaniappan.thillainathan.myapplicationproject;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private TextView textViewOutput;
    private Translator englishGermanTranslator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.editTextInput);
        textViewOutput = findViewById(R.id.textViewOutput);
        Button buttonTranslate = findViewById(R.id.buttonTranslate);

        // Set up Translator options for English-German translation
        TranslatorOptions options = new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.HINDI)
                .build();
        englishGermanTranslator = Translation.getClient(options);

        // Start model download when the app is opened
        downloadModel();

        // Set button click listener to perform translation
        buttonTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateText();
            }
        });
    }

    private void downloadModel() {
        DownloadConditions conditions = new DownloadConditions.Builder()
                .requireWifi()  // Download only on Wi-Fi
                .build();

        englishGermanTranslator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(unused -> {
                    // Model downloaded successfully
                    Toast.makeText(this, "Model downloaded", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Failed to download model
                    Toast.makeText(this, "Failed to download model: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }

    private void translateText() {
        String inputText = editTextInput.getText().toString();

        if (inputText.isEmpty()) {
            Toast.makeText(this, "Please enter text to translate", Toast.LENGTH_SHORT).show();
            return;
        }

        // Perform translation
        englishGermanTranslator.translate(inputText)
                .addOnSuccessListener(translatedText -> {
                    textViewOutput.setText(translatedText);
                })
                .addOnFailureListener(e -> {
                    // Error in translation
                    textViewOutput.setText("Translation failed. Please try again.");
                    Toast.makeText(this, "Translation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (englishGermanTranslator != null) {
            englishGermanTranslator.close();  // Release resources
        }
    }
}
