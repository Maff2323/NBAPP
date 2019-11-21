package com.iesfranciscodelosrios.informatica.nbapp.views;

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
import android.widget.Spinner;

public class SearchActivityMenu extends AppCompatActivity implements SearchInterface.View {
    String TAG = "NBApp/SearchActivityMenu";
    private SearchInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.Conferencia);
        String[] letra = {"ESTE","ESTE"};
        spinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,letra));

        presenter = new SearchPresenter(this);
        presenter.UpButton();




        Button button = findViewById(R.id.buttonBuscar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.buttonBuscar();
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


    public void buttonBuscar() {
        Log.d(TAG, "Lanzando Formulario..");
        Intent intent = new Intent(SearchActivityMenu.this, ListadoActivity.class);
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



}