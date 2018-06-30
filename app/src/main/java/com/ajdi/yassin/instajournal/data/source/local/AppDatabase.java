package com.ajdi.yassin.instajournal.data.source.local;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.ajdi.yassin.instajournal.data.model.Note;

/**
 * The Room Database that contains the note table.
 */
@Database(entities = {Note.class}, version = 1, exportSchema  = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "Notes.db";

    private static AppDatabase INSTANCE;

    public abstract NotesDao notesDao();

    private static final Object sLock = new Object();

    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, DATABASE_NAME)
                        .build();
            }
            return INSTANCE;
        }
    }

}

