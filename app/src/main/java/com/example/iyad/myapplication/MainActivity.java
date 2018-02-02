package com.example.iyad.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButtonListener();
        loadSavedFile();
    }
    private void loadSavedFile()
    {
        EditText Notetxt = (EditText)findViewById(R.id.note);
        try {
            FileInputStream Fis = openFileInput("1stNote");
            BufferedReader Dis = new BufferedReader(new InputStreamReader(new DataInputStream(Fis)));
            String LoadedText,s="";
            while ((LoadedText = Dis.readLine())!=null)
            {
                s+=LoadedText+"\n";
            }
            Dis.close();
            Fis.close();
            Notetxt.setText(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addButtonListener()
    {
        Button Savebtn=(Button) findViewById(R.id.save);
        Savebtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                EditText Notetxt = (EditText)findViewById(R.id.note);
                String SavedNote=Notetxt.getText().toString();
                try {
                    FileOutputStream Fos=openFileOutput("1stNote", Context.MODE_PRIVATE);
                    Fos.write(SavedNote.getBytes());
                    Fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
