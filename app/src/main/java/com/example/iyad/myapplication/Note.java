package com.example.iyad.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Note {
    int id;
    int name;

    public boolean loadSavedFile(final Activity activity) {
        EditText Notetxt = (EditText) activity.findViewById(R.id.note);
        try {
            FileInputStream Fis = activity.openFileInput("1stNote");
            BufferedReader Dis = new BufferedReader(new InputStreamReader(new DataInputStream(Fis)));
            String LoadedText, s = "";
            while ((LoadedText = Dis.readLine()) != null) {
                s += LoadedText + "\n";
            }
            Dis.close();
            Fis.close();
            Notetxt.setText(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void addButtonListener(final Activity activity) {
        Button Savebtn = (Button) activity.findViewById(R.id.save);
        Savebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText Notetxt = (EditText) activity.findViewById(R.id.note);
                String SavedNote = Notetxt.getText().toString();
                try {
                    FileOutputStream Fos = activity.openFileOutput("1stNote", Context.MODE_PRIVATE);
                    Fos.write(SavedNote.getBytes());
                    Fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
