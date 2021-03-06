package com.iesfranciscodelosrios.informatica.nbapp.views;


import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.iesfranciscodelosrios.informatica.nbapp.R;
import com.iesfranciscodelosrios.informatica.nbapp.interfaces.ListadoInterface;
import com.iesfranciscodelosrios.informatica.nbapp.models.PersonR;
import com.iesfranciscodelosrios.informatica.nbapp.presenters.ListadoPresenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity implements ListadoInterface.View {

    String TAG="NBApp/ListadoActivity";
    private ListadoInterface.Presenter presenter;
    private RecyclerView recyclerView;
    private ArrayList<PersonR> items;
    private PersonAdapter adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter =new ListadoPresenter(this);


        // Inicializa el RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.listadoRecyclerView);

        // Crea el Adaptador con los datos de la lista anterior
         items = presenter.getAllPerson();
         adaptador = new PersonAdapter(items);


        // Asocia el elemento de la lista con una acción al ser pulsado
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al pulsar el elemento
                int position = recyclerView.getChildAdapterPosition(v);
                Log.d(TAG,"Click RV: " + items.get(position).getId().toString());
                presenter.onClickRecyclerView(items.get(position).getId());
            }
        });


        // Asocia el Adaptador al RecyclerView
        recyclerView.setAdapter(adaptador);

        // Muestra el RecyclerView en vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // BOTÓN!
        FloatingActionButton fab = findViewById(R.id.ListadoAnadir); //Encontrar vista por su ID
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Pulsando botón flotante..");
                presenter.onClickAdd();


            }
        });



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
    public void lanzarFormulario(int id) {
        Log.d(TAG,"Lanzando Formulario..");
        if(id==-1) {
            Intent intent = new Intent(ListadoActivity.this, FormularioActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(ListadoActivity.this, FormularioActivity.class);
            startActivity(intent);
            //BUNDLE
        }
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.i("", "Nuevo!");
                Intent intent = new Intent(ListadoActivity.this, AboutMeActivityMenu.class);
                startActivity(intent);
                return true;
            case R.id.action_settingsConfig:
                Log.i("", "Config!");
                return true;

            case R.id.action_settingsOrder:
                Log.i("", "Order!");
                return true;

            case R.id.action_settingsSearch:
                Log.i("", "Search!");
                Intent intentS= new Intent(ListadoActivity.this, SearchActivityMenu.class);
                startActivity(intentS);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

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

}
