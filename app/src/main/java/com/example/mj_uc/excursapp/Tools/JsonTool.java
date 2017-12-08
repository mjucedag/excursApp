package com.example.mj_uc.excursapp.Tools;

import android.content.Context;

import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class JsonTool {

    private static final String JSON_FILE = "jsonExcursapp.json";

    @Deprecated
    public static ObjectJson readFromFile(Context context) {

        JsonReader jsonReader = null;
        ObjectJson objectJsons = null;

        try {
            jsonReader = new JsonReader(new InputStreamReader(context.getAssets().open(JSON_FILE))); //abrimos el fichero
            objectJsons = new Gson().fromJson(jsonReader, ObjectJson.class); //leemos el fichero JSON y lo almacenamos en el objeto

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (jsonReader != null) {
                try {
                    jsonReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return  objectJsons;
    }
}
