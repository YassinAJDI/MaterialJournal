package com.ajdi.yassin.instajournal.data.source;

public class NotesRepository {

    private volatile static NotesRepository INSTANCE = null;

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
}
