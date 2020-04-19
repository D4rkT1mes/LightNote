package com.nanasdev.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


public class NoteActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    EditText headline;
    com.nanasdev.myapplication.LinedEditText maintext;
    private Note note;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        //define buttons from note_header.xml
        headline = findViewById(R.id.headline);
        maintext = findViewById(R.id.maintext);

        note = (Note) getIntent().getSerializableExtra("noteBean");
        if (note != null) {
            headline.setText(note.getHeader());
            maintext.setText(note.getBody());
        }
    }





    public void saveNote(View view) {
//        EditText headline = findViewById(R.id.headline);
//        com.nanasdev.myapplication.LinedEditText maintext = findViewById(R.id.maintext);
        if (note == null) {
            note = new Note(new Date(), headline.getText().toString(), maintext.getText().toString());
            saveToFile(view, "" + (new Date()).toString(), note);
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        } else {
            note.setHeader(headline.getText().toString());
            note.setBody(maintext.getText().toString());
            saveToFile(view, note.getDate().toString(), note);
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        }

    }


    public void saveToFile(View view, String filename, Note note) {
        //To allow other apps to access files stored in this directory within internal storage, use a FileProvider with the FLAG_GRANT_READ_URI_PERMISSION attribute.
        try (FileOutputStream fos = this.openFileOutput(filename, Context.MODE_PRIVATE)) {
            // System.out.println("!!!!!!!!!!!!!!!!!"+fos.getFD());
            Gson gson = new Gson();
            String fileContents = gson.toJson(note);
            fos.write(fileContents.getBytes("UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void exitNote(View v) {
        PopupMenu p = new PopupMenu(this, v);
        p.setOnMenuItemClickListener(this);
        p.inflate(R.menu.exit_note_popup);
        p.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem i) {
        switch (i.getItemId()) {
            case R.id.save:
                saveNote(i.getActionView());
                return true;
            case R.id.exit_nosave:
                Intent a = new Intent(this, MainActivity.class);
                startActivity(a);
                finish();
                return true;
            case R.id.exit_save:
                saveNote(i.getActionView());
                Intent t = new Intent(this, MainActivity.class);
                startActivity(t);
                finish();
                return true;
            default:
                return false;
        }
    }
}
