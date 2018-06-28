package com.ajdi.yassin.instajournal.ui.addedit;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ajdi.yassin.instajournal.data.model.Note;
import com.ajdi.yassin.instajournal.data.source.NotesRepository;
import com.ajdi.yassin.instajournal.utils.SingleLiveEvent;

import java.util.Date;

/**
 * ViewModel for the Add/Edit screen.
 */
public class AddEditNoteViewModel extends AndroidViewModel {

    public final ObservableField<String> title = new ObservableField<>();

    public final ObservableField<String> content = new ObservableField<>();

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);

    private final SingleLiveEvent<Void> mNoteUpdated = new SingleLiveEvent<>();

    private final NotesRepository mNotesRepository;

    @Nullable
    private String mNoteId;

    public AddEditNoteViewModel(@NonNull Application context, NotesRepository notesRepository) {
        super(context);
        mNotesRepository = notesRepository;
    }

    SingleLiveEvent<Void> getNoteUpdatedEvent() {
        return mNoteUpdated;
    }

    // Called when clicking on fab.
    void saveNote() {
        Note note = new Note(title.get(), content.get(), new Date().getTime());
        if (note.isEmpty()) {
            //mSnackbarText.setValue(R.string.empty_task_message);
            return;
        }

    }
}
