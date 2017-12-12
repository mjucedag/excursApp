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
        if (fileName != null && fileName.contains(".")) {
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
            String nombreProfesor;
            try{
                Integer idProfesor = actividad.getProfesores().get(0);//controla el null
                nombreProfesor = getNombreProfesor(objectJsons, idProfesor);
            }catch (IndexOutOfBoundsException ex){
                nombreProfesor = "";
            }

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

    /**
     * <p>Checks if a CharSequence is whitespace, empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = true
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace
     */
    public static boolean isEmpty(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if a string array is whitespace, empty ("") or null.</p>
     *
     * @param strArr the String array to check
     * @return {@code true} if one string is null, empty or whitespace
     */
    public static boolean stringsEmptyValidator(String... strArr) {
        for (String st : strArr) {
            if  (isEmpty(st))
                return true;

        }
        return false;
    }
}
