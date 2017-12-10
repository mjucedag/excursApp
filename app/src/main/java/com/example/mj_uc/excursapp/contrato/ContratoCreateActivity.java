package com.example.mj_uc.excursapp.contrato;


/**
 * The interface Contrato create activity.
 */
public interface ContratoCreateActivity {

    /**
     * The interface Vista.
     */
    interface Vista {

        /**
         * Init create activity.
         */
        void initCreateActivity();
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
         * Prepare view.
         */
        void prepareView();

        /**
         * Save activity.
         */
        void saveActivity();

        /**
         * Go to vista imagenes.
         */
        void goToVistaImagenes();
    }
}
