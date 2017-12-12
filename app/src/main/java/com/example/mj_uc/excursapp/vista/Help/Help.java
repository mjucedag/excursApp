package com.example.mj_uc.excursapp.vista.Help;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.mj_uc.excursapp.R;
import com.example.mj_uc.excursapp.contrato.ContratoHelpActivity;
import com.example.mj_uc.excursapp.dagger.HelpActivityModule;
import com.example.mj_uc.excursapp.databinding.HelpBinding;
import com.example.mj_uc.excursapp.presentador.PresentadorHelpActivity;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * The type Help.
 */
public class Help extends AppCompatActivity implements ContratoHelpActivity.Vista{

    /**
     * The Presentador.
     */
    @Inject
    ContratoHelpActivity.Presentador presentador;

    private void init() {

        HelpBinding binding = DataBindingUtil.setContentView(this, R.layout.help);

        // Inyecta las clases con Dagger. Esto solo lo tenemos aquí por simplicidad.
        ObjectGraph objectGraph = ObjectGraph.create(new HelpActivityModule());
        objectGraph.inject(this);

        presentador.setVista(this);
        binding.setPresenter((PresentadorHelpActivity) presentador);
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
