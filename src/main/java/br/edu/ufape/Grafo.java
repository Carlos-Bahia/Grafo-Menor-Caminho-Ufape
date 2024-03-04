package br.edu.ufape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    private List<Vertice> vertices;
    private List<Aresta> arestas;
    private Map<Vertice, List<Aresta>> listaAdjacencia;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
        this.listaAdjacencia = new HashMap<>();;
    }

    public void adicionarVertice(Vertice vertice) {
        vertices.add(vertice);
        listaAdjacencia.put(vertice, new ArrayList<>());
    }

    public void adicionarAresta(Aresta aresta) {
        arestas.add(aresta);
        listaAdjacencia.get(aresta.getInicio()).add(aresta);
    }
    public List<Vertice> getVertices() {
        return vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }
    
    public Map<Vertice, List<Aresta>> getListaAdjacencia() {
        return listaAdjacencia;
    }
}
