package com.iesfranciscodelosrios.informatica.nbapp.presenters;

import com.iesfranciscodelosrios.informatica.nbapp.interfaces.FormularioInterface;



public class FormularioPresenter implements FormularioInterface.Presenter{

    private FormularioInterface.View view;

    public FormularioPresenter(FormularioInterface.View view) {
        this.view = view;
    }


    public void botonGuardar(){
        view.botonGuardar();
    }


}
