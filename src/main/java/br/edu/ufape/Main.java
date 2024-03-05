package br.edu.ufape;

import javax.print.DocPrintJob;
import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String arquivo = System.getProperty("user.dir") + File.separator + "teste.txt";
        Grafo grafo;
        double distancia = 0;

        File file = new File(arquivo);
        if(!file.exists()){
            System.err.println("Arquivo nao encontrado: " + arquivo);
            return;
        }

        grafo = GrafoLoader.loadGrafo(arquivo);

        System.out.println("Numero de vertices: " + grafo.getVertices().size());
        System.out.println("Numero de arestas: " + grafo.getArestas().size());

        List<Vertice> menorCaminhoVertices = DijsktraAlgorithm.menorCaminhoVertices(grafo, grafo.procurarVertice(0), grafo.procurarVertice(2));
        List<Aresta> menorCaminho = DijsktraAlgorithm.menorCaminhoArestas(grafo, menorCaminhoVertices);

        System.out.println("\nPara percorrer o menor caminho, sera necessario percorrer as seguintes ruas: ");
        for(int i = 0; i < menorCaminho.size(); i++){
            Aresta aresta = menorCaminho.get(i);
            System.out.print(aresta.getNomeRua());
            distancia += aresta.getPeso();
            if(i != menorCaminho.size()-1){
                System.out.print(" -> ");
            } else{
                System.out.println(".");
            }
        }
        System.out.println("\nDistancia total: " + distancia + "m.");
    }
}