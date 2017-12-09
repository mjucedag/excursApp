package com.example.mj_uc.excursapp.Tools;


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
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }
}
