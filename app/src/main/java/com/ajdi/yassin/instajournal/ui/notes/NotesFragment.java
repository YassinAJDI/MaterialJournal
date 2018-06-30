package com.ajdi.yassin.instajournal.ui.notes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.instajournal.R;
import com.ajdi.yassin.instajournal.data.model.Note;
import com.ajdi.yassin.instajournal.databinding.FragmentNotesBinding;
import com.ajdi.yassin.instajournal.utils.SnackbarMessage;
import com.ajdi.yassin.instajournal.utils.SnackbarUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

/**
 * Display a grid of {@link Note}s. User can choose to view all, favorite or trashed notes.
 */
public class NotesFragment extends Fragment {

    private NotesViewModel mNotesViewModel;

    private NotesAdapter mNotesAdapter;

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

        mFragmentNotesBinding.setViewmodel(mNotesViewModel);
        //mFragmentNotesBinding.setLifecycleOwner(this);

        setHasOptionsMenu(true);

        return mFragmentNotesBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupFab();

        setupListAdapter();

        setupSnackbar();
    }

    @Override
    public void onStart() {
        super.onStart();
        mNotesViewModel.start();
    }

    private void setupListAdapter() {
        Timber.d("setupListAdapter");
        RecyclerView recyclerView = mFragmentNotesBinding.recyclerNoteList;
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false));

        mNotesAdapter = new NotesAdapter(getActivity(), new ArrayList<Note>(0), mNotesViewModel);

        recyclerView.setAdapter(mNotesAdapter);
    }

    private void setupFab() {
        if (getActivity() == null)
            return;

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_add_note);

        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotesViewModel.addNewNote();
            }
        });
    }

    private void setupSnackbar() {
        mNotesViewModel.getSnackbarMessage().observe(this, new SnackbarMessage.SnackbarObserver() {
            @Override
            public void onNewMessage(@StringRes int snackbarMessageResourceId) {
                SnackbarUtils.showSnackbar(getView(), getString(snackbarMessageResourceId));
            }
        });
    }
}
