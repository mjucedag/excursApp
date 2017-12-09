package com.example.mj_uc.excursapp.vista;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mj_uc.excursapp.R;
import com.example.mj_uc.excursapp.contrato.ContratoCreateActivity;
import com.example.mj_uc.excursapp.dagger.CreateActivityModule;
import com.example.mj_uc.excursapp.modelo.Pojo.Actividad;
import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;
import com.example.mj_uc.excursapp.tools.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;


/**
 * The type Create activity.
 */
public class CreateActivity extends AppCompatActivity implements ContratoCreateActivity.Vista{

    private ObjectJson objectJson;

    private int day, month, year, hour, min, seg;
    private Calendar dateTime;
    private EditText tituloAct, lugarActividad, direccion, descripcion;
    private TextView fechaSalida, HoraLlegada, HoraSalida, profesores, grupos;
    private Button insertarImagen;

    private ImageView imageView;
    private Actividad actividad = new Actividad();
    private String[] gruposArray;
    private String[] profesoresArray;

    private List<String> NomProfesores = new ArrayList<>();
    private List<String> NomGrupos = new ArrayList<>();

    /**
     * The Presentador.
     */
    @Inject
    ContratoCreateActivity.Presentador presentador;

    @Override
    public void initCreateActivity() {
        // DECLARACION
        tituloAct = (EditText) findViewById(R.id.tituloAct);
        profesores = (TextView) findViewById(R.id.profesores);
        grupos = (TextView) findViewById(R.id.grupos);
        lugarActividad = (EditText) findViewById(R.id.lugarActividad);
        direccion = (EditText) findViewById(R.id.direccion);
        imageView = (ImageView) findViewById(R.id.imageView);
        descripcion = (EditText) findViewById(R.id.descripcion);
        insertarImagen = (Button) findViewById(R.id.insertarImagen);
        fechaSalida = (TextView) findViewById(R.id.fechaSalida);
        HoraSalida = (TextView) findViewById(R.id.horaSalida);
        HoraLlegada = (TextView) findViewById(R.id.HoraLlegada);
        // INSTANCIAMOS EL CALENDARIO
        dateTime = Calendar.getInstance();
        day = dateTime.get(Calendar.DAY_OF_MONTH);
        month = dateTime.get(Calendar.MONTH);
        year = dateTime.get(Calendar.YEAR);
        hour = dateTime.get(Calendar.HOUR_OF_DAY);
        min = dateTime.get(Calendar.MINUTE);
        // LE SUMAMOS + 1. ES NECESARIO PARA EL DATEPICKER
        month += 1;

        displayImage();
        initEvents();

        // COMPROBAMOS QUE LA HORA DE SALIDA ESTA PUESTA PARA PODER HABILITAR LA HORA DE LLEGADA
        if (HoraSalida.getText().equals("")) {
            HoraLlegada.setEnabled(false);
        } else {
            HoraLlegada.setEnabled(true);
        }

        // Inyecta las clases con Dagger. Esto solo lo tenemos aquí por simplicidad.
        ObjectGraph objectGraph = ObjectGraph.create(new CreateActivityModule());
        objectGraph.inject(this);

        presentador.setVista(this);
        presentador.prepareView();
    }

