package Classes;

import DataStructureClasses.SimpleList;

/**
 *
 * @author B-St
 */
public class Graph {

    private SimpleList vertexs;
    private final int[][] matrix;
    private int numVertex;

    public Graph(int size) {
        this.numVertex = 0;
        this.matrix = new int[size][size];
        // inicializar la matriz de adyacencia en 0:
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.matrix[i][j] = 0;
            }
        }
    }

    public void addVertex(Vertex vertex) {
        vertexs.addStart(vertex);
        vertex.setNumVertex(vertexs.getSize());
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
    }

    public String printGraphString() {
        String chain = "";
        for (int i = 0; i < this.numVertex; i++) {
            chain += "{";
            for (int j = 0; j < this.numVertex; j++) {
                chain += this.matrix[i][j] + ",";
            }
        }
        return chain;
    }

}
