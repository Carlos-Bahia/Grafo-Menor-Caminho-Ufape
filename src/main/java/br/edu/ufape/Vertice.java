package br.edu.ufape;

public class Vertice {
    private int id;
    private String pontoReferencia;
    public Vertice(int id) {
        this.id = id;
        pontoReferencia = null;
    }

    public Vertice(int id, String pontoReferencia){
        this.id = id;
        this.pontoReferencia = pontoReferencia;
    }

    public int getId() {
        return id;
    }

}
