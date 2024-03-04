package br.edu.ufape;

public class Aresta {
	private String nomeRua;
    private Vertice inicio;
    private Vertice destino;
    private double peso;

    public Aresta(String nomeRua, Vertice inicio, Vertice destino, double peso){
    	this.nomeRua = nomeRua;
        this.inicio = inicio;
        this.destino = destino;
        this.peso = peso;
    }
    
    public String getNomeRua() {
        return nomeRua;
    }
    
    public void setNomeRua(String nomeRua) {
    	this.nomeRua = nomeRua;
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


