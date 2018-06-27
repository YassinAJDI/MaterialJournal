package com.ajdi.yassin.instajournal.ui.notes;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ajdi.yassin.instajournal.R;

public class NotesActivity extends AppCompatActivity {

    private NotesFragment mNotesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        setupToolbar();

        setupViewFragment();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupViewFragment() {
        mNotesFragment =
                (NotesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (mNotesFragment == null) {
            // Create the fragment
            mNotesFragment = mNotesFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, mNotesFragment, R.id.fragment_container)
                    .commit();
        }
    }
}
