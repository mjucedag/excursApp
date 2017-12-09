package com.example.mj_uc.excursapp.vista.Help;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mj_uc.excursapp.R;

/**
 * The type Help.
 */
public class Help extends AppCompatActivity {

    private ImageButton crearImg;
    private TextView crear;
    private ImageButton filtroImg;
    private TextView filtro;
    private ImageButton imprimirImg;
    private TextView imprimir;
    private ImageButton editarImg;
    private TextView editar;
    private ImageButton borrarImg;
    private TextView borrar;

    private void init() {
        crearImg = (ImageButton) findViewById(R.id.crearImg);
        crear = (TextView) findViewById(R.id.crear);
        filtroImg = (ImageButton)findViewById(R.id.filtrarImg);
        filtro = (TextView)findViewById(R.id.filtrar);
        imprimirImg = (ImageButton)findViewById(R.id.imprimirImg);
        imprimir = (TextView) findViewById(R.id.imprimir);
        editarImg =  (ImageButton) findViewById(R.id.editarImg);
        editar = (TextView) findViewById(R.id.editar);
        borrarImg = (ImageButton)findViewById(R.id.borrarImg);
        borrar = (TextView) findViewById(R.id.borrar);

        crearImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Help.this, HelpCreate.class);
                startActivity(i);
            }
        });
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Help.this, HelpCreate.class);
                startActivity(i);
            }
        });
        filtroImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Help.this, HelpSearch.class);
                startActivity(i);
            }
        });
        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Help.this, HelpSearch.class);
                startActivity(i);
            }
        });
        imprimirImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Help.this, HelpPrint.class);
                startActivity(i);
            }
        });
        imprimir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Help.this, HelpPrint.class);
                startActivity(i);
            }
        });
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Help.this, HelpEdit.class);
                startActivity(i);
            }
        });
        editarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Help.this, HelpEdit.class);
                startActivity(i);
            }
        });
        borrarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Help.this, HelpDelete.class);
                startActivity(i);
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Help.this, HelpDelete.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
