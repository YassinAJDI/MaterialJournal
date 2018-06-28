package com.ajdi.yassin.instajournal.ui.notedetail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ajdi.yassin.instajournal.R;
import com.ajdi.yassin.instajournal.utils.ViewModelFactory;
import com.ajdi.yassin.instajournal.utils.ActivityUtils;

public class NoteDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NOTE_ID = "NOTE_ID";

    private NoteDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupToolbar();

        setupViewFragment();

        mViewModel = obtainViewModel(this);
    }

    private void setupViewFragment() {
        // Get the requested note id
        String noteId = getIntent().getStringExtra(EXTRA_NOTE_ID);

        NoteDetailFragment noteDetailFragment = (NoteDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        if (noteDetailFragment == null) {
            noteDetailFragment = NoteDetailFragment.newInstance(noteId);
        }

        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),
                noteDetailFragment, R.id.fragment_container);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
    }

    public static NoteDetailViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(NoteDetailViewModel.class);
    }
}