    private void initEvents() {
        profesores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClickInProfesores();
            }
        });
        grupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClickInGrupos();
            }
        });
        insertarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClickInInsertarImagen();
            }
        });
        fechaSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClickInFechaSalida();
            }
        });
        HoraSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClickInHoraSalida();
            }
        });
        HoraLlegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClickInHoraLlegada();
            }
        });
    }

    private void displayImage() {
        Bundle b = getIntent().getExtras();

        if (b != null) {
            imageView.setImageResource(b.getInt("idImg"));
            actividad.setImg(b.getString("nombre"));
            tituloAct.setText(b.getString("tituloAct"));
            lugarActividad.setText(b.getString("lugar"));
            direccion.setText(b.getString("direccion"));
            profesores.setText(b.getString("profesores"));
            grupos.setText(b.getString("grupos"));
            fechaSalida.setText(b.getString("fecha"));
            HoraSalida.setText(b.getString("horaSalida"));
            HoraLlegada.setText(b.getString("horaLlegada"));
            descripcion.setText(b.getString("descripcion"));
        } else {
            imageView.setImageResource(R.drawable.izvicono);
            actividad.setImg(Constants.DEFAULT_NAME_IMAGE);
        }
    }

    private void setOnClickInInsertarImagen() {
        /*Intent i = new Intent(CreateActivity.this, VistaImagenes.class);
        i.putExtra("tituloAct", tituloAct.getText().toString());
        i.putExtra("lugar", lugarActividad.getText().toString());
        i.putExtra("direccion", direccion.getText().toString());
        i.putExtra("profesores", profesores.getText().toString());
        i.putExtra("grupos", grupos.getText().toString());
        i.putExtra("fecha", fechaSalida.getText().toString());
        i.putExtra("horaSalida", HoraSalida.getText().toString());
        i.putExtra("horaLlegada", HoraLlegada.getText().toString());
        i.putExtra("descripcion", descripcion.getText().toString());
        startActivity(i);*/
    }

    private void setOnClickInHoraLlegada() {
        TimePickerDialog timePicker = new TimePickerDialog(CreateActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                HoraLlegada.setText("");
                if (minute == 0) {
                    HoraLlegada.setText(hourOfDay + ":" + minute + minute);
                } else {
                    HoraLlegada.setText(hourOfDay + ":" + minute);
                }
            }
        }, hour, min, true);
        timePicker.show();
    }

    private void setOnClickInHoraSalida() {
        TimePickerDialog timePicker = new TimePickerDialog(CreateActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                HoraSalida.setText("");
                if (minute == 0) {
                    HoraSalida.setText(hourOfDay + ":" + minute + minute);
                } else {
                    HoraSalida.setText(hourOfDay + ":" + minute);
                }
            }
        }, hour, min, true);
        timePicker.show();
    }

    private void setOnClickInFechaSalida() {
        DatePickerDialog datePicker = new DatePickerDialog(CreateActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                fechaSalida.setText("Fecha de Salida -> " + dayOfMonth + "-" + month + "-" + year);
            }
        }, year, month, day);
        datePicker.show();
    }

    private void setOnClickInGrupos() {
        final ArrayList<Integer> seleccionado = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateActivity.this, AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("Selecciona los grupos").setIcon(R.drawable.a1234).setMultiChoiceItems(gruposArray, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    seleccionado.add(which);
                } else if (seleccionado.contains(which)) {
                    seleccionado.remove(Integer.valueOf(which));

                }
            }
        }).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String p = "";
                for (int i = 0; i < seleccionado.size(); i++) {
                    p += gruposArray[seleccionado.get(i)];
                    NomGrupos.add(gruposArray[seleccionado.get(i)]);
                    if (i != gruposArray.length - 1) {
                        p += ", ";
                    }
                }
                if ("".equalsIgnoreCase(p)) {
                    grupos.setText("Grupos(*)");
                } else {
                    grupos.setText(p);
                }
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }

    private void setOnClickInProfesores() {
        final ArrayList<Integer> seleccionado = new ArrayList<>();
        final AlertDialog.Builder builder = new AlertDialog.Builder(CreateActivity.this, AlertDialog.THEME_HOLO_LIGHT);
        builder.setIcon(R.drawable.a1234);
        builder.setTitle("Selecciona los profesores").setMultiChoiceItems(profesoresArray, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    seleccionado.add(which);
                } else if (seleccionado.contains(which)) {
                    seleccionado.remove(Integer.valueOf(which));
                }
            }
        }).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String p = "";
                for (int i = 0; i < seleccionado.size(); i++) {
                    p += profesoresArray[seleccionado.get(i)];
                    NomProfesores.add(profesoresArray[seleccionado.get(i)]);
                    Log.v("xyxy", "ARRAYNombres -> " + NomProfesores.toString());
                    if (i != profesoresArray.length - 1) {
                        p += ", ";
                    }
                }
                if ("".equalsIgnoreCase(p)) {
                    profesores.setText("Profesores *");
                } else {
                    profesores.setText(p);
                }
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        initCreateActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu2) {
        getMenuInflater().inflate(R.menu.menu2, menu2);
        return super.onCreateOptionsMenu(menu2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item2) {
        int id = item2.getItemId();

        switch (id) {
            case R.id.enviar:
                // COMPROBAMOS QUE TODOS LOS CAMPOS ESTEN RELLENOS (LA FOTO NO ESTÁ COMPROBADA PORQUE NO SÉ COMO LO VAMOS A PLANTEAR AÚN)
                if (profesores.getText().equals("Profesores*") || grupos.getText().equals("Grupos*") ||
                        fechaSalida.getText().equals("Fecha de Salida*") || HoraSalida.getText().equals("Hora de Salida*")
                        || HoraLlegada.getText().equals("Hora de Llegada*")) {
                    Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_LONG).show();
                    String hs, hl;
                    hs = HoraSalida.getText().toString();
                    hl = HoraLlegada.getText().toString();
                    if (hs.compareTo(hl) > 0) {
                        Toast.makeText(this, "ERROR, La llegada no puede ser antes que la salida", Toast.LENGTH_LONG).show();
                    }

                } else {
                    presentador.saveActivity();
                }
                break;
        }
        return super.onOptionsItemSelected(item2);
    }

    @Override
    protected void onSaveInstanceState(Bundle guardaEstado) {
        super.onSaveInstanceState(guardaEstado);
        // Guardamos las variables
        String titulo = tituloAct.getText().toString();
        String dir = direccion.getText().toString();
        String lugar = lugarActividad.getText().toString();
        String nomGuardados = profesores.getText().toString();
        String nomGrupos = grupos.getText().toString();
        String fechaGuardada = fechaSalida.getText().toString();
        String horaSalida = HoraSalida.getText().toString();
        String horaLlegada = HoraLlegada.getText().toString();
        String descrip = descripcion.getText().toString();
        // Almacenamos en el Bundle
        guardaEstado.putString("titulo", titulo);
        guardaEstado.putString("dir", dir);
        guardaEstado.putString("lugar", lugar);
        guardaEstado.putString("prof", nomGuardados);
        guardaEstado.putString("grupos", nomGrupos);
        guardaEstado.putString("fecha", fechaGuardada);
        guardaEstado.putString("horaSalida", horaSalida);
        guardaEstado.putString("horaLlegada", horaLlegada);
        guardaEstado.putString("descrip", descrip);
    }

    @Override
    protected void onRestoreInstanceState(Bundle recuperaEstado) {
        super.onRestoreInstanceState(recuperaEstado);
        // Recuperamos los datos
        String titulo = recuperaEstado.getString("titulo");
        String dir = recuperaEstado.getString("dir");
        String lugar = recuperaEstado.getString("lugar");
        String nomGuardados = recuperaEstado.getString("prof");
        String nomGrupos = recuperaEstado.getString("grupos");
        String fechaGuardada = recuperaEstado.getString("fecha");
        String horaSalida = recuperaEstado.getString("horaSalida");
        String horaLlegada = recuperaEstado.getString("horaLlegada");
        String descrip = recuperaEstado.getString("descrip");
        // Los insertamos
        tituloAct.setText(titulo);
        direccion.setText(dir);
        lugarActividad.setText(lugar);
        profesores.setText(nomGuardados);
        grupos.setText(nomGrupos);
        fechaSalida.setText(fechaGuardada);
        HoraSalida.setText(horaSalida);
        HoraLlegada.setText(horaLlegada);
        descripcion.setText(descrip);
    }


    /**
     * Gets object json.
     *
     * @return the object json
     */
    public ObjectJson getObjectJson() {
        return objectJson;
    }

    /**
     * Sets object json.
     *
     * @param objectJson the object json
     */
    public void setObjectJson(ObjectJson objectJson) {
        this.objectJson = objectJson;
    }

    /**
     * Gets grupos.
     *
     * @return the grupos
     */
    public TextView getGrupos() {
        return grupos;
    }

    /**
     * Sets grupos.
     *
     * @param grupos the grupos
     */
    public void setGrupos(TextView grupos) {
        this.grupos = grupos;
    }

    /**
     * Gets actividad.
     *
     * @return the actividad
     */
    public Actividad getActividad() {
        return actividad;
    }

    /**
     * Sets actividad.
     *
     * @param actividad the actividad
     */
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }


    /**
     * Sets grupos array.
     *
     * @param gruposArray the grupos array
     */
    public void setGruposArray(String[] gruposArray) {
        this.gruposArray = gruposArray;
    }


    /**
     * Sets profesores array.
     *
     * @param profesoresArray the profesores array
     */
    public void setProfesoresArray(String[] profesoresArray) {
        this.profesoresArray = profesoresArray;
    }

    /**
     * Gets presentador.
     *
     * @return the presentador
     */
    public ContratoCreateActivity.Presentador getPresentador() {
        return presentador;
    }

    /**
     * Sets presentador.
     *
     * @param presentador the presentador
     */
    public void setPresentador(ContratoCreateActivity.Presentador presentador) {
        this.presentador = presentador;
    }

    /**
     * Gets titulo act.
     *
     * @return the titulo act
     */
    public EditText getTituloAct() {
        return tituloAct;
    }

    /**
     * Gets lugar actividad.
     *
     * @return the lugar actividad
     */
    public EditText getLugarActividad() {
        return lugarActividad;
    }

    /**
     * Gets direccion.
     *
     * @return the direccion
     */
    public EditText getDireccion() {
        return direccion;
    }

    /**
     * Gets descripcion.
     *
     * @return the descripcion
     */
    public EditText getDescripcion() {
        return descripcion;
    }

    /**
     * Gets fecha salida.
     *
     * @return the fecha salida
     */
    public TextView getFechaSalida() {
        return fechaSalida;
    }

    /**
     * Gets hora llegada.
     *
     * @return the hora llegada
     */
    public TextView getHoraLlegada() {
        return HoraLlegada;
    }

    /**
     * Gets hora salida.
     *
     * @return the hora salida
     */
    public TextView getHoraSalida() {
        return HoraSalida;
    }

    /**
     * Gets nom profesores.
     *
     * @return the nom profesores
     */
    public List<String> getNomProfesores() {
        return NomProfesores;
    }

    /**
     * Gets nom grupos.
     *
     * @return the nom grupos
     */
    public List<String> getNomGrupos() {
        return NomGrupos;
    }
}
