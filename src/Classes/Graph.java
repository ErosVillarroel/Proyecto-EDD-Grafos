package Classes;

import Classes.Vertex;
import DataStructureClasses.SimpleList;

/**
 *
 * @author B-St
 */
public class Graph {

    private SimpleList vertexsList;
    private final int[][] matrix;
    private int numVertexs;

    public Graph(int size) {
        
        this.vertexsList = new SimpleList();
        this.numVertexs = size;
        this.matrix = new int[size][size];
        // inicializar la matriz de adyacencia en 0:
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.matrix[i][j] = 0;
            }
        }
    }

    public void addVertex(Vertex vertex) {
        
        if (this.vertexsList.getSize() < this.numVertexs) {
            vertexsList.addAtTheEnd(vertex);
            vertex.setNumVertex(vertexsList.getSize());
        } else {
            System.out.println("No se pueden anadir mas vertices al grafo.");
        }
    }

    public void addEdge(int srcVertex, int dstVertex) {
        matrix[srcVertex][dstVertex] = 1;
    }

    public boolean checkEdge(int srcVertex, int dstVertex) {
        return matrix[srcVertex][dstVertex] == 1;
    }

    public void print() {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        
//        this.vertexsList.printSimpleVertexList();
    }

    public String printGraphString() {
        String chain = "";
        for (int i = 0; i < this.numVertexs; i++) {
            chain += "{";
            for (int j = 0; j < this.numVertexs; j++) {
                chain += this.matrix[i][j] + ",";
            }
        }
        return chain;
    }
}
