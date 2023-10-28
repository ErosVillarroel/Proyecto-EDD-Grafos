package main;

import Classes.OurGraph;
import Classes.Vertex;

public class Main {

    public static void main(String[] args) {

        /*

        -Disenar interfaz grafica:
            -Graficar el grafo
            -Reacomodar el visualizador segun los casos: eliminar, y añadir vertices
            -Añadir funcionalidades al visual: 
                * Mover la pantalla manteniendo presionado clic izquierdo
                * Si es posible: clic derecho en cada nodo para editar su nombre y modificar su relacion?
            -
        -Construir logica:
        
            -Logica para verificar la existencia de archivos
            -Logica para modificar archivos 
            -Parsear la data a leible
            -
        
        
         */
//        MainInterface interfaz1 = new MainInterface();
        OurGraph graph = new OurGraph(8);

        Vertex vertex = new Vertex("A");
        Vertex vertex1 = new Vertex("B");
        Vertex vertex2 = new Vertex("C");
        Vertex vertex3 = new Vertex("D");
        Vertex vertex4 = new Vertex("E");
        Vertex vertex5 = new Vertex("F");
        Vertex vertex6 = new Vertex("G");
        Vertex vertex7 = new Vertex("H");

        graph.addVertex(vertex);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);
        graph.addVertex(vertex6);
        graph.addVertex(vertex7);
        
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(3, 1);
        graph.addEdge(5, 7);
        graph.addEdge(4, 2);
        graph.addEdge(0, 7);
        graph.addEdge(1, 0);
        graph.addEdge(7, 6);

        graph.print();

        graph.deleteVertex(0);

        graph.print();

    }

}
