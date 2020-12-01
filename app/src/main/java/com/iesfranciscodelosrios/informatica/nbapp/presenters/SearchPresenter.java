package com.iesfranciscodelosrios.informatica.nbapp.presenters;


import com.iesfranciscodelosrios.informatica.nbapp.interfaces.SearchInterface;

public class SearchPresenter implements SearchInterface.Presenter{
    private SearchInterface.View view;

    public SearchPresenter(SearchInterface.View view) {
        this.view = view;
    }

    public void buttonSearch(){
        view.buttonSearch();
    }
    public void UpButton(){
        view.UpButton();
    }

}

