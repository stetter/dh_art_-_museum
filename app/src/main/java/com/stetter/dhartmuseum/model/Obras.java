package com.stetter.dhartmuseum.model;

public class Obras {

    private String obra;
    private String descrição;

    public Obras(){
    }

    public Obras(String obra, String descrição) {
        this.obra = obra;
        this.descrição = descrição;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }
}
