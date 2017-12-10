package com.example.mj_uc.excursapp.vista;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mj_uc.excursapp.R;
import com.example.mj_uc.excursapp.contrato.ContratoConsultaFecha;
import com.example.mj_uc.excursapp.dagger.ConsultaFechaModule;
import com.example.mj_uc.excursapp.modelo.Adapter.AlbumAdapter;
import com.example.mj_uc.excursapp.modelo.Album;
import com.example.mj_uc.excursapp.tools.Tools;
import com.example.mj_uc.excursapp.vista.RecycleViewTools.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * The type Consulta fecha.
 */
public class ConsultaFecha extends AppCompatActivity implements ContratoConsultaFecha.Vista{

    private RecyclerView recyclerView;
    private AlbumAdapter adapter;
    private List<Album> albumList;
    private FloatingActionButton fab;
    private NestedScrollView nestedScrollView;

    /**
     * The Presentador.
     */
    @Inject
    ContratoConsultaFecha.Presentador presentador;

    /**
     * Gets adapter.
     *
     * @return the adapter
     */
    public AlbumAdapter getAdapter() {
        return adapter;
    }

    /**
     * Gets album list.
     *
     * @return the album list
     */
    public List<Album> getAlbumList() {
        return albumList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_fecha);

        // Inyecta las clases con Dagger. Esto solo lo tenemos aquí por simplicidad.
        ObjectGraph objectGraph = ObjectGraph.create(new ConsultaFechaModule());
        objectGraph.inject(this);

        presentador.setVista(this);

        nestedScrollView = (NestedScrollView) findViewById(R.id.scroll_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        albumList = new ArrayList<>();
        adapter = new AlbumAdapter(this, albumList);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //pone la flecha

        initializeRecyclerView();

        //botón flotante
        initializeFabButton();

        presentador.doQueryConsult();
    }

    /**
     * Initialize recycler view.
     */
    public void initializeRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, Tools.dpToPx(10, this), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    /**
     * Initialize fab button.
     */
    public void initializeFabButton() {
        fab = (FloatingActionButton) findViewById(R.id.fab);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == 0) {
                    fab.hide();
                }else{
                    fab.show();
                }
            }
        });

        fab.hide();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //te lleva al principio de la pagina
                NestedScrollView scrollView= (NestedScrollView) findViewById(R.id.scroll_view);
                scrollView.setScrollY(0);
            }
        });
    }
}
