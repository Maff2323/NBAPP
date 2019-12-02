package com.iesfranciscodelosrios.informatica.nbapp.interfaces;

import android.content.Context;
import android.content.pm.PackageManager;

public interface FormularioInterface {

    public interface View {
        void botonGuardar();
        void UpButton();
        void requestPermission();

    }
    public interface Presenter {
        void botonGuardar();
        void UpButton();
        void onClickImage(Context myContext);
        void resultPermission(int result);
    }


}

