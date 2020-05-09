package com.nanasdev.lightnote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements NoteOpener {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.notesView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        refreshNoteList();


    }

    public void refreshNoteList() {
        final String[] files = this.fileList();
        Arrays.sort(files, Collections.reverseOrder());
        NotesAdapter mAdapter = new NotesAdapter(files, this);
        recyclerView.setAdapter(mAdapter);
    }

    public void goTo(View view) {
        Intent i = new Intent(this, NoteActivity.class);
        startActivity(i);
        finish();
    }


    public void openNote(String filename) {
        Intent i = new Intent(this, NoteActivity.class);
        Gson gson = new Gson();
        try {
            FileInputStream fis = this.openFileInput(filename);
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fis, StandardCharsets.UTF_8);
            JsonReader reader = new JsonReader(inputStreamReader);
            Note note = gson.fromJson(reader, Note.class);
            if (note != null) {
                i.putExtra("note", note);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        startActivity(i);
        finish();
    }
}