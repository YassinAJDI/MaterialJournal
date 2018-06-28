package com.ajdi.yassin.instajournal.ui.addedit;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.instajournal.R;
import com.ajdi.yassin.instajournal.databinding.FragmentAddeditNoteBinding;
import com.ajdi.yassin.instajournal.utils.SnackbarMessage;
import com.ajdi.yassin.instajournal.utils.SnackbarUtils;

/**
 * Main UI for the add note screen. Users can enter note details.
 */
public class AddEditNoteFragment extends Fragment {

    public static final String ARGUMENT_EDIT_NOTE_ID = "EDIT_NOTE_ID";

    private AddEditNoteViewModel mViewModel;

    private FragmentAddeditNoteBinding mViewDataBinding;


    public AddEditNoteFragment() {
        // Required empty public constructor
    }

    public static AddEditNoteFragment newInstance() {
        return new AddEditNoteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewDataBinding = FragmentAddeditNoteBinding.inflate(inflater, container, false);

        mViewModel = AddEditNoteActivity.obtainViewModel(getActivity());

        mViewDataBinding.setViewmodel(mViewModel);

        setHasOptionsMenu(true);
        setRetainInstance(false);

        return mViewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupFab();

        setupActionBar();

        setupSnackbar();
    }

    private void setupActionBar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        if (getArguments() != null && getArguments().get(ARGUMENT_EDIT_NOTE_ID) != null) {
            actionBar.setTitle(R.string.edit_note);
        } else {
            actionBar.setTitle(R.string.add_note);
        }
    }

    private void setupFab() {
        FloatingActionButton fab = getActivity().findViewById(R.id.fab_edit_note_done);
        fab.setImageResource(R.drawable.ic_done_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.saveNote();
            }
        });
    }

    private void setupSnackbar() {
        mViewModel.getSnackbarMessage().observe(this, new SnackbarMessage.SnackbarObserver() {
            @Override
            public void onNewMessage(@StringRes int snackbarMessageResourceId) {
                SnackbarUtils.showSnackbar(getView(), getString(snackbarMessageResourceId));
            }
        });
    }
}
