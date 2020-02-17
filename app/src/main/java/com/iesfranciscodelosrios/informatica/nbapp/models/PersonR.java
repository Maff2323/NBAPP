package com.iesfranciscodelosrios.informatica.nbapp.models;

import java.util.regex.Pattern;

public class PersonR {

        private Integer id = null;
        private String image = null;
        private String nEquipo = null;
        private Long tSalarial = null;
        private String fecha=null;
        private String spinner=null;
        private String nDueño=null;


        public PersonR() {


        }

        public Integer getId() {
            return id;
        }

        public boolean setId(Integer id) {

            if(id==id){
                this.id = id;
                return true;
            }else{
                return false;
            }
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getnEquipo() {
            return nEquipo;
        }

        public boolean setnEquipo(String nEquipo) {
            if(nEquipo.isEmpty()) {

                return false;
            }else{
                this.nEquipo = nEquipo;
                return true;
            }


        }
        public Long gettSalarial() {
            return tSalarial;
        }

        public boolean settSalarial(Long tSalarial) {
            String tcorrecta="^([0-9])*$";
            Pattern patron =Pattern.compile(tcorrecta);
            String tc =fecha.trim();

            if(!patron.matcher(tc).matches()){
                this.tSalarial = tSalarial;
                return false;
            }else{
                this.tSalarial = tSalarial;
                return true;
            }

        }
    public String getFecha() {
        return fecha;
    }

    public boolean setFecha(String fecha) {
        String fcorrecta="^([0-2][0-9]|3[0-1])(\\/)(0[1-9]|1[0-2])\\2(\\d{4})$";
        Pattern patron =Pattern.compile(fcorrecta);
        String fc =fecha.trim();

        if(fecha=="" || !patron.matcher(fc).matches()){
            return false;
        }else{
            this.fecha = fecha;
            return true;
        }

    }
    public String getSpinner() {
        return spinner;
    }
    public boolean setSpinner(String spinner) {

        if(spinner.isEmpty()) {
            return false;
        }else{
            this.spinner = spinner;
            return true;
        }

    }
    public String getNDueño() {
        return nDueño;
    }


    public boolean setNDueño(String nDueño) {

        if(spinner.isEmpty()) {
            return false;
        }else{
            this.spinner = spinner;
            return true;
        }

    }

    }

