package com.example.mj_uc.excursapp.contrato;


/**
 * The interface Contrato ver actividad.
 */
public interface ContratoVerActividad {

    /**
     * The interface Vista.
     */
    interface Vista {
        /**
         * Init ver actividad.
         */
        void initVerActividad();
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
         * Delete actividad.
         *
         * @param id the id
         */
        void deleteActividad(final Integer id);

        /**
         * Do photo print.
         */
        void doPhotoPrint();

        /**
         * Edit actividad.
         *
         * @param id the id
         */
        void editActividad(final Integer id);
    }
}
