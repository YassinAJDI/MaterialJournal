package com.ajdi.yassin.instajournal.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ajdi.yassin.instajournal.data.source.NotesRepository;

/**
 * Enables injection of production implementations for
 * {@link NotesRepository} at compile time.
 *
 * @author Yassin AJDI
 */
public class Injection {

    public static NotesRepository provideFeedsRepository(@NonNull Context context) {
        return NotesRepository.getInstance(provideLocalDataSource(context));
    }
}
