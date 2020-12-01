package com.iesfranciscodelosrios.informatica.nbapp.presenters;

import com.iesfranciscodelosrios.informatica.nbapp.interfaces.ListInterface;

public class ListPresenter implements ListInterface.Presenter{

    private ListInterface.View view;

    public ListPresenter(ListInterface.View view) {
        this.view = view;
    }


    public void onClickAdd(){
        view.launchForm();
    }

    @Override
    public void AboutUs() {
        view.launchAboutUs();
    }

    @Override
    public void Search() {
        view.launchSearch();
    }

}
