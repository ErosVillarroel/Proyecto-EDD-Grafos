/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Classes.OurGraph;
import Classes.Kosaraju;
import Classes.Vertex;
import DataStructureClasses.SimpleList;
import FileManager.FileManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author bcsoporte
 */
public class MainInterface extends javax.swing.JFrame {

    // definir las listas globales con los datos necesario de los vertices
    private SimpleList<String> usersList = new SimpleList();
    private SimpleList<String> relationsList = new SimpleList();
    private OurGraph grafo;
    //Page1 page1 = new Page1();
    //Page2 page2 = new Page2();
    private GraphVisualization graphVisualization;

//    DibujarGrafo dibujarGrafo = new DibujarGrafo();
//    DrawGraph puebaDraw = new DrawGraph();
    /**
     * Creates new form main
     */
    public MainInterface() {
        initComponents();
        System.setProperty("org.graphstream.ui", "swing");
        setTitle("Pantalla Principal");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
//      setDefaultCloseOperation(EXIT_ON_CLOSE);
//      page1.setSize(410, 320);
//      page1.setLocation(0, 0);
//      content.removeAll();
//      content.add(page1, BorderLayout.CENTER);
//      content.revalidate();
//      content.repaint();
        // Inicializar GraphVisualization
        graphVisualization = new GraphVisualization();

        // Llamar al método visualizeGraph con tu grafo y el panel de contenido
        //Graphe grafo = obtenerTuGrafo(); // Reemplaza esto con tu lógica para obtener el grafo
        this.setVisible(true);

    }

//    private void displayFromFile(File file) {
//        String line;
//
//        try {
//            this.graphComponents.setText("");
//            FileReader filereader = new FileReader(file);
//            BufferedReader reader = new BufferedReader(filereader);
//
//            while ((line = reader.readLine()) != null) {
//                // ejemplo: mostrar informacion en un text area
//                this.graphComponents.append(line);
//                this.graphComponents.append("\n");
//                line = reader.readLine();
//            }
//
//            reader.close();
//            JOptionPane.showMessageDialog(null, "El archivo ha sido cargado exitosamente!");
//
//        } catch (IOException e) {
//            e.printStackTrace(System.out);
//        } catch (NullPointerException e) {
//            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún archivo");
//        }
//    }
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
        bg2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        vertexButn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        checkKosaraju = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        graphTextarea = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        cargarButton = new javax.swing.JButton();
        menubar = new javax.swing.JMenuBar();
        menu1 = new javax.swing.JMenu();
        openBtn = new javax.swing.JMenuItem();
        saveBtn = new javax.swing.JMenuItem();
        newProjectBtn = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitBtn = new javax.swing.JMenuItem();
        menu2 = new javax.swing.JMenu();
        aboutButn = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setBackground(new java.awt.Color(255, 133, 83));
        bg.setBorder(new javax.swing.border.MatteBorder(null));
        bg.setForeground(new java.awt.Color(102, 102, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        bg.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 830, 460));

