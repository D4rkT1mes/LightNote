package com.nanasdev.lightnote;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import java.text.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;


public class NoteActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private EditText headline;
    private com.nanasdev.lightnote.LinedEditText maintext;
    private Note note;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        headline = findViewById(R.id.headline);
        maintext = findViewById(R.id.maintext);

        note = (Note) getIntent().getSerializableExtra("note");
        if (note != null) {
            headline.setText(note.getHeader());
            maintext.setText(note.getBody());
        }
    }


    private void goToMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
       if (isModified(note)) {
            String ifLeave = getString(R.string.if_leave);
            String wannaSave = getString(R.string.wanna_save);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(ifLeave);
            builder.setMessage(wannaSave);
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    saveNote(maintext);
                    goToMain(headline);
                }
            });
            builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    goToMain(headline);
                }
            });
            builder.show();
        } else {
            goToMain(headline);
        }
    }


    public void saveNote(final View view) {
//        EditText headline = findViewById(R.id.headline);
//        com.nanasdev.myapplication.LinedEditText maintext = findViewById(R.id.maintext);
        String onEmpty = getString(R.string.onEmpty);
        String notModified = getString(R.string.not_modified);
        String saved = getString(R.string.saved);
        String deleted = getString(R.string.deleted);
        if (note == null) {
            note = new Note(new Date(), headline.getText().toString(), maintext.getText().toString());

            if (isNoteEmpty(note)) {
                Toast.makeText(this, onEmpty, Toast.LENGTH_SHORT).show();
                return;
            }

            saveToFile(view, DateFormat.getDateTimeInstance().format(new Date()) + "_" + headline.getText().toString(), note);
            Toast.makeText(this, saved, Toast.LENGTH_SHORT).show();

        } else {
            if (!isModified(note)) {
                Toast.makeText(this, notModified, Toast.LENGTH_SHORT).show();
                return;
            }
            String oldFileName = note.getModifiedDateString() + "_" + note.getHeader();
            if(!note.getHeader().equalsIgnoreCase(headline.getText().toString())){
                this.deleteFile(oldFileName);
            } else if(note.getHeader().equalsIgnoreCase(headline.getText().toString()) && !note.getBody().equalsIgnoreCase(maintext.getText().toString())){
                this.deleteFile(oldFileName);
            } else {
                Toast.makeText(this, deleted, Toast.LENGTH_SHORT).show();
            }
// || )
            note.setHeader(headline.getText().toString());
            note.setBody(maintext.getText().toString());
            note.setModifiedDate(new Date());

            if (isNoteEmpty(note)) {
                Toast.makeText(this, onEmpty, Toast.LENGTH_SHORT).show();
                return;
            }

            saveToFile(view, note.getModifiedDateString() + "_" + headline.getText().toString(), note);
            Toast.makeText(this, saved, Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isNoteEmpty(Note note) {
        return note.getBody().equals("") || note.getHeader().equals("");
    }

    private boolean isModified(Note note) {
        if(note == null){
            note = new Note();
        }
        return   (!note.getHeader().equalsIgnoreCase(headline.getText().toString()) || !note.getBody().equalsIgnoreCase(maintext.getText().toString()));
    }


    private void saveToFile(View view, String filename, Note note) {
        try (FileOutputStream fos = this.openFileOutput(filename, Context.MODE_PRIVATE)) {
            // System.out.println("!!!!!!!!!!!!!!!!!"+fos.getFD());
            Gson gson = new Gson();
            String fileContents = gson.toJson(note);
            fos.write(fileContents.getBytes(StandardCharsets.UTF_8));
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

    public void shareNote (View view) {
        String share = headline.getText().toString() + "\n" + maintext.getText().toString();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, share);
        startActivity(shareIntent);
    }
}
