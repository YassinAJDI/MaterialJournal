package com.ajdi.yassin.instajournal.ui.notes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ajdi.yassin.instajournal.R;
import com.ajdi.yassin.instajournal.ui.addedit.AddEditNoteActivity;
import com.ajdi.yassin.instajournal.utils.ActivityUtils;
import com.ajdi.yassin.instajournal.utils.ViewModelFactory;

public class NotesActivity extends AppCompatActivity {

    private NotesViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        setupToolbar();

        setupViewFragment();

        mViewModel = obtainViewModel(this);

        // Subscribe to add new note event
        mViewModel.getNewNoteEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                addNewNote();
            }
        });
    }

    private void addNewNote() {
        Intent intent = new Intent(this, AddEditNoteActivity.class);
        startActivityForResult(intent, AddEditNoteActivity.REQUEST_CODE);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupViewFragment() {
        NotesFragment mNotesFragment = (NotesFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (mNotesFragment == null) {
            // Create the fragment
            mNotesFragment = NotesFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            ActivityUtils.replaceFragmentInActivity(fragmentManager,
                    mNotesFragment, R.id.fragment_container);
        }
    }

    public static NotesViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(NotesViewModel.class);
    }
}
