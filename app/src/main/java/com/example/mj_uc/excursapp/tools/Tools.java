package com.example.mj_uc.excursapp.tools;


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
}
