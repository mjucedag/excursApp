package com.example.mj_uc.excursapp.dagger;

import com.example.mj_uc.excursapp.contrato.ContratoVistaImagenes;
import com.example.mj_uc.excursapp.presentador.PresentadorVistaImagenes;
import com.example.mj_uc.excursapp.vista.VistaImagenes;

import dagger.Module;
import dagger.Provides;

/**
 * The type Vista imagenes module.
 */
@Module(injects = {VistaImagenes.class})
public class VistaImagenesModule {


    /**
     * Provide main activity presenter contrato vista imagenes . presentador.
     *
     * @return the contrato vista imagenes . presentador
     */
    @Provides
    public ContratoVistaImagenes.Presentador provideMainActivityPresenter(){
        return new PresentadorVistaImagenes();
    }
}

