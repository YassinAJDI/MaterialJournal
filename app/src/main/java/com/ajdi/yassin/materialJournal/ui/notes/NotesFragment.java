package com.ajdi.yassin.materialJournal.ui.notes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.materialJournal.R;
import com.ajdi.yassin.materialJournal.data.model.Note;
import com.ajdi.yassin.materialJournal.databinding.FragmentNotesBinding;
import com.ajdi.yassin.materialJournal.ui.login.AuthUiActivity;
import com.ajdi.yassin.materialJournal.utils.SnackbarMessage;
import com.ajdi.yassin.materialJournal.utils.SnackbarUtils;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import timber.log.Timber;

/**
 * Display a grid of {@link Note}s. User can choose to view all, favorite or trashed notes.
 */
public class NotesFragment extends Fragment {

    private NotesViewModel mNotesViewModel;

    private NotesAdapter mNotesAdapter;

    private FragmentNotesBinding mFragmentNotesBinding;

    public NotesFragment() {
        // Required empty public constructor
    }

    public static NotesFragment newInstance() {
        return new NotesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentNotesBinding = FragmentNotesBinding.inflate(inflater, container,
                false);

        mNotesViewModel = NotesActivity.obtainViewModel(getActivity());

        mFragmentNotesBinding.setViewmodel(mNotesViewModel);
        //mFragmentNotesBinding.setLifecycleOwner(this);

        setHasOptionsMenu(true);

        return mFragmentNotesBinding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout){
            Timber.d("log out");
            AuthUI.getInstance()
                    .signOut(getActivity())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startActivity(AuthUiActivity.createIntent(getActivity()));
                                getActivity().finish();
                            } else {
                               // Log.w(TAG, "signOut:failure", task.getException());
                                //showSnackbar(R.string.sign_out_failed);
                            }
                        }
                    });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupFab();

        setupListAdapter();

        setupSnackbar();

        setupRefreshLayout();
    }

    @Override
    public void onStart() {
        super.onStart();
        mNotesViewModel.start();
    }

    private void setupListAdapter() {
        Timber.d("setupListAdapter");
        RecyclerView recyclerView = mFragmentNotesBinding.recyclerNoteList;
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false));

        mNotesAdapter = new NotesAdapter(getActivity(), new ArrayList<Note>(0), mNotesViewModel);

        recyclerView.setAdapter(mNotesAdapter);
    }

    private void setupRefreshLayout() {
        final SwipeRefreshLayout swipeRefreshLayout = mFragmentNotesBinding.refreshLayout;
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
    }

    private void setupFab() {
        if (getActivity() == null)
            return;

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_add_note);

        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotesViewModel.addNewNote();
            }
        });
    }

    private void setupSnackbar() {
        mNotesViewModel.getSnackbarMessage().observe(this, new SnackbarMessage.SnackbarObserver() {
            @Override
            public void onNewMessage(@StringRes int snackbarMessageResourceId) {
                SnackbarUtils.showSnackbar(getView(), getString(snackbarMessageResourceId));
            }
        });
    }
}
