package com.ajdi.yassin.instajournal.ui.notedetail;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.ajdi.yassin.instajournal.data.model.Note;
import com.ajdi.yassin.instajournal.data.source.NotesDataSource;
import com.ajdi.yassin.instajournal.data.source.NotesRepository;
import com.ajdi.yassin.instajournal.utils.SingleLiveEvent;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

public class NoteDetailViewModel extends AndroidViewModel implements NotesDataSource.GetNoteCallback {

    public final ObservableField<Note> note = new ObservableField<>();

    private final SingleLiveEvent<Void> mEditNoteCommand = new SingleLiveEvent<>();

    private final SingleLiveEvent<Void> mNoteLoadedCommand = new SingleLiveEvent<>();

    private final NotesRepository mNotesRepository;

    private final Context mContext;

    private boolean mIsDataLoading;

    public NoteDetailViewModel(@NonNull Application context, NotesRepository repository) {
        super(context);

        mContext = context.getApplicationContext(); // Force use of Application Context.
        mNotesRepository = repository;
    }

    public void start(String noteId) {
        if (noteId != null) {
            mIsDataLoading = true;
            mNotesRepository.getNote(noteId, this);
        }
    }

    @Override
    public void onNoteLoaded(Note note) {
        setNote(note);

        mIsDataLoading = false;

        Handler mainHandler = new Handler(Looper.getMainLooper());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                mNoteLoadedCommand.call();
            }
        };
        mainHandler.post(myRunnable);
    }

    @Override
    public void onDataNotAvailable() {
        setNote(null);
        mIsDataLoading = false;
    }

    public void setNote(Note note) {
        this.note.set(note);
    }

    public void editNote() {
        mEditNoteCommand.call();
    }

    public SingleLiveEvent<Void> getEditNoteCommand() {
        return mEditNoteCommand;
    }

    public SingleLiveEvent<Void> getNoteLoadedCommand() {
        return mNoteLoadedCommand;
    }
}
