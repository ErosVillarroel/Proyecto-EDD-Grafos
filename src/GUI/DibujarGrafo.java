/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author andre
 */

/*
Una vez que creada la clase Grafo, puedes agregarla a un contenedor de tu elección, como un JFrame,
y llamar al método repaint() para actualizar el dibujo del grafo.

    [Details] - Modificar para que reciba las listas de la main interface
              - Buscar forma de añadir, modificar y eliminar nodos a voluntadxd (click derecho, menu contextual, lanzar evento)
*/
public class DibujarGrafo extends JPanel {
    /*
    Dentro de la clase, creamos dos listas: 
    una lista de vértices (vertices) y una lista de aristas (aristas).
    Luego, agregamos algunos vértices y aristas a las listas.
    */
    private ArrayList<Point> vertices = new ArrayList<Point>();
    private ArrayList<int[]> aristas = new ArrayList<int[]>();

    public DibujarGrafo() {
        // Agregar algunos vértices y aristas
        vertices.add(new Point(50, 50));
        vertices.add(new Point(150, 50));
        vertices.add(new Point(100, 150));
        aristas.add(new int[]{0, 1});
        aristas.add(new int[]{1, 2});
        aristas.add(new int[]{2, 0});

        // Agregar un MouseListener para detectar eventos del mouse
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Agregar un nuevo vértice en la posición del clic del mouse
                vertices.add(e.getPoint());
                repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar las aristas
        g.setColor(Color.BLACK);
        for (int[] arista : aristas) {
            Point p1 = vertices.get(arista[0]);
            Point p2 = vertices.get(arista[1]);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        // Dibujar los vértices
        g.setColor(Color.BLUE);
        for (Point vertice : vertices) {
            g.fillOval(vertice.x - 5, vertice.y - 5, 10, 10);
        }
    }

    public static void main(String[] args) {
        // Crear la ventana y mostrar el panel
        JFrame frame = new JFrame("Dibujar Grafo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new DibujarGrafo());
        frame.setPreferredSize(new Dimension(650, 440));
        frame.pack();
        frame.setVisible(true);
    }
}
