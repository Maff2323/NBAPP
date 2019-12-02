package com.iesfranciscodelosrios.informatica.nbapp.views;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.iesfranciscodelosrios.informatica.nbapp.R;
import com.iesfranciscodelosrios.informatica.nbapp.interfaces.FormularioInterface;
import com.iesfranciscodelosrios.informatica.nbapp.presenters.FormularioPresenter;

import android.app.DatePickerDialog;;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.widget.ImageView;
import android.widget.Spinner;

import static com.iesfranciscodelosrios.informatica.nbapp.R.id.buttonAddSpinner;
import static com.iesfranciscodelosrios.informatica.nbapp.R.id.imageButton;
import static com.iesfranciscodelosrios.informatica.nbapp.R.id.spinnerAdd;


public class FormularioActivity extends AppCompatActivity implements FormularioInterface.View,View.OnClickListener {
    private FormularioInterface.Presenter presenter;
    String TAG="NBApp/FormularioActivity";
    private static final String CERO = "0";
    private static final String BARRA = "/";
    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Widgets
    EditText Fechadecreación;
    ImageButton imageFecha;

    private Context myContext=this;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    ImageButton buttonAdd;

    TextInputLayout TextNombre;

    ImageButton imageButton;

    final private int CODE_READ_EXTERNAL_STORAGE_PERMISSION=123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        final AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Widget EditText donde se mostrara la fecha obtenida
        Fechadecreación = (EditText) findViewById(R.id.Fechadecreación);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        imageFecha = (ImageButton) findViewById(R.id.imageFecha);
        //Evento setOnClickListener - clic
        imageFecha.setOnClickListener(this);

        TextNombre = (TextInputLayout) findViewById(R.id.textInputLayout);

        imageButton=(ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                presenter.onClickImage(myContext);
            }
        });


        presenter = new FormularioPresenter(this);
        presenter.UpButton();

        Button button = findViewById(R.id.botonGuardar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.botonGuardar();
            }
        });


        TextInputEditText nombre = (TextInputEditText) findViewById(R.id.nombre);
        nombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    TextInputEditText et = (TextInputEditText) view;
                    Log.d("AppCRUD", et.getText().toString());
                    if (et.getText().toString().startsWith("Incorrecto")) {
                        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
                        textInputLayout.setError("Nombre incorrecto");
                    } else {
                        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
                        textInputLayout.setError("");
                    }
                }
            }
        });
        Button eliminar = findViewById(R.id.buttonEliminar);
        eliminar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialogo1.setTitle("ATENCION!");
                dialogo1.setMessage("Va a eliminar los datos introducidos esta seguro?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Aceptar();
                    }

                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int buttonEliminar) {
                        Cancelar();
                    }
                });
                dialogo1.show();
            }

            public void Aceptar() {

                finish();
            }

            public void Cancelar() {


            }
        });

        ArrayList<String> items = new ArrayList<String>();


        // Definición del Adaptador que contiene la lista de opciones
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // Definición del Spinner
        spinner = (Spinner) findViewById(spinnerAdd);
        spinner.setAdapter(adapter);

        // Definición de la acción del botón
        buttonAdd = (ImageButton) findViewById(buttonAddSpinner);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                // Recuperación de la vista del AlertDialog a partir del layout de la Actividad
                LayoutInflater layoutActivity = LayoutInflater.from(myContext);
                View viewAlertDialog = layoutActivity.inflate(R.layout.alert_dialog, null);

                // Definición del AlertDialog
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(myContext);

                // Asignación del AlertDialog a su vista
                alertDialog.setView(viewAlertDialog);

                // Recuperación del EditText del AlertDialog
                final EditText dialogInput = (EditText) viewAlertDialog.findViewById(R.id.dialogInput);

                // Configuración del AlertDialog
                alertDialog
                        .setCancelable(false)
                        // Botón Añadir
                        .setPositiveButton(getResources().getString(R.string.add_new_option),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        adapter.add(dialogInput.getText().toString());
                                        spinner.setSelection(adapter.getPosition(dialogInput.getText().toString()));
                                    }
                                })
                        // Botón Cancelar
                        .setNegativeButton(getResources().getString(R.string.cancel),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                })
                        .create()
                        .show();
            }
        });



    }
    @Override
    public void botonGuardar() {
        Log.d(TAG, "Lanzando Formulario..");
        Intent intent = new Intent(FormularioActivity.this, ListadoActivity.class);
        startActivity(intent);
    }


    @Override
    protected  void onStart(){
        super.onStart();
        Log.d(TAG,"onStart...");
    }
    @Override
    protected  void onResume(){
        super.onResume();
        Log.d(TAG,"onResume...");
    }
    @Override
    protected  void onPause(){
        super.onPause();
        Log.d(TAG,"onPause...");
    }
    @Override
    protected  void onStop(){
        super.onStop();
        Log.d(TAG,"onStop...");
    }
    @Override
    protected  void onRestart(){
        super.onRestart();
        Log.d(TAG,"onRestart...");
    }
    @Override
    protected  void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy...");
    }


    @Override
    public void UpButton() {
        Log.d(TAG,"Lanzando Formulario..");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG,"onBackPressed...");
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Log.d(TAG,"onSupportnavigateUp...");
        return super.onSupportNavigateUp();
    }




        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imageFecha:
                    obtenerFecha();
                    break;
            }
        }

        private void obtenerFecha(){
            DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                    final int mesActual = month + 1;
                    //Formateo el día obtenido: antepone el 0 si son menores de 10
                    String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                    //Formateo el mes obtenido: antepone el 0 si son menores de 10
                    String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                    //Muestro la fecha con el formato deseado
                    Fechadecreación.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


                }
                //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
                /**
                 *También puede cargar los valores que usted desee
                 */
            },anio, mes, dia);
            //Muestro el widget
            recogerFecha.show();

        }
    public void onClickImage(View v) {
        presenter.onClickImage(myContext);

    }
    public void requestPermission(){
        ActivityCompat.requestPermissions(FormularioActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, CODE_READ_EXTERNAL_STORAGE_PERMISSION);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CODE_READ_EXTERNAL_STORAGE_PERMISSION:
                presenter.resultPermission(grantResults[0]);
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    }
