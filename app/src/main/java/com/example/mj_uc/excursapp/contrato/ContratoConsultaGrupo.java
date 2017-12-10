package com.example.mj_uc.excursapp.contrato;


/**
 * The interface Contrato drawer menu.
 */
public interface ContratoConsultaGrupo {

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
         * Do query consult.
         */
        void doQueryConsult();
    }
}
