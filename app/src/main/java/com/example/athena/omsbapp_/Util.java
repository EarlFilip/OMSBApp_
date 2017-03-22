package com.example.athena.omsbapp_;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

public class Util {
    public static Movie JSONtoOmdb(String jsonString){
        try{
            Movie movie = new Movie();

            JSONObject mainObj = new JSONObject(jsonString);
            movie.setTitle(mainObj.getString("Title"));
            movie.setPlot(mainObj.getString("Plot"));

            return movie;
        }catch (Exception e){
            Log.e("ERROR","Error ",e);
            return null;
        }
    }

}
