package com.example.mj_uc.excursapp.dagger;

import com.example.mj_uc.excursapp.contrato.ContratoConsultaFecha;
import com.example.mj_uc.excursapp.contrato.ContratoConsultaGrupo;
import com.example.mj_uc.excursapp.presentador.PresentadorConsultaFecha;
import com.example.mj_uc.excursapp.presentador.PresentadorConsultaGrupo;
import com.example.mj_uc.excursapp.vista.ConsultaFecha;
import com.example.mj_uc.excursapp.vista.ConsultaGrupo;

import dagger.Module;
import dagger.Provides;

/**
 * The type Consulta fecha module.
 */
@Module(injects = {ConsultaFecha.class})
public class ConsultaFechaModule {


    /**
     * Provide main activity presenter contrato consulta fecha . presentador.
     *
     * @return the contrato consulta fecha . presentador
     */
    @Provides
    public ContratoConsultaFecha.Presentador provideMainActivityPresenter(){
        return new PresentadorConsultaFecha();
    }
}

