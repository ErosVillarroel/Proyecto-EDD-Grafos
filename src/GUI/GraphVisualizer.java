/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Classes.Kosaraju;
import Classes.OurGraph;
import DataStructureClasses.SimpleList;

import java.awt.BorderLayout;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerPipe;

/**
 *
 * @author andre
 */
public class GraphVisualizer {

    private Graph graph;
    private Viewer viewer;

    public GraphVisualizer() {
        // Crear un nuevo grafo
        this.graph = new SingleGraph("Grafo");
        this.viewer = null;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void eraseVisualizer() {
        if (viewer != null) {
            viewer.close();
            viewer = null;
        }

        graph = new SingleGraph("Grafo");
        viewer = graph.display(true);
        viewer.enableAutoLayout();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
        graph.clear();
    }

    public void resetView() {
        if (viewer != null) {
            viewer.getDefaultView().getCamera().resetView();
        }
    }

    public void visualizeGraph(OurGraph grafo, JPanel graphPanel) {
        // Antes de cargar un nuevo grafo
        if (viewer != null) {
            viewer.close();
        }

        // Crear un nuevo grafo y visor
        this.graph = new SingleGraph("Grafo");
        this.viewer = graph.display();
        viewer.enableAutoLayout();

        // Aplicar la configuración para ocultar la ventana emergente de GraphStream        
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);

        // limpiar el grafo existente
        graph.clear();

        // Actualizar o agregar nodos y aristas según sea necesario
        for (int i = 0; i < grafo.getVertexsListSize(); i++) {
            Node node = graph.getNode(Integer.toString(i));
            if (node == null) {
                // El nodo no existe, lo creamos
                node = graph.addNode(Integer.toString(i));
                node.setAttribute("ui.label", grafo.getVertexName(i));
                node.setAttribute("ui.style", "fill-color: blue; size: 15px; text-size: 20px;");

            } else {
                node = graph.addNode(Integer.toString(i));
                node.setAttribute("ui.label", grafo.getVertexName(i));
                node.setAttribute("ui.style", "fill-color: blue; size: 15px; text-size: 20px;");
                // Establecer posicion específica para cada nodo
//                double x = 50 + i * 100;  // Ajustar valores
//                double y = 50;
//                double z = 0;
//
//                node.setAttribute("xyz", x, y, z);

//            /*
//                NODOS ISLA [CONFIGURACION DE ESTILOS? OURGRAPH METHODS]
//            */ 
//            if (grafo.isVertexIsland(i)) {
//                // Si el nodo es una isla (sin aristas), cambiar su estilo
//                node.setAttribute("ui.style", "fill-color: gray; size: 20px; text-size: 20px;");
//            } else {
//                // Si el nodo tiene aristas, cambiar su estilo aquí
//                node.setAttribute("ui.style", "fill-color: orange; size: 20px; text-size: 20px;");
//            }
            }
        }
        // Limpiar nodos no utilizados
        for (int i = 0; i < grafo.getVertexsListSize(); i++) {
            Node node = graph.getNode(Integer.toString(i));

            int nodeIndex = Integer.parseInt(node.getId());
            if (!grafo.vertexExists(nodeIndex)) {
                // El nodo no está presente en el grafo, eliminarlo de la visualización
                graph.removeNode(node);
            }
        }

        // Añadir aristas
        for (int i = 0; i < grafo.getNumVertexs(); i++) {
            for (int j = 0; j < grafo.getNumVertexs(); j++) {
                if (grafo.checkEdge(i, j)) {
                    Edge edge = graph.getEdge(Integer.toString(i) + "-" + Integer.toString(j));
                    if (edge == null) {
                        // La arista no existe, la creamos
                        edge = graph.addEdge(Integer.toString(i) + "-" + Integer.toString(j), Integer.toString(i), Integer.toString(j), true);
                        // Configurar atributos personalizados
                        edge.setAttribute("ui.style", "fill-color: red; size: 2px; arrow-size: 10px, 5px;");
                    }
                }
            }
        }

        // Identificar y manejar nodos aislados
        for (int i = 0; i < grafo.getVertexsListSize(); i++) {
            Node node = graph.getNode(Integer.toString(i));
            int nodeIndex = Integer.parseInt(node.getId());

            if (!grafo.vertexExists(nodeIndex)) {
                // El nodo no está presente en el grafo, eliminarlo de la visualización
                graph.removeNode(node);
            } else if (node.getDegree() == 0) {
                // El nodo no tiene aristas conectadas, establecer un estilo especial
                node.setAttribute("ui.style", "fill-color: gray; size: 20px; text-size: 20px;");
            }
//            else if (isNodeStronglyConnected(nodeIndex, grafo)) {
//                // Establecer un color distintivo para los nodos fuertemente conexos
//                node.setAttribute("ui.style", "fill-color: purple; size: 20px; text-size: 20px;");
//
//            }
        }

        // Obtener el panel de vista y agregarlo al panel proporcionado
        ViewPanel view = (ViewPanel) viewer.getDefaultView();
        graphPanel.setLayout(new BorderLayout());
        graphPanel.add(view, BorderLayout.CENTER);
        graphPanel.revalidate();
        graphPanel.repaint();

        // Agregar escuchador de la rueda del mouse para el zoom
        view.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    // Rueda hacia arriba (zoom in)
                    zoomIn(viewer);
                } else {
                    // Rueda hacia abajo (zoom out)
                    zoomOut(viewer);
                }
            }
        });

    }

    public void zoomIn(Viewer viewer) {
        if (viewer != null && viewer.getDefaultView() != null) {
            viewer.getDefaultView().getCamera().setViewPercent(viewer.getDefaultView().getCamera().getViewPercent() * 0.9);
        }
    }

    public void zoomOut(Viewer viewer) {
        if (viewer != null && viewer.getDefaultView() != null) {
            viewer.getDefaultView().getCamera().setViewPercent(viewer.getDefaultView().getCamera().getViewPercent() * 1.1);
        }
    }

    private boolean isNodeStronglyConnected(int nodeIndex, OurGraph grafo) {

        Kosaraju kosaraju = new Kosaraju(grafo);
        SimpleList<SimpleList> stronglyConnectedComponents = kosaraju.getComponents();

        for (int i = 0; i < stronglyConnectedComponents.getSize(); i++) {
            SimpleList<Integer> component = stronglyConnectedComponents.getValueByIndex(i);
            if (component.contains(nodeIndex)) {
                return true;
            }
        }

        return false;
    }

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
