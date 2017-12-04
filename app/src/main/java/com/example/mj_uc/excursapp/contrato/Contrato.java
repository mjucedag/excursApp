package com.example.mj_uc.excursapp.contrato;

import com.example.mj_uc.excursapp.MainActivity;
import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;

public interface Contrato {

    interface Vista {

    }

    interface Presentador {
        void setVista(Vista vista);
        void setContext(MainActivity mainActivity);
        void prepareAlbums();
        String getNombreProfesor(ObjectJson objectJsons, Integer idProfesor);
    }
}