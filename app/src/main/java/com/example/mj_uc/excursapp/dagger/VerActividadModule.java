package com.example.mj_uc.excursapp.dagger;

import com.example.mj_uc.excursapp.contrato.ContratoVerActividad;
import com.example.mj_uc.excursapp.presentador.PresentadorVerActividad;
import com.example.mj_uc.excursapp.vista.VerActividad;

import dagger.Module;
import dagger.Provides;

/**
 * The type Ver actividad module.
 */
@Module(injects = {VerActividad.class})
public class VerActividadModule {

    /**
     * Provide main activity presenter contrato ver actividad . presentador.
     *
     * @return the contrato ver actividad . presentador
     */
    @Provides
    public ContratoVerActividad.Presentador provideMainActivityPresenter(){
        return new PresentadorVerActividad();
    }
}

