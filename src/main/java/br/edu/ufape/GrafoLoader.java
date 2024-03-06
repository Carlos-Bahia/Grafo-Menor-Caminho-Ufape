package br.edu.ufape;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class GrafoLoader {
    public static Grafo loadGrafo(@NotNull String arquivo){
        Grafo grafo = new Grafo();
        try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){
            String linha;

            while((linha = br.readLine()) != null){
                String[] data = linha.split("/");
                if(Objects.equals(data[0], "V")){ //Linha representa um Vertice
                    if(data.length == 3){
                        int id = Integer.parseInt(data[1]);
                        String pontoReferencia = data[2];

                        Vertice vertice = new Vertice(id, pontoReferencia);
                        grafo.adicionarVertice(vertice);

                    } else if (data.length == 2) {
                        int id = Integer.parseInt(data[1]);

                        Vertice vertice = new Vertice(id);
                        grafo.adicionarVertice(vertice);
                    }
                } else if (Objects.equals(data[0], "A")) {//Linha representa uma aresta
                    if(data.length == 5){
                        int origem = Integer.parseInt(data[1]); //Passa o id do Vetice
                        int destino = Integer.parseInt(data[2]);
                        String nome = data[3];
                        double tamanho = Double.parseDouble(data[4]);

                        Vertice verticeOrigem = grafo.procurarVertice(origem);
                        Vertice verticeDestino = grafo.procurarVertice(destino);
                        if(verticeOrigem != null && verticeDestino != null) {
                            Aresta aresta = new Aresta(nome, verticeOrigem, verticeDestino, tamanho);
                            grafo.adicionarAresta(aresta);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erro de entrada/saida ao ler o arquivo: " + e.getMessage());
        }

        return grafo;
    }

    public static void atualizarGrafo(String arquivo, Grafo grafo){
        List<Vertice> vertices = grafo.getVertices();
        List<Aresta> arestas = grafo.getArestas();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));
            for(Vertice vertice : vertices){
                if(vertice.getPontoReferencia() != null){
                    writer.write("V/"+vertice.getId()+"/"+vertice.getPontoReferencia());
                    writer.newLine();
                } else{
                    writer.write("V/"+vertice.getId());
                    writer.newLine();
                }
            }
            for (Aresta aresta : arestas) {
                writer.write("A/"+aresta.getInicio().getId()+"/"+aresta.getDestino().getId()+"/"+aresta.getNomeRua()+"/"+aresta.getPeso());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}
