package edu.sjsu.android.crossword;

import android.provider.BaseColumns;

public final class CrosswordDatabaseContract {
    private CrosswordDatabaseContract() {}

    public static class WordsTable implements BaseColumns {
        public static final String TABLE_NAME = "words";
        public static final String COLUMN_NAME_WORD = "word";
        public static final String COLUMN_NAME_CLUE = "clue";
    }

    public static class ProgressTable implements BaseColumns {
        public static final String TABLE_NAME = "progress";
        public static final String COLUMN_NAME_WORD_ID = "word_id";
        public static final String COLUMN_NAME_LETTERS_ENTERED = "letters_entered";
    }
}
