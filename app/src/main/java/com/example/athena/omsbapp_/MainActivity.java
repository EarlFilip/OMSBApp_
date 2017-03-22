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

    //region Download Sample

    //endregion

    //region Search

    //endregion

    public void sample(View v){

    }

    public void search(View v){

    }
}
