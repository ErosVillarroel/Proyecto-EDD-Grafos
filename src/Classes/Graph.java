package Classes;

import Classes.Vertex;
import DataStructureClasses.BooleanNode;
import DataStructureClasses.SimpleList;
import DataStructureClasses.TypeStack;

/**
 *
 * @author B-St
 */
public class Graph {

    private SimpleList vertexsList;
    private int[][] matrix;
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

    public Graph(SimpleList<String> usersList, SimpleList<String> relationsList) {

        int size = usersList.getSize();

        this.vertexsList = new SimpleList();
        this.numVertexs = size;
        this.matrix = new int[size][size];
        // inicializar la matriz de adyacencia en 0:
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.matrix[i][j] = 0;
            }
        }

        for (int i = 0; i < size; i++) {
            try {
                Vertex newVertex = new Vertex(usersList.getValueByIndex(i), i);
//                    newVertex.printVertex();
                this.addVertex(newVertex);
            } catch (Exception e) {
                System.out.println("Algo salio mal :(");
            }
        }

        this.generateRelationsFromList(relationsList, usersList);

    }

    public void addVertex(Vertex vertex) {
        if (this.vertexsList.getSize() < this.numVertexs) {
            vertexsList.addAtTheEnd(vertex);
            vertex.setNumVertex(vertexsList.getSize() - 1);
//            System.out.println("Vertice re100 aniadido");
//            vertex.printVertex();
        } else {
            System.out.println("No se pueden anadir mas vertices al grafo.");
        }
    }

    public void addEdge(int srcVertex, int dstVertex) {

        this.verifyVertex(srcVertex, dstVertex);
        this.differentVertex(srcVertex, dstVertex);

        this.matrix[srcVertex][dstVertex] = 1;
    }

    public void deleteEdge(int srcVertex, int dstVertex) {

        this.verifyVertex(srcVertex, dstVertex);
        this.differentVertex(srcVertex, dstVertex);

        this.matrix[srcVertex][dstVertex] = 0;
    }

    public boolean checkEdge(int srcVertex, int dstVertex) {
        this.verifyVertex(srcVertex, dstVertex);
//        this.differentVertex(srcVertex, dstVertex);
        return matrix[srcVertex][dstVertex] == 1;
    }

    /*
        - Cambiar las excepciones para que se vean en la GUI
     */
    private void verifyVertex(int srcVertex, int dstVertex) {
        if (srcVertex < 0 || srcVertex >= this.numVertexs) {
            throw new IllegalArgumentException("Error: Vertice inexistente o fuera de rango => " + srcVertex);
        }
        if (dstVertex < 0 || dstVertex >= this.numVertexs) {
            throw new IllegalArgumentException("Error: Vertice inexistente o fuera de rango => " + dstVertex);
        }

    }

    private void differentVertex(int srcVertex, int dstVertex) {
        if (srcVertex == dstVertex) {
            throw new IllegalArgumentException("Error: no se permiten bucles: (" + srcVertex + ", " + dstVertex + ")");
        }
    }

    public void print() {

        System.out.println("Numero de vertices -> " + this.numVertexs);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        this.vertexsList.printVertexList();
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

    private void generateRelationsFromList(SimpleList<String> relationships, SimpleList<String> userNameList) {

        for (int i = 0; i < relationships.getSize(); i++) {
            String[] parts;
            parts = relationships.getValueByIndex(i).split(",");
            String source = parts[0];
            String destiny = parts[1].trim();

            int originIndex = userNameList.indexOf(source);
            int destinyIndex = userNameList.indexOf(destiny);
            this.addEdge(originIndex, destinyIndex);
        }

    }

    public void transpose() {

        int num = this.numVertexs;
        
        // create empty transpose matrix of size m*n
        int[][] transposedMatrix = new int[num][num];

        // traverse matrix M
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                transposedMatrix[j][i] = this.matrix[i][j];
            }
        }   
        this.matrix = transposedMatrix; 
    }

    public int getNumVertexs() {
        return numVertexs;
    }

    public int getVertexsListSize() {
        return vertexsList.getSize();
    }
}
