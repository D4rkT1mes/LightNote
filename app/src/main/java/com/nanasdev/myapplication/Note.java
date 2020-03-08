package com.nanasdev.myapplication;

import android.annotation.SuppressLint;
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


public class Note extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    EditText headline;
    EditText maintext;
    Button save;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        //define buttons from note_header.xml lol
        headline = findViewById(R.id.headline);
        maintext = findViewById(R.id.maintext);
        //asdkadsapf
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
