package br.edu.ufape;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void exibirMenu(Grafo grafo){
        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            System.out.println("------- Menu -------");
            System.out.println("1 - Procurar menor caminho");
            System.out.println("2 - Detalhar Grafo");
            System.out.println("3 - Editar Grafo");
            System.out.println("99 - Sair");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            clearConsole();
            switch (escolha) {
                case 1:
                    exibirMenuMenorCaminho(grafo);
                    break;
                case 2:
                    detalharGrafo(grafo);
                    break;
                case 3:
                    editarGrafo(grafo);
                    break;
                case 99:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        } while (escolha != 99);

        scanner.close();
    }

    public static void editarGrafo(Grafo grafo){
        Scanner scanner = new Scanner(System.in);
        int escolha = 0;

        do {
            System.out.println("------- Submenu - Editar Grafo-------");
            System.out.println("1 - Adicionar Vertice");
            System.out.println("2 - Remover Vertice");
            System.out.println("3 - Adicionar Aresta");
            System.out.println("4 - Remover Aresta");
            System.out.println("5 - Voltar ao menu anterior");
            System.out.println("**\\Alteraçoes no Grafo sao feitas em tempo de execuçao, mas somente sao salvas no arquivo quando o programa fechar completamente./**");
            System.out.println("Escolha uma opção: ");

            try{
                escolha = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }

            switch (escolha){
                case 1:
                    adicionarVertice(grafo);
                    break;
                case 2:
                    removerVertice(grafo);
                    break;
                case 3:
                    adicionarAresta(grafo);
                    break;
                case 4:
                    removerAresta(grafo);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida:");
                    break;

            }
            clearConsole();

        }while(escolha != 5);
    }

    public static void removerAresta(Grafo grafo){
        Scanner scanner = new Scanner(System.in);
        int origem = -1;
        int destino = -1;

        do {
            System.out.print("Digite o Id do Vertice de origem a ser removido: ");
            try {
                origem = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Opção inválida. Por favor, digite um numero inteiro.");
            }
        } while (origem == -1);

        do {
            System.out.print("Digite o Id do Vertice de destino a ser removido: ");
            try {
                destino = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Opção inválida. Por favor, digite um numero inteiro.");
            }
        } while (destino == -1);

        grafo.removerAresta(origem, destino);
    }

    public static void removerVertice(Grafo grafo){
        Scanner scanner = new Scanner(System.in);
        int id = -1;

        do {
            System.out.print("Digite o Id do Vertice a ser removido: ");
            try {
                id = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Opção inválida. Por favor, digite um numero inteiro.");
            }
        } while (id == -1);
        Vertice vertice = grafo.procurarVertice(id);
        grafo.removerVertice(vertice);
    }


    public static void adicionarAresta(Grafo grafo){
        Scanner scanner = new Scanner(System.in);
        int origem = -1;
        int destino = -1;
        String rua = "";
        String buffer;
        double peso = 0.0;
        Vertice vertice1, vertice2;

        do {
            do {
                System.out.print("Digite o Id do Vertice de origem: ");
                try {
                    origem = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Opção inválida. Por favor, digite um numero inteiro.");
                }
            } while (origem == -1);
            vertice1 = grafo.procurarVertice(origem);
            if(vertice1 == null){
                System.out.println("Esse vertice nao esta cadastrado. Tente Novamente!");
            }
        }while (vertice1 == null);

        do {
            do {
                System.out.print("Digite o Id do Vertice de destino: ");
                try {
                    destino = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Opção inválida. Por favor, digite um numero inteiro.");
                }
            } while (destino == -1);
            vertice2 = grafo.procurarVertice(destino);
            if(vertice2 == null){
                System.out.println("Esse vertice nao esta cadastrado. Tente Novamente!");
            }
        } while (vertice2 == null);

        do{
            System.out.print("Digite o nome da Rua que esta aresta represente: ");
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                rua = reader.readLine();
            } catch (Exception e){
                System.out.println("Houve um erro de leitura, tente novamente.");
            }
        } while(rua.isEmpty());

        do{
            System.out.print("Digite o tamanho(comprimento em m.) da rua: ");
            scanner.nextLine();
            buffer = scanner.next();
            try{
                peso = Double.parseDouble(buffer);
            } catch (Exception e){
                System.out.println("Peso invalido. Por favor, digite um double valido: ");
            }
        } while (peso == 0.0);

        Aresta aresta = new Aresta(rua, vertice1, vertice2, peso);
        grafo.adicionarAresta(aresta);
    }

    public static void adicionarVertice(Grafo grafo){
        Scanner scanner = new Scanner(System.in);
        int escolha = 0;

        do{
            System.out.println("------- Submenu - Adicionar Vertice-------");
            System.out.println("1 - Adicionar Vertice sem Ponto de Referencia");
            System.out.println("2 - Adicionar Vertice com POnto de Referencia");
            System.out.println("3 - Voltar ao menu anterior");

            try{
                escolha = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Opção inválida. Por favor, digite um numero inteiro.");
            }
            int id;

            switch (escolha){
                case 1:
                    id = -1;
                    do {
                        System.out.print("Digite o ID do vertice a ser adicionado: ");
                        try {
                            id = scanner.nextInt();
                        } catch (Exception e) {
                            System.out.println("Opção inválida. Por favor, escolha um valor inteiro");
                        }
                    }while(id == -1);

                    Vertice vertice = new Vertice(id);
                    grafo.adicionarVertice(vertice);
                    break;

                case 2:
                    id = -1;
                    do {
                        System.out.print("Digite o ID do vertice a ser adicionado: ");
                        try {
                            id = scanner.nextInt();
                        } catch (Exception e) {
                            System.out.println("Opção inválida. Por favor, escolha um valor inteiro");
                        }
                    }while(id == -1);
                    System.out.print("Digite o Ponto de Referencia do vertice a ser adicionado: ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String ref = "";
                    try{
                        ref = reader.readLine();
                    } catch (Exception ignored){

                    }

                    Vertice vertice2 = new Vertice(id, ref);
                    grafo.adicionarVertice(vertice2);
                    break;

                default:
                    System.out.print("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
                clearConsole();
        } while (escolha != 3);
        clearConsole();
    }

    public static void detalharGrafo(Grafo grafo){
        Scanner scanner = new Scanner(System.in);
        int escolha = 0;

        do {
            System.out.println("------- Submenu - Detalhar Grafo-------");
            System.out.println("1 - Exibir numero de vertices");
            System.out.println("2 - Exibir numero de arestas");
            System.out.println("3 - Detalhar arestas de um vertice");
            System.out.println("4 - Voltar ao menu anterior");
            System.out.println("Escolha uma opção: ");

            try{
                escolha = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }

            switch (escolha){
                case 1:
                    System.out.println("\n\nO grafo possui " + grafo.getVertices().size() + " vertices.\n");
                    break;
                case 2:
                    System.out.println("\n\nO grafo possui " + grafo.getArestas().size() + " arestas.\n");
                    break;
                case 3:
                    Vertice vertice;
                    do{
                        System.out.print("Digite o vertice: ");
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        String entrada = "";
                        try {
                            entrada = reader.readLine();
                        }catch (Exception ignored){

                        }
                        int verticeEntrada;
                        try {
                            verticeEntrada = Integer.parseInt(entrada);
                        } catch (Exception e) {
                            try {
                                verticeEntrada = grafo.procurarVerticeString(entrada.toLowerCase()).getId();
                            } catch (Exception el){
                                verticeEntrada = -1;
                            }
                        }
                        vertice = grafo.procurarVertice(verticeEntrada);

                        if(vertice == null){
                            System.out.println("Digite um vértice valido!");
                        }
                    } while(vertice == null);

                    System.out.println("\nO vertice possui as seguintes arestas: ");
                    for (int i = 0; i < grafo.getListaAdjacencia().get(vertice).size(); i++){
                        Aresta aresta = grafo.getListaAdjacencia().get(vertice).get(i);
                        System.out.print(aresta.getNomeRua() + " com destino ao vertice de id: ");
                        if(aresta.getInicio() == vertice){
                            System.out.println(aresta.getDestino().getId());
                        } else{
                            System.out.println(aresta.getInicio().getId());
                        }
                    }
                    System.out.print("\n");
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }

        } while(escolha != 4);
        clearConsole();
    }

    public static void exibirMenuMenorCaminho(Grafo grafo){
        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            System.out.println("\n\n------- Submenu - Procurar Menor Caminho -------");
            System.out.print("Digite o vértice de entrada:");
            Vertice vertice1;
            do {
                String entrada = scanner.next();
                int verticeEntrada;
                try {
                    verticeEntrada = Integer.parseInt(entrada);
                } catch (Exception e) {
                    try {
                        verticeEntrada = grafo.procurarVerticeString(entrada.toLowerCase()).getId();
                    } catch (Exception el){
                        verticeEntrada = -1;
                    }
                }
                vertice1 = grafo.procurarVertice(verticeEntrada);

                if(vertice1 == null){
                    System.out.print("Digite um vértice valido de entrada:");
                }
            }while(vertice1 == null);

            System.out.print("Digite o vértice de saída:");
            Vertice vertice2;
            do{
                String saida = scanner.next();
                int verticeSaida;
                try {
                    verticeSaida = Integer.parseInt(saida);
                } catch (Exception e){
                    try {
                        verticeSaida = grafo.procurarVerticeString(saida.toLowerCase()).getId();
                    } catch (Exception el){
                        verticeSaida = -1;
                    }
                }
                vertice2 = grafo.procurarVertice(verticeSaida);

                if (vertice2 == null){
                    System.out.print("Digite um vértice valido de saida:");
                }
            } while (vertice2 == null);
            clearConsole();

            // Aqui você pode calcular a menor rota usando o algoritmo de Dijkstra
            List<Vertice> vertices = DijsktraAlgorithm.menorCaminhoVertices(grafo, vertice1, vertice2);

            System.out.println("******************************************");
            DijsktraAlgorithm.printVerticesCaminho(vertices, vertice1, vertice2);
            DijsktraAlgorithm.printArestasCaminho(DijsktraAlgorithm.menorCaminhoArestas(grafo, vertices));
            System.out.println("******************************************");

            System.out.println("1 - Tentar novamente");
            System.out.println("2 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            do {
                try {
                    escolha = scanner.nextInt();
                } catch (Exception e){
                    escolha = -1;
                }

                switch (escolha) {
                    case 1:
                        break;
                    case 2:
                        System.out.println("Voltando ao menu anterior...");
                        break;
                    default:
                        System.out.print("Opção inválida. Por favor, escolha uma opção válida:");
                        break;
                }
            }while(escolha < 1 || escolha > 2);
            clearConsole();
        } while (escolha != 2);
    }

    public static void clearConsole(){
        for(int i = 0; i < 20; i++){
            System.out.println("\n");
        }
    }

}
