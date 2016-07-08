package com.example.spider.derek2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cloud:
                Toast.makeText(this, "Cloud", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                MyDialogFragment myDialog = new MyDialogFragment();
                myDialog.show(getFragmentManager(), "Settings");
                break;
            case R.id.exit:
                finish();
                break;
        }
        return true;
    }

    public void goGetName(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        final int result = 1;
        intent.putExtra("ActivityName", "Main");
        startActivityForResult(intent, result);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            TextView tv = (TextView) findViewById(R.id.textView2);
//            String name = data.getExtras().getString("Name");
            Human human = (Human) data.getExtras().getSerializable("HUMAN");
            String name = human.getName();
            String message = "Your Name is "+name;
            try {
                tv.setText(message);
            }
            catch (NullPointerException ex){
             ex.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
