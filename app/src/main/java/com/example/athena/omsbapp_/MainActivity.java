package com.example.athena.omsbapp_;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {
    ImageView imgMovie;
    EditText editTitle;
    TextView textTitle;
    TextView textPlot;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgMovie = (ImageView) findViewById(R.id.imgMovie);
        editTitle = (EditText) findViewById(R.id.editTitle);
        textTitle = (TextView) findViewById(R.id.textTitle);
        textPlot = (TextView) findViewById(R.id.textPlot);
    }

    public void sample(View v){

    }

    public void search(View v){

    }

    //region Download Sample
    private class DownloadFromOpenWeather extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                String result = Util.webToString(urlConnection.getInputStream());

                return result;
            } catch (Exception e) {
                Log.e("Error", "Error ", e);
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Movie movie= Util.JSONtoOmdb(s);
            if(movie != null){
                textTitle.setText(movie.getTitle());
                textPlot.setText(movie.getPlot());
                //imgMovie.setImageBitmap()
            }
        }
    }
    //endregion

    //region Search

    //endregion
}
