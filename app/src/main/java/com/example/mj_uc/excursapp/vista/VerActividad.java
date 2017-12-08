package com.example.mj_uc.excursapp.vista;

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

public class VerActividad extends AppCompatActivity implements ContratoVerActividad.Vista{


    private TextView titulo, descripcion, campoProfesor, campoGrupos, campoLugar, campoDireccion, campoFecha, horaSalida, horaLlegada;
    private ImageView fotoAct;

    @Inject
    ContratoVerActividad.Presentador presentador;

    private void init() {

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
        presentador.populateFields();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_actividad);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        // MANIFIESTO --> android:parentActivityName=""
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.pdf) {

        } else if (id == R.id.editar) {

        } else if (id == R.id.papelera) {

        }
        return super.onOptionsItemSelected(item);
    }

    private void createPDF() {
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

    public TextView getHoraLlegada() {
        return horaLlegada;
    }

    public ImageView getFotoAct() {
        return fotoAct;
    }
}

