package com.iesfranciscodelosrios.informatica.nbapp.presenters;

import com.iesfranciscodelosrios.informatica.nbapp.interfaces.FormInterface;

public class FormPresenter implements FormInterface.Presenter{

    private FormInterface.View view;

    public FormPresenter(FormInterface.View view) {
        this.view = view;
    }


    public void buttonSave(){
        view.buttonSave();
    }
    public void UpButton(){
        view.UpButton();
    }

}
