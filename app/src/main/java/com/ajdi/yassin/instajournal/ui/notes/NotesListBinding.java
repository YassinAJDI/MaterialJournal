package com.ajdi.yassin.instajournal.ui.notes;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.ajdi.yassin.instajournal.data.model.Note;

import java.util.List;

/**
 * Contains {@link BindingAdapter}s for the {@link Note} list.
 */
public class NotesListBinding {

    @BindingAdapter("app:items")
    public static void setItems(RecyclerView recyclerView, List<Note> items) {
        NotesAdapter adapter = (NotesAdapter) recyclerView.getAdapter();
        if (adapter != null)
        {
            adapter.replaceData(items);
        }
    }
}
