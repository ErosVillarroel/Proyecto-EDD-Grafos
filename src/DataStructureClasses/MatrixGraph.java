/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructureClasses;

/**
 *
 * @author andre
 */
public class MatrixGraph {

    int numVert;
    Vertex[] vertex;
    int[][] matrixAd;

    // CONSTRUCTOR argumento: maximo de numero de vertices?
    public MatrixGraph(int mx) {
        this.matrixAd = new int[mx][mx];
        this.vertex = new Vertex[mx];
        for (int i = 0; i < mx; i++) {
            for (int j = 0; j < mx; j++) {
                this.matrixAd[i][j] = 0;
            }
        }
        this.numVert = 0;
    }

    // busca el vertice en el array. Devuelve -1 si no lo encuentra
    public int existVertex(String vs_name) {
        Vertex vertice = new Vertex(vs_name);
        boolean found = false;
        int i = 0;

        for (; i < this.numVert && !found;) {
            found = this.vertex[i].equals(vertice);
            if (!found) {
                i++;
            }
        }
        return (i < this.numVert) ? i : -1;
    }

    // crear un vertice, si no existe en la lista incrementa el numeor de vertices y lo asigna
    public void createVertex(String name) {
        // comprueba si ya existe en la lista un vertice con el nombre "name"
        boolean exists = existVertex(name) >= 0;

        if (!exists) {
            Vertex vertice = new Vertex(name);
            vertice.assignVertex(this.numVert);
            this.vertex[this.numVert++] = vertice;
        }
    }

    // añadir un arco: recibe el nombre de cada vertice del arco
    public void newArco(String a, String b) throws Exception {
        int va,
         vb;
        va  = this.existVertex(a);
        vb = this.existVertex(b);

        if (va  < 0 || vb < 0) {
            throw new Exception("Vertice no existe");
        }
        this.matrixAd[va][vb] = 1;
    }

    // determina si dos vertices forman un arco, o sea si el elemento de la matriz de adyacencia es 1
    public boolean isAdjacent(String a, String b) throws Exception {
        int va,vb;
        va  = this.existVertex(a);
        vb = this.existVertex(b);

        if (va  < 0 || vb < 0) throw new Exception("Vertice no existe");
        return this.matrixAd[va][vb] == 1;
    }
    
    public void printGrafo(){
        for (int i = 0; i < this.matrixAd.length; i++) {
            for (int j = 0; j < this.matrixAd[i].length; j++){
                System.out.print(this.matrixAd[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
