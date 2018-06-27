package com.ajdi.yassin.instajournal.ui.notedetail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ajdi.yassin.instajournal.data.source.NotesRepository;

public class NoteDetailViewModel extends AndroidViewModel {

    private final NotesRepository mNotesRepository;

    private final Context mContext;

    public NoteDetailViewModel(@NonNull Application context, NotesRepository repository) {
        super(context);

        mContext = context.getApplicationContext(); // Force use of Application Context.
        mNotesRepository = repository;
    }
}
