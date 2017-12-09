package com.example.mj_uc.excursapp.dagger;

import com.example.mj_uc.excursapp.contrato.ContratoDrawerMenu;
import com.example.mj_uc.excursapp.presentador.PresentadorDrawerMenu;
import com.example.mj_uc.excursapp.vista.navigationDrawer.DrawerMenuItem;

import dagger.Module;
import dagger.Provides;

/**
 * The type Drawer menu module.
 */
@Module(injects = {DrawerMenuItem.class})
public class DrawerMenuModule {

    /**
     * Provide main activity presenter contrato drawer menu . presentador.
     *
     * @return the contrato drawer menu . presentador
     */
    @Provides
    public ContratoDrawerMenu.Presentador provideMainActivityPresenter(){
        return new PresentadorDrawerMenu();
    }
}

