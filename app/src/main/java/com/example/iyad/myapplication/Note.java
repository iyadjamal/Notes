package com.example.iyad.myapplication;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Note {
    Activity activity;
    private int id;
    private String name = "1stNote";
    private boolean saved;
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            saved = false;
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public Note(Activity activity, String name) {
        this.activity = activity;
        addButtonListener();
        loadSavedFile();
        addOnChangeTextListener();
    }

    public boolean loadSavedFile() {
        EditText Notetxt = (EditText) activity.findViewById(R.id.note);
        try {
            FileInputStream Fis = activity.openFileInput(name);
            BufferedReader Dis = new BufferedReader(new InputStreamReader(new DataInputStream(Fis)));
            String LoadedText, s = "";
            while ((LoadedText = Dis.readLine()) != null) {
                s += LoadedText + "\n";
            }
            Dis.close();
            Fis.close();
            Notetxt.setText(s);
            this.saved = true;
        } catch (Exception e) {
            this.saved = false;
            return false;
        }
        return true;
    }

    public void addOnChangeTextListener() {
        EditText Notetxt = (EditText) activity.findViewById(R.id.note);
        Notetxt.addTextChangedListener(watcher);
    }

    public void addButtonListener() {
        Button Savebtn = (Button) activity.findViewById(R.id.save);
        Savebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (saved) {
                    Toast.makeText(activity,R.string.already_saved,Toast.LENGTH_LONG).show();
                    return;
                }
                EditText Notetxt = (EditText) activity.findViewById(R.id.note);
                String SavedNote = Notetxt.getText().toString();
                try {
                    FileOutputStream Fos = activity.openFileOutput(name, Context.MODE_PRIVATE);
                    Fos.write(SavedNote.getBytes());
                    Fos.close();
                    saved = true;
                    Toast.makeText(activity,R.string.saved_successfully,Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    saved = false;
                    Toast.makeText(activity,R.string.save_error,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void release() {
        EditText Notetxt = (EditText) activity.findViewById(R.id.note);
        Notetxt.removeTextChangedListener(watcher);
    }
}
