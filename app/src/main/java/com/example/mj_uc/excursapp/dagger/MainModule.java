package com.example.mj_uc.excursapp.dagger;

import dagger.Module;
import dagger.Provides;
import com.example.mj_uc.excursapp.MainActivity;
import com.example.mj_uc.excursapp.contrato.Contrato;
import com.example.mj_uc.excursapp.presentador.Presentador;

@Module(injects = {MainActivity.class})
public class MainModule {

    @Provides
    public Contrato.Presentador provideMainActivityPresenter(){
        return new Presentador();
    }
}

