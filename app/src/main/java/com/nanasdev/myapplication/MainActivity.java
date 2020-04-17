package com.nanasdev.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NoteOpener {
    private RecyclerView recyclerView;
    private NotesAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.notesView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final String[] files = this.fileList();

        // specify an adapter (see also next example)
        mAdapter = new NotesAdapter(files, this);
        recyclerView.setAdapter(mAdapter);
    }

    public void goTo(View view) {
        Intent i = new Intent(this, Note.class);
        startActivity(i);
        finish();
    }

    @Override
    public void openNote(String filename) {
        Intent i = new Intent(this, Note.class);
        Gson gson = new Gson();
        try {
            FileInputStream fis = this.openFileInput(filename);
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fis, StandardCharsets.UTF_8);
            JsonReader reader = new JsonReader(inputStreamReader);
            NoteBean noteBean = gson.fromJson(reader, NoteBean.class);
            if(noteBean !=null){
                i.putExtra("noteBean", noteBean);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        startActivity(i);
        finish();
    }
}