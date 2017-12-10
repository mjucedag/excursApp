package com.example.mj_uc.excursapp.vista;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.mj_uc.excursapp.contrato.ContratoEditarActividad;
import com.example.mj_uc.excursapp.dagger.EditarActividadModule;
import com.example.mj_uc.excursapp.modelo.Pojo.Actividad;
import com.example.mj_uc.excursapp.modelo.Pojo.Grupo;
import com.example.mj_uc.excursapp.modelo.Pojo.ObjectJson;
import com.example.mj_uc.excursapp.modelo.Pojo.Profesor;
import com.example.mj_uc.excursapp.tools.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;

public class EditarActividad extends AppCompatActivity implements ContratoEditarActividad.Vista{

    /**
     * The Presentador.
     */
    @Inject
    ContratoEditarActividad.Presentador presentador;

    private ImageView imageView;
    private int day, month, year, hour, min, seg;
    private Calendar dateTime;
    private EditText tituloAct, lugarActividad, direccion, descripcion;
    private TextView fechaSalida, HoraLlegada, HoraSalida, profesores, grupos;
    private Button insertarImagen;
    private ObjectJson objectJson;

    public ObjectJson getObjectJson() {
        return objectJson;
    }

    public void setObjectJson(ObjectJson objectJson) {
        this.objectJson = objectJson;
    }

    private ArrayList<Integer> idGrupos = new ArrayList<>();
    private ArrayList<Integer> idprofesor=new ArrayList<>();
    private List<Profesor> NOMBREPRFESOR=new ArrayList<>();
    private List<Grupo> NOMBREGRUPO = new ArrayList<>();
    private List<String> nombresProfesores = new ArrayList<>();
    private List<String> nombresGrupos = new ArrayList<>();
    private String link;
    private String[] grupo;
    private String[] profesoresArray;
    private Actividad actividades = new Actividad();
    private ArrayList<Integer> idProfesores = new ArrayList<>();

