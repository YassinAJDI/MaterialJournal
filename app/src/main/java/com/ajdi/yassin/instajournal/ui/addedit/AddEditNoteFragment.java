package com.ajdi.yassin.instajournal.ui.addedit;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.instajournal.R;
import com.ajdi.yassin.instajournal.databinding.FragmentAddeditNoteBinding;
import com.ajdi.yassin.instajournal.utils.SnackbarMessage;
import com.ajdi.yassin.instajournal.utils.SnackbarUtils;

/**
 * Main UI for the add note screen. Users can enter note details.
 */
public class AddEditNoteFragment extends Fragment {

    public static final String ARGUMENT_EDIT_NOTE_ID = "EDIT_NOTE_ID";

    public final static int PICK_PHOTO_CODE = 1046;

    private AddEditNoteViewModel mViewModel;

    public AddEditNoteFragment() {
        // Required empty public constructor
    }

    public static AddEditNoteFragment newInstance() {
        return new AddEditNoteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentAddeditNoteBinding binding =
                FragmentAddeditNoteBinding.inflate(inflater, container, false);

        mViewModel = AddEditNoteActivity.obtainViewModel(getActivity());

        binding.setViewmodel(mViewModel);

        setHasOptionsMenu(true);
        setRetainInstance(false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupFab();

        setupActionBar();

        setupSnackbar();

        loadData();

        // Trigger gallery selection for a photo
        setupImagePicker();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_PHOTO_CODE && data != null) {
            Uri photoUri = data.getData();
            Snackbar.make(getView(), photoUri.toString(), Snackbar.LENGTH_LONG);
            // Do something with the photo based on Uri
            //Bitmap selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            // Load the selected image into a preview
            //ImageView ivPreview = (ImageView) findViewById(R.id.ivPreview);
            //ivPreview.setImageBitmap(selectedImage);
        }
    }

    private void setupImagePicker() {
        // Create intent for picking a photo from the gallery
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Bring up gallery to select a photo
            startActivityForResult(intent, PICK_PHOTO_CODE);
        }
    }

    private void loadData() {
        // Add or edit an existing note?
        if (getArguments() != null) {
            mViewModel.start(getArguments().getString(ARGUMENT_EDIT_NOTE_ID));
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
