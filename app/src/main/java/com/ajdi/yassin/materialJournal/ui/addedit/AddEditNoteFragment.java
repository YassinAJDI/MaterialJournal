package com.ajdi.yassin.materialJournal.ui.addedit;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.materialJournal.R;
import com.ajdi.yassin.materialJournal.databinding.FragmentAddeditNoteBinding;
import com.ajdi.yassin.materialJournal.utils.GlideApp;
import com.ajdi.yassin.materialJournal.utils.SnackbarMessage;
import com.ajdi.yassin.materialJournal.utils.SnackbarUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;

/**
 * Main UI for the add note screen. Users can enter note details.
 */
public class AddEditNoteFragment extends Fragment {

    public static final String ARGUMENT_EDIT_NOTE_ID = "EDIT_NOTE_ID";

    public final static int PICK_PHOTO_CODE = 1046;

    private AddEditNoteViewModel mViewModel;

    private FragmentAddeditNoteBinding mNotebinding;

    public AddEditNoteFragment() {
        // Required empty public constructor
    }

    public static AddEditNoteFragment newInstance() {
        return new AddEditNoteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mNotebinding = FragmentAddeditNoteBinding.inflate(inflater, container, false);

        mViewModel = AddEditNoteActivity.obtainViewModel(getActivity());

        mNotebinding.setViewmodel(mViewModel);

        setHasOptionsMenu(true);
        setRetainInstance(false);

        return mNotebinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupFab();

        setupActionBar();

        setupSnackbar();

        loadData();

        mNotebinding.buttonPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Trigger gallery selection for a photo
                openGallery();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Timber.d("onActivityResult");
        if (requestCode == PICK_PHOTO_CODE && resultCode == RESULT_OK && data != null) {

            Uri photoUri = data.getData();
//            Timber.d("Selectted image Uri: " + photoUri.toString());
//            String uriToString = ImageUtils.getPathFromURI(getActivity(), photoUri);
//            mViewModel.image.set(uriToString);
//            Timber.d("Selectted image String Uri: " + uriToString);
           GlideApp.with(this)
                   .load(photoUri)
                    .into(mNotebinding.imageNote);
        }
    }

    private void openGallery() {
        // Create intent for picking a photo from the gallery
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Bring up gallery to select a photo
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTO_CODE);
        }
    }

    private void loadData() {
        // Add or edit an existing note?
        if (getArguments() != null) {
            mViewModel.start(getArguments().getString(ARGUMENT_EDIT_NOTE_ID));
//            GlideApp.with(this)
//                    .load(mViewModel.image)
//                    .into(mNotebinding.imageNote);
        } else {
            mViewModel.start(null);
        }
    }

    private void setupActionBar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        if (getArguments() != null && getArguments().get(ARGUMENT_EDIT_NOTE_ID) != null) {
            actionBar.setTitle(R.string.edit_note);
        } else {
            actionBar.setTitle(R.string.add_note);
        }
    }

    private void setupFab() {
        FloatingActionButton fab = getActivity().findViewById(R.id.fab_edit_note_done);
        fab.setImageResource(R.drawable.ic_done_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.saveNote();
            }
        });
    }

    private void setupSnackbar() {
        mViewModel.getSnackbarMessage().observe(this, new SnackbarMessage.SnackbarObserver() {
            @Override
            public void onNewMessage(@StringRes int snackbarMessageResourceId) {
                SnackbarUtils.showSnackbar(getView(), getString(snackbarMessageResourceId));
            }
        });
    }
}