    @Override
    public void initEditarActivity(){
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
        month+=1;

        displayImage();
        initEvents();

        presentador.recogerDatosActividad();
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

    private void setOnClickInHoraLlegada() {
        TimePickerDialog timePicker = new TimePickerDialog(EditarActividad.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                HoraLlegada.setText("");
                if (minute == 0){
                    HoraLlegada.setText(hourOfDay + ":" + minute + minute);
                }else {
                    HoraLlegada.setText(hourOfDay + ":" + minute);
                }
            }
        }, hour, min, true);
        timePicker.show();
    }

    private void setOnClickInHoraSalida() {
        TimePickerDialog timePicker = new TimePickerDialog(EditarActividad.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                HoraSalida.setText("");
                if (minute == 0){
                    HoraSalida.setText(hourOfDay + ":" + minute + minute);
                }else {
                    HoraSalida.setText(hourOfDay + ":" + minute);
                }
            }
        }, hour, min, true);
        timePicker.show();
    }

    private void setOnClickInFechaSalida() {
        DatePickerDialog datePicker = new DatePickerDialog(EditarActividad.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                fechaSalida.setText("Fecha de Salida -> " + dayOfMonth + "-" + month + "-" + year);
            }
        }, year, month, day);
        datePicker.show();
    }

    private void displayImage() {
        // RECOGEMOS LA IMAGEN SELECCIONADA
        Bundle c = getIntent().getExtras();
        String img;
        if (c != null){
            imageView.setImageResource(c.getInt("idImg"));
            actividades.setImg(c.getString("nombre"));
            tituloAct.setText(c.getString("tituloAct"));
            lugarActividad.setText(c.getString("lugar"));
            direccion.setText(c.getString("direccion"));
            profesores.setText("");
            profesores.setText(c.getString("profesores"));
            grupos.setText("");
            grupos.setText(c.getString("grupos"));
            fechaSalida.setText(c.getString("fecha"));
            HoraSalida.setText(c.getString("horaSalida"));
            HoraLlegada.setText(c.getString("horaLlegada"));
            descripcion.setText(c.getString("descripcion"));
        } else if (c == null){
            imageView.setImageResource(R.drawable.izvicono);
            actividades.setImg(Constants.DEFAULT_NAME_IMAGE);
        }
    }

    private void setOnClickInInsertarImagen() {
        presentador.goToVistaImagenes();
    }

    private void setOnClickInGrupos() {
        final ArrayList<Integer> seleccionado = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(EditarActividad.this, AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("Selecciona los grupos").setIcon(R.drawable.a1234).setMultiChoiceItems(grupo, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked){
                    seleccionado.add(which);
                } else if(seleccionado.contains(which)){
                    seleccionado.remove(Integer.valueOf(which));
                }
            }
        })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String p = "";
                        for (int i = 0; i < seleccionado.size(); i++){
                            p +=  grupo[seleccionado.get(i)];
                            if (i != grupo.length - 1){
                                p+=", ";
                            }
                        }
                        if(p == null || p.equalsIgnoreCase("")){
                            grupos.setText("Grupos(*)");
                        }else {
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(EditarActividad.this, AlertDialog.THEME_HOLO_LIGHT);
        builder.setIcon(R.drawable.a1234);
        builder.setTitle("Selecciona los profesores").setMultiChoiceItems(profesoresArray, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked){
                    seleccionado.add(which);
                } else if(seleccionado.contains(which)){
                    seleccionado.remove(Integer.valueOf(which));
                }
            }
        })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String p = "";
                        for (int i = 0; i < seleccionado.size(); i++){
                            p +=  profesoresArray[seleccionado.get(i)];
                            nombresProfesores.add(profesoresArray[seleccionado.get(i)]);
                            Log.v("xyxy" , "ARRAYNombres -> " +  nombresProfesores.toString());
                            if (i != profesoresArray.length - 1){
                                p+=", ";
                            }
                        }
                        if(p == null || p.equalsIgnoreCase("")){
                            profesores.setText("Profesores *");
                        }else {
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
    public boolean onCreateOptionsMenu(Menu menu2) {
        getMenuInflater().inflate(R.menu.menu2, menu2);
        return super.onCreateOptionsMenu(menu2);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item2) {
        int id = item2.getItemId();
        switch (id){
            case R.id.enviar:
                // COMPROBAMOS QUE TODOS LOS CAMPOS ESTEN RELLENOS (LA FOTO NO ESTÁ COMPROBADA PORQUE NO SÉ COMO LO VAMOS A PLANTEAR AÚN)
                if(profesores.getText().equals("Profesores*") || grupos.getText().equals("Grupos*") ||
                       fechaSalida.getText().equals("Fecha de Salida*") || HoraSalida.getText().equals("Hora de Salida*")
                        || HoraLlegada.getText().equals("Hora de Llegada*")){
                    Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_LONG).show();
                    String hs, hl;
                    hs = HoraSalida.getText().toString();
                    hl = HoraLlegada.getText().toString();
                    if (hs.compareTo(hl) > 0){
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_actividad);

        // Inyecta las clases con Dagger. Esto solo lo tenemos aquí por simplicidad.
        ObjectGraph objectGraph = ObjectGraph.create(new EditarActividadModule());
        objectGraph.inject(this);

        presentador.setVista(this);

        initEditarActivity();
    }

    public ContratoEditarActividad.Presentador getPresentador() {
        return presentador;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getSeg() {
        return seg;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public EditText getTituloAct() {
        return tituloAct;
    }

    public EditText getLugarActividad() {
        return lugarActividad;
    }

    public EditText getDireccion() {
        return direccion;
    }

    public EditText getDescripcion() {
        return descripcion;
    }

    public TextView getFechaSalida() {
        return fechaSalida;
    }

    public TextView getHoraLlegada() {
        return HoraLlegada;
    }

    public TextView getHoraSalida() {
        return HoraSalida;
    }

    public TextView getProfesores() {
        return profesores;
    }

    public TextView getGrupos() {
        return grupos;
    }

    public Button getInsertarImagen() {
        return insertarImagen;
    }

    public void setGrupo(String[] grupo) {
        this.grupo = grupo;
    }

    public void setProfesoresArray(String[] profesoresArray) {
        this.profesoresArray = profesoresArray;
    }

    public ArrayList<Integer> getIdGrupos() {
        return idGrupos;
    }

    public ArrayList<Integer> getIdprofesor() {
        return idprofesor;
    }

    public List<Profesor> getNOMBREPRFESOR() {
        return NOMBREPRFESOR;
    }

    public List<Grupo> getNOMBREGRUPO() {
        return NOMBREGRUPO;
    }

    public List<String> getNombresProfesores() {
        return nombresProfesores;
    }

    public List<String> getNombresGrupos() {
        return nombresGrupos;
    }

    public String getLink() {
        return link;
    }

    public String[] getGrupo() {
        return grupo;
    }

    public String[] getProfesoresArray() {
        return profesoresArray;
    }

    public Actividad getActividades() {
        return actividades;
    }

    public ArrayList<Integer> getIdProfesores() {
        return idProfesores;
    }
}
