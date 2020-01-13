package com.iesfranciscodelosrios.informatica.nbapp.models;

public class PersonR {

        private Integer id = null;
        private String image = null;
        private String nEquipo = null;
        private Long tSalarial = null;

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
            if(nEquipo==""){
            this.nEquipo = nEquipo;
            return true;
        }else{
            return false;
            }
        }
        public Long gettSalarial() {
            return tSalarial;
        }

        public boolean settSalarial(Long tSalarial) {
            if(tSalarial==tSalarial){
                this.tSalarial = tSalarial;
                return true;
            }else{
                return false;
            }

        }
    }

