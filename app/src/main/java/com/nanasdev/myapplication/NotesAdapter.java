package com.nanasdev.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

    public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

        private String[] files;
    private NoteOpener noteOpener;

    public NotesAdapter(String[] files, NoteOpener noteOpener) {
        this.files = files;
        this.noteOpener = noteOpener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        NotesViewHolder vh = new NotesViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        final String fileName = files[position];
        holder.textView.setText(fileName);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteOpener.openNote(fileName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return files.length;
    }


    public static class NotesViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public NotesViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }
}
