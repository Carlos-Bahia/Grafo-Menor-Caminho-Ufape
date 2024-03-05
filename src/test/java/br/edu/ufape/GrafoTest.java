package br.edu.ufape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class GrafoTest {

    private Grafo grafo;

    @Before
    public void setUp() {
        grafo = new Grafo();
    }

    @Test
    public void testAdicionarVertice() {
        Vertice vertice = new Vertice(1);
        grafo.adicionarVertice(vertice);

        assertTrue(grafo.getVertices().contains(vertice));
    }

    @Test
    public void testAdicionarAresta() {
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Aresta aresta = new Aresta("Rua 1", v1, v2, 5.0);

        grafo.adicionarVertice(v1);
        grafo.adicionarVertice(v2);
        grafo.adicionarAresta(aresta);
        grafo.adicionarAresta(aresta);

        assertTrue(grafo.getArestas().contains(aresta));
        assertEquals(1, grafo.getListaAdjacencia().get(v1).size());
        assertEquals(1, grafo.getListaAdjacencia().get(v2).size());
    }
}

