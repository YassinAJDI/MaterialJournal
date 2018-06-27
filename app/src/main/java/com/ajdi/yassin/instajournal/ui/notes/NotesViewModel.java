package com.ajdi.yassin.instajournal.ui.notes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.ajdi.yassin.instajournal.data.model.Note;
import com.ajdi.yassin.instajournal.data.source.NotesDataSource;
import com.ajdi.yassin.instajournal.data.source.NotesRepository;

import java.util.List;

import timber.log.Timber;

/**
 * Exposes the data to be used in the notes list screen.
 */
public class NotesViewModel extends AndroidViewModel {

    // These observable fields will update Views automatically
    public final ObservableList<Note> items = new ObservableArrayList<>();

    private final NotesRepository mNotesRepository;

    private final Context mContext;

    public NotesViewModel(@NonNull Application context, NotesRepository repository) {
        super(context);

        mContext = context.getApplicationContext(); // Force use of Application Context.
        mNotesRepository = repository;
    }

    public void start() {
        loadNotes(false);
    }

    private void loadNotes(boolean forceUpdate) {
        loadNotes(false, true);
    }

    private void loadNotes(boolean forceUpdate, final boolean showLoadingUI) {
        Timber.d("Loading notes.");
        if (showLoadingUI) {
            // TODO: 27/06/2018 show loading
        }
        if (forceUpdate) {
            mNotesRepository.refreshNotes();
        }

        mNotesRepository.getNotes(new NotesDataSource.LoadNotesCallback() {
            @Override
            public void onNotesLoaded(List<Note> notes) {
                Timber.d("Notes loaded:" + notes.size());
                //List<Note> notesToShow = new ArrayList<>();


                if (showLoadingUI) {
                    // TODO: 27/06/2018 stop loading
                }

                items.clear();
                items.addAll(notes);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

    }
}
