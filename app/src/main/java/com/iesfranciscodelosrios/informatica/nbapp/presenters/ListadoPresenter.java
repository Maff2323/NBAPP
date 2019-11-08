package com.iesfranciscodelosrios.informatica.nbapp.presenters;

import com.iesfranciscodelosrios.informatica.nbapp.interfaces.ListadoInterface;

public class ListadoPresenter implements ListadoInterface.Presenter{

    private ListadoInterface.View view;

    public ListadoPresenter(ListadoInterface.View view) {
        this.view = view;
    }


    public void onClickAdd(){
        view.lanzarFormulario();
    }

}
