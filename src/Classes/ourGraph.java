package Classes;

import Classes.Vertex;
import DataStructureClasses.SimpleList;

/**
 *
 * @author B-St
 */
public class OurGraph {

    private SimpleList<Vertex> vertexsList;
    private int[][] matrix;
    private int numVertexs;

    public OurGraph(int size) {

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

    public OurGraph(String userName) {

        this.vertexsList = new SimpleList();
        this.numVertexs = 1;
        this.matrix = new int[1][1];
        // inicializar la matriz de adyacencia en 0:
        this.matrix[0][0] = 0;

        Vertex newVertex = new Vertex(userName, 0);
        this.vertexsList.addAtTheEnd(newVertex);
    }

    public OurGraph(SimpleList<String> usersList, SimpleList<String> relationsList) {

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

    public boolean isGraphEmpty() {
        return this.numVertexs == 0;
    }

    public int[][] getMatrix() {
        return this.matrix;
    }

    public boolean userExists(String userName) {
        for (int i = 0; i < this.numVertexs; i++) {
            Vertex vertex = this.getVertex(i);

            // Verificar si el vértice es nulo
            if (vertex != null) {
                String vertexName = vertex.getName();

                // Verificar si el nombre del vértice es igual a userName
                if (vertexName != null && vertexName.equals(userName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getVertexName(int index) {
        Vertex vertex = this.vertexsList.getValueByIndex(index);

        if (vertex != null) {
            return vertex.getName();
        } else {
            return null;
        }
    }

    public Vertex getVertex(int vertexIndex) {
        if (vertexIndex >= 0 && vertexIndex < vertexsList.getSize()) {
            return vertexsList.getValueByIndex(vertexIndex);
        } else {
            System.out.println("Indice de vertice fuera de rango: " + vertexIndex);
            return null; // o puedes lanzar una excepción aquí
        }
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

    public boolean vertexExists(int vertexIndex) {
        return vertexIndex >= 0 && vertexIndex < this.getVertexsListSize();
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

        try {
//            System.out.println("Numero de vertices -> " + this.numVertexs);
            System.out.println("Vertex ammount -> " + this.numVertexs);

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }

            this.vertexsList.printVertexList();
        } catch (Exception e) {
            System.out.println("print error");
        }
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

    public void generateRelationsFromList(SimpleList<String> relationships, SimpleList<String> userNameList) {

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

    // WOIP, hacer una sola relacion?
    public void addUser(String userName, int relation) {

        int vertexsNum = this.getVertexsListSize();
        Vertex newVertex = new Vertex(userName, vertexsNum);
        this.vertexsList.addAtTheEnd(newVertex);

        // Inicializar la nueva matriz con el tamaño de la matriz original + 1
        int[][] newMatrix = new int[vertexsNum + 1][vertexsNum + 1];

        // Copiar la matriz anterior correctamente
        for (int i = 0; i < vertexsNum; i++) {
            for (int j = 0; j < vertexsNum; j++) {
                newMatrix[i][j] = this.matrix[i][j];
            }
        }

        this.matrix = newMatrix;
        // actualizar numero de vertices del grafo
        this.numVertexs = this.getVertexsListSize();

        this.addEdge(newVertex.getNumVertex(), relation);

//        // Imprimir información de depuración
//        System.out.println("Matrix after copying:");
//        for (int i = 0; i < this.matrix.length; i++) {
//            for (int j = 0; j < this.matrix[i].length; j++) {
//                System.out.print(this.matrix[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    /*
        - PRUEBA Eliminar un vertice del grafo
     */
    public void deleteVertex(int vertexIndex) {
        if (vertexIndex < 0 || vertexIndex >= this.numVertexs) {
            System.out.println("El vertice esta fuera de rango o no existe en: " + vertexIndex);
            return;
        }

        for (int i = 0; i < numVertexs; i++) {
            if (this.matrix[vertexIndex][i] == 1) {
                deleteEdge(vertexIndex, i);
            }
        }

        this.vertexsList.deleteAtIndex(vertexIndex);
        this.numVertexs--;

        if (vertexIndex == 0 && this.isGraphEmpty()) {
            this.matrix = new int[0][0];
        }

        // Crear la nueva matriz actualizada
        int[][] newMatrix = new int[this.numVertexs][this.numVertexs];

        int newI = 0;

        for (int i = 0; i < this.numVertexs + 1; i++) {
            if (i == vertexIndex) {
                continue; // Saltar la fila correspondiente al vértice eliminado
            }

            int newJ = 0;
            for (int j = 0; j < this.numVertexs + 1; j++) {
                if (j != vertexIndex) {
                    newMatrix[newI][newJ] = this.matrix[i][j];
                    newJ++;
                }
            }

            newI++;
        }

        this.matrix = newMatrix;
        this.updateVertexIndices();

    }

    // para actualizar los indices de los vertices restantes en la lista
    private void updateVertexIndices() {
        for (int i = 0; i < this.numVertexs; i++) {
            Vertex currentVertex = vertexsList.getValueByIndex(i);
            if (currentVertex != null) {
                currentVertex.setNumVertex(i);
            }
        }
    }

    public void modifyVertexName(int vertexIndex, String newName) {
        if (!isValidVertexIndex(vertexIndex)) {
            System.out.println("Error indice no valido");
            return;
        }

        Vertex vertex = getVertexByIndex(vertexIndex);
        if (vertex != null) {
            vertex.setName(newName);
        } else {
            System.out.println("No se encontro el vertice de la posicion: " + vertexIndex);
        }
    }

    // Verificar si el vertice de la lista es valido
    private boolean isValidVertexIndex(int vertexIndex) {
        return vertexsList.isValidIndex(vertexIndex);
    }

    // obtener un vertice por indice
    private Vertex getVertexByIndex(int vertexIndex) {
        return vertexsList.getValueByIndex(vertexIndex);
    }

    public int getNumVertexs() {
        return numVertexs;
    }

    public int getVertexsListSize() {
        return vertexsList.getSize();
    }

    public SimpleList getVertexsList() {
        return this.vertexsList;
    }

    // posibles metodos para el funcionamiento del visual
//    public boolean isVertexIsland(int vertexIndex) {
//        if (isValidVertexIndex(vertexIndex)) {
//            Vertex vertex = getVertex(vertexIndex);
//            return !hasOutgoingEdges(vertex) && !hasIncomingEdges(vertex);
//        }
//        return false;
//    }
//
//    private boolean hasOutgoingEdges(Vertex vertex) {
//        return vertex.getOutgoingEdges().size() > 0;
//    }
//
//    private boolean hasIncomingEdges(Vertex vertex) {
//        return vertex.getIncomingEdges().size() > 0;
//    }
}
