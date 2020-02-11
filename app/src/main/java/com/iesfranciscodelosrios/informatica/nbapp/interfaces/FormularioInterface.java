package com.iesfranciscodelosrios.informatica.nbapp.interfaces;

import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.iesfranciscodelosrios.informatica.nbapp.models.PersonR;

public interface FormularioInterface {

    public interface View {
        void botonGuardar();
        void UpButton();
        void requestPermission();
        void launchGallery();


    }
    public interface Presenter {
        void botonGuardar(PersonR p);
        void UpButton();
        void onClickImage(Context myContext);
        void resultPermission(int result);

        void onActivityResult(int requestCode, int resultCode, Intent data);
    }


}

