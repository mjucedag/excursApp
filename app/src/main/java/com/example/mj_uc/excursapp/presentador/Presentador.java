package com.example.mj_uc.excursapp.presentador;

import com.example.mj_uc.excursapp.MainActivity;
import com.example.mj_uc.excursapp.contrato.Contrato;
import com.example.mj_uc.excursapp.modelo.Album;
import com.example.mj_uc.excursapp.modelo.Pojo.Actividad;
import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;
import com.example.mj_uc.excursapp.modelo.Pojo.Profesor;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Presentador implements Contrato.Presentador {

    private Contrato.Vista vista;

    @Override
    public void setVista(Contrato.Vista vista) {
        this.vista = vista;
    }

    @Override
    public void prepareAlbums() {

        MainActivity mainActivity = (MainActivity) vista;
        JsonReader jsonReader = null;

        try {

            jsonReader = new JsonReader(new InputStreamReader(mainActivity.getAssets().open("jsonExcursapp.json"))); //creamos el fichero
            ObjectJson objectJsons = new Gson().fromJson(jsonReader, ObjectJson.class); //leemos el fichero JSON y lo almacenamos en el objeto
            List<Album> albumOrdenadoFecha= new ArrayList<>();

            //leer el objeto para convertirlo en los objetos Album (titulo, profesor, imagen)que es lo que mostramos (albumList)

            for(Actividad actividad : objectJsons.getActividad()){

                Integer id= actividad.getId();
                String titulo = actividad.getTitulo();
                String imagen = actividad.getImg();
                //Nos hace falta recuperar el NOMBRE del profesor, no el ID. pero en actividad solo tenemos el ID
                Integer idProfesor = actividad.getProfesores().get(0);//controla el null
                // metodo (IDPROFESOR), TE DEVUELVA EL NOMBRE DE ESE PROFESOR
                String nombreProfesor = this.getNombreProfesor(objectJsons,idProfesor);
                //entonces, cuando ya tengas el nombre, ya puedes crearte tu objeto ALBUM
                String fecha = actividad.getFechasalida();
                albumOrdenadoFecha.add(new Album(id,titulo, nombreProfesor, imagen, fecha));
            }
            //ordenar mis fotos por fecha
            Collections.sort(albumOrdenadoFecha);
            mainActivity.getAlbumList().addAll(albumOrdenadoFecha);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(jsonReader != null){
                try {
                    jsonReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        mainActivity.getAdapter().notifyDataSetChanged();
    }

    @Override
    public String getNombreProfesor(ObjectJson objectJsons, Integer idProfesor) {
        for(Profesor profesor : objectJsons.getProfesor()){
            if(profesor.getId() == idProfesor) {
                return profesor.getNombre();
            }
        }
        return "";
    }
}
