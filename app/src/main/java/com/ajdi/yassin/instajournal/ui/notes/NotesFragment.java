package com.ajdi.yassin.instajournal.ui.notes;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.instajournal.R;
import com.ajdi.yassin.instajournal.data.model.Note;
import com.ajdi.yassin.instajournal.databinding.FragmentNotesBinding;

/**
 * Display a grid of {@link Note}s. User can choose to view all, favorite or trashed notes.
 */
public class NotesFragment extends Fragment {

    private NotesViewModel mNotesViewModel;

    private FragmentNotesBinding mFragmentNotesBinding;

    public NotesFragment() {
        // Required empty public constructor
    }

    public static NotesFragment newInstance() {
        return new NotesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentNotesBinding = FragmentNotesBinding.inflate(inflater, container,
                false);

        mNotesViewModel = NotesActivity.obtainViewModel(getActivity());

        //mFragmentNotesBinding.setViewmodel(mFeedsViewModel);

        setHasOptionsMenu(true);

        return mFragmentNotesBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupFab();

        setupListAdapter();
    }

    private void setupListAdapter() {
        RecyclerView recyclerView = mFragmentNotesBinding.recyclerNoteList;

        recyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(null);
    }

    private void setupFab() {
        if (getActivity() == null)
            return;

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_add_note);

        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mTasksViewModel.addNewTask();
            }
        });
    }
}
