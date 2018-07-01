package com.ajdi.yassin.materialJournal.ui.notes;

import com.ajdi.yassin.materialJournal.data.model.Note;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

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
