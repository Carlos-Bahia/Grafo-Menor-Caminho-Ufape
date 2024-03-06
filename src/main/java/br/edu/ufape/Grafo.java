package br.edu.ufape;

import java.util.*;

public class Grafo {
    private List<Vertice> vertices;
    private Map<Integer, Vertice> verticesMap;
    private Map<String, Vertice> verticePontoMap;
    private List<Aresta> arestas;
    private Map<Vertice, List<Aresta>> listaAdjacencia;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.verticesMap = new HashMap<>();
        this.verticePontoMap = new HashMap<>();
        this.arestas = new ArrayList<>();
        this.listaAdjacencia = new HashMap<>();;
    }

    public Vertice procurarVertice(int id){
        return verticesMap.get(id);
    }

    public Vertice procurarVerticeString(String nome){
        return verticePontoMap.get(nome);
    }
    public void adicionarVertice(Vertice vertice) {
        vertices.add(vertice);
        verticesMap.put(vertice.getId(), vertice);
        if(vertice.getPontoReferencia() != null){
            verticePontoMap.put(vertice.getPontoReferencia().toLowerCase(), vertice);
        }
        listaAdjacencia.put(vertice, new ArrayList<>());
    }

    public void adicionarAresta(Aresta aresta) {
        if(aresta.getInicio() != aresta.getDestino()) {
            arestas.add(aresta);
            int flag = 0;
            for (Aresta edge : getListaAdjacencia().get(aresta.getInicio())) {

                if ((edge.getInicio() == aresta.getInicio() && edge.getDestino() == aresta.getDestino()) ||
                        edge.getInicio() == aresta.getDestino() && edge.getDestino() == aresta.getInicio()) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                listaAdjacencia.get(aresta.getInicio()).add(aresta);
                listaAdjacencia.get(aresta.getDestino()).add(aresta);
            }
        }
    }
    public List<Vertice> getVertices() {
        return vertices;
    }

    public void removerAresta(int origem, int destino) {
        List<Aresta> arestas = this.getArestas(); // Cópia da lista
        Iterator<Aresta> iterator = arestas.iterator();

        while (iterator.hasNext()) {
            Aresta aresta = iterator.next();
            if ((aresta.getInicio().getId() == origem && aresta.getDestino().getId() == destino) ||
                    (aresta.getInicio().getId() == destino && aresta.getDestino().getId() == origem)) {
                this.getListaAdjacencia().get(aresta.getInicio()).remove(aresta);
                this.getListaAdjacencia().get(aresta.getDestino()).remove(aresta);
                iterator.remove();
            }
        }
    }

        public void removerVertice(Vertice vertice){
        if (vertice != null) {
            List<Aresta> arestasARemover = new ArrayList<>(getListaAdjacencia().get(vertice));
            for (Aresta aresta : arestasARemover) {
                if (aresta.getInicio() == vertice) {
                    getListaAdjacencia().get(aresta.getDestino()).remove(aresta);
                } else {
                    getListaAdjacencia().get(aresta.getInicio()).remove(aresta);
                }
                getListaAdjacencia().get(vertice).remove(aresta);
                arestas.remove(aresta);
            }
            vertices.remove(vertice);
        }
    }

    public List<Aresta> getArestas() {
        return arestas;
    }
    
    public Map<Vertice, List<Aresta>> getListaAdjacencia() {
        return listaAdjacencia;
    }
}
