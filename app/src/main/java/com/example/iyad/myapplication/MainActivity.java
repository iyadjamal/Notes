package com.example.iyad.myapplication;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        note = new Note(this, "hi");
    }

    @Override
    protected void onPause() {
        super.onPause();
        note.release();
    }
}
