package com.iesfranciscodelosrios.informatica.nbapp.interfaces;
public interface ListInterface{

    public interface View {
        void launchForm();
        void launchAboutUs();
        void launchSearch();

    }
    public interface Presenter {
        void onClickAdd();
        void AboutUs();
        void Search();
    }
}
