package com.example.dailyworkoutcompanion;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class QuestActivity extends AppCompatActivity {

    private TextView questTitleTextView;
    private TextView questDescriptionTextView;
    private Button completeButton;
    private Button newQuestButton;

    private String[] questTitles = {
            "Strength Builder",
            "Cardio Blast",
            "Core Challenge",
            "Flexibility Journey",
            "Endurance Test"
    };

    private String[][] questDetails = {
            {"3 sets of push-ups (10 reps)", "Build upper body strength with this classic exercise."},
            {"15-minute intense cardio session", "Get your heart pumping with jumping jacks, high knees, or running."},
            {"Plank for 1 minute, 3 times", "Strengthen your core muscles with this isometric exercise."},
            {"Full body stretch routine", "Improve flexibility and reduce muscle tension."},
            {"30-minute sustained activity", "Walk, jog, or cycle - keep moving for half an hour."}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        questTitleTextView = findViewById(R.id.questTitleTextView);
        questDescriptionTextView = findViewById(R.id.questDescriptionTextView);
        completeButton = findViewById(R.id.completeButton);
        newQuestButton = findViewById(R.id.newQuestButton);

        generateRandomQuest();

        completeButton.setOnClickListener(v -> {
            // Save completion to database
            DatabaseHelper db = new DatabaseHelper(this);
            db.addCompletedQuest(
                    questTitleTextView.getText().toString(),
                    questDescriptionTextView.getText().toString()
            );
            db.close();
            finish();
        });

        newQuestButton.setOnClickListener(v -> generateRandomQuest());
    }

    private void generateRandomQuest() {
        Random random = new Random();
        int index = random.nextInt(questTitles.length);
        
        questTitleTextView.setText(questTitles[index]);
        questDescriptionTextView.setText(questDetails[index][0] + "\n\n" + questDetails[index][1]);
    }
}
