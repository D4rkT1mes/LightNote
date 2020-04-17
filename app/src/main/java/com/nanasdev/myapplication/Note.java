package com.nanasdev.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;


public class Note extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    EditText headline;
    com.nanasdev.myapplication.LinedEditText maintext;
    private NoteBean noteBean;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        //define buttons from note_header.xml
        headline = findViewById(R.id.headline);
        maintext = findViewById(R.id.maintext);

        noteBean = (NoteBean) getIntent().getSerializableExtra("noteBean");
        if(noteBean != null){
            headline.setText(noteBean.getHeader());
            maintext.setText(noteBean.getBody());
        }
    }



    public void exitNote(View v) {
        PopupMenu p = new PopupMenu(this, v);
        p.setOnMenuItemClickListener(this);
        p.inflate(R.menu.exit_note_popup);
        p.show();
    }


    public void saveNote(View view) {
//        EditText headline = findViewById(R.id.headline);
//        com.nanasdev.myapplication.LinedEditText maintext = findViewById(R.id.maintext);
        if(noteBean == null) {
            noteBean = new NoteBean(new Date(), headline.getText().toString(), maintext.getText().toString());
            saveToFile(view, "" + (new Date()).toString(), noteBean);
        } else {
            noteBean.setHeader(headline.getText().toString());
            noteBean.setBody(maintext.getText().toString());
            saveToFile(view, noteBean.getDate().toString(), noteBean);
        }

    }

    public void saveToFile(View view, String filename, NoteBean note){
        //To allow other apps to access files stored in this directory within internal storage, use a FileProvider with the FLAG_GRANT_READ_URI_PERMISSION attribute.
        try (FileOutputStream fos = this.openFileOutput(filename, Context.MODE_PRIVATE)) {
            System.out.println("!!!!!!!!!!!!!!!!!"+fos.getFD());
            Gson gson = new Gson();
            String fileContents = gson.toJson(note);
            fos.write(fileContents.getBytes("UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public boolean onMenuItemClick(MenuItem i) {
        switch (i.getItemId()) {
            case R.id.save:
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.exit_nosave:
                Intent a = new Intent(this, MainActivity.class);
                startActivity(a);
                finish();
                return true;
            case R.id.exit_save:
                Toast.makeText(this, "Saved. Leaving...", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }


}
