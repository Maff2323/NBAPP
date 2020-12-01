package com.iesfranciscodelosrios.informatica.nbapp.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;


import com.iesfranciscodelosrios.informatica.nbapp.R;
import com.iesfranciscodelosrios.informatica.nbapp.interfaces.SearchInterface;
import com.iesfranciscodelosrios.informatica.nbapp.presenters.SearchPresenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.Calendar;

public class SearchActivityMenu extends AppCompatActivity implements SearchInterface.View, View.OnClickListener{
    String TAG = "NBApp/SearchActivityMenu";
    private SearchInterface.Presenter presenter;
    private static final String CERO = "0";
    private static final String BARRA = "/";
    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int month = c.get(Calendar.MONTH);
    final int day = c.get(Calendar.DAY_OF_MONTH);
    final int year = c.get(Calendar.YEAR);

    //Widgets
    EditText DateSearch;
    ImageButton imageDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Widget EditText donde se mostrara la fecha obtenida
        DateSearch = (EditText) findViewById(R.id.DateSearch);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        imageDate = (ImageButton) findViewById(R.id.imageDate);
        //Evento setOnClickListener - clic
        imageDate.setOnClickListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.Conference);
        String[] letter = {"EAST","WEST"};
        spinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,letter));

        presenter = new SearchPresenter(this);
        presenter.UpButton();




        Button button = findViewById(R.id.buttonSearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.buttonSearch();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy...");
    }


    public void buttonSearch() {
        Log.d(TAG, "Lanzando Formulario..");
        Intent intent = new Intent(SearchActivityMenu.this, ListActivity.class);
        startActivity(intent);
    }


    public void UpButton() {
        Log.d(TAG, "Lanzando Formulario..");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed...");
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Log.d(TAG, "onSupportnavigateUp...");
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
                DateSearch.setText(dayFormat + BARRA + monthFormat + BARRA + year);


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