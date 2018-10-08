package com.ajdi.yassin.materialjournal.ui.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.materialjournal.R;
import com.ajdi.yassin.materialjournal.data.model.Note;
import com.ajdi.yassin.materialjournal.databinding.ItemNoteBinding;
import com.ajdi.yassin.materialjournal.utils.Constants;
import com.ajdi.yassin.materialjournal.utils.GlideApp;
import com.ajdi.yassin.materialjournal.utils.TimeUtils;
import com.ajdi.yassin.materialjournal.utils.UiUtils;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final NotesViewModel mNotesViewModel;

    private List<Note> mNotes;

    private Context mContext;

    FirebaseUser mUser;

    public NotesAdapter(Context context, List<Note> notes, NotesViewModel notesViewModel) {
        mNotesViewModel = notesViewModel;
        mContext = context;
        replaceData(notes);
        mUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Create the binding
        ItemNoteBinding binding =
                ItemNoteBinding.inflate(layoutInflater, parent, false);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Note note = mNotes.get(position);
        Timber.d(note.toString());

        NoteViewHolder noteViewHolder = (NoteViewHolder) holder;

        NoteItemUserActionsListener userActionsListener = new NoteItemUserActionsListener() {
            @Override
            public void onNoteClicked(Note note) {
                mNotesViewModel.getOpenNoteEvent().setValue(String.valueOf(note.getId()));
            }
        };

        noteViewHolder.binding.setListener(userActionsListener);
        noteViewHolder.binding.setNote(note);

        GlideApp.with(mContext)
                .load("https://static-cdn.123rf.com/images/v5/index-thumbnail/84170952-b.jpg")
                .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(16)))
                .into(noteViewHolder.binding.imageNote);

        GlideApp.with(mContext)
                .load(mUser.getPhotoUrl())
                .apply(new RequestOptions().circleCrop())
                .into(noteViewHolder.binding.imageUserIcon);

        String timeAgo = (String) TimeUtils.getTimeAgo(note.getDate(), mContext);
        noteViewHolder.binding.textPublisherName.setText(mUser.getDisplayName());

        noteViewHolder.binding.textTime.setText(timeAgo);

        // card share
        DrawableCompat.setTintList(mContext.getResources().getDrawable(
                R.drawable.ic_share_black_24dp), null);
        noteViewHolder.binding.cardActionShareButton.setImageResource(
                R.drawable.ic_share_black_24dp);
        noteViewHolder.binding.cardActionShareButton.setImageAlpha(Constants.ALPHA_VALUE);
        noteViewHolder.binding.cardActionShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UiUtils.fireShareIntent(mContext, note.getTitle(), note.getContent());
            }
        });

        // card add to favorites button
        DrawableCompat.setTintList(mContext.getResources().getDrawable(
                R.drawable.ic_star_black_24dp), null);
        DrawableCompat.setTintList(mContext.getResources().getDrawable(
                R.drawable.ic_star_border_black_24dp), null);

        noteViewHolder.binding.cardActionStarButton.setImageResource(
                note.isStar() ? R.drawable.ic_star_black_24dp :
                        R.drawable.ic_star_border_black_24dp);
        noteViewHolder.binding.cardActionStarButton.setImageAlpha(Constants.ALPHA_VALUE);
        noteViewHolder.binding.cardActionStarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!note.isStar()) {
                    mNotesViewModel.starNote(note);
                } else {
                    mNotesViewModel.instarNote(note);
                }
            }
        });


        noteViewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mNotes != null ? mNotes.size() : 0;
    }

    public void replaceData(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }

    /**
     * The {@link NoteViewHolder} class.
     * Provides a binding reference to each view in the note cardView.
     */
    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private final ItemNoteBinding binding;

        public NoteViewHolder(final ItemNoteBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
