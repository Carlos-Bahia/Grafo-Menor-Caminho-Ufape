package br.edu.ufape;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private int id;
    private String nome;
    private List<Aresta> arestas;

    public Vertice(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.arestas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void adicionarAresta(Aresta aresta) {
        arestas.add(aresta);
    }
}
