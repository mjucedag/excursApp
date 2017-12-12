package com.example.mj_uc.excursapp.contrato;


/**
 * The interface Contrato editar activity.
 */
public interface ContratoEditarActividad {

    /**
     * The interface Vista.
     */
    interface Vista {

        /**
         * Init editar activity.
         */
        void initEditarActivity();
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
         * Save activity.
         */
        void saveActivity();

        /**
         * Go to vista imagenes.
         */
        void goToVistaImagenes();

        /**
         * Recoger datos actividad.
         */
        void recogerDatosActividad();
    }
}
