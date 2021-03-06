package com.ajdi.yassin.materialjournal.ui.notedetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ajdi.yassin.materialjournal.R;
import com.ajdi.yassin.materialjournal.ui.addedit.AddEditNoteActivity;
import com.ajdi.yassin.materialjournal.ui.addedit.AddEditNoteFragment;
import com.ajdi.yassin.materialjournal.utils.ActivityUtils;
import com.ajdi.yassin.materialjournal.utils.UiUtils;
import com.ajdi.yassin.materialjournal.utils.ViewModelFactory;
import com.google.android.material.bottomappbar.BottomAppBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import static com.ajdi.yassin.materialjournal.ui.addedit.AddEditNoteActivity.ADD_EDIT_RESULT_OK;
import static com.ajdi.yassin.materialjournal.ui.notedetail.NoteDetailFragment.REQUEST_EDIT_NOTE;

public class NoteDetailActivity extends AppCompatActivity implements NoteDetailNavigator {

    public static final String EXTRA_NOTE_ID = "NOTE_ID";

    public static final int EDIT_RESULT_OK = RESULT_FIRST_USER + 3;

    private NoteDetailViewModel mViewModel;

    private BottomAppBar mBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        setupToolbar();

        setupViewFragment();

        //setupBottomBar();

        mViewModel = obtainViewModel(this);

        // The activity observes the navigation commands in the ViewModel
        mViewModel.getEditNoteCommand().observeEvent(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                NoteDetailActivity.this.onStartEditNote();
            }
        });
//        mViewModel.getDeleteTaskCommand().observeEvent(this, new Observer<Void>() {
//            @Override
//            public void onChanged(@Nullable Void _) {
//                TaskDetailActivity.this.onTaskDeleted();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_bar_details, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);

        if (menuItem != null) {
            UiUtils.tintMenuIcon(this, menuItem, R.color.md_white_1000);
        }

        menuItem = menu.findItem(R.id.action_trash);

        if (menuItem != null) {
            UiUtils.tintMenuIcon(this, menuItem, R.color.md_white_1000);
        }
        return true;
    }

    private void setupBottomBar() {
        mBar = findViewById(R.id.bottom_app_bar);
        setSupportActionBar(mBar);
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
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_EDIT_NOTE) {
            // If the note was edited successfully, go back to the list.
            if (resultCode == ADD_EDIT_RESULT_OK) {
                // If the result comes from the add/edit screen, it's an edit.
                setResult(EDIT_RESULT_OK);
                finish();
            }
        }
    }

    @Override
    public void onStartEditNote() {
        String noteId = getIntent().getStringExtra(EXTRA_NOTE_ID);
        Intent intent = new Intent(this, AddEditNoteActivity.class);
        intent.putExtra(AddEditNoteFragment.ARGUMENT_EDIT_NOTE_ID, noteId);
        startActivityForResult(intent, REQUEST_EDIT_NOTE);
    }
}
