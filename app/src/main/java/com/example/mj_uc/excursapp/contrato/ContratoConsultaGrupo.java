package com.example.mj_uc.excursapp.contrato;


/**
 * The interface Contrato Consulta Grupo.
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
