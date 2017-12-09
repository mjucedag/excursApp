package com.example.mj_uc.excursapp.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mj_uc.excursapp.R;
import com.example.mj_uc.excursapp.contrato.ContratoVerActividad;
import com.example.mj_uc.excursapp.dagger.VerActividadModule;

import javax.inject.Inject;

import dagger.ObjectGraph;

public class VerActividad extends AppCompatActivity implements ContratoVerActividad.Vista {

    private TextView titulo, descripcion, campoProfesor, campoGrupos, campoLugar, campoDireccion, campoFecha, horaSalida, horaLlegada;
    private ImageView fotoAct;
    private Integer idActividad;

    @Inject
    ContratoVerActividad.Presentador presentador;

    @Override
    public void initVerActividad() {
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            idActividad = b.getInt("ID_ACTIVIDAD");
        }

        fotoAct = (ImageView) findViewById(R.id.imagen);
        titulo = (TextView) findViewById(R.id.titulo);
        descripcion = (TextView) findViewById(R.id.campoDescripcion);
        campoProfesor = (TextView) findViewById(R.id.campoProfesor);
        campoFecha = (TextView) findViewById(R.id.campoFecha);
        campoGrupos = (TextView) findViewById(R.id.campoGrupo);
        campoLugar = (TextView) findViewById(R.id.campoLugar);
        horaSalida = (TextView) findViewById(R.id.campoHora);
        horaLlegada = (TextView) findViewById(R.id.campoHora);
        campoDireccion = (TextView) findViewById(R.id.campoDireccion);
        //campoFecha = findViewById(R.id.campoFecha);

        // Inyecta las clases con Dagger. Esto solo lo tenemos aquí por simplicidad.
        ObjectGraph objectGraph = ObjectGraph.create(new VerActividadModule());
        objectGraph.inject(this);

        presentador.setVista(this);
        presentador.prepareView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_actividad);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initVerActividad();
        // MANIFIESTO --> android:parentActivityName=""
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idMenu = item.getItemId();

        if (idMenu == R.id.pdf) {
            presentador.doPhotoPrint();
        } else if (idMenu == R.id.editar) {

        } else if (idMenu == R.id.papelera) {
            presentador.deleteActividad(idActividad);
        }
        return super.onOptionsItemSelected(item);
    }

    public TextView getTitulo() {
        return titulo;
    }

    public TextView getDescripcion() {
        return descripcion;
    }

    public TextView getCampoProfesor() {
        return campoProfesor;
    }

    public TextView getCampoGrupos() {
        return campoGrupos;
    }

    public TextView getCampoLugar() {
        return campoLugar;
    }

    public TextView getCampoDireccion() {
        return campoDireccion;
    }

    public TextView getCampoFecha() {
        return campoFecha;
    }

    public TextView getHoraSalida() {
        return horaSalida;
    }

    public ImageView getFotoAct() {
        return fotoAct;
    }
}

