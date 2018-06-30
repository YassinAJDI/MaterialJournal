package com.ajdi.yassin.instajournal.ui.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ajdi.yassin.instajournal.data.model.Note;
import com.ajdi.yassin.instajournal.databinding.ItemNoteBinding;
import com.ajdi.yassin.instajournal.utils.GlideApp;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final NotesViewModel mNotesViewModel;

    private List<Note> mNotes;

    private Context mContext;

    public NotesAdapter(Context context, List<Note> notes, NotesViewModel notesViewModel) {
        mNotesViewModel = notesViewModel;
        mContext = context;
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
        GlideApp.with(mContext)
                .load("https://lh3.googleusercontent.com/-xT9nbe3isBs/WxNLkWcpSHI/AAAAAAAAAic/pulWUZTPWooItSTuxghGgjt9dhV2kzf9gCEwYBhgL/w140-h140-p/4711f20662911d1ff51216d692c1354025357acf_hq.jpg")
                .apply(new RequestOptions().circleCrop())
                .into(noteViewHolder.binding.imageUserIcon);

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
