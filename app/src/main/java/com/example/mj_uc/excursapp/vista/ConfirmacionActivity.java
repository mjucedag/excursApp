package com.example.mj_uc.excursapp.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mj_uc.excursapp.R;

public class ConfirmacionActivity extends AppCompatActivity {

    ImageView imageView;
    Button cancelar, aceptar;
    String titulo, dir, lugar, nomGuardados, nomGrupos, fechaGuardada, horaSalida, horaLlegada, descrip;

    private void init(){
        imageView = (ImageView) findViewById(R.id.imageView);
        cancelar = (Button) findViewById(R.id.cancelar);
        aceptar = (Button) findViewById(R.id.aceptar);

        Bundle b = getIntent().getExtras();
        final int id = b.getInt("idImg");
        final String nom = b.getString("nombresImg");
        if (b != null){
            // Guardamos las variables
            imageView.setImageResource(b.getInt("idImg"));
            titulo = b.getString("tituloAct");
            dir = b.getString("direccion");
            lugar = b.getString("lugar");
            nomGuardados = b.getString("profesores");
            nomGrupos = b.getString("grupos");
            fechaGuardada = b.getString("fecha");
            horaSalida = b.getString("horaSalida");
            horaLlegada = b.getString("horaLlegada");
            descrip = b.getString("descripcion");
        }

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConfirmacionActivity.this, "Imagen seleccionada", Toast.LENGTH_LONG).show();
                Intent i = new Intent(ConfirmacionActivity.this, CreateActivity.class);
                i.putExtra("idImg", id);
                i.putExtra("nombre", nom);
                i.putExtra("tituloAct", titulo);
                i.putExtra("lugar", lugar);
                i.putExtra("direccion", dir);
                i.putExtra("profesores", nomGuardados);
                i.putExtra("grupos", nomGrupos);
                i.putExtra("fecha", fechaGuardada);
                i.putExtra("horaSalida", horaSalida);
                i.putExtra("horaLlegada", horaLlegada);
                i.putExtra("descripcion", descrip);
                startActivity(i);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(ConfirmacionActivity.this, VistaImagenes.class);
                startActivity(volver);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);
        init();
    }
}
