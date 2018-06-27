package com.ajdi.yassin.instajournal.ui.notedetail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ajdi.yassin.instajournal.R;
import com.ajdi.yassin.instajournal.ui.notes.NotesViewModel;
import com.ajdi.yassin.instajournal.ui.notes.ViewModelFactory;

public class NoteDetailActivity extends AppCompatActivity {

    private NotesViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupToolbar();

        setupViewFragment();

        mViewModel = obtainViewModel(this);
    }

    private NotesViewModel obtainViewModel(NoteDetailActivity noteDetailActivity) {
        return null;
    }

    private void setupViewFragment() {

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
