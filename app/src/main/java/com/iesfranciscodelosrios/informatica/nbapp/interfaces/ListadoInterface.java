package com.iesfranciscodelosrios.informatica.nbapp.interfaces;

import com.iesfranciscodelosrios.informatica.nbapp.models.PersonR;

import java.util.ArrayList;

public interface ListadoInterface{

    public interface View {
        void lanzarFormulario(int id);


    }

    public interface Presenter {
        void onClickAdd();
        ArrayList<PersonR>getAllPerson();
        void onClickRecyclerView(int id);
    }
}
