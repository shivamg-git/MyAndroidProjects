package com.example.spider007.trsvisapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SQLiteExample extends AppCompatActivity implements View.OnClickListener {

    EditText name, hotness;
    Button bUpdateSQL, bView;

    EditText rowId;
    Button bGetInfo, bEdit, bDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_example);
        initialize();
    }

    private void initialize() {
        name = (EditText) findViewById(R.id.etName);
        hotness = (EditText) findViewById(R.id.etHotness);
        bUpdateSQL = (Button) findViewById(R.id.bSUpdateInSql);
        bView = (Button) findViewById(R.id.bView);
        bUpdateSQL.setOnClickListener(this);
        bView.setOnClickListener(this);

        rowId = (EditText) findViewById(R.id.etRowId);
        bGetInfo = (Button) findViewById(R.id.bGetInfo);
        bEdit = (Button) findViewById(R.id.bEdit);
        bDelete = (Button) findViewById(R.id.bDelete);
        bGetInfo.setOnClickListener(this);
        bEdit.setOnClickListener(this);
        bDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bSUpdateInSql:
                String name;
                int hotness;
                boolean isCreated;
                try {
                    name = this.name.getText().toString();
                    hotness = Integer.parseInt(this.hotness.getText().toString());
                    isCreated = false;
                }catch (Exception e){
                    e.printStackTrace();
                    break;
                }

                try {
                    isCreated = true;
                    HotOrNot entry = new HotOrNot(SQLiteExample.this);
                    entry.open();
                    if (entry.createEntry(name, hotness) == -1) isCreated = false;
                    entry.close();

                } catch (Exception e) {
                    e.printStackTrace();
                    isCreated = false;
                    Dialog d = new Dialog(this);
                    d.setTitle("Failed :( ");
                    TextView tv = new TextView(this);
                    tv.setText(e.toString());
                    tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    tv.setPadding(50, 0, 0, 0);
                    d.setContentView(tv);
                    d.show();
                } finally {
                    if (isCreated) {
                        Dialog d = new Dialog(this);
                        d.setTitle("Success!");
                        TextView tv = new TextView(this);
                        tv.setText("Entry created!");
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        tv.setPadding(50, 0, 0, 0);
                        d.setContentView(tv);
                        d.show();
                    }
                }
                break;

            case R.id.bView:
                Intent i = new Intent("com.example.spider007.SQLView");
                startActivity(i);
                break;

            case R.id.bGetInfo:
                try {
                    String s = rowId.getText().toString();
                    long l = Long.parseLong(s);
                    HotOrNot hon = new HotOrNot(this);
                    hon.open();
                    String returnedName = hon.getName(l);
                    Integer returnedHotness = hon.getHotness(l);
                    hon.close();

                    this.name.setText(returnedName);
                    this.hotness.setText(returnedHotness.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Dialog d = new Dialog(this);
                    d.setTitle("Failed :( ");
                    TextView tv = new TextView(this);
                    tv.setText(e.toString());
                    tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    tv.setPadding(50, 0, 0, 0);
                    d.setContentView(tv);
                    d.show();
                }

                break;
            case R.id.bEdit:

                try {
                    String s1 = rowId.getText().toString();
                    long l1 = Long.parseLong(s1);
                    String n1 = this.name.getText().toString();
                    String h1 = this.hotness.getText().toString();

                    HotOrNot hon1 = new HotOrNot(this);
                    hon1.open();
                    hon1.updateEntry(l1, n1, h1);
                    hon1.close();
                    Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Dialog d = new Dialog(this);
                    d.setTitle("Failed :( ");
                    TextView tv = new TextView(this);
                    tv.setText(e.toString());
                    tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    tv.setPadding(50, 0, 0, 0);
                    d.setContentView(tv);
                    d.show();
                }

                break;
            case R.id.bDelete:
                try {
                    String s2 = rowId.getText().toString();
                    long l2 = Long.parseLong(s2);
                    HotOrNot hon2 = new HotOrNot(this);
                    hon2.open();
                    hon2.deleteEntry(l2);
                    hon2.close();
                    Toast.makeText(this, "Deleted", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                    Dialog d = new Dialog(this);
                    d.setTitle("Failed :( ");
                    TextView tv = new TextView(this);
                    tv.setText(e.toString());
                    tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    tv.setPadding(50, 0, 0, 0);
                    d.setContentView(tv);
                    d.show();
                }
                break;
        }
    }
}
