package com.example.mj_uc.excursapp.contrato;


/**
 * The interface Contrato confirmacion activity.
 */
public interface ContratoConfirmacionActivity {

    /**
     * The interface Vista.
     */
    interface Vista {}

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
         * Confirm image.
         */
        void confirmImage();

        /**
         * Cancel image.
         */
        void cancelImage();
    }
}
