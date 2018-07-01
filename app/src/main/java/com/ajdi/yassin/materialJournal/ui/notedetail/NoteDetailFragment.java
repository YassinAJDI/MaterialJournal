package com.ajdi.yassin.materialJournal.ui.notedetail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.materialJournal.R;
import com.ajdi.yassin.materialJournal.databinding.FragmentNoteDetailBinding;
import com.ajdi.yassin.materialJournal.utils.GlideApp;
import com.ajdi.yassin.materialJournal.utils.UiUtils;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import timber.log.Timber;

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

        mNoteDetailViewModel.getNoteLoadedCommand().observeEvent(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                loadData();
            }
        });

        mNoteDetailViewModel.getmNoteStaredCommand().observeEvent(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                recreateOptionsMenu();
                showMessage("Note added to your favorites successfully");
            }
        });

        mNoteDetailViewModel.getmNoteUnstaredCommand().observeEvent(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                recreateOptionsMenu();
                showMessage("Note removed from your favorites successfully");
            }
        });
    }

    private void recreateOptionsMenu() {
        if (getActivity() != null) {
            Timber.d("recreateOptionsMenu");
            getActivity().invalidateOptionsMenu();
        }
    }

    private void loadData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // show user image
        GlideApp.with(this)
                .load(user.getPhotoUrl())
                .apply(new RequestOptions().circleCrop())
                .into(mBinding.imagePublisherIcon);

        mBinding.textPublisherName.setText(user.getDisplayName());

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.findItem(R.id.action_star);
        if (menuItem != null && mNoteDetailViewModel.note.get() != null) {
            if (mNoteDetailViewModel.note.get().isStar())
                menuItem.setTitle("Delete from favorites").setIcon(R.drawable.ic_star_black_24dp);
            else
                menuItem.setTitle("Add to favorites").setIcon(R.drawable.ic_star_border_black_24dp);

            UiUtils.tintMenuIcon(getActivity(), menuItem, R.color.md_white_1000);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share: {
                String title = mNoteDetailViewModel.note.get().getTitle();
                String text = mNoteDetailViewModel.note.get().getContent();
                UiUtils.fireShareIntent(getActivity(), title, text);
                return true;
            }
            case R.id.action_star: {

                if (!mNoteDetailViewModel.note.get().isStar()) {
                    Timber.d("isStar: " + mNoteDetailViewModel.note.get().isStar());
                    mNoteDetailViewModel.starNote();
                } else {
                    mNoteDetailViewModel.unstarNote();
                }
                return true;
            }
//            case R.id.action_copy_link: {
//                mPresenter.copyLink();
//                return true;
//            }
            default:
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
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

    private void showMessage(String message) {
        if (getView() != null)
            Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}
