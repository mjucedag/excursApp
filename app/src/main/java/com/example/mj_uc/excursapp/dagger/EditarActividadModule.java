package com.example.mj_uc.excursapp.dagger;

import com.example.mj_uc.excursapp.contrato.ContratoCreateActivity;
import com.example.mj_uc.excursapp.contrato.ContratoEditarActividad;
import com.example.mj_uc.excursapp.presentador.PresentadorCreateActivity;
import com.example.mj_uc.excursapp.presentador.PresentadorEditarActividad;
import com.example.mj_uc.excursapp.vista.CreateActivity;
import com.example.mj_uc.excursapp.vista.EditarActividad;

import dagger.Module;
import dagger.Provides;


/**
 * The type editar activity module.
 */
@Module(injects = {EditarActividad.class})
public class EditarActividadModule {


    /**
     * Provide main activity presenter contrato editar activity . presentador.
     *
     * @return the contrato create activity . presentador
     */
    @Provides
    public ContratoEditarActividad.Presentador provideMainActivityPresenter() {
        return new PresentadorEditarActividad();
    }
}

