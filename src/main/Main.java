package main;

import Classes.Graph;
import Classes.Vertex;

public class Main {

    public static void main(String[] args) {
        /*
        -Construir Clases de datos
        -Disenar interfaz grafica:
        
            -Disenar el panel
            -Anadir menu de opciones
            -Graficar el grafo
        
        -Construir logica:
        
            -Logica para verificar la existencia de archivos
            -Logica para ingresar nuevos archivos
            -Logica para modificar archivos 
        
        -Construir clase stack 
        */
        System.out.println("Holla mundo");
        System.out.println("Wenas its me man");
        
        Graph graph = new Graph(5);
        graph.addVertex(new Vertex("A"));
        graph.addVertex(new Vertex("B"));
        graph.addVertex(new Vertex("C"));
        graph.addVertex(new Vertex("D"));
        graph.addVertex(new Vertex("E"));
        
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(2,4);
        graph.addEdge(4,0);
        graph.addEdge(4,2);
        
        graph.print();
        
    }
    
}
