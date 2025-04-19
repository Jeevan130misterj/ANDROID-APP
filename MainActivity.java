package com.example.dailyworkoutcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView dateTextView;
    private Button todaysQuestButton;
    private Button moodCheckButton;
    private Button progressButton;
    private Button journalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize views
        dateTextView = findViewById(R.id.dateTextView);
        todaysQuestButton = findViewById(R.id.todaysQuestButton);
        moodCheckButton = findViewById(R.id.moodCheckButton);
        progressButton = findViewById(R.id.progressButton);
        journalButton = findViewById(R.id.journalButton);

        // Set current date
        String currentDate = new SimpleDateFormat("EEEE, MMMM d", Locale.getDefault()).format(new Date());
        dateTextView.setText(currentDate);

        // Set click listeners
        todaysQuestButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, QuestActivity.class)));
        moodCheckButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MoodActivity.class)));
        progressButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProgressActivity.class)));
        journalButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, JournalActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
