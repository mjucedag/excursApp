package com.example.mj_uc.excursapp.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mj_uc.excursapp.R;
import com.example.mj_uc.excursapp.contrato.ContratoConfirmacionActivity;
import com.example.mj_uc.excursapp.dagger.ConfirmacionActivityModule;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * The type Confirmacion activity.
 */
public class ConfirmacionActivity extends AppCompatActivity implements ContratoConfirmacionActivity.Vista {

    private ImageView imageView;
    private Button cancelar, aceptar;
    private String titulo, dir, lugar, nomGuardados, nomGrupos, fechaGuardada, horaSalida, horaLlegada, descrip, nom;
    private int id;

    /**
     * The Presentador.
     */
    @Inject
    ContratoConfirmacionActivity.Presentador presentador;

    private void init() {
        imageView = (ImageView) findViewById(R.id.imageView);
        cancelar = (Button) findViewById(R.id.cancelar);
        aceptar = (Button) findViewById(R.id.aceptar);

        Bundle b = getIntent().getExtras();
        id = b.getInt("idImg");
        nom = b.getString("nombresImg");
        if (b != null) {
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
                presentador.confirmImage();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presentador.cancelImage();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        // Inyecta las clases con Dagger. Esto solo lo tenemos aquí por simplicidad.
        ObjectGraph objectGraph = ObjectGraph.create(new ConfirmacionActivityModule());
        objectGraph.inject(this);

        presentador.setVista(this);
        init();
    }

    /**
     * Gets image view.
     *
     * @return the image view
     */
    public ImageView getImageView() {
        return imageView;
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
     * Gets nom.
     *
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }
}
