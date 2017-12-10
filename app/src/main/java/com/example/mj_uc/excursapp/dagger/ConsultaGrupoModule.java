package com.example.mj_uc.excursapp.dagger;

import com.example.mj_uc.excursapp.contrato.ContratoConsultaGrupo;
import com.example.mj_uc.excursapp.presentador.PresentadorConsultaGrupo;
import com.example.mj_uc.excursapp.vista.ConsultaGrupo;

import dagger.Module;
import dagger.Provides;

/**
 * The type Consulta grupo module.
 */
@Module(injects = {ConsultaGrupo.class})
public class ConsultaGrupoModule {


    /**
     * Provide main activity presenter contrato consulta grupo . presentador.
     *
     * @return the contrato consulta grupo . presentador
     */
    @Provides
    public ContratoConsultaGrupo.Presentador provideMainActivityPresenter(){
        return new PresentadorConsultaGrupo();
    }
}

