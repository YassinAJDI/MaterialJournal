package com.ajdi.yassin.materialJournal.data.source.local;

import com.ajdi.yassin.materialJournal.data.model.Note;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Data Access Object for notes table.
 */
@Dao
public interface NotesDao {

    /**
     * Select all notes from the note table.
     *
     * @return all notes.
     */
    @Query("SELECT * FROM note ORDER BY date DESC")
    List<Note> getNotes();

    /**
     * Select a note by id.
     *
     * @param noteId the note id.
     * @return the note with noteId.
     */
    @Query("SELECT * FROM note WHERE id = :noteId")
    Note getNoteById(String noteId);

    /**
     * Insert a note in the database. If the note already exists, replace it.
     *
     * @param note the note to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    /**
     * Update a note.
     *
     * @param note note to be updated
     * @return the number of notes updated. This should always be 1.
     */
    @Update
    int updateNote(Note note);

    /**
     * Delete a note by id.
     *
     * @return the number of notes deleted. This should always be 1.
     */
    @Query("DELETE FROM note WHERE id = :noteId")
    int deleteNoteById(String noteId);

    /**
     * Delete all notes.
     */
    @Query("DELETE FROM note")
    void deleteNotes();

}
