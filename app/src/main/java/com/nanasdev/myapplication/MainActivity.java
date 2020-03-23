package com.nanasdev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goTo(View view) {
        Intent i = new Intent(this, Note.class);
        startActivity(i);
        finish();
    }

    //creating file onclick add
    public long createFile(View view, String fileName) {
        try {
            File file = new File("d:/testfiles.txt");
            PrintWriter writer = new PrintWriter(new FileWriter(file));
            writer.printf("%x", 255); //Записываем текст в файл
            writer.close(); // Закрываем файл
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}