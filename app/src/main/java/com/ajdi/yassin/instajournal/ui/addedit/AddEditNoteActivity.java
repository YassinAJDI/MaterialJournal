package com.ajdi.yassin.instajournal.ui.addedit;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ajdi.yassin.instajournal.R;
import com.ajdi.yassin.instajournal.utils.ActivityUtils;
import com.ajdi.yassin.instajournal.utils.ViewModelFactory;

/**
 * Displays an add or edit note screen.
 */
public class AddEditNoteActivity extends AppCompatActivity implements AddEditNoteNavigator {

    public static final int REQUEST_CODE = 1;

    public static final int ADD_EDIT_RESULT_OK = RESULT_FIRST_USER + 1;

    AddEditNoteViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addedit_note);

        setupToolbar();

        setupViewFragment();

        mViewModel = obtainViewModel(this);

        // The activity observes the navigation events in the ViewModel
        mViewModel.getNoteUpdatedEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                AddEditNoteActivity.this.onNoteSaved();
            }
        });

    }

    public static AddEditNoteViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(AddEditNoteViewModel.class);
    }

    private void setupViewFragment() {
        AddEditNoteFragment addEditNoteFragment = (AddEditNoteFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        if (addEditNoteFragment == null) {
            addEditNoteFragment = AddEditNoteFragment.newInstance();

            // Send the note ID to the fragment
            Bundle bundle = new Bundle();
            bundle.putString(AddEditNoteFragment.ARGUMENT_EDIT_NOTE_ID,
                    getIntent().getStringExtra(AddEditNoteFragment.ARGUMENT_EDIT_NOTE_ID));
            addEditNoteFragment.setArguments(bundle);
        }

        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),
                addEditNoteFragment, R.id.fragment_container);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onNoteSaved() {
        setResult(ADD_EDIT_RESULT_OK);
        finish();
    }
}