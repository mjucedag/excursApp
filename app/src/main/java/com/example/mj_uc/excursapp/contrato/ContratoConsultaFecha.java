package com.example.mj_uc.excursapp.contrato;


/**
 * The interface Contrato Consulta Fecha.
 */
public interface ContratoConsultaFecha {

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
