package br.edu.ufape;

import java.util.*;

public class DijsktraAlgorithm {
    public static List<Vertice> menorCaminhoVertices(Grafo grafo, Vertice origem, Vertice destino) {
        if(origem == destino){
            return List.of(origem);
        }

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

    public static void printArestasCaminho(List<Aresta> menorCaminho){
        int distancia = 0;
        System.out.println("\nPara percorrer o menor caminho, será necessário percorrer a(s) seguinte(s) rua(s): ");
        String anterior = "";
        for(int i = 0; i < menorCaminho.size(); i++){
            Aresta aresta = menorCaminho.get(i);
            if(!aresta.getNomeRua().equals(anterior)){
                System.out.print(aresta.getNomeRua());
                anterior = aresta.getNomeRua();
                if(i != menorCaminho.size()-1){
                    System.out.print(" -> ");
                }
            }
            distancia += aresta.getPeso();
        }
        System.out.println(".\n\nDistância total: " + distancia + "m.\n");
    }


    public static void printVerticesCaminho(List<Vertice> menorCaminhoVertices, Vertice origem, Vertice destino){
        if(menorCaminhoVertices.size() == 1 && origem == destino){
            System.out.println("\nOrigem e Destino sao iguais");
            return;
        } else if (menorCaminhoVertices.size() == 1) {
            System.out.println("\nNao foi possivel encontrar o menor caminho possivel, pois nao existem arestas que os liguem.");
            return;
        }
        System.out.println(("\nPara percorrer o menor caminho, sera necessario percorrer o(s) seguinte(s) vertice(s): "));
        for(int i = 0; i < menorCaminhoVertices.size(); i++){
            Vertice vertice = menorCaminhoVertices.get(i);
            System.out.print(vertice.getId());
            if(i != menorCaminhoVertices.size()-1){
                System.out.print(" -> ");
            } else{
                System.out.println(".");
            }
        }
    }


}
