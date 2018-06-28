package com.ajdi.yassin.instajournal.data.source;

import android.support.annotation.NonNull;

import com.ajdi.yassin.instajournal.data.model.Note;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class NotesRepository implements NotesDataSource {

    private volatile static NotesRepository INSTANCE = null;

    private final NotesDataSource mNotesLocalDataSource;

    Map<Integer, Note> mCachedNotes;

    // force clean cache notes
    private boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    private NotesRepository(@NonNull NotesDataSource notesLocalDataSource) {
        mNotesLocalDataSource = checkNotNull(notesLocalDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @return the {@link NotesRepository} instance
     */
    public static NotesRepository getInstance(NotesDataSource notesLocalDataSource) {
        if (INSTANCE == null) {
            synchronized (NotesRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NotesRepository(notesLocalDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getNotes(@NonNull final LoadNotesCallback callback) {
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (mCachedNotes != null && !mCacheIsDirty) {
            callback.onNotesLoaded(new ArrayList<>(mCachedNotes.values()));
            return;
        }

        // Query the local storage if available. If not, query the network.
        mNotesLocalDataSource.getNotes(new LoadNotesCallback() {
            @Override
            public void onNotesLoaded(List<Note> notes) {
                refreshCache(notes);
                callback.onNotesLoaded(new ArrayList<>(mCachedNotes.values()));
            }

            @Override
            public void onDataNotAvailable() {
                // database is empty get data from remote db
            }
        });
    }

    @Override
    public void getNote(String noteId, @NonNull final GetNoteCallback callback) {
        checkNotNull(noteId);
        checkNotNull(callback);

        Note cachedNote = getTaskWithId(noteId);

        // Respond immediately with cache if available
        if (cachedNote != null) {
            callback.onNoteLoaded(cachedNote);
            return;
        }

        // Load from server/persisted if needed.
        // Is the note in the local data source? If not, query the network.
        mNotesLocalDataSource.getNote(noteId, new GetNoteCallback() {
            @Override
            public void onNoteLoaded(Note note) {
                // Do in memory cache update to keep the app UI up to date
                if (mCachedNotes == null) {
                    mCachedNotes = new LinkedHashMap<>();
                }
                mCachedNotes.put(note.getId(), note);

                callback.onNoteLoaded(note);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void saveNote(@NonNull Note note) {
        checkNotNull(note);
        mNotesLocalDataSource.saveNote(note);
        refreshNotes();
    }

    private Note getTaskWithId(String noteId) {
        checkNotNull(noteId);
        if (mCachedNotes == null || mCachedNotes.isEmpty()) {
            return null;
        } else {
            return mCachedNotes.get(noteId);
        }
    }

    private void refreshCache(List<Note> notes) {
        if (mCachedNotes == null) {
            mCachedNotes = new LinkedHashMap<>();
        }
        mCachedNotes.clear();
        for (Note note : notes) {
            mCachedNotes.put(note.getId(), note);
        }
        mCacheIsDirty = false;
    }

    private void refreshNotes() {
        mCacheIsDirty = true;
    }
}
