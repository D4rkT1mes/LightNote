package com.nanasdev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;

public class Note extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }

    public void exitNote(View v) {
        PopupMenu p = new PopupMenu(this, v);
        p.setOnMenuItemClickListener(this);
        p.inflate(R.menu.exit_note_popup);
        p.show();
    }


//    mSubmitButton.setOnClickListener(new View.OnClickListener{
//        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
//        String fileName = "Data.csv";
//        String filePath = baseDir + File.separator + fileName;
//        File f = new File(filePath );
//        CSVWriter writer;
//        // File exist
//        if(f.exists() && !f.isDirectory()){
//            mFileWriter = new FileWriter(com.nanasdev.myapplication., true);
//            writer = new CSVWriter(mFileWriter);
//        }
//        else {
//            writer = new CSVWriter(new FileWriter(filePath));
//        }
//        String[] data = getDataStringArray();
//
//        writer.writeNext(data);
//
//        writer.close();
//    });

    @Override
    public boolean onMenuItemClick(MenuItem i) {
        switch (i.getItemId()) {
            case R.id.save:
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.exit_nosave:
                Intent a = new Intent(this, MainActivity.class);
                startActivity(a);
                return true;
            case R.id.exit_save:
                Toast.makeText(this, "Saved. Leaving...", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }


   

}
