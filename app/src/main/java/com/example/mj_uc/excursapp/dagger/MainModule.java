package com.example.mj_uc.excursapp.dagger;

import dagger.Module;
import dagger.Provides;
import com.example.mj_uc.excursapp.MainActivity;
import com.example.mj_uc.excursapp.contrato.ContratoMainActivity;
import com.example.mj_uc.excursapp.presentador.PresentadorMainActivity;

@Module(injects = {MainActivity.class})
public class MainModule {

    @Provides
    public ContratoMainActivity.Presentador provideMainActivityPresenter(){
        return new PresentadorMainActivity();
    }
}

