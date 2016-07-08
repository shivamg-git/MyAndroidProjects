package com.example.spider.dbdemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    SQLiteDatabase contactsDB;

    Button createDBButton, addContactButton, deleteContactButton, getContactsButton,
            deleteDBButton;
    EditText nameEditText, emailEditText, contactListEditText, idEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDBButton = (Button) findViewById(R.id.createDBButton);
        addContactButton = (Button) findViewById(R.id.addContactButton);
        deleteContactButton = (Button) findViewById(R.id.deleteContactButton);
        getContactsButton = (Button) findViewById(R.id.getContactsButton);
        deleteDBButton = (Button) findViewById(R.id.deleteDBButton);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        contactListEditText = (EditText) findViewById(R.id.contactListEditText);
        idEditText = (EditText) findViewById(R.id.idEditText);
    }

    public void createDatabase(View view) {
        try {
            contactsDB = this.openOrCreateDatabase("SpiderDatabase", MODE_PRIVATE, null);
            String query = "CREATE TABLE IF NOT EXISTS USER (id INTEGER PRIMARY KEY, username VARCHAR, email VARCHAR);";
            contactsDB.execSQL(query);
            Log.e("QUERY:", "DONE");

            File db = getApplicationContext().getDatabasePath("SpiderDatabase.db");
            Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
            if (!db.exists())
                toast.setText("Database Created!");
            else
                toast.setText("Database Creation Failed!");
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addContact(View view) {
        String contactName = nameEditText.getText().toString();
        String contactEmail = emailEditText.getText().toString();
        String query = "INSERT INTO USER (username,email) VALUES ('" + contactName + "','" + contactEmail + "');";
        contactsDB.execSQL(query);
    }

    public void getContacts(View view) {
        String query = "SELECT * FROM USER";
        Cursor cursor = contactsDB.rawQuery(query, null);
        int idColumn = cursor.getColumnIndex("id");
        int nameColumn = cursor.getColumnIndex("username");
        int emailColumn = cursor.getColumnIndex("email");

        cursor.moveToFirst();
        String contactList = "";

        if (cursor.getCount() > 0) {
            do {
                String id = cursor.getString(idColumn);
                String name = cursor.getString(nameColumn);
                String email = cursor.getString(emailColumn);

                contactList = contactList + id + " : " + name + " : " + email + "\n";

            } while (cursor.moveToNext());
            contactListEditText.setText(contactList);

        } else {

            Toast.makeText(this, "No Results to Show", Toast.LENGTH_SHORT).show();
            contactListEditText.setText("");

        }
        cursor.close();
    }

    public void deleteContact(View view) {
        String id = idEditText.getText().toString();
        contactsDB.execSQL("DELETE FROM USER WHERE id = " + id + ";");

    }

    public void deleteDatabase(View view) {
        this.deleteDatabase("SpiderDatabase");
    }

    @Override
    protected void onDestroy() {
        contactsDB.close();
        super.onDestroy();
    }
}