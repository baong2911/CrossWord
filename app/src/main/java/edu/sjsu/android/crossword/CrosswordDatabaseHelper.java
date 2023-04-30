package edu.sjsu.android.crossword;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CrosswordDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "crossword.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_WORDS_TABLE =
            "CREATE TABLE " + CrosswordDatabaseContract.WordsTable.TABLE_NAME + " (" +
                    CrosswordDatabaseContract.WordsTable._ID + " INTEGER PRIMARY KEY," +
                    CrosswordDatabaseContract.WordsTable.COLUMN_NAME_WORD + " TEXT," +
                    CrosswordDatabaseContract.WordsTable.COLUMN_NAME_CLUE + " TEXT)";

    private static final String CREATE_PROGRESS_TABLE =
            "CREATE TABLE " + CrosswordDatabaseContract.ProgressTable.TABLE_NAME + " (" +
                    CrosswordDatabaseContract.ProgressTable._ID + " INTEGER PRIMARY KEY," +
                    CrosswordDatabaseContract.ProgressTable.COLUMN_NAME_WORD_ID + " INTEGER," +
                    CrosswordDatabaseContract.ProgressTable.COLUMN_NAME_LETTERS_ENTERED + " TEXT)";

    private static final String DROP_WORDS_TABLE =
            "DROP TABLE IF EXISTS " + CrosswordDatabaseContract.WordsTable.TABLE_NAME;

    private static final String DROP_PROGRESS_TABLE =
            "DROP TABLE IF EXISTS " + CrosswordDatabaseContract.ProgressTable.TABLE_NAME;

    public CrosswordDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WORDS_TABLE);
        db.execSQL(CREATE_PROGRESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_WORDS_TABLE);
        db.execSQL(DROP_PROGRESS_TABLE);
        onCreate(db);
    }

    public long insertWord(String word, String clue) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CrosswordDatabaseContract.WordsTable.COLUMN_NAME_WORD, word);
        values.put(CrosswordDatabaseContract.WordsTable.COLUMN_NAME_CLUE, clue);
        long newRowId = db.insert(CrosswordDatabaseContract.WordsTable.TABLE_NAME, null, values);
        return newRowId;
    }

    public int updateWord(int wordId, String word, String clue) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CrosswordDatabaseContract.WordsTable.COLUMN_NAME_WORD, word);
        values.put(CrosswordDatabaseContract.WordsTable.COLUMN_NAME_CLUE, clue);
        String selection = CrosswordDatabaseContract.WordsTable._ID + " = ?";
        String[] selectionArgs = { String.valueOf(wordId) };
        int count = db.update(CrosswordDatabaseContract.WordsTable.TABLE_NAME, values, selection, selectionArgs);
        return count;
    }

    public int deleteWord(int wordId) {
        SQLiteDatabase db = getWritableDatabase();
        String selection = CrosswordDatabaseContract.WordsTable._ID + " = ?";
        String[] selectionArgs = { String.valueOf(wordId) };
        int count = db.delete(CrosswordDatabaseContract.WordsTable.TABLE_NAME, selection, selectionArgs);
        return count;
    }
}
