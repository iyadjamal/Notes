package com.example.iyad.myapplication;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Note note = new Note();
        note.addButtonListener(this);
        note.loadSavedFile(this);
    }
}
