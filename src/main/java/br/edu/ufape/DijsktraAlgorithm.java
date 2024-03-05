package br.edu.ufape;

import java.util.*;

public class DijsktraAlgorithm {
    public static List<Vertice> menorCaminhoVertices(Grafo grafo, Vertice origem, Vertice destino) {
        Map<Vertice, Double> distancia = new HashMap<>();
        Map<Vertice, Vertice> predecessores = new HashMap<>();
        PriorityQueue<Vertice> filaPrioridade = new PriorityQueue<>(Comparator.comparingDouble(distancia::get));

        for (Vertice vertice : grafo.getVertices()) {
            if (vertice.equals(origem))
                distancia.put(vertice, 0.0);
            else
                distancia.put(vertice, Double.MAX_VALUE);
            predecessores.put(vertice, null);
            filaPrioridade.add(vertice);
        }

        while (!filaPrioridade.isEmpty()) {
            Vertice verticeAtual = filaPrioridade.poll();
            if (verticeAtual.equals(destino))
                break;

            for (Aresta aresta : grafo.getListaAdjacencia().get(verticeAtual)) {
                Vertice vizinho = aresta.getInicio().equals(verticeAtual) ? aresta.getDestino() : aresta.getInicio();
                double pesoTotal = distancia.get(verticeAtual) + aresta.getPeso();
                if (pesoTotal < distancia.get(vizinho)) {
                    distancia.put(vizinho, pesoTotal);
                    predecessores.put(vizinho, verticeAtual);
                    filaPrioridade.remove(vizinho); // Removendo e adicionando o vizinho para atualizar a fila de prioridade
                    filaPrioridade.add(vizinho);
                }
            }
        }

        List<Vertice> caminho = new ArrayList<>();
        Vertice verticeAtual = destino;
        while (verticeAtual != null) {
            caminho.add(verticeAtual);
            verticeAtual = predecessores.get(verticeAtual);
        }
        Collections.reverse(caminho);

        return caminho;
    }


    public static List<Aresta> menorCaminhoArestas(Grafo grafo, List<Vertice> verticesCaminho) {
        List<Aresta> arestasCaminho = new ArrayList<>();

        for (int i = 0; i < verticesCaminho.size() - 1; i++) {
            Vertice verticeAtual = verticesCaminho.get(i);
            Vertice proximoVertice = verticesCaminho.get(i + 1);
            for (Aresta aresta : grafo.getListaAdjacencia().get(verticeAtual)) {
                if ((aresta.getInicio().equals(verticeAtual) && aresta.getDestino().equals(proximoVertice)) ||
                        (aresta.getInicio().equals(proximoVertice) && aresta.getDestino().equals(verticeAtual))) {
                    arestasCaminho.add(aresta);
                    break;
                }
            }
        }

        return arestasCaminho;
    }
}
