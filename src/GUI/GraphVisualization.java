/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Classes.ourGraph;
import java.awt.BorderLayout;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.swing_viewer.*;
import org.graphstream.ui.view.Viewer;

/**
 *
 * @author andre
 */
public class GraphVisualization {

    private Graph graph;

    public GraphVisualization() {
        // Crear un nuevo grafo
        this.graph = new SingleGraph("Grafo");
    }

    public void visualizeGraph(ourGraph grafo, JPanel graphPanel) {
        // Limpiar el grafo existente
        graph.clear();

        // Agregar vértices al grafo
        for (int i = 0; i < grafo.getVertexsListSize(); i++) {
            Node node = graph.addNode(Integer.toString(i));
            node.setAttribute("ui.label", grafo.getVertexName(i));

            // Establecer posición específica para cada nodo
            double x = 50 + i * 100;  // Ajusta estos valores según sea necesario
            double y = 50;
            double z = 0;

            node.setAttribute("xyz", x, y, z);
        }

        // Agregar aristas al grafo con atributos personalizados
        for (int i = 0; i < grafo.getNumVertexs(); i++) {
            for (int j = 0; j < grafo.getNumVertexs(); j++) {
                if (grafo.checkEdge(i, j)) {
                    Edge edge = graph.addEdge(Integer.toString(i) + "-" + Integer.toString(j), Integer.toString(i), Integer.toString(j));
                    // Configurar atributos personalizados
                    edge.setAttribute("ui.style", "shape: line; fill-mode: dyn-plain; fill-color: #222, #555, green, yellow; arrow-size: 3px, 2px;");
                }
            }
        }

        // Crear un visor de grafo
        Viewer viewer = graph.display(false);
        viewer.enableAutoLayout();

        // Aplicar la configuración para ocultar la ventana emergente de GraphStream
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);

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
