package com.ajdi.yassin.instajournal.ui.notedetail;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ajdi.yassin.instajournal.R;
import com.ajdi.yassin.instajournal.ui.notes.NotesViewModel;

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


}
