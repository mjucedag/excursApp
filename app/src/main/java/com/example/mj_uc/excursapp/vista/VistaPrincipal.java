package com.example.mj_uc.excursapp.vista;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.mj_uc.excursapp.R;
import com.example.mj_uc.excursapp.json.JsonTool;
import com.example.mj_uc.excursapp.modelo.Pojo.Actividad;
import com.example.mj_uc.excursapp.modelo.Pojo.Grupo;
import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;
import com.example.mj_uc.excursapp.modelo.Pojo.Profesor;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class VistaPrincipal extends AppCompatActivity {

    private String NOMBRE_DOCUMENTO = "actividad.pdf";
    private ArrayList<Integer> idprofesor = new ArrayList<>();
    private String link;
    private String nombre;
    private Context context;
    private Profesor profesores = new Profesor();
    private TextView titulo, descripcion, campoProfesor, campoGrupos, campoLugar, campoDireccion, campoFecha, horaSalida, horaLlegada;
    private List<Profesor> NOMBREPROFESOR;
    private List<Grupo> NOMBREGRUPO;
    private ArrayList<Integer> idGrupos = new ArrayList<>();
    private ImageView fotoAct;

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

        //--------PUPULATE FIELDS--------------//
        Intent intent = this.getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            Integer idActividad = b.getInt("ID_ACTIVIDAD");

            ObjectJson objectJsons = JsonTool.readFromFile(this);

            //Recogemos nuestra actividad
            Actividad actividades = new Actividad();
            for (Actividad actividad : objectJsons.getActividad()) {

                if (idActividad == actividad.getId()) {
                    actividades = actividad;
                    break;
                }
            }

            //Rellenamos los campos con nuestra actividad
            titulo.setText(actividades.getTitulo());
            descripcion.setText(actividades.getDescripcion());
            idprofesor.addAll(actividades.getProfesores());
            idGrupos.addAll(actividades.getGrupos());
            campoLugar.setText(actividades.getLugar());
            campoDireccion.setText(actividades.getDireccion());
            campoFecha.setText(actividades.getFechasalida());
            horaSalida.setText("Desde las " + actividades.getHorasalida() + " hasta las " + actividades.getHorallegada());
            String nomFoto = actividades.getImg();
            nomFoto = removeExtension(nomFoto);
            Log.v("foto", nomFoto);
            Context context = fotoAct.getContext();
            int id = context.getResources().getIdentifier(nomFoto, "drawable", context.getPackageName());
            fotoAct.setImageResource(id);

            //Rellenamos los nombres de profesores
            NOMBREPROFESOR = objectJsons.getProfesor();
            for (Profesor profesor : NOMBREPROFESOR) {
                for (int i = 0; i < idprofesor.size(); i++) {
                    if (profesor.getId() == idprofesor.get(i)) {
                        campoProfesor.setText(campoProfesor.getText() + " " + profesor.getNombre() + "\t");
                    }
                }
            }

            //Rellanamos los nombre de los grupos
            NOMBREGRUPO = objectJsons.getGrupo();
            for (Grupo grupo : NOMBREGRUPO) {
                for (int i = 0; i < idGrupos.size(); i++) {
                    if (grupo.getId() == idGrupos.get(i)) {
                        campoGrupos.setText(campoGrupos.getText() + " , " + grupo.getNombre());
                    }
                }
            }


        }

    }

    public String removeExtension(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_principal);
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

}

