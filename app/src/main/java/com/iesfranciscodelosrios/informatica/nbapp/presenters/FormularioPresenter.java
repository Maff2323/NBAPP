package com.iesfranciscodelosrios.informatica.nbapp.presenters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;
import com.iesfranciscodelosrios.informatica.nbapp.interfaces.FormularioInterface;
import com.iesfranciscodelosrios.informatica.nbapp.models.PersonR;
import com.iesfranciscodelosrios.informatica.nbapp.models.PersonRModel;



public class FormularioPresenter implements FormularioInterface.Presenter{

    private FormularioInterface.View view;
    private PersonRModel model;


    public FormularioPresenter(FormularioInterface.View view) {
        this.view = view;
        model =PersonRModel.getInstance();
    }


    public void botonGuardar(PersonR p){
        view.botonGuardar();
        boolean guardado=true;

        if( model.addEquipo(p)==true){

        }else{

        }
    }



    public void UpButton(){
        view.UpButton();
    }

    @Override
    public void onClickImage(Context myContext){
        int ReadPermission = ContextCompat.checkSelfPermission(myContext, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (ReadPermission != PackageManager.PERMISSION_GRANTED) {
            view.requestPermission();
            //Snackbar.make((View) view, "Sin permisos no puedes entrar", Snackbar.LENGTH_LONG).show();
        }else{

            view.launchGallery();
        }
    }

    public void resultPermission(int result){
        if (result == PackageManager.PERMISSION_GRANTED) {
            // Permiso aceptado
            Log.d("AppCRUD","Permiso Aceptado");
            //Snackbar.make((View) view, "Sin permisos no puedes entrar", Snackbar.LENGTH_LONG).show();
            view.launchGallery();


        } else {

            // Permiso rechazado
            Log.d("AppCRUD","Permiso Rechazado");
            //Snackbar
        }

            //view.showSnackbar();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

}
