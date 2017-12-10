package com.example.mj_uc.excursapp.tools;


import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;

import com.example.mj_uc.excursapp.modelo.Album;
import com.example.mj_uc.excursapp.modelo.Pojo.Actividad;
import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;
import com.example.mj_uc.excursapp.modelo.Pojo.Profesor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Tools.
 */
public class Tools {

    /**
     * Remove extension string.
     *
     * @param fileName the file name
     * @return the string
     */
    public static String removeExtension(String fileName) {
        if(fileName.contains(".")){
            return fileName.substring(0, fileName.lastIndexOf('.'));
        }
        return fileName;
    }


    /**
     * Dp to px int.
     *
     * @param dp          the dp
     * @param application the application
     * @return the int
     */
    public static int dpToPx(int dp, AppCompatActivity application) {
        Resources r = application.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    /**
     * Gets albums of actividades.
     *
     * @param actividadesWithGroups the actividades with groups
     * @param objectJsons           the object jsons
     * @return the albums of actividades
     */
    public static List<Album> getAlbumsOfActividades(List<Actividad> actividadesWithGroups, ObjectJson objectJsons) {
        //Here we have all the Actividades to show
        List<Album> albumOrdenadoFecha = new ArrayList<>();

        //leer el objeto para convertirlo en los objetos Album (titulo, profesor, imagen)que es lo que mostramos (albumList)

        for (Actividad actividad : actividadesWithGroups) {

            Integer id = actividad.getId();
            String titulo = actividad.getTitulo();
            String imagen = actividad.getImg();
            //Nos hace falta recuperar el NOMBRE del profesor, no el ID. pero en actividad solo tenemos el ID
            Integer idProfesor = actividad.getProfesores().get(0);//controla el null
            // metodo (IDPROFESOR), TE DEVUELVA EL NOMBRE DE ESE PROFESOR
            String nombreProfesor = getNombreProfesor(objectJsons, idProfesor);
            //entonces, cuando ya tengas el nombre, ya puedes crearte tu objeto ALBUM
            String fecha = actividad.getFechasalida();
            albumOrdenadoFecha.add(new Album(id, titulo, nombreProfesor, imagen, fecha));
        }
        //ordenar mis fotos por fecha
        Collections.sort(albumOrdenadoFecha);
        return albumOrdenadoFecha;
    }

    /**
     * Gets nombre profesor.
     *
     * @param objectJsons the object jsons
     * @param idProfesor  the id profesor
     * @return the nombre profesor
     */
    public static String getNombreProfesor(ObjectJson objectJsons, Integer idProfesor) {
        for (Profesor profesor : objectJsons.getProfesor()) {
            if (profesor.getId() == idProfesor) {
                return profesor.getNombre();
            }
        }
        return "";
    }
}
