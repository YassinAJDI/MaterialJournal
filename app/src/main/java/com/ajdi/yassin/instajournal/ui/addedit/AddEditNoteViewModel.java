package com.ajdi.yassin.instajournal.ui.addedit;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ajdi.yassin.instajournal.data.source.NotesRepository;

/**
 * ViewModel for the Add/Edit screen.
 */
public class AddEditNoteViewModel extends AndroidViewModel {

    public final ObservableField<String> title = new ObservableField<>();

    public final ObservableField<String> content = new ObservableField<>();

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);

    private final NotesRepository mNotesRepository;

    @Nullable
    private String mTaskId;

    public AddEditNoteViewModel(@NonNull Application context, NotesRepository notesRepository) {
        super(context);
        mNotesRepository = notesRepository;
    }


}
