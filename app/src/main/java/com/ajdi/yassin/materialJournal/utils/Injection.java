package com.ajdi.yassin.materialJournal.utils;

import android.content.Context;
import androidx.annotation.NonNull;

import com.ajdi.yassin.materialJournal.data.source.NotesRepository;
import com.ajdi.yassin.materialJournal.data.source.local.AppDatabase;
import com.ajdi.yassin.materialJournal.data.source.local.NotesLocalDataSource;

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
