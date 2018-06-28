package com.ajdi.yassin.instajournal.ui.notes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ajdi.yassin.instajournal.data.model.Note;
import com.ajdi.yassin.instajournal.databinding.ItemNoteBinding;

import java.util.List;

import timber.log.Timber;

public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final NotesViewModel mNotesViewModel;

    private List<Note> mNotes;

    public NotesAdapter(List<Note> notes, NotesViewModel notesViewModel) {
        mNotesViewModel = notesViewModel;
        replaceData(notes);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Create the binding
        ItemNoteBinding binding =
                ItemNoteBinding.inflate(layoutInflater, parent, false);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Timber.d("Binding item at position: " + position);
        Note note = mNotes.get(position);
        Timber.d(note.toString());

        NoteViewHolder noteViewHolder = (NoteViewHolder) holder;

        NoteItemUserActionsListener userActionsListener = new NoteItemUserActionsListener() {
            @Override
            public void onNoteClicked(Note note) {
                mNotesViewModel.getOpenNoteEvent().setValue(String.valueOf(note.getId()));
            }
        };

        noteViewHolder.binding.setListener(userActionsListener);
        noteViewHolder.binding.setNote(note);
        noteViewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mNotes != null ? mNotes.size() : 0;
    }

    public void replaceData(List<Note> notes) {
        Timber.d("Data replaced total: " + notes.size());
        mNotes = notes;
        notifyDataSetChanged();
    }


    /**
     * The {@link NoteViewHolder} class.
     * Provides a binding reference to each view in the note cardView.
     */
    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private final ItemNoteBinding binding;

        public NoteViewHolder(final ItemNoteBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
