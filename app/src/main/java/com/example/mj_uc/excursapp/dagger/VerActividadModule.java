package com.example.mj_uc.excursapp.dagger;

import com.example.mj_uc.excursapp.contrato.ContratoVerActividad;
import com.example.mj_uc.excursapp.presentador.PresentadorVerActividad;
import com.example.mj_uc.excursapp.vista.VerActividad;

import dagger.Module;
import dagger.Provides;

@Module(injects = {VerActividad.class})
public class VerActividadModule {

    @Provides
    public ContratoVerActividad.Presentador provideMainActivityPresenter(){
        return new PresentadorVerActividad();
    }
}

