package br.edu.ufape;

public class Aresta {
    private Vertice inicio;
    private Vertice destino;
    private double peso;

    public Aresta(Vertice inicio, Vertice destino, double peso){
        this.inicio = inicio;
        this.destino = destino;
        this.peso = peso;
    }

    public Vertice getInicio() {
        return this.inicio;
    }

    public void setInicio(Vertice inicio) {
        this.inicio = inicio;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}


