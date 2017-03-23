package com.example.athena.omsbapp_;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;

public class Util {
    /**
     *Read a URL and turn it to a Bitmap.
     *@param url gets the url image to do function.
     *@return Bitmap.
     */
    public static Bitmap loadImage(URL url) throws IOException {
        InputStream inputStream;
        Bitmap myBitmap;

        inputStream = url.openStream();
        myBitmap = BitmapFactory.decodeStream(inputStream);

        inputStream.close();
        return myBitmap;
    }

    /**
     *Lê um arquivo da web via HTTP e converte o mesmo em String.
     *@param inputStream Stream do arquivo local no aplicativo
     *@return O arquivo convertido em String.
     */
    public static String webToString(InputStream inputStream) {
        InputStream localStream = inputStream;
        String localString = "";
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(localStream, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
            localString = writer.toString();
            writer.close();
            reader.close();
            localStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return localString;
    }

    /**
     *Read one file JSON and convert to String and insert into class Movie.
     *@param jsonString String json
     *@return O arquivo convertido em String.
     */
    public static Movie JSONtoOmdb(String jsonString){
        try{
            Movie movie = new Movie();
            Boolean response = true;

            JSONObject mainObj = new JSONObject(jsonString);
            response = mainObj.getBoolean("Response");
            if(response == false){
                movie.setTitle("Title not found");
                movie.setPlot("Plot not found because Movie has not found ");
                movie.setUrlImage("http://vignette2.wikia.nocookie.net" +
                        "/mixels/images/f/f4/No-image-found.jpg/revision/latest?cb=20150916222215");
            }else{
                movie.setTitle(mainObj.getString("Title"));
                movie.setPlot(mainObj.getString("Plot"));
                movie.setUrlImage(mainObj.getString("Poster"));
            }

            return movie;
        }catch (Exception e){
            Log.e("ERROR","Error ",e);
            return null;
        }
    }

}
