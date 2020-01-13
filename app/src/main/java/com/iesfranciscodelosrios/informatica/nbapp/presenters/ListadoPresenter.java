package com.iesfranciscodelosrios.informatica.nbapp.presenters;

import com.iesfranciscodelosrios.informatica.nbapp.interfaces.ListadoInterface;
import com.iesfranciscodelosrios.informatica.nbapp.models.PersonR;
import com.iesfranciscodelosrios.informatica.nbapp.models.PersonRModel;

import java.util.ArrayList;

public class ListadoPresenter implements ListadoInterface.Presenter{

    private ListadoInterface.View view;
    private PersonRModel person;


    public ListadoPresenter(ListadoInterface.View view) {
        this.view = view;
        this.person = PersonRModel.getInstance();
    }


    public void onClickAdd(){
        view.lanzarFormulario(-1);
    }

    @Override
    public ArrayList<PersonR> getAllPerson(){
        return person.getAllPerson();
    }

    @Override
    public void onClickRecyclerView(int id){
       view.lanzarFormulario(id);
    }
}
