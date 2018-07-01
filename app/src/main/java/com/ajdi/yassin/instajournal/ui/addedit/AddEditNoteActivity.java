package com.ajdi.yassin.instajournal.ui.addedit;

import android.os.Bundle;

import com.ajdi.yassin.instajournal.R;
import com.ajdi.yassin.instajournal.utils.ActivityUtils;
import com.ajdi.yassin.instajournal.utils.ViewModelFactory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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
        mViewModel.getNoteUpdatedEvent().observeEvent(this, new Observer<Void>() {
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
