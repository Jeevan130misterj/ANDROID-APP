package com.example.dailyworkoutcompanion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MoodActivity extends AppCompatActivity {

    private RadioGroup moodRadioGroup;
    private Button submitMoodButton;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        moodRadioGroup = findViewById(R.id.moodRadioGroup);
        submitMoodButton = findViewById(R.id.submitMoodButton);
        db = new DatabaseHelper(this);

        submitMoodButton.setOnClickListener(v -> {
            int selectedId = moodRadioGroup.getCheckedRadioButtonId();
            String mood = "";
            
            switch (selectedId) {
                case R.id.radioExcited:
                    mood = "Excited";
                    break;
                case R.id.radioHappy:
                    mood = "Happy";
                    break;
                case R.id.radioNeutral:
                    mood = "Neutral";
                    break;
                case R.id.radioSad:
                    mood = "Sad";
                    break;
                case R.id.radioAngry:
                    mood = "Angry";
                    break;
                default:
                    Toast.makeText(MoodActivity.this, "Please select a mood", Toast.LENGTH_SHORT).show();
                    return;
            }
            
            db.addMoodEntry(mood);
            Toast.makeText(MoodActivity.this, "Mood recorded: " + mood, Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
