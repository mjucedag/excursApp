package com.example.mj_uc.excursapp.Tools;


public class Tools {

    public static String removeExtension(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }
}
