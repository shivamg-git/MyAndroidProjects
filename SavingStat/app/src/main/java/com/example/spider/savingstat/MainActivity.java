package com.example.spider.savingstat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.PersistableBundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    EditText note;
    private static final int SETTINGS_INFO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        note = (EditText) findViewById(R.id.editText);

        if (savedInstanceState != null) {
            String tempNote = savedInstanceState.getString("NOTE");
            note.setText(tempNote);
        }

        String tempNote = getPreferences(Context.MODE_PRIVATE).getString("NOTE", "EMPTY");
        if (tempNote != "EMPTY") {
            note.setText(tempNote);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString("NOTE", note.getText().toString());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveNote();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                openSettingDialog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openSettingDialog() {
        Intent intentPreferences = new Intent(getApplicationContext(), Settings.class);
        startActivityForResult(intentPreferences, SETTINGS_INFO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SETTINGS_INFO){
           updateNoteText();
        }
    }

    private void updateNoteText() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if(sharedPreferences.getBoolean("pref_text_bold", false)){
            note.setTypeface(null, Typeface.BOLD_ITALIC);
        } else {
            note.setTypeface(null, Typeface.NORMAL);
        }

        String textSizeStr = sharedPreferences.getString("pref_text_size", "16");
       float textSizeFloat = Float.parseFloat(textSizeStr);
        note.setTextSize(textSizeFloat);
    }

    private void saveNote() {
        SharedPreferences.Editor sp = getPreferences(Context.MODE_PRIVATE).edit();
        sp.putString("NOTE", note.getText().toString());
        sp.apply();
    }
}
