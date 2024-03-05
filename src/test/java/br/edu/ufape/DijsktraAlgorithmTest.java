import br.edu.ufape.*;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DijsktraAlgorithmTest {

    @Test
    public void testMenorCaminhoVertices() {
        // Criação do grafo de teste
        Grafo grafo = new Grafo();
        Vertice v0 = new Vertice(0);
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);
        Vertice v4 = new Vertice(4);
        grafo.adicionarVertice(v0);
        grafo.adicionarVertice(v1);
        grafo.adicionarVertice(v2);
        grafo.adicionarVertice(v3);
        grafo.adicionarVertice(v4);

        Aresta a0_1 = new Aresta("Rua 1", v0, v1, 10.0);
        Aresta a0_2 = new Aresta("Rua 2", v0, v2, 5.0);
        Aresta a1_2 = new Aresta("Rua 3", v1, v2, 2.0);
        Aresta a1_3 = new Aresta("Rua 4", v1, v3, 30.0);
        Aresta a2_1 = new Aresta("Rua 5", v2, v1, 3.0);
        Aresta a2_3 = new Aresta("Rua 6", v2, v3, 9.0);
        Aresta a2_4 = new Aresta("Rua 7", v2, v4, 2.0);
        Aresta a3_4 = new Aresta("Rua 8", v3, v4, 4.0);
        Aresta a4_0 = new Aresta("Rua 9", v4, v0, 8.0);

        grafo.adicionarAresta(a0_1);
        grafo.adicionarAresta(a0_2);
        grafo.adicionarAresta(a1_2);
        grafo.adicionarAresta(a1_3);
        grafo.adicionarAresta(a2_1);
        grafo.adicionarAresta(a2_3);
        grafo.adicionarAresta(a2_4);
        grafo.adicionarAresta(a3_4);
        grafo.adicionarAresta(a4_0);

        // Teste do menor caminho entre v0 e v4
        List<Vertice> menorCaminho = DijsktraAlgorithm.menorCaminhoVertices(grafo, v0, v4);
        List<Vertice> expectedCaminho = Arrays.asList(v0, v2, v4);
        assertEquals(expectedCaminho, menorCaminho);

        // Teste do menor caminho entre v3 e v1
        menorCaminho = DijsktraAlgorithm.menorCaminhoVertices(grafo, v3, v1);
        expectedCaminho = Arrays.asList(v3, v4, v2, v1);
        assertEquals(expectedCaminho, menorCaminho);
    }

    @Test
    public void testMenorCaminhoArestas() {
        // Criação do grafo de teste
        Grafo grafo = new Grafo();
        Vertice v0 = new Vertice(0);
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);
        Vertice v4 = new Vertice(4);
        grafo.adicionarVertice(v0);
        grafo.adicionarVertice(v1);
        grafo.adicionarVertice(v2);
        grafo.adicionarVertice(v3);
        grafo.adicionarVertice(v4);

        Aresta a0_1 = new Aresta("Rua 1", v0, v1, 10.0);
        Aresta a0_2 = new Aresta("Rua 2", v0, v2, 5.0);
        Aresta a1_2 = new Aresta("Rua 3", v1, v2, 2.0);
        Aresta a1_3 = new Aresta("Rua 4", v1, v3, 30.0);
        Aresta a2_1 = new Aresta("Rua 5", v2, v1, 3.0);
        Aresta a2_3 = new Aresta("Rua 6", v2, v3, 9.0);
        Aresta a2_4 = new Aresta("Rua 7", v2, v4, 2.0);
        Aresta a3_4 = new Aresta("Rua 8", v3, v4, 4.0);
        Aresta a4_0 = new Aresta("Rua 9", v4, v0, 8.0);

        grafo.adicionarAresta(a0_1);
        grafo.adicionarAresta(a0_2);
        grafo.adicionarAresta(a1_2);
        grafo.adicionarAresta(a1_3);
        grafo.adicionarAresta(a2_1);
        grafo.adicionarAresta(a2_3);
        grafo.adicionarAresta(a2_4);
        grafo.adicionarAresta(a3_4);
        grafo.adicionarAresta(a4_0);

        // Teste do menor caminho entre v0 e v4
        List<Vertice> menorCaminhoVertices = DijsktraAlgorithm.menorCaminhoVertices(grafo, v0, v4);
        List<Aresta> menorCaminhoArestas = DijsktraAlgorithm.menorCaminhoArestas(grafo, menorCaminhoVertices);

        assertEquals(2, menorCaminhoArestas.size());
        assertEquals(a0_2, menorCaminhoArestas.get(0));
        assertEquals(a2_4, menorCaminhoArestas.get(1));

        // Teste do menor caminho entre v3 e v1
        menorCaminhoVertices = DijsktraAlgorithm.menorCaminhoVertices(grafo, v3, v1);
        menorCaminhoArestas = DijsktraAlgorithm.menorCaminhoArestas(grafo, menorCaminhoVertices);

        assertEquals(3, menorCaminhoArestas.size());
        assertEquals(a3_4, menorCaminhoArestas.get(0));
        assertEquals(a2_4, menorCaminhoArestas.get(1));
        assertEquals(a1_2, menorCaminhoArestas.get(2));
    }
}
