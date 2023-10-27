/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Classes.OurGraph;
import java.awt.BorderLayout;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

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

    public void visualizeGraph(OurGraph grafo, JPanel graphPanel) {
        // Antes de cargar un nuevo grafo
        if (viewer != null) {
            viewer.close();
        }

        // Crear un nuevo grafo y visor
        this.graph = new SingleGraph("Grafo");
        this.viewer = graph.display(false);
        viewer.enableAutoLayout();

        // limpiar el grafo existente
        graph.clear();

        // Actualizar o agregar nodos y aristas según sea necesario
        for (int i = 0; i < grafo.getVertexsListSize(); i++) {
            Node node = graph.addNode(Integer.toString(i));
            node.setAttribute("ui.label", grafo.getVertexName(i));
            node.setAttribute("ui.style", "fill-color: blue; size: 15px; text-size: 20px;");
            // Establecer posicion específica para cada nodo
            double x = 50 + i * 100;  // Ajustar valores
            double y = 50;
            double z = 0;

            node.setAttribute("xyz", x, y, z);
        }

        for (int i = 0; i < grafo.getNumVertexs(); i++) {
            for (int j = 0; j < grafo.getNumVertexs(); j++) {
                if (grafo.checkEdge(i, j)) {
                    Edge edge = graph.getEdge(Integer.toString(i) + "-" + Integer.toString(j));
                    if (edge == null) {
                        // La arista no existe, la creamos
                        edge = graph.addEdge(Integer.toString(i) + "-" + Integer.toString(j), Integer.toString(i), Integer.toString(j));
                        // Configurar atributos personalizados
                        edge.setAttribute("ui.style", "fill-color: red; size: 2px; arrow-size: 50px, 50px;");
                    }
                }
            }
        }
        //graphPanel.revalidate();
        //graphPanel.repaint();

        // Crear un visor de grafo
        //viewer = graph.display(false);
        //viewer.enableAutoLayout();

        // Aplicar la configuración para ocultar la ventana emergente de GraphStream
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);

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
        viewer.getDefaultView().getCamera().setViewPercent(viewer.getDefaultView().getCamera().getViewPercent() * 0.9);
    }

    public void zoomOut(Viewer viewer) {
        viewer.getDefaultView().getCamera().setViewPercent(viewer.getDefaultView().getCamera().getViewPercent() * 1.1);
    }

}
