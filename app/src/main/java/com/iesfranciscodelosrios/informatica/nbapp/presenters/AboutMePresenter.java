package com.iesfranciscodelosrios.informatica.nbapp.presenters;

import com.iesfranciscodelosrios.informatica.nbapp.interfaces.AboutMeInterface;

public class AboutMePresenter implements AboutMeInterface.Presenter{

    private AboutMeInterface.View view;

    public AboutMePresenter(AboutMeInterface.View view) {
        this.view = view;
    }

    public void UpButton(){
        view.UpButton();
    }

}


