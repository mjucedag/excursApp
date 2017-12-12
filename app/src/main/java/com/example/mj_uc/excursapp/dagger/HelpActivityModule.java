package com.example.mj_uc.excursapp.dagger;

import com.example.mj_uc.excursapp.contrato.ContratoHelpActivity;
import com.example.mj_uc.excursapp.presentador.PresentadorHelpActivity;
import com.example.mj_uc.excursapp.vista.Help.Help;

import dagger.Module;
import dagger.Provides;


@Module(injects = {Help.class})
public class HelpActivityModule {


    @Provides
    public ContratoHelpActivity.Presentador provideMainActivityPresenter(){
        return new PresentadorHelpActivity();
    }
}

