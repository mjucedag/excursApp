package com.example.mj_uc.excursapp.dagger;

import com.example.mj_uc.excursapp.contrato.ContratoCreateActivity;
import com.example.mj_uc.excursapp.presentador.PresentadorCreateActivity;
import com.example.mj_uc.excursapp.vista.CreateActivity;

import dagger.Module;
import dagger.Provides;


/**
 * The type Create activity module.
 */
@Module(injects = {CreateActivity.class})
public class CreateActivityModule {


    /**
     * Provide main activity presenter contrato create activity . presentador.
     *
     * @return the contrato create activity . presentador
     */
    @Provides
    public ContratoCreateActivity.Presentador provideMainActivityPresenter() {
        return new PresentadorCreateActivity();
    }
}

