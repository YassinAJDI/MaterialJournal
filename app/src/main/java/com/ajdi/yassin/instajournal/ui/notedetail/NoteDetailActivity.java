package com.ajdi.yassin.instajournal.ui.notedetail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ajdi.yassin.instajournal.R;
import com.ajdi.yassin.instajournal.ui.addedit.AddEditNoteActivity;
import com.ajdi.yassin.instajournal.ui.addedit.AddEditNoteFragment;
import com.ajdi.yassin.instajournal.utils.ActivityUtils;
import com.ajdi.yassin.instajournal.utils.ViewModelFactory;

import static com.ajdi.yassin.instajournal.ui.notedetail.NoteDetailFragment.REQUEST_EDIT_NOTE;

public class NoteDetailActivity extends AppCompatActivity implements NoteDetailNavigator {

    public static final String EXTRA_NOTE_ID = "NOTE_ID";

    private NoteDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        setupToolbar();

        setupViewFragment();

        mViewModel = obtainViewModel(this);

        // The activity observes the navigation commands in the ViewModel
        mViewModel.getEditNoteCommand().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                NoteDetailActivity.this.onStartEditNote();
            }
        });
//        mViewModel.getDeleteTaskCommand().observe(this, new Observer<Void>() {
//            @Override
//            public void onChanged(@Nullable Void _) {
//                TaskDetailActivity.this.onTaskDeleted();
//            }
//        });
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

    @Override
    public void onStartEditNote() {
        String noteId = getIntent().getStringExtra(EXTRA_NOTE_ID);
        Intent intent = new Intent(this, AddEditNoteActivity.class);
        intent.putExtra(AddEditNoteFragment.ARGUMENT_EDIT_NOTE_ID, noteId);
        startActivityForResult(intent, REQUEST_EDIT_NOTE);
    }
}
