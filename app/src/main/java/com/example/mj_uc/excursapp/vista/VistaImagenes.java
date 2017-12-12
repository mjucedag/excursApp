package com.example.mj_uc.excursapp.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mj_uc.excursapp.R;
import com.example.mj_uc.excursapp.contrato.ContratoVistaImagenes;
import com.example.mj_uc.excursapp.dagger.VistaImagenesModule;
import com.example.mj_uc.excursapp.modelo.Adapter.ImagenAdapter;
import com.example.mj_uc.excursapp.tools.Constants;

import javax.inject.Inject;

import dagger.ObjectGraph;


/**
 * The type Vista imagenes.
 */
public class VistaImagenes extends AppCompatActivity implements ContratoVistaImagenes.Vista{

    private String titulo, dir, lugar, nomGuardados, nomGrupos, fechaGuardada, horaSalida, horaLlegada, descrip, className;
    private int idActividad;
    private ListView mListView;

    /**
     * The Presentador.
     */
    @Inject
    ContratoVistaImagenes.Presentador presentador;

    private void init() {

        Bundle b = getIntent().getExtras();

        if(b != null){
            // Guardamos las variables
            titulo = b.getString("tituloAct");
            dir = b.getString("direccion");
            lugar = b.getString("lugar");
            nomGuardados = b.getString("profesores");
            nomGrupos = b.getString("grupos");
            fechaGuardada = b.getString("fecha");
            horaSalida = b.getString("horaSalida");
            horaLlegada = b.getString("horaLlegada");
            descrip = b.getString("descripcion");
            className = b.getString("className");
            idActividad = b.getInt("idActividad");
        }

        mListView = (ListView) findViewById(R.id.listview);

        ImagenAdapter imagenAdapter =new ImagenAdapter(VistaImagenes.this, Constants.nombresImg, Constants.idImg);
        mListView.setAdapter(imagenAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                presentador.goToConfirmacionActivity(i);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_imagen);

        // Inyecta las clases con Dagger. Esto solo lo tenemos aquí por simplicidad.
        ObjectGraph objectGraph = ObjectGraph.create(new VistaImagenesModule());
        objectGraph.inject(this);

        presentador.setVista(this);
        init();
    }

    /**
     * Gets titulo.
     *
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Gets dir.
     *
     * @return the dir
     */
    public String getDir() {
        return dir;
    }

    /**
     * Gets lugar.
     *
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Gets nom guardados.
     *
     * @return the nom guardados
     */
    public String getNomGuardados() {
        return nomGuardados;
    }

    /**
     * Gets nom grupos.
     *
     * @return the nom grupos
     */
    public String getNomGrupos() {
        return nomGrupos;
    }

    /**
     * Gets fecha guardada.
     *
     * @return the fecha guardada
     */
    public String getFechaGuardada() {
        return fechaGuardada;
    }

    /**
     * Gets hora salida.
     *
     * @return the hora salida
     */
    public String getHoraSalida() {
        return horaSalida;
    }

    /**
     * Gets hora llegada.
     *
     * @return the hora llegada
     */
    public String getHoraLlegada() {
        return horaLlegada;
    }

    /**
     * Gets descrip.
     *
     * @return the descrip
     */
    public String getDescrip() {
        return descrip;
    }

    /**
     * Gets class name.
     *
     * @return the class name
     */
    public String getClassName() {
        return className;
    }


    /**
     * Gets id actividad.
     *
     * @return the id actividad
     */
    public int getIdActividad() {
        return idActividad;
    }
}
