package com.iesfranciscodelosrios.informatica.nbapp.presenters;


import com.iesfranciscodelosrios.informatica.nbapp.interfaces.SearchInterface;

public class SearchPresenter {
    private SearchInterface.View view;

    public SearchPresenter(SearchInterface.View view) {
        this.view = view;
    }

    public void buttonBuscar(){ view.buttonBuscar();
    }

}

