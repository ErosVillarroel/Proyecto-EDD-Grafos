/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Classes.OurGraph;
import Classes.Kosaraju;
import Classes.Vertex;
import DataStructureClasses.SimpleList;
import FileManager.ComboBoxAPI;
import FileManager.FileManager;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author bcsoporte
 */
public class MainInterface extends javax.swing.JFrame {

    // definir las listas globales con los datos necesario de los vertices
    private OurGraph grafo;
    private GraphVisualizer graphVisualizer;

    /**
     * Creates new form main
     */
    public MainInterface() {
        initComponents();
        System.setProperty("org.graphstream.ui", "swing");
        setTitle("Pantalla Principal");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        // Inicializar Grafo
        this.grafo = null;
        this.graphVisualizer = new GraphVisualizer();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

//        Inicializar GraphVisualizer
//        graphVisualization = new GraphVisualizer();
        this.setVisible(true);
    }

    private void showAboutDialog() {
        String aboutMessage = "[ESTRUCTURAS DE DATOS]\n* Proyecto de Grafo *\n\n"
                + "Autores:\n"
                + "- [Nombre del Autor 1]\n"
                + "- [Nombre del Autor 2]\n\n"
                + "Descripción:\n"
                + "[Breve explicación del proyecto y su propósito]";

        JOptionPane.showMessageDialog(
                null,
                aboutMessage,
                "Acerca del Programa",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private int showVertexList() {
        if (grafo == null || grafo.getVertexsListSize() == 0) {
            JOptionPane.showMessageDialog(null, "No hay usuarios para modificar.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }

        StringBuilder message = new StringBuilder("Seleccione el indice del usuario que desea modificar:\n");

        for (int i = 0; i < grafo.getVertexsListSize(); i++) {
            message.append(i).append(": ").append(grafo.getVertexName(i)).append("\n");
        }

        try {
            return Integer.parseInt(JOptionPane.showInputDialog(null, message.toString()));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor numerico valido.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private boolean isValidVertexIndex(int vertexIndex) {
        return vertexIndex >= 0 && vertexIndex <= this.grafo.getVertexsListSize();
    }

    private int showExitConfirmationDialog() {

        String[] options = new String[]{"Sí, salir", "No, quedarse"};
        int choice = JOptionPane.showOptionDialog(null, "¿Realmente quieres salir del programa?", "Confirmación de salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        return choice;
    }

    public void rebootGraph() {
        // Reiniciar el grafo a un estado inicial
        this.grafo = null;

        // Reiniciar el visualizador de grafo
        if (this.graphVisualizer != null) {
            this.graphVisualizer.eraseVisualizer();
        }

        // Mensaje informativo
//        JOptionPane.showMessageDialog(null, "Proyecto reiniciado con exito!", "Reinicio", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showAddRelationsDialog(int selectedVertexIndex) {
        if (selectedVertexIndex < 0 || selectedVertexIndex >= grafo.getNumVertexs()) {
            JOptionPane.showMessageDialog(null, "Seleccione un vertice valido para agregar relaciones.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String input = JOptionPane.showInputDialog(null, "Ingrese multiples relaciones para el vertice " + selectedVertexIndex + " (separadas por comas):\n\n*Ejemplo:\nRelaciones: 1,2,3,...,n\nRelacionar vertice ORIGEN con: 1,9,6,3,etc");

        if (input != null && !input.isEmpty()) {
            // Procesar el input y agregar relaciones al grafo
            String[] relations = input.split(",");
            for (String relation : relations) {
                try {
                    int targetVertex = Integer.parseInt(relation.trim());
                    grafo.addEdge(selectedVertexIndex, targetVertex);
                } catch (NumberFormatException e) {
                    // Manejar el caso en que el usuario ingresa algo que no es un número
                    JOptionPane.showMessageDialog(null, "Ingrese números separados por comas.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Visualizar el grafo actualizado
            graphVisualizer.visualizeGraph(grafo, content);
            JOptionPane.showMessageDialog(null, "Relaciones agregadas con exito al vertice " + selectedVertexIndex + ".", "Exito", JOptionPane.INFORMATION_MESSAGE);
            grafo.print();
        }
    }

    private void showDeleteRelationsDialog(int selectedVertexIndex) {
        if (selectedVertexIndex < 0 || selectedVertexIndex >= grafo.getNumVertexs()) {
            JOptionPane.showMessageDialog(null, "Seleccione un vertice valido para eliminar relaciones.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String currentRelations = grafo.getVertexRelations(selectedVertexIndex);
        String input = JOptionPane.showInputDialog(null, "\nIngrese relaciones para eliminar (separadas por comas):"
                + "\n\nRelaciones actuales del vértice: '" + selectedVertexIndex + "'\n==> " + currentRelations);

        if (input != null && !input.isEmpty()) {
            // Procesar el input y eliminar relaciones del grafo
            String[] relations = input.split(",");
            for (String relation : relations) {
                try {
                    int targetVertex = Integer.parseInt(relation.trim());
                    grafo.deleteEdge(selectedVertexIndex, targetVertex);
                } catch (NumberFormatException e) {

                    JOptionPane.showMessageDialog(null, "Ingrese números separados por comas.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Visualizar el grafo actualizado
            graphVisualizer.visualizeGraph(grafo, content);
            JOptionPane.showMessageDialog(null, "Relaciones eliminadas con éxito del vértice " + selectedVertexIndex + ".", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            grafo.print();
        }
    }

    private void updateJTextArea() {
        StringBuilder usersText = new StringBuilder("Usuarios:\n");
        StringBuilder relationsText = new StringBuilder("Relaciones:\n");

        // Obtener nombres de usuarios y relaciones
        for (int i = 0; i < grafo.getVertexsListSize(); i++) {
            String userName = grafo.getVertexName(i);
            String relationsString = grafo.getVertexRelations(i);

            // Puedes personalizar la forma en que se muestra la información en el JTextArea
            usersText.append(i).append(": @").append(userName).append("\n");
            relationsText.append("@").append(userName).append(", ").append(relationsString).append("\n");
        }

        // Actualizar el contenido del JTextArea
        this.userListTextArea.setText(usersText.toString() + "\n" + relationsText.toString());
    }

    private boolean isStronglyConnected(int nodeIndex, OurGraph grafo) {
        // Verificar si el nodo pertenece a algún componente fuertemente conexo
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        bg = new javax.swing.JPanel();
        content = new javax.swing.JPanel();
        resetViewBtn = new javax.swing.JButton();
        bg2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        vertexDeleteBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        checkKosaraju = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        vertexAddBtn = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        MatrixTextArea = new javax.swing.JTextArea();
        cargarButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        userListTextArea = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        manualTextArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        menubar = new javax.swing.JMenuBar();
        menu1 = new javax.swing.JMenu();
        openBtn = new javax.swing.JMenuItem();
        saveBtn = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        newProjectBtn = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitBtn = new javax.swing.JMenuItem();
        menu2 = new javax.swing.JMenu();
        changeVertexNameBtn = new javax.swing.JMenuItem();
        addRelationMenu = new javax.swing.JMenuItem();
        removeRelationMenu = new javax.swing.JMenuItem();
        modifyRelationMenu = new javax.swing.JMenuItem();
        aboutBtn = new javax.swing.JMenu();
        aboutMsgBtn = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setBackground(new java.awt.Color(255, 133, 83));
        bg.setBorder(new javax.swing.border.MatteBorder(null));
        bg.setForeground(new java.awt.Color(102, 102, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        resetViewBtn.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        resetViewBtn.setText("Reiniciar Vista");
        resetViewBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        resetViewBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        resetViewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetViewBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(resetViewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(703, Short.MAX_VALUE))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(resetViewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(412, Short.MAX_VALUE))
        );

        bg.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 830, 460));

        bg2.setBackground(new java.awt.Color(0, 0, 0));
        bg2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Social Network");
        bg2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, -1, -1));

        vertexDeleteBtn.setFont(new java.awt.Font("Roboto", 3, 14)); // NOI18N
        vertexDeleteBtn.setText("Borrar");
        vertexDeleteBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        vertexDeleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vertexDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vertexDeleteBtnActionPerformed(evt);
            }
        });
        bg2.add(vertexDeleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 620, 170, 60));

        jLabel5.setFont(new java.awt.Font("Roboto Black", 2, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Funciones de Usuarios:");
        bg2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 580, 280, -1));

        checkKosaraju.setFont(new java.awt.Font("Roboto", 3, 14)); // NOI18N
        checkKosaraju.setText("Verificar conexiones");
        checkKosaraju.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        checkKosaraju.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        checkKosaraju.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkKosarajuActionPerformed(evt);
            }
        });
        bg2.add(checkKosaraju, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 570, 180, 90));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Elementos");
        bg2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 480, 180, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Conexos");
        bg2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 520, 210, 40));

        vertexAddBtn.setFont(new java.awt.Font("Roboto", 3, 14)); // NOI18N
        vertexAddBtn.setText("Añadir");
        vertexAddBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        vertexAddBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vertexAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vertexAddBtnActionPerformed(evt);
            }
        });
        bg2.add(vertexAddBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 620, 170, 60));

        bg.add(bg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 830, 750));

        jPanel7.setBackground(new java.awt.Color(255, 102, 0));

        MatrixTextArea.setEditable(false);
        MatrixTextArea.setBackground(new java.awt.Color(204, 255, 153));
        MatrixTextArea.setColumns(20);
        MatrixTextArea.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        MatrixTextArea.setRows(5);
        MatrixTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane3.setViewportView(MatrixTextArea);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        bg.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 450, 210));

        cargarButton.setBackground(new java.awt.Color(255, 153, 153));
        cargarButton.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        cargarButton.setText("Leer Informacion");
        cargarButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cargarButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cargarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarButtonActionPerformed(evt);
            }
        });
        bg.add(cargarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 120, 30));

        jPanel8.setBackground(new java.awt.Color(255, 153, 51));

        jLabel6.setFont(new java.awt.Font("Roboto", 3, 24)); // NOI18N
        jLabel6.setText("Lista");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel7.setText("de");

        jLabel8.setFont(new java.awt.Font("Roboto", 3, 24)); // NOI18N
        jLabel8.setText("Usuarios");

        userListTextArea.setEditable(false);
        userListTextArea.setBackground(new java.awt.Color(153, 255, 153));
        userListTextArea.setColumns(20);
        userListTextArea.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        userListTextArea.setRows(5);
        userListTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane4.setViewportView(userListTextArea);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        bg.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 450, 270));

        manualTextArea.setBackground(new java.awt.Color(204, 255, 153));
        manualTextArea.setColumns(20);
        manualTextArea.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        manualTextArea.setRows(5);
        jScrollPane1.setViewportView(manualTextArea);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 400, 180));

        jLabel4.setFont(new java.awt.Font("Roboto", 3, 18)); // NOI18N
        jLabel4.setText("Editor de texto plano (Manual)");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 260, -1));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 750));

        menu1.setText("Archivo");
        menu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu1ActionPerformed(evt);
            }
        });

        openBtn.setText("Abrir");
        openBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openBtnActionPerformed(evt);
            }
        });
        menu1.add(openBtn);

        saveBtn.setText("Guardar");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        menu1.add(saveBtn);
        menu1.add(jSeparator2);

        newProjectBtn.setText("Nuevo Proyecto");
        newProjectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newProjectBtnActionPerformed(evt);
            }
        });
        menu1.add(newProjectBtn);
        menu1.add(jSeparator1);

        exitBtn.setText("Salir");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        menu1.add(exitBtn);

        menubar.add(menu1);

        menu2.setText("Editor");
        menu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        changeVertexNameBtn.setText("Cambiar Nombre");
        changeVertexNameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeVertexNameBtnActionPerformed(evt);
            }
        });
        menu2.add(changeVertexNameBtn);

        addRelationMenu.setText("Añadir Relaciones");
        addRelationMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRelationMenuActionPerformed(evt);
            }
        });
        menu2.add(addRelationMenu);

        removeRelationMenu.setText("Eliminar Relaciones");
        removeRelationMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeRelationMenuActionPerformed(evt);
            }
        });
        menu2.add(removeRelationMenu);

        modifyRelationMenu.setText("Modificar Relaciones");
        modifyRelationMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyRelationMenuActionPerformed(evt);
            }
        });
        menu2.add(modifyRelationMenu);

        menubar.add(menu2);

        aboutBtn.setText("Acerca de...");
        aboutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        aboutMsgBtn.setText("Sobre el Proyecto");
        aboutMsgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMsgBtnActionPerformed(evt);
            }
        });
        aboutBtn.add(aboutMsgBtn);

        menubar.add(aboutBtn);

        setJMenuBar(menubar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openBtnActionPerformed
//      crear objeto de tipo filechooser que contendra el archivo txt

        if (evt.getSource() == this.openBtn) {

            if (this.grafo == null) {
                FileManager fileManager = new FileManager();
                File file = fileManager.selectFile();

                SimpleList usersList = fileManager.parseUsersFromFile(file);
                SimpleList relationsList = fileManager.parseRelationshipsFromFile(file);

                OurGraph grafo = new OurGraph(usersList, relationsList);
                this.grafo = grafo;
                grafo.print();
                //this.displayFromFile(file);
                // Visualizar el grafo en el panel

                graphVisualizer = new GraphVisualizer();
                graphVisualizer.visualizeGraph(grafo, this.content);

                // actualizar lista de usuarios en la GUI
                this.updateJTextArea();

                JOptionPane.showMessageDialog(null, "El grafo se ha cargado con exito!", "Cargando archivo de texto", JOptionPane.INFORMATION_MESSAGE);

            }

        } else {
            System.out.println("Desea abrir un nuevo grafo? se perderan los cambios sin guardar.");
        }

    }//GEN-LAST:event_openBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if (evt.getSource() == this.exitBtn) {

            int option = showExitConfirmationDialog();

            if (option == JOptionPane.YES_OPTION) {
                System.out.println("Exit");
                JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                System.exit(0);
            } else {
                // El usuario eligio quedarse
                System.out.println("Stay");
                JOptionPane.showMessageDialog(null, "Continuando con el programa...");
                return;
            }
        }
    }//GEN-LAST:event_exitBtnActionPerformed


    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        if (evt.getSource() == this.saveBtn) {
            if (this.grafo == null) {
                JOptionPane.showMessageDialog(null, "Error: El grafo no debe estar vacio!", "Mensaje de Error", JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                FileManager filemanager = new FileManager();
                filemanager.saveGraphToFile(this.grafo);
            }
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void menu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu1ActionPerformed

    private void newProjectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProjectBtnActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == this.newProjectBtn) {

            if (this.grafo != null && this.grafo.getNumVertexs() > 0) {
                int decision = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas reiniciar el grafo? Se perdera toda la informacion.", "Confirmacion", JOptionPane.YES_NO_OPTION);

                if (decision != JOptionPane.YES_OPTION) {
                    // El usuario no quiere reiniciar, salir del método
                    return;
                } else {
                    this.rebootGraph();
                    // actualizar lista de usuarios en la GUI
                    this.updateJTextArea();
                    JOptionPane.showMessageDialog(null, "Grafo reiniciado con éxito", "Reinicio", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El proyecto ya se encuentra sin ningun cambio realizado.", "Atencion!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

        }
    }//GEN-LAST:event_newProjectBtnActionPerformed

    private void vertexDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vertexDeleteBtnActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == this.vertexDeleteBtn) {
            if (this.grafo == null || this.grafo.isGraphEmpty()) {
                JOptionPane.showMessageDialog(null, "El grafo está vacio. No hay vértices para eliminar.", "Grafo Vacío", JOptionPane.WARNING_MESSAGE);
                return;

            }

            int selectedIndex = this.showVertexList();

            if (this.isValidVertexIndex(selectedIndex) && grafo.vertexExists(selectedIndex)) {
                // eliminar el vertice seleccionado por su indice

                this.grafo.deleteVertex(selectedIndex);

                graphVisualizer.visualizeGraph(grafo, content);
                // actualizar lista de usuarios en la GUI
                this.updateJTextArea();

                JOptionPane.showMessageDialog(null, "Vertice en la posicion '" + selectedIndex + "' " + " fue eliminado!", "Eliminado con exito", JOptionPane.INFORMATION_MESSAGE);
                this.grafo.print();

                if (this.grafo.isGraphEmpty()) {
                    this.rebootGraph();
                    this.graphVisualizer.eraseVisualizer();
                }

            } else {
                JOptionPane.showMessageDialog(null, "El vertice no existe!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_vertexDeleteBtnActionPerformed

    private void checkKosarajuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkKosarajuActionPerformed
        if (evt.getSource() == this.checkKosaraju) {
            if (grafo == null || grafo.isGraphEmpty()) {
                JOptionPane.showMessageDialog(null, "El grafo esta vacio. No se puede realizar ninguna accion.", "Grafo Vacio", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            //StringBuilder resultMessage = new StringBuilder("Resultados de la verificacion de nodos fuertemente conectados:\n");

            for (int i = 0; i < this.grafo.getNumVertexs(); i++) {
                boolean isStronglyConnected = this.isStronglyConnected(i, this.grafo);
                //resultMessage.append("Nodo en la posicion ").append(i).append(": ");

                if (isStronglyConnected) {
                    graphVisualizer.visualizeGraph(grafo, this.content);
                    //resultMessage.append("Fuertemente Conectado\n");
                } else {
                    //resultMessage.append("No Fuertemente Conectado\n");
                }
            }
            //JOptionPane.showMessageDialog(null, resultMessage.toString(), "Verificación Completa", JOptionPane.INFORMATION_MESSAGE);

        }

    }//GEN-LAST:event_checkKosarajuActionPerformed

    private void changeVertexNameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeVertexNameBtnActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == this.changeVertexNameBtn) {
            int selectedIndex = showVertexList();

            if (isValidVertexIndex(selectedIndex)) {
                String newNameVertex = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre para el usuario '" + grafo.getVertexName(selectedIndex) + "': ");

                if (newNameVertex != null && !newNameVertex.isEmpty()) {

                    grafo.modifyVertexName(selectedIndex, newNameVertex.toLowerCase());

                    // mostrar cambios visualizer
                    graphVisualizer.visualizeGraph(grafo, content);

                    // actualizar lista de usuarios en la GUI
                    this.updateJTextArea();
                    JOptionPane.showMessageDialog(null, "Nombre modificado exitosamente!");
                }
            } else if (selectedIndex != -1) {
                JOptionPane.showMessageDialog(null, "Indice invalido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_changeVertexNameBtnActionPerformed

    private void vertexAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vertexAddBtnActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == this.vertexAddBtn) {
            if (this.grafo == null) {
                JOptionPane.showMessageDialog(null, "* Creando un nuevo grafo...", "Atencion!", JOptionPane.INFORMATION_MESSAGE);
                String userName = JOptionPane.showInputDialog(null, "Por favor ingrese el nombre del usuario: ").toLowerCase();

                try {
                    OurGraph newGrafo = new OurGraph(userName);
                    this.grafo = newGrafo;

                    // Crear instancia de GraphVisualization
                    // Visualizar el grafo en el panel
                    graphVisualizer.visualizeGraph(grafo, this.content);

                    // actualizar lista de usuarios en la GUI
                    this.updateJTextArea();

                    newGrafo.print();
                } catch (NullPointerException n) {
                    JOptionPane.showMessageDialog(null, "Cancelando operacion.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Index out of bound.");
                }

            } else {
                SimpleList<Vertex> vertexsList = this.grafo.getVertexsList();

                Page2 comboPage = new Page2(vertexsList);

                // Mostrar un diálogo para ingresar el nombre del usuario y seleccionar el vértice
                int result = JOptionPane.showConfirmDialog(null, comboPage, "Añadir Usuario", JOptionPane.OK_CANCEL_OPTION);

                ComboBoxAPI boxAPI = comboPage.parsePage2();
                if (result == JOptionPane.OK_OPTION) {
                    String userName = boxAPI.getUserName();
                    int relationIndex = boxAPI.getRelationIndex();

                    boxAPI.printBoxApi();

                    if (grafo.userExists(userName)) {
                        JOptionPane.showMessageDialog(null, "El usuario '" + userName.toUpperCase() + "' ya existe en la lista!");
                        return;
                    }

                    this.grafo.addUser(userName, relationIndex);

                    // Visualizar el grafo en el panel
                    graphVisualizer.visualizeGraph(this.grafo, this.content);
                    grafo.print();
                } else {
                    JOptionPane.showMessageDialog(null, "Cancelando operacion, volviendo...");
                }
            }
        }
    }//GEN-LAST:event_vertexAddBtnActionPerformed

    private void cargarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarButtonActionPerformed
        try {
            String textAreaContent = this.manualTextArea.getText();
            FileManager fileManager = new FileManager();

            String[] validationSplit = textAreaContent.split("\n");

            if (!(textAreaContent.contains("usuarios"))) {
                JOptionPane.showMessageDialog(null, "Formato incorrecto: hace falta el campo usuarios.");
                return;
            }

            if (!(textAreaContent.contains("relaciones"))) {
                JOptionPane.showMessageDialog(null, "Formato incorrecto: hace falta el campo relaciones.");
                return;
            }

            SimpleList<String> usersList = fileManager.parseUsersFromTextArea(textAreaContent);
            SimpleList<String> relationsList = fileManager.parseRelationshipsFromTextArea(textAreaContent);

            if (usersList == null) {
                JOptionPane.showMessageDialog(null, "Error en el formato de los usuarios.");
            }

            if (relationsList == null) {
                JOptionPane.showMessageDialog(null, "Error en el formato de las relaciones.");
            }

            OurGraph grafo = new OurGraph(usersList, relationsList);
            grafo.print();
            this.grafo = grafo;

            graphVisualizer = new GraphVisualizer();
            graphVisualizer.visualizeGraph(grafo, this.content);

            // actualizar lista de usuarios en la GUI
            this.updateJTextArea();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error desconocido.");
        }

    }//GEN-LAST:event_cargarButtonActionPerformed

    private void resetViewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetViewBtnActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == this.resetViewBtn) {
            if (this.graphVisualizer.getViewer() != null) {
                this.graphVisualizer.resetView();
            }

        }
    }//GEN-LAST:event_resetViewBtnActionPerformed

    private void addRelationMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRelationMenuActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == this.addRelationMenu) {

            if (grafo == null || grafo.isGraphEmpty()) {
                JOptionPane.showMessageDialog(null, "El grafo esta vacio. No hay vertices para agregar relaciones.", "Grafo Vacio", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int selectedIndex = showVertexList();

            if (isValidVertexIndex(selectedIndex) && grafo.vertexExists(selectedIndex)) {

                this.showAddRelationsDialog(selectedIndex);
                // actualizar lista de usuarios en la GUI
                this.updateJTextArea();
            } else {
                JOptionPane.showMessageDialog(null, "El vértice no existe!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_addRelationMenuActionPerformed

    private void removeRelationMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeRelationMenuActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == this.removeRelationMenu) {

            if (grafo == null || grafo.isGraphEmpty()) {
                JOptionPane.showMessageDialog(null, "El grafo esta vacio. No hay vertices para eliminar relaciones.", "Grafo Vacio", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int selectedIndex = showVertexList();

            if (isValidVertexIndex(selectedIndex) && grafo.vertexExists(selectedIndex)) {

                this.showDeleteRelationsDialog(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(null, "El vertice no existe!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_removeRelationMenuActionPerformed

    private void modifyRelationMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyRelationMenuActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == this.modifyRelationMenu) {
            if (grafo == null || grafo.isGraphEmpty()) {
                JOptionPane.showMessageDialog(null, "El grafo esta vacio. No hay vertices para ser modificados.", "Grafo Vacio", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int selectedIndex = showVertexList();

            if (this.isValidVertexIndex(selectedIndex) && grafo.vertexExists(selectedIndex)) {
                String[] options = {"Añadir Relación", "Eliminar Relación", "Cancelar"};
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Seleccione la accion a realizar:",
                        "Modificar Relaciones",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                switch (choice) {
                    case 0:
                        // Añadir Relación
                        this.showAddRelationsDialog(selectedIndex);
                        // actualizar lista de usuarios en la GUI
                        this.updateJTextArea();
                        break;
                    case 1:
                        // Eliminar Relación
                        this.showDeleteRelationsDialog(selectedIndex);
                        // actualizar lista de usuarios en la GUI
                        this.updateJTextArea();
                        break;
                    default:
                        // Cancelar
                        break;
                }
            }

        }

    }//GEN-LAST:event_modifyRelationMenuActionPerformed

    private void aboutMsgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMsgBtnActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == this.aboutMsgBtn) {
            this.showAboutDialog();
        }
    }//GEN-LAST:event_aboutMsgBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public void runMainGUI() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea MatrixTextArea;
    private javax.swing.JMenu aboutBtn;
    private javax.swing.JMenuItem aboutMsgBtn;
    private javax.swing.JMenuItem addRelationMenu;
    private javax.swing.JPanel bg;
    private javax.swing.JPanel bg2;
    private javax.swing.JButton cargarButton;
    private javax.swing.JMenuItem changeVertexNameBtn;
    private javax.swing.JButton checkKosaraju;
    private javax.swing.JPanel content;
    private javax.swing.JMenuItem exitBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTextArea manualTextArea;
    private javax.swing.JMenu menu1;
    private javax.swing.JMenu menu2;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JMenuItem modifyRelationMenu;
    private javax.swing.JMenuItem newProjectBtn;
    private javax.swing.JMenuItem openBtn;
    private javax.swing.JMenuItem removeRelationMenu;
    private javax.swing.JButton resetViewBtn;
    private javax.swing.JMenuItem saveBtn;
    private javax.swing.JTextArea userListTextArea;
    private javax.swing.JButton vertexAddBtn;
    private javax.swing.JButton vertexDeleteBtn;
    // End of variables declaration//GEN-END:variables
}
