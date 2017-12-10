package com.example.mj_uc.excursapp.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mj_uc.excursapp.R;
import com.example.mj_uc.excursapp.modelo.Adapter.ImagenAdapter;


public class VistaImagenes extends AppCompatActivity {
    String titulo, dir, lugar, nomGuardados, nomGrupos, fechaGuardada, horaSalida, horaLlegada, descrip;
    ListView mListView;

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
        }

        mListView = (ListView) findViewById(R.id.listview);

        final String[] nombresImg = {"alhambra", "aplicaciones", "devfest", "futbol", "parqueciencias", "ptmalaga",
                "robotica", "senderismo", "sierranevada"};
        final int[] idImg = {R.drawable.alhambra, R.drawable.aplicaciones, R.drawable.devfest, R.drawable.futbol,
                R.drawable.parqueciencias, R.drawable.ptmalaga, R.drawable.robotica, R.drawable.senderismo, R.drawable.sierranevada};
        ImagenAdapter imagenAdapter =new ImagenAdapter(VistaImagenes.this, nombresImg, idImg);
        mListView.setAdapter(imagenAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(VistaImagenes.this, ConfirmacionActivity.class);
                intent.putExtra("nombresImg", nombresImg[i]);
                intent.putExtra("idImg", idImg[i]);
                intent.putExtra("tituloAct", titulo);
                intent.putExtra("lugar", lugar);
                intent.putExtra("direccion", dir);
                intent.putExtra("profesores", nomGuardados);
                intent.putExtra("grupos", nomGrupos);
                intent.putExtra("fecha", fechaGuardada);
                intent.putExtra("horaSalida", horaSalida);
                intent.putExtra("horaLlegada", horaLlegada);
                intent.putExtra("descripcion", descrip);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_imagen);
        init();
    }
}
