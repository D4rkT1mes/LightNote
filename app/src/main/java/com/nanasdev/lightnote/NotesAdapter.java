package com.nanasdev.lightnote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private String[] files;
    private NoteOpener noteOpener;
    private String endName;
    private String endDate;


    public NotesAdapter(String[] files, NoteOpener noteOpener) {
        this.files = files;
        this.noteOpener = noteOpener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        NotesViewHolder vh = new NotesViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        final String fileName = files[position];
        String filen = fileName;
        String del = "_";
        String[] subStr = filen.split(del);
        for(int i = 0; i < subStr.length; i++) {
            endName = subStr[0];
            endDate = subStr[1];
        }
        holder.textHeader.setText(endName);
        holder.textDate.setText(endDate);
        holder.view.setOnClickListener(new View.OnClickListener() {
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
        TextView textHeader;
        TextView textDate;
        View view;

        public NotesViewHolder(View v) {
            super(v);
            view = v;
            textHeader = v.findViewById(R.id.headerView);
            textDate = v.findViewById(R.id.dateView);
        }
    }
}
