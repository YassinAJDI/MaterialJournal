package com.ajdi.yassin.instajournal.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ajdi.yassin.instajournal.data.source.NotesRepository;
import com.ajdi.yassin.instajournal.data.source.local.AppDatabase;
import com.ajdi.yassin.instajournal.data.source.local.NotesLocalDataSource;

/**
 * Enables injection of production implementations for
 * {@link NotesRepository} at compile time.
 *
 * @author Yassin AJDI
 */
public class Injection {

    public static NotesRepository provideNotesRepository(@NonNull Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        return NotesRepository.getInstance(NotesLocalDataSource.getInstance(database.notesDao()));
    }
}