        bg2.setBackground(new java.awt.Color(0, 0, 0));
        bg2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Social Network");
        bg2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, -1, -1));

        vertexButn.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        vertexButn.setText("Vertices");
        vertexButn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        vertexButn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vertexButnActionPerformed(evt);
            }
        });
        bg2.add(vertexButn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 170, 60));

        jLabel5.setFont(new java.awt.Font("Roboto Black", 2, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Añadir usuarios");
        bg2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, 180, -1));

        checkKosaraju.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        checkKosaraju.setText("Verificar conexiones");
        checkKosaraju.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        checkKosaraju.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkKosarajuActionPerformed(evt);
            }
        });
        bg2.add(checkKosaraju, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 580, 180, 90));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Elementos");
        bg2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 490, 180, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Conexos");
        bg2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 530, 210, 40));

        bg.add(bg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 830, 750));

        jPanel8.setBackground(new java.awt.Color(255, 153, 51));

        graphTextarea.setColumns(20);
        graphTextarea.setRows(5);
        jScrollPane1.setViewportView(graphTextarea);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        bg.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, 380));

        jPanel7.setBackground(new java.awt.Color(255, 102, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        bg.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 450, 180));

        cargarButton.setText("Cargar");
        bg.add(cargarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 750));

        menu1.setText("Archivo");
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
        menubar.add(menu2);

        aboutButn.setText("Acerca de...");
        menubar.add(aboutButn);

        setJMenuBar(menubar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openBtnActionPerformed
//      crear objeto de tipo filechooser que contendra el archivo txt
        if (evt.getSource() == this.openBtn) {

            FileManager fileManager = new FileManager();
            File file = fileManager.selectFile();

            usersList = fileManager.parseUsersFromFile(file);
            relationsList = fileManager.parseRelationshipsFromFile(file);

            OurGraph grafo = new OurGraph(usersList, relationsList);
            Kosaraju kosaraju = new Kosaraju(grafo);

            
            grafo.print(); 
            
            //this.displayFromFile(file);
            // Crear instancia de GraphVisualization
            GraphVisualization graphVisual = new GraphVisualization();

            // Visualizar el grafo en el panel
            graphVisual.visualizeGraph(grafo, this.content);
            //graphVisual.visualizeGraph(grafo, this.content);


        }

    }//GEN-LAST:event_openBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if (evt.getSource() == this.exitBtn) {
            System.exit(0);
        }
    }//GEN-LAST:event_exitBtnActionPerformed


    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        if (evt.getSource() == this.saveBtn) {
            FileManager fileManager = new FileManager();
            File file = fileManager.selectFile();
            fileManager.saveFileToTxt(file);
            //this.displayFromFile(file);

//          Hacer display directo 
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void menu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu1ActionPerformed

    private void newProjectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProjectBtnActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == this.newProjectBtn) {
//            FileManager fileManager = new FileManager();
//            File file_prueba = fileManager.selectFile();
//            //fileManager.parseUsersFromFile(file_prueba, ignore);
//            users_list = fileManager.parseUsersFromFile(file_prueba);
//
//            //Probando que la lista global esté funcionando
//            JOptionPane.showMessageDialog(null, "* Lista de usuarios actualizada\n" + users_list.printToString());
        }
    }//GEN-LAST:event_newProjectBtnActionPerformed

    private void vertexButnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vertexButnActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == this.vertexButn) {
            String userName = JOptionPane.showInputDialog(null, "Por favor ingrese el nombre del usuario: ").toLowerCase();
            System.out.println("User retrieved: " + userName);
            this.usersList.addAtTheEnd(userName);
            usersList.printList();

            grafo = new OurGraph(this.usersList.getSize());

            for (int i = 0; i < this.usersList.getSize(); i++) {
                Vertex newVertex = new Vertex(this.usersList.getValueByIndex(i), i);
                newVertex.printVertex();
                grafo.addVertex(newVertex);
            }

            grafo.print();

            // Crear instancia de GraphVisualization
            GraphVisualization graphVisual = new GraphVisualization();

            // Visualizar el grafo en el panel
            graphVisual.visualizeGraph(grafo, this.content);

        }
    }//GEN-LAST:event_vertexButnActionPerformed

    private void checkKosarajuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkKosarajuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkKosarajuActionPerformed

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
    private javax.swing.JMenu aboutButn;
    private javax.swing.JPanel bg;
    private javax.swing.JPanel bg2;
    private javax.swing.JButton cargarButton;
    private javax.swing.JButton checkKosaraju;
    private javax.swing.JPanel content;
    private javax.swing.JMenuItem exitBtn;
    private javax.swing.JTextArea graphTextarea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menu1;
    private javax.swing.JMenu menu2;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JMenuItem newProjectBtn;
    private javax.swing.JMenuItem openBtn;
    private javax.swing.JMenuItem saveBtn;
    private javax.swing.JButton vertexButn;
    // End of variables declaration//GEN-END:variables
}
