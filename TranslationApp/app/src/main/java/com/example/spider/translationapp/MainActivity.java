package com.example.spider.translationapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends Activity implements View.OnClickListener {

    EditText translateEditText;
    Button translateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        translateButton = (Button) findViewById(R.id.button);
        translateEditText = (EditText) findViewById(R.id.editText);
        translateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!isEditTextEmpty(translateEditText)) {
            Toast.makeText(this, "Translating...", Toast.LENGTH_LONG).show();
            String[] params = {translateEditText.getText().toString()};
            new SaveTheFeed().execute(params);
        } else {
            Toast.makeText(this, "Write Something First...", Toast.LENGTH_SHORT).show();
        }

    }

    // Utility Methods
    private boolean isEditTextEmpty(EditText translateEditText) {
        return translateEditText.getText().toString().trim().length() == 0;
    }

    // Async Task
    class SaveTheFeed extends AsyncTask<String, Void, Void> {

        String jsonString = "";
        String result = "";

        @Override
        protected Void doInBackground(String... params) {

            String wordstoConvert = params[0];
            wordstoConvert = wordstoConvert.replace(" ", "+");

            String url = "http://newjustin.com/translateit.php?action=translations&english_words=" + wordstoConvert;
            HTTP http = new HTTP();
            try {
                jsonString = http.run(url);
                try {
                    JSONObject jObject = new JSONObject(jsonString);
                    JSONArray jArray = jObject.getJSONArray("translations");
                    outputTranslations(jArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        private void outputTranslations(JSONArray jArray) {
            // Used to get the translation using a key
            String[] languages = {"arabic", "chinese", "danish", "dutch",
                    "french", "german", "italian", "portuguese", "russian",
                    "spanish"};

            // Save all the translations by getting them with the key
            try {

                for (int i = 0; i < jArray.length(); i++) {

                    JSONObject translationObject =
                            jArray.getJSONObject(i);

                    result = result + languages[i] + " : " +
                            translationObject.getString(languages[i]) + "\n";

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView translationTextView = (TextView) findViewById(R.id.translationTextView);

            translationTextView.setText(result);
        }
    }
}
