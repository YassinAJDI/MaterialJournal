package com.ajdi.yassin.instajournal.data.source;

import android.support.annotation.NonNull;

import com.ajdi.yassin.instajournal.data.model.Note;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class NotesRepository implements NotesDataSource {

    private volatile static NotesRepository INSTANCE = null;

    Map<String, Note> mCachedNotes;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested.
     */
    private boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    private NotesRepository() {

    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @return the {@link NotesRepository} instance
     */
    public static NotesRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (NotesRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NotesRepository();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public void refreshNotes() {
    }

    @Override
    public void getNotes(@NonNull LoadNotesCallback callback) {
        checkNotNull(callback);

        // this is just for test purpose

//        List<Note> notes = Arrays.asList(
//                new Note("Test 1", "content 1"),
//                new Note("Test 2", "content 2"),
//                new Note("Test 3", "content 3"),
//                new Note("Test 4", "content 4"),
//                new Note("Test 5", "content 5")
//        );

        callback.onNotesLoaded(null);


        // Respond immediately with cache if available and not dirty
//        if (mCachedNotes != null && !mCacheIsDirty) {
//            callback.onNotesLoaded(new ArrayList<>(mCachedNotes.values()));
//            return;
//        }
    }

    @Override
    public void getNote(String noteId, @NonNull GetNoteCallback callback) {
        checkNotNull(noteId);
        checkNotNull(callback);

        Note cachedNote = getTaskWithId(noteId);

        // Respond immediately with cache if available
        if (cachedNote != null) {
            callback.onNoteLoaded(cachedNote);
            return;
        }

        // Load from server/persisted if needed.

        // Is the task in the local data source? If not, query the network.
        // TODO: 27/06/2018 implement loading from room

    }

    private Note getTaskWithId(String noteId) {
        checkNotNull(noteId);
        if (mCachedNotes == null || mCachedNotes.isEmpty()) {
            return null;
        } else {
            return mCachedNotes.get(noteId);
        }
    }
}
