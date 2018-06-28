package com.ajdi.yassin.instajournal.ui.notedetail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.instajournal.R;
import com.ajdi.yassin.instajournal.databinding.FragmentNoteDetailBinding;

public class NoteDetailFragment extends Fragment {

    public static final String ARGUMENT_NOTE_ID = "NOTE_ID";

    public static final int REQUEST_EDIT_NOTE = 1;

    private NoteDetailViewModel mNoteDetailViewModel;

    public NoteDetailFragment() {
        // Required empty public constructor
    }

    public static NoteDetailFragment newInstance(String noteId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_NOTE_ID, noteId);
        NoteDetailFragment fragment = new NoteDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentNoteDetailBinding binding = FragmentNoteDetailBinding.
                inflate(inflater, container, false);

        mNoteDetailViewModel = NoteDetailActivity.obtainViewModel(getActivity());

        binding.setViewmodel(mNoteDetailViewModel);

        binding.setLifecycleOwner(this);

        setHasOptionsMenu(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupFab();

        setupSnackbar();
    }

    @Override
    public void onResume() {
        super.onResume();
        mNoteDetailViewModel.start(getArguments().getString(ARGUMENT_NOTE_ID));
    }

    private void setupFab() {
        FloatingActionButton fab = getActivity().findViewById(R.id.fab_edit_note);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mViewModel.editTask();
            }
        });
    }

    private void setupSnackbar() {

    }

}
