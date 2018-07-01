package com.ajdi.yassin.materialJournal.ui.notes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ajdi.yassin.materialJournal.R;
import com.ajdi.yassin.materialJournal.ui.addedit.AddEditNoteActivity;
import com.ajdi.yassin.materialJournal.ui.login.AuthUiActivity;
import com.ajdi.yassin.materialJournal.ui.notedetail.NoteDetailActivity;
import com.ajdi.yassin.materialJournal.utils.ActivityUtils;
import com.ajdi.yassin.materialJournal.utils.UiUtils;
import com.ajdi.yassin.materialJournal.utils.ViewModelFactory;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.util.ExtraConstants;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class NotesActivity extends AppCompatActivity implements NotesNavigator, NoteItemNavigator {

    private NotesViewModel mViewModel;

    private BottomAppBar mBar;

    private BottomSheetBehavior<View> bottomDrawerBehavior;

    private FirebaseAuth mFirebaseAuth;

    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        checkIfSignedIn();

        setupViewFragment();

        setupBottomBar();

        setUpBottomDrawer();

        mViewModel = obtainViewModel(this);

        // Subscribe to add new note event
        mViewModel.getNewNoteEvent().observeEvent(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                addNewNote();
            }
        });

        // Subscribe to open note event
        mViewModel.getOpenNoteEvent().observeEvent(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String noteId) {
                if (noteId != null) {
                    openNoteDetails(noteId);
                }
            }
        });
    }

    private void checkIfSignedIn() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser == null){
            //Not signed in, launch the Sign In Activity
            startActivity(new Intent(this, AuthUiActivity.class));
            finish();
            return;
        }

    }

    private void setUpBottomDrawer() {
        View bottomDrawer = findViewById(R.id.bottom_drawer);
        bottomDrawerBehavior = BottomSheetBehavior.from(bottomDrawer);
        bottomDrawerBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        mBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDrawerBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
            }
        });
        mBar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        //bar.replaceMenu(R.menu.demo_primary);
    }

    private void setupBottomBar() {
        mBar = findViewById(R.id.bottom_app_bar);
        setSupportActionBar(mBar);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);

        if (menuItem != null) {
            UiUtils.tintMenuIcon(this, menuItem, R.color.md_white_1000);
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mViewModel.handleActivityResult(requestCode, resultCode);

    }

    @Override
    public void addNewNote() {
        Intent intent = new Intent(this, AddEditNoteActivity.class);
        startActivityForResult(intent, AddEditNoteActivity.REQUEST_CODE);
    }

    @Override
    public void openNoteDetails(String noteId) {
        Intent intent = new Intent(this, NoteDetailActivity.class);
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_ID, noteId);
        startActivityForResult(intent, AddEditNoteActivity.REQUEST_CODE);
    }

    @Override
    public void onBackPressed() {

        if (bottomDrawerBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
            bottomDrawerBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            return;
        }
        super.onBackPressed();
    }

    public static Intent createIntent(Context context, IdpResponse idpResponse) {
        return new Intent().setClass(context, NotesActivity.class)
                .putExtra(ExtraConstants.IDP_RESPONSE, idpResponse);
    }
}
