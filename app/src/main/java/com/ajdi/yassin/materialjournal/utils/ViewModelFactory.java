package com.ajdi.yassin.materialjournal.utils;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ajdi.yassin.materialjournal.data.source.NotesRepository;
import com.ajdi.yassin.materialjournal.ui.addedit.AddEditNoteViewModel;
import com.ajdi.yassin.materialjournal.ui.notedetail.NoteDetailViewModel;
import com.ajdi.yassin.materialjournal.ui.notes.NotesViewModel;


/**
 * A creator is used to inject the product ID into the ViewModel
 */
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    private final NotesRepository mNotesRepository;

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(application,
                            Injection.provideNotesRepository(application.getApplicationContext()));
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(Application application, NotesRepository repository) {
        mApplication = application;
        mNotesRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NotesViewModel.class)) {
            //noinspection unchecked
            return (T) new NotesViewModel(mApplication, mNotesRepository);
        } else if (modelClass.isAssignableFrom(NoteDetailViewModel.class)) {
            //noinspection unchecked
            return (T) new NoteDetailViewModel(mApplication, mNotesRepository);
        } else if (modelClass.isAssignableFrom(AddEditNoteViewModel.class)) {
            //noinspection unchecked
            return (T) new AddEditNoteViewModel(mApplication, mNotesRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
