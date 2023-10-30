package Classes;

import Classes.Vertex;
import DataStructureClasses.SimpleList;

/**
 *
 * @author B-St
 */

//Clase que se encarga de toda la logica detras de los grafos
public class OurGraph {

    private SimpleList<Vertex> vertexsList;
    private int[][] matrix;
    private int numVertexs;

//    crea un grafo vacio con el tamano que se le introduzca 
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
    
    //crea u  grafo con un solo vertice con el nombre introducido
    public OurGraph(String userName) {

        this.vertexsList = new SimpleList();
        this.numVertexs = 1;
        this.matrix = new int[1][1];
        // inicializar la matriz de adyacencia en 0:
        this.matrix[0][0] = 0;

        Vertex newVertex = new Vertex(userName, 0);
        this.vertexsList.addAtTheEnd(newVertex);
    }

//    crea un grafo a partir de una lista de usuarios y una de relaciones parseadas de un archivo con el formato premitido
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

    //revisa si el grafo esta vacio
    public boolean isGraphEmpty() {
        return this.numVertexs == 0;
    }

    //devuelve la matriz que representa las relaciones del grafo
    public int[][] getMatrix() {
        return this.matrix;
    }

    //revisa si un usuario existe en el grafo
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
    
//  devuelve el nombre de un vertice usando su indice como entrada
    public String getVertexName(int index) {
        Vertex vertex = this.vertexsList.getValueByIndex(index);

        if (vertex != null) {
            return vertex.getName();
        } else {
            return null;
        }
    }

    //devuelve un vertice a partir de su indice
    public Vertex getVertex(int vertexIndex) {
        if (vertexIndex >= 0 && vertexIndex < vertexsList.getSize()) {
            return vertexsList.getValueByIndex(vertexIndex);
        } else {
            System.out.println("Indice de vertice fuera de rango: " + vertexIndex);
            return null; 
        }
    }

    //anade un vertice al grafo, actualizando su matriz
    public void addVertex(Vertex vertex) {
        if (this.vertexsList.getSize() < this.numVertexs) {
            vertexsList.addAtTheEnd(vertex);
            vertex.setNumVertex(vertexsList.getSize() - 1);

        } else {
            System.out.println("No se pueden anadir mas vertices al grafo.");
        }
    }

    //Revisa si un vertice existe a traves de su indice
    public boolean vertexExists(int vertexIndex) {
        return vertexIndex >= 0 && vertexIndex < this.getVertexsListSize();
    }
    
    //anade una relacion al vertice
    public void addEdge(int srcVertex, int dstVertex) {

        this.verifyVertex(srcVertex, dstVertex);
        this.differentVertex(srcVertex, dstVertex);

        this.matrix[srcVertex][dstVertex] = 1;
    }
    
    //elimina una relacion del grafo
    public void deleteEdge(int srcVertex, int dstVertex) {

        this.verifyVertex(srcVertex, dstVertex);
        this.differentVertex(srcVertex, dstVertex);

        this.matrix[srcVertex][dstVertex] = 0;
    }

    //revisa si una relacion existe
    public boolean checkEdge(int srcVertex, int dstVertex) {
        this.verifyVertex(srcVertex, dstVertex);
//        this.differentVertex(srcVertex, dstVertex);
        return matrix[srcVertex][dstVertex] == 1;
    }

       
    private void verifyVertex(int srcVertex, int dstVertex) {
        if (srcVertex < 0 || srcVertex >= this.numVertexs) {
            throw new IllegalArgumentException("Error: Vertice inexistente o fuera de rango => " + srcVertex);
        }
        if (dstVertex < 0 || dstVertex >= this.numVertexs) {
            throw new IllegalArgumentException("Error: Vertice inexistente o fuera de rango => " + dstVertex);
        }

    }

    //revisa que un vertice sea diferente de si mismo
    private void differentVertex(int srcVertex, int dstVertex) {
        if (srcVertex == dstVertex) {
            throw new IllegalArgumentException("Error: no se permiten bucles: (" + srcVertex + ", " + dstVertex + ")");
        }
    }

    //imprime el grafo
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

    //devuelve una string que representa el grafo
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

    //genera relaciones en el grafo a traves de una lista de relaciones y una de usuarios
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

    //transpone la matriz del grafo
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

    // Anade un usuario con una relacion al grafo
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

    }

    //elimina un vertice y sus relaciones utilizando su indice
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

        this.vertexsList.deleteByIndex(vertexIndex);
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

    //modifica el nombre de un vertice usando su indice
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

    //devuelve las relaciones de un vertice
    public String getVertexRelations(int vertexIndex) {
        
        if (isValidVertexIndex(vertexIndex)) {
            StringBuilder relations = new StringBuilder();
            
            for (int i = 0; i < numVertexs; i++) {
                if (checkEdge(vertexIndex, i)) {
                    relations.append(i).append(", ");
                }
            }
            // Eliminar la coma y el espacio del final
            if (relations.length() > 0) {
                relations.delete(relations.length() - 2, relations.length());
            }
            return relations.toString();
        }
        return "";
    }
    

    // Verificar si el vertice de la lista es valido
    private boolean isValidVertexIndex(int vertexIndex) {
        return vertexsList.isValidIndex(vertexIndex);
    }

    // obtener un vertice por indice
    private Vertex getVertexByIndex(int vertexIndex) {
        return vertexsList.getValueByIndex(vertexIndex);
    }

    //devuelve el numero de vertices 
    public int getNumVertexs() {
        return numVertexs;
    }

    //devuelve el numero de vertices en la lista de vertices
    public int getVertexsListSize() {
        return vertexsList.getSize();
    }
    
    //devuelve la lista de vertices
    public SimpleList getVertexsList() {
        return this.vertexsList;
    }

}
