package com.nanasdev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Note extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }

    public void exitNote(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.exit_note_popup, popup.getMenu());
        popup.show();
    }

    public void goToMAIN(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }



}
