package com.ajdi.yassin.instajournal.ui.notes;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ajdi.yassin.instajournal.R;
import com.ajdi.yassin.instajournal.utils.ActivityUtils;

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
            mNotesFragment = NotesFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            ActivityUtils.replaceFragmentInActivity(fragmentManager,
                    mNotesFragment, R.id.fragment_container);
        }
    }
}
