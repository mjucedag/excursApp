package com.example.mj_uc.excursapp.contrato;


import com.example.mj_uc.excursapp.MainActivity;

import java.util.List;

/**
 * The interface Contrato drawer menu.
 */
public interface ContratoDrawerMenu {

    /**
     * The interface Presentador.
     */
    interface Presentador {
        /**
         * On group query menu selected.
         */
        void onGroupQueryMenuSelected();

        /**
         * On date query menu selected.
         */
        void onDateQueryMenuSelected();

        /**
         * On help menu selected.
         */
        void onHelpMenuSelected();

        /**
         * Sets main activity.
         *
         * @param mainActivity the main activity
         */
        void setMainActivity(MainActivity mainActivity);

        /**
         * Create alert dialog.
         *
         * @param list  the list
         * @param title the title
         */
        void createAlertDialog(List<CharSequence> list, String title);
    }
}
