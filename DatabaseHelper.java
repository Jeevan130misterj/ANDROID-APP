package com.example.dailyworkoutcompanion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "WorkoutCompanion.db";
    private static final int DATABASE_VERSION = 1;

    // Tables and columns
    private static final String TABLE_QUESTS = "quests";
    private static final String COLUMN_QUEST_ID = "id";
    private static final String COLUMN_QUEST_TITLE = "title";
    private static final String COLUMN_QUEST_DESC = "description";
    private static final String COLUMN_QUEST_DATE = "date";

    private static final String TABLE_MOOD = "mood";
    private static final String COLUMN_MOOD_ID = "id";
    private static final String COLUMN_MOOD_TYPE = "mood";
    private static final String COLUMN_MOOD_DATE = "date";

    private static final String TABLE_JOURNAL = "journal";
    private static final String COLUMN_JOURNAL_ID = "id";
    private static final String COLUMN_JOURNAL_ENTRY = "entry";
    private static final String COLUMN_JOURNAL_DATE = "date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create quests table
        String CREATE_QUESTS_TABLE = "CREATE TABLE " + TABLE_QUESTS + "("
                + COLUMN_QUEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_QUEST_TITLE + " TEXT,"
                + COLUMN_QUEST_DESC + " TEXT,"
                + COLUMN_QUEST_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(CREATE_QUESTS_TABLE);

        // Create mood table
        String CREATE_MOOD_TABLE = "CREATE TABLE " + TABLE_MOOD + "("
                + COLUMN_MOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_MOOD_TYPE + " TEXT,"
                + COLUMN_MOOD_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(CREATE_MOOD_TABLE);

        // Create journal table
        String CREATE_JOURNAL_TABLE = "CREATE TABLE " + TABLE_JOURNAL + "("
                + COLUMN_JOURNAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_JOURNAL_ENTRY + " TEXT,"
                + COLUMN_JOURNAL_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(CREATE_JOURNAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOURNAL);
        onCreate(db);
    }

    public void addCompletedQuest(String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUEST_TITLE, title);
        values.put(COLUMN_QUEST_DESC, description);
        db.insert(TABLE_QUESTS, null, values);
    }

    public void addMoodEntry(String mood) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MOOD_TYPE, mood);
        db.insert(TABLE_MOOD, null, values);
    }

    public void addJournalEntry(String entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_JOURNAL_ENTRY, entry);
        db.insert(TABLE_JOURNAL, null, values);
    }

    public List<String> getCompletedQuests() {
        List<String> quests = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTS + " ORDER BY " + COLUMN_QUEST_DATE + " DESC LIMIT 7";
        
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) {
            do {
                quests.add(cursor.getString(1) + "\n" + cursor.getString(2) + "\n" + cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return quests;
    }

    public List<String> getMoodHistory() {
        List<String> moods = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MOOD + " ORDER BY " + COLUMN_MOOD_DATE + " DESC LIMIT 7";
        
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) {
            do {
                moods.add(cursor.getString(1) + " - " + cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return moods;
    }
}
