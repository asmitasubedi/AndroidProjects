package com.example.asmitas.workshop5;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.MalformedJsonException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button fetch_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetch_data = (Button) findViewById(R.id.fetch_button);
        fetch_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FetchServerData("This is a test").execute();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case (R.id.fetch):
                new FetchServerData("From Option Menu").execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class FetchServerData extends AsyncTask<String, Void, JSONArray>{

        ProgressDialog progressDialog;
        HttpURLConnection urlConnection;
        BufferedReader reader;

        public FetchServerData(String test){
            System.out.println("test =" + test);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please Wait..");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected JSONArray doInBackground(String... params) {
            JSONArray jsonArray = null;
            try {
                URL url = new URL("https://api.github.com/users/mralexgray/repos");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                //Read the input stream into string
                InputStream inputStream = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer = new StringBuffer();
                String line;
                while((line = reader.readLine())!=null){
                    System.out.println(">>>>>>>");
                    buffer.append(line);
                }
                System.out.println("buffer = " +buffer.toString());
                jsonArray = new JSONArray(buffer.toString());

            }catch (Exception e){
                e.printStackTrace();
            }
            return jsonArray;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
