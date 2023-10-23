/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Classes.Graphe;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

/**
 *
 * @author andre
 */
public class DrawGraph extends JPanel implements MouseListener, MouseMotionListener {

    private Graphe graph;
    private int selectedVertexIndex = -1; // Índice del vértice seleccionado


    // Constructor para inicializar el grafo
    public DrawGraph() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    // Constructor que toma como parametro un Grafo
    public DrawGraph(Graphe graph) {
        this.graph = graph;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setGrafo(Graphe graph) {
        this.graph = graph;
        repaint(); // Volver a pintar cuando se actualice el grafo
    }

    // DIBUJAR EL GRAFO
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (graph != null) {
            int numVertices = graph.getNumVertexs();

            // Calcular el centro del panel
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            // Calcular el radio del círculo en el que se colocarán los vértices
            int radius = Math.min(getWidth(), getHeight()) / 2 - 50;

            Graphics2D g2d = (Graphics2D) g;

            // Dibujar las aristas
            int[][] matrix = graph.getMatrix();
            for (int i = 0; i < numVertices; i++) {
                double angleI = 2 * Math.PI * i / numVertices;
                int x1 = (int) (centerX + radius * Math.cos(angleI));
                int y1 = (int) (centerY + radius * Math.sin(angleI));

                // Dibujar el vértice como un círculo
                Ellipse2D.Double vertex = new Ellipse2D.Double(x1 - 10, y1 - 10, 20, 20);
                g2d.setColor(Color.MAGENTA);
                g2d.fill(vertex);
                g2d.setColor(Color.BLACK);
                g2d.draw(vertex);

                // Mostrar el nombre del vértice
                String vertexName = graph.getVertexName(i);
                g2d.setColor(Color.BLACK);
                g2d.drawString(vertexName, x1 - 5, y1 - 15);

                for (int j = 0; j < numVertices; j++) {
                    if (matrix[i][j] == 1) {
                        // Dibujar una línea entre los vértices conectados
                        double angleJ = 2 * Math.PI * j / numVertices;
                        int x2 = (int) (centerX + radius * Math.cos(angleJ));
                        int y2 = (int) (centerY + radius * Math.sin(angleJ));

                        // Dibujar la línea de la arista
                        g2d.setColor(Color.BLUE);
                        g2d.draw(new Line2D.Double(x1, y1, x2, y2));

                        // Dibujar la flecha al final de la arista con color verde
                        dibujarFlecha(g2d, x1, y1, x2, y2, Color.blue.darker());
                    }
                }
            }
        }
    }

    private void dibujarFlecha(Graphics2D g2d, int x1, int y1, int x2, int y2, Color color) {
        int arrowSize = 8;

        double angle = Math.atan2(y2 - y1, x2 - x1);
        int x = (int) (x2 - arrowSize * Math.cos(angle));
        int y = (int) (y2 - arrowSize * Math.sin(angle));

        Polygon arrowHead = new Polygon();
        arrowHead.addPoint(x2, y2);
        arrowHead.addPoint(x + arrowSize, y + arrowSize);
        arrowHead.addPoint(x + arrowSize, y - arrowSize);

        g2d.setColor(color); // Cambia el color de la flecha
        g2d.fillPolygon(arrowHead);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
