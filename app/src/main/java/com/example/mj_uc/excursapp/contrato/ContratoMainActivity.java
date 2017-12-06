package com.example.mj_uc.excursapp.contrato;

import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;

public interface ContratoMainActivity {

    interface Vista {
        void initializeFabButton();
        void showTextInToolBar();
        void setupDrawer();
        void initializeRecyclerView();
    }

    interface Presentador {
        void setVista(Vista vista);
        void prepareAlbums();
        String getNombreProfesor(ObjectJson objectJsons, Integer idProfesor);
    }
}
