package com.example.dailyworkoutcompanion;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class JournalActivity extends AppCompatActivity {

    private EditText journalEntryEditText;
    private Button saveJournalButton;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        journalEntryEditText = findViewById(R.id.journalEntryEditText);
        saveJournalButton = findViewById(R.id.saveJournalButton);
        db = new DatabaseHelper(this);

        saveJournalButton.setOnClickListener(v -> {
            String entry = journalEntryEditText.getText().toString().trim();
            if (!entry.isEmpty()) {
                db.addJournalEntry(entry);
                journalEntryEditText.setText("");
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
