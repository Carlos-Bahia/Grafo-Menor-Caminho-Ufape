package br.edu.ufape;

import java.io.File;
public class Main {
    public static void main(String[] args) {

        String arquivo = System.getProperty("user.dir") + File.separator + "teste.txt";
        Grafo grafo;

        File file = new File(arquivo);
        if(!file.exists()){
            System.err.println("Arquivo nao encontrado: " + arquivo);
            return;
        }

        grafo = GrafoLoader.loadGrafo(arquivo);
        Menu.exibirMenu(grafo);

        GrafoLoader.atualizarGrafo(arquivo, grafo);
    }
}