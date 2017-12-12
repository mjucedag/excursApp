package com.example.mj_uc.excursapp.dagger;

import com.example.mj_uc.excursapp.contrato.ContratoHelpActivity;
import com.example.mj_uc.excursapp.presentador.PresentadorHelpActivity;
import com.example.mj_uc.excursapp.vista.Help.Help;

import dagger.Module;
import dagger.Provides;


/**
 * The type Help activity module.
 */
@Module(injects = {Help.class})
public class HelpActivityModule {


    /**
     * Provide main activity presenter contrato help activity . presentador.
     *
     * @return the contrato help activity . presentador
     */
    @Provides
    public ContratoHelpActivity.Presentador provideMainActivityPresenter(){
        return new PresentadorHelpActivity();
    }
}

