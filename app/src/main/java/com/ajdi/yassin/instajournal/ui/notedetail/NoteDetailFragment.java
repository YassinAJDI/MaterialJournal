package com.ajdi.yassin.instajournal.ui.notedetail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.instajournal.R;
import com.ajdi.yassin.instajournal.databinding.FragmentNoteDetailBinding;
import com.ajdi.yassin.instajournal.utils.GlideApp;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

public class NoteDetailFragment extends Fragment {

    public static final String ARGUMENT_NOTE_ID = "NOTE_ID";

    public static final int REQUEST_EDIT_NOTE = 1;

    private NoteDetailViewModel mNoteDetailViewModel;

    private FragmentNoteDetailBinding mBinding;

    public NoteDetailFragment() {
        // Required empty public constructor
    }

    public static NoteDetailFragment newInstance(String noteId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_NOTE_ID, noteId);
        NoteDetailFragment fragment = new NoteDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentNoteDetailBinding.
                inflate(inflater, container, false);

        mNoteDetailViewModel = NoteDetailActivity.obtainViewModel(getActivity());

        mBinding.setViewmodel(mNoteDetailViewModel);

        mBinding.setLifecycleOwner(this);

        setHasOptionsMenu(true);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupFab();

        setupSnackbar();

        mNoteDetailViewModel.getNoteLoadedCommand().observeEvent(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                loadData();
            }
        });
    }

    private void loadData() {

        // show user image
        GlideApp.with(this)
                .load("https://lh3.googleusercontent.com/-xT9nbe3isBs/WxNLkWcpSHI/AAAAAAAAAic/pulWUZTPWooItSTuxghGgjt9dhV2kzf9gCEwYBhgL/w140-h140-p/4711f20662911d1ff51216d692c1354025357acf_hq.jpg")
                .apply(new RequestOptions().circleCrop())
                .into(mBinding.imagePublisherIcon);

        mBinding.textPublisherName.setText("Yassin AJDI");

        // show note image
        //String image = mNoteDetailViewModel.note.get().getImage();
        GlideApp.with(this)
                .load("")
                .into(mBinding.imageArticleImage);


    }

    @Override
    public void onResume() {
        super.onResume();
        mNoteDetailViewModel.start(getArguments().getString(ARGUMENT_NOTE_ID));
    }

    private void setupFab() {
        FloatingActionButton fab = getActivity().findViewById(R.id.fab_edit_note);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNoteDetailViewModel.editNote();
            }
        });
    }

    private void setupSnackbar() {

    }

}
