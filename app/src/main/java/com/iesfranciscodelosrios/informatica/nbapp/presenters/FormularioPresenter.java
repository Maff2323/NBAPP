package com.iesfranciscodelosrios.informatica.nbapp.presenters;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.iesfranciscodelosrios.informatica.nbapp.interfaces.FormularioInterface;

public class FormularioPresenter implements FormularioInterface.Presenter{

    private FormularioInterface.View view;

    public FormularioPresenter(FormularioInterface.View view) {
        this.view = view;
    }


    public void botonGuardar(){
        view.botonGuardar();
    }

    public void UpButton(){
        view.UpButton();
    }

    @Override
    public void onClickImage(Context myContext){
        int ReadPermission = ContextCompat.checkSelfPermission(myContext, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (ReadPermission != PackageManager.PERMISSION_GRANTED) {
            view.requestPermission();
        }else{
            //view.launchGallery();
        }
    }
    public void resultPermission(int result){
        if (result == PackageManager.PERMISSION_GRANTED) {
            // Permiso aceptado
            Log.d("AppCRUD","Permiso Aceptado");
            //view.launchGallery();

        } else {
            // Permiso rechazado
            Log.d("AppCRUD","Permiso Rechazado");
            //Snackbar
            //view.showSnackbar("Sin permisos no puedes entrar")
        }
    }
}
