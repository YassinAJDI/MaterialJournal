package com.ajdi.yassin.materialjournal.ui.notes;

import android.app.Application;
import android.content.Context;

import com.ajdi.yassin.materialjournal.R;
import com.ajdi.yassin.materialjournal.data.model.Note;
import com.ajdi.yassin.materialjournal.data.source.NotesDataSource;
import com.ajdi.yassin.materialjournal.data.source.NotesRepository;
import com.ajdi.yassin.materialjournal.ui.addedit.AddEditNoteActivity;
import com.ajdi.yassin.materialjournal.utils.SingleLiveEvent;
import com.ajdi.yassin.materialjournal.utils.SnackbarMessage;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableList;
import androidx.lifecycle.AndroidViewModel;
import timber.log.Timber;

/**
 * Exposes the data to be used in the notes list screen.
 */
public class NotesViewModel extends AndroidViewModel {

    // These observable fields will update Views automatically
    public final ObservableList<Note> items = new ObservableArrayList<>();

    private final SingleLiveEvent<Void> mNewNoteEvent = new SingleLiveEvent<>();

    private final SingleLiveEvent<String> mOpenNoteEvent = new SingleLiveEvent<>();

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);

    private final SnackbarMessage mSnackbarText = new SnackbarMessage();

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
        loadNotes(forceUpdate, true);
    }

    private void loadNotes(boolean forceUpdate, final boolean showLoadingUI) {
        Timber.d("Loading notes.");
        if (showLoadingUI) {
            dataLoading.set(true);
        }

        mNotesRepository.getNotes(new NotesDataSource.LoadNotesCallback() {
            @Override
            public void onNotesLoaded(List<Note> notes) {
                Timber.d("Notes loaded:" + notes.size());
                List<Note> notesToShow = new ArrayList<>();


                if (showLoadingUI) {
                    dataLoading.set(false);
                }

                items.clear();
                items.addAll(notes);
            }

            @Override
            public void onDataNotAvailable() {
                dataLoading.set(false);
            }
        });

    }

    SingleLiveEvent<String> getOpenNoteEvent() {
        return mOpenNoteEvent;
    }

    SingleLiveEvent<Void> getNewNoteEvent() {
        return mNewNoteEvent;
    }

    SnackbarMessage getSnackbarMessage() {
        return mSnackbarText;
    }

    /**
     * Called by the Data Binding library when user click FAB button.
     */
    public void addNewNote() {
        mNewNoteEvent.call();
    }

    void handleActivityResult(int requestCode, int resultCode) {
        if (AddEditNoteActivity.REQUEST_CODE == requestCode) {
            switch (resultCode) {
                case AddEditNoteActivity.ADD_EDIT_RESULT_OK:
                    mSnackbarText.setValue(R.string.successfully_added_note_message);
                    break;
            }
        }
    }

    public void starNote(Note note) {
        mNotesRepository.starNote(note);
        loadNotes(false);
    }

    public void instarNote(Note note) {
        mNotesRepository.unstarNote(note);
        loadNotes(false);
    }
}
