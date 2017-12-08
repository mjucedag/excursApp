package com.example.mj_uc.excursapp.vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.print.PrintHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mj_uc.excursapp.MainActivity;
import com.example.mj_uc.excursapp.R;
import com.example.mj_uc.excursapp.apirest.WebRequest;
import com.example.mj_uc.excursapp.contrato.ContratoVerActividad;
import com.example.mj_uc.excursapp.dagger.VerActividadModule;

import javax.inject.Inject;

import dagger.ObjectGraph;

public class VerActividad extends AppCompatActivity implements ContratoVerActividad.Vista{


    private TextView titulo, descripcion, campoProfesor, campoGrupos, campoLugar, campoDireccion, campoFecha, horaSalida, horaLlegada;
    private ImageView fotoAct;
    private Integer idActividad;

    @Inject
    ContratoVerActividad.Presentador presentador;

    private void init() {
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

        int idMenu = item.getItemId();

        if (idMenu == R.id.pdf) {
            doPhotoPrint();
        } else if (idMenu == R.id.editar) {

        } else if (idMenu == R.id.papelera) {
            deleteActivity(idActividad);
        }
        return super.onOptionsItemSelected(item);
    }

    private Bitmap takeScreenshot() {
        try {
            // crear un bitmap con la captura de pantalla
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
            return bitmap;
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
        return null;
    }

    private void deleteActivity(final Integer id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Borrar actividad").setMessage("¿Desea borrar esta actividad?").setIcon(R.drawable.ic_delete_black_24dp).setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
                    @Override
                    protected String doInBackground(String... args) {
                        WebRequest webreq = new WebRequest();
                        String jsonStr = webreq.makeWebServiceCall("https://apirest-mjuceda.c9users.io/actividad/"+id, WebRequest.DELETERequest);
                        return jsonStr;
                    }
                    @Override
                    protected void onPostExecute(String s) {
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.verActividad), "La actividad se ha borrado correctamente", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(VerActividad.this, MainActivity.class);// <- Poner el index para que cuando borre se vaya al index
                                startActivity(i);
                            }
                        }, 2000);

                    }
                };
                task.execute();
      }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    private void doPhotoPrint() {
        PrintHelper photoPrinter = new PrintHelper(VerActividad.this);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT); // CAPTURA DE TODA LA PANTALLA
        Bitmap bitmap = takeScreenshot();
        photoPrinter.printBitmap("actividadPDP.jpg - test print", bitmap);
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

