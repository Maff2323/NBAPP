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

        public void setId(Integer id) {
            this.id = id;
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

        public void setnEquipo(String nEquipo) {
            this.nEquipo = nEquipo;
        }

        public Long gettSalarial() {
            return tSalarial;
        }

        public void settSalarial(Long tSalarial) {
            this.tSalarial = tSalarial;
        }
    }

