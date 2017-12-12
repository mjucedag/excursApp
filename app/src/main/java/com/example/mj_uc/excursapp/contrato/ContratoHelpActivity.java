package com.example.mj_uc.excursapp.contrato;


/**
 * The interface Contrato help activity.
 */
public interface ContratoHelpActivity {

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
         * Go to help create.
         */
        void goToHelpCreate();

        /**
         * Go to help search.
         */
        void goToHelpSearch();

        /**
         * Go to help print.
         */
        void goToHelpPrint();

        /**
         * Go to help edit.
         */
        void goToHelpEdit();

        /**
         * Go to help delete.
         */
        void goToHelpDelete();
    }
}
