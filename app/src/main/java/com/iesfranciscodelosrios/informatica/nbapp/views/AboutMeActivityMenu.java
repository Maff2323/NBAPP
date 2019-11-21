package com.iesfranciscodelosrios.informatica.nbapp.views;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.iesfranciscodelosrios.informatica.nbapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

public class AboutMeActivityMenu extends AppCompatActivity {
    String TAG="NBApp/AboutMeActivityMenu";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
}
