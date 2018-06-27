package com.ajdi.yassin.instajournal.ui.notes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.instajournal.data.model.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Note> mNotes;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    /**
     * The {@link NoteViewHolder} class.
     * Provides a binding reference to each view in the note cardView.
     */
    public class NoteViewHolder extends RecyclerView.ViewHolder {

        //private final ItemNoteBin cardBinding;

        public NoteViewHolder(final View cardView) {
            super(cardView);

            //cardBinding = CardArticleItemBinding.bind(cardView);
        }
    }
}
