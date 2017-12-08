package com.example.mj_uc.excursapp.presentador;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ListView;

import com.example.mj_uc.excursapp.MainActivity;
import com.example.mj_uc.excursapp.R;
import com.example.mj_uc.excursapp.apirest.WebRequest;
import com.example.mj_uc.excursapp.contrato.ContratoDrawerMenu;
import com.example.mj_uc.excursapp.modelo.Pojo.Actividad;
import com.example.mj_uc.excursapp.modelo.Pojo.Grupo;
import com.example.mj_uc.excursapp.vista.Help.Help;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PresentadorDrawerMenu implements ContratoDrawerMenu.Presentador {

    private MainActivity mainActivity;

    @Override
    public void onGroupQueryMenuSelected() {

        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... args) {
                WebRequest webreq = new WebRequest();
                String jsonStr = webreq.makeWebServiceCall("https://apirest-mjuceda.c9users.io/grupo", WebRequest.GETRequest);
                return jsonStr;
            }

            @Override
            protected void onPostExecute(String s) {
                List<CharSequence> list = new ArrayList<>();
                Gson g = new Gson();
                List<Grupo> grupos = g.fromJson(s, new TypeToken<List<Grupo>>() {}.getType());
                for (Grupo grupo : grupos) {
                    list.add(grupo.getNombre());
                }
                createAlertDialog(list, "Selecciona uno o más grupos");
            }
        };
        task.execute();
    }

    @Override
    public void onDateQueryMenuSelected() {

        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... args) {
                WebRequest webreq = new WebRequest();
                String jsonStr = webreq.makeWebServiceCall("https://apirest-mjuceda.c9users.io/actividad", WebRequest.GETRequest);
                return jsonStr;
            }

            @Override
            protected void onPostExecute(String s) {
                List<CharSequence> list = new ArrayList<>();
                try {

                    List<Date> listDate = new ArrayList<>();
                    Gson g = new Gson();
                    List<Actividad> actividad = g.fromJson(s, new TypeToken<List<Actividad>>() {
                    }.getType());
                    //Necessary parse to Date in order to sort it
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    for (Actividad a : actividad) {
                        listDate.add(formatter.parse(a.getFechasalida()));
                    }

                    Collections.sort(listDate);

                    //We get back to String
                    for (Date d : listDate) {
                        list.add(formatter.format(d));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                createAlertDialog(list, "Selecciona una o más fechas");
            }
        };
        task.execute();
    }

    @Override
    public void onHelpMenuSelected() {
        Intent goToHelp = new Intent(mainActivity, Help.class);
        mainActivity.startActivity(goToHelp);
    }

    @Override
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void createAlertDialog(List<CharSequence> list, String title) {
        // Initialize  readable sequence of char values
        final CharSequence[] dialogList = list.toArray(new CharSequence[list.size()]);
        final AlertDialog.Builder builderDialog = new AlertDialog.Builder(mainActivity, AlertDialog.THEME_HOLO_LIGHT);
        builderDialog.setTitle(title);
        builderDialog.setIcon(R.drawable.ic_search_black_24dp);
        int count = dialogList.length;
        boolean[] is_checked = new boolean[count]; // set is_checked boolean false;
        // Creating multiple selection by using setMutliChoiceItem method
        builderDialog.setMultiChoiceItems(dialogList, is_checked, new DialogInterface.OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialog, int whichButton, boolean isChecked) {
            }
        });
        builderDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ListView list = ((AlertDialog) dialog).getListView();
                // make selected item in the comma seprated string
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < list.getCount(); i++) {
                    boolean checked = list.isItemChecked(i);
                    if (checked) {
                        if (stringBuilder.length() > 0) stringBuilder.append(",");
                        stringBuilder.append(list.getItemAtPosition(i));
                    }
                }
                if (stringBuilder.toString().trim().equals("")) {
                    stringBuilder.setLength(0);
                    dialog.cancel();
                } else {
                    //Ir a la otra ventana, pasando los valores concatenados por "," del stringbuilder
                }
            }
        });
        builderDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builderDialog.create();
        alert.show();
        Button b = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        b.setTextColor(mainActivity.getResources().getColor(R.color.azulAlertDialog));
        b.setTextSize(20);
        Button b1 = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        b1.setTextColor(mainActivity.getResources().getColor(R.color.azulAlertDialog));
        b1.setTextSize(20);
    }
}
