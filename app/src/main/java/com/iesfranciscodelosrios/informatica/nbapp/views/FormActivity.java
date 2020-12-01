package com.iesfranciscodelosrios.informatica.nbapp.views;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.iesfranciscodelosrios.informatica.nbapp.R;
import com.iesfranciscodelosrios.informatica.nbapp.interfaces.FormInterface;
import com.iesfranciscodelosrios.informatica.nbapp.presenters.FormPresenter;

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

import android.util.Log;
import android.widget.Spinner;

import static com.iesfranciscodelosrios.informatica.nbapp.R.id.buttonAddSpinner;
import static com.iesfranciscodelosrios.informatica.nbapp.R.id.spinnerAdd;


public class FormActivity extends AppCompatActivity implements FormInterface.View,View.OnClickListener {
    private FormInterface.Presenter presenter;
    String TAG="NBApp/FormActivity";
    private static final String CERO = "0";
    private static final String BARRA = "/";
    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int month = c.get(Calendar.MONTH);
    final int day = c.get(Calendar.DAY_OF_MONTH);
    final int year = c.get(Calendar.YEAR);

    //Widgets
    EditText DateCreation;
    ImageButton imageDate;

    private Context myContext=this;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    ImageButton buttonAdd;

    TextInputLayout TextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        final AlertDialog.Builder dialog1 = new AlertDialog.Builder(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Widget EditText donde se mostrara la fecha obtenida
        DateCreation = (EditText) findViewById(R.id.DateCreation);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        imageDate = (ImageButton) findViewById(R.id.imageDate);
        //Evento setOnClickListener - clic
        imageDate.setOnClickListener(this);

        TextName = (TextInputLayout) findViewById(R.id.textInputLayout);

        presenter = new FormPresenter(this);
        presenter.UpButton();

        Button button = findViewById(R.id.buttonSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.buttonSave();
            }
        });


        TextInputEditText name = (TextInputEditText) findViewById(R.id.name);
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    TextInputEditText et = (TextInputEditText) view;
                    Log.d("AppCRUD", et.getText().toString());
                    if (et.getText().toString().startsWith("Incorrect")) {
                        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
                        textInputLayout.setError("Name incorrect");
                    } else {
                        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
                        textInputLayout.setError("");
                    }
                }
            }
        });
        Button delete = findViewById(R.id.buttonDelete);
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog1.setTitle("ATENCION!");
                dialog1.setMessage("Want you delete this?");
                dialog1.setCancelable(false);
                dialog1.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Acept();
                    }

                });
                dialog1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int buttonEliminar) {
                        Cancel();
                    }
                });
                dialog1.show();
            }

            public void Acept() {

                finish();
            }

            public void Cancel() {


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
    public void buttonSave() {
        Log.d(TAG, "Lanzando Formulario..");
        Intent intent = new Intent(FormActivity.this, ListActivity.class);
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
                case R.id.imageDate:
                    getDate();
                    break;
            }
        }

        private void getDate(){
            DatePickerDialog pickDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                    final int ActualMonth = month + 1;
                    //Formateo el día obtenido: antepone el 0 si son menores de 10
                    String dayFormat = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                    //Formateo el mes obtenido: antepone el 0 si son menores de 10
                    String monthFormat = (ActualMonth < 10)? CERO + String.valueOf(ActualMonth):String.valueOf(ActualMonth);
                    //Muestro la fecha con el formato deseado
                    DateCreation.setText(dayFormat + BARRA + monthFormat + BARRA + year);


                }
                //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
                /**
                 *También puede cargar los valores que usted desee
                 */
            },year, month, day);
            //Muestro el widget
            pickDate.show();

        }




    }
