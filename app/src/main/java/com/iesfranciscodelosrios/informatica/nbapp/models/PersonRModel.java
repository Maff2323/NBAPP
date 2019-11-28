package com.iesfranciscodelosrios.informatica.nbapp.models;



import java.util.ArrayList;

public class PersonRModel {



        private String TAG = "PersonRModel";

        public PersonRModel () {
        }

        public ArrayList<PersonR> getAllPerson() {
           ArrayList<PersonR> list=new ArrayList<>();
           PersonR person=new PersonR();

           person.setId(1);
           person.setnEquipo("Nets");
           person.settSalarial(1500000L);
           list.add(person);

           PersonR person2=new PersonR();
           person2.setId(2);
           person2.setnEquipo("Lakers");
           person2.settSalarial(3000000L);
           list.add(person2);



            return list;
        }



}
