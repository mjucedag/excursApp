package com.example.mj_uc.excursapp.contrato;



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

        void goToHelpCreate();
        void goToHelpSearch();
        void goToHelpPrint();
        void goToHelpEdit();
        void goToHelpDelete();
    }
}
