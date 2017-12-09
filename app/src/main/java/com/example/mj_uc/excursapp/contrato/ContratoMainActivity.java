package com.example.mj_uc.excursapp.contrato;

import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;

/**
 * The interface Contrato main activity.
 */
public interface ContratoMainActivity {

    /**
     * The interface Vista.
     */
    interface Vista {
        /**
         * Initialize fab button.
         */
        void initializeFabButton();

        /**
         * Show text in tool bar.
         */
        void showTextInToolBar();

        /**
         * Sets drawer.
         */
        void setupDrawer();

        /**
         * Initialize recycler view.
         */
        void initializeRecyclerView();
    }

    /**
     * The interface Presentador.
     */
    interface Presentador {
        /**
         * Sets vista.
         *
         * @param vista the vista
         */
        void setVista(Vista vista);

        /**
         * Prepare albums to the first rendering view.
         */
        void prepareAlbums();

        /**
         * Gets nombre profesor.
         *
         * @param objectJsons the object jsons
         * @param idProfesor  the id profesor
         * @return the nombre profesor
         */
        String getNombreProfesor(ObjectJson objectJsons, Integer idProfesor);
    }
}
