package com.example.mj_uc.excursapp.dagger;

import com.example.mj_uc.excursapp.contrato.ContratoConfirmacionActivity;
import com.example.mj_uc.excursapp.contrato.ContratoVistaImagenes;
import com.example.mj_uc.excursapp.presentador.PresentadorConfirmacionActivity;
import com.example.mj_uc.excursapp.presentador.PresentadorVistaImagenes;
import com.example.mj_uc.excursapp.vista.ConfirmacionActivity;
import com.example.mj_uc.excursapp.vista.VistaImagenes;

import dagger.Module;
import dagger.Provides;


/**
 * The type Confirmacion activity module.
 */
@Module(injects = {ConfirmacionActivity.class})
public class ConfirmacionActivityModule {


    /**
     * Provide main activity presenter contrato confirmacion activity . presentador.
     *
     * @return the contrato confirmacion activity . presentador
     */
    @Provides
    public ContratoConfirmacionActivity.Presentador provideMainActivityPresenter(){
        return new PresentadorConfirmacionActivity();
    }
}

