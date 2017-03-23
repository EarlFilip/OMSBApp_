package com.example.athena.omsbapp_;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {
    ImageView imgMovie;
    EditText editTitle;
    TextView textTitle;
    TextView textPlot;
    String search;

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
        search = "South+Park%3A+Bigger%2C+Longer+%26+Uncut";
        new DownloadFromOMDB().execute();
    }

    public void search(View v){
        search= editTitle.getText().toString();
        search.replaceAll(" ", "+");
        new DownloadFromOMDB().execute();
    }

    //region Image Download
    private class DownloadImageAsync extends AsyncTask<URL, Integer,Bitmap>
    {
        ProgressDialog progress;

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(MainActivity.this,
                    "Downloading image \n",
                    "pls wait a second!");
        }

        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            if(bitmap != null)
            {
                imgMovie.setImageBitmap(bitmap);
            }
            progress.dismiss();
        }

        @Override
        protected Bitmap doInBackground(URL... params)
        {
            Bitmap myBitmap = null;
            try{
                myBitmap = Util.loadImage(params[0]);
            }catch (IOException e)
            {
                Toast.makeText(MainActivity.this,
                        "Error in download image.",
                        Toast.LENGTH_SHORT).show();
            }

            return myBitmap;
        }
    }
    //endregion

    //region Download
    private class DownloadFromOMDB extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://www.omdbapi.com/?t="+search);

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
                DownloadImageAsync downloadImageAsync= new DownloadImageAsync();
                URL url = null;
                try {
                    url = new URL(movie.getUrlImage());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                downloadImageAsync.execute(url);

                editTitle.setText("");
            }
        }
    }
    //endregion

}
