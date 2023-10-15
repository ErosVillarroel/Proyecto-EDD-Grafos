/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import FileManager.FileManager;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author bcsoporte
 */
public class MainInterface extends javax.swing.JFrame {

    Page1 page1 = new Page1();
    Page2 page2 = new Page2();

    /**
     * Creates new form main
     */
    public MainInterface() {
        initComponents();
        setTitle("Pantalla Principal");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        page1.setSize(410, 320);
        page1.setLocation(0, 0);
        content.removeAll();
        content.add(page1, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
        btnPrev.setEnabled(false);
        
        this.setVisible(true);
    }

    private void ShowPanel(JPanel panel) {
        panel.setSize(410, 320);
        panel.setLocation(0, 0);
        
        content.removeAll();
        content.add(panel, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    private void disableButtons() {
    }

    private void displayFromFile() {
        FileManager fileManager = new FileManager();
        File file = fileManager.selectFile();
        String line;

        try {
            FileReader filereader = new FileReader(file);
            BufferedReader reader = new BufferedReader(filereader);

            while ((line = reader.readLine()) != null) { 
                // ejemplo: mostrar informacion en un text area
                this.txtArea1.append(line);
                this.txtArea1.append("\n");
                line = reader.readLine();
            }

            reader.close();
            JOptionPane.showMessageDialog(null, "El archivo ha sido cargado exitosamente!");

        } catch (IOException e) {
            e.printStackTrace(System.out);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún archivo");
        }
    }
    
    private void saveFile(){
        // metodo para guardar el archivo, WOIP, cambiar el input de un filechooser a lo q este escrito en el textarea
        
        FileManager fileManager = new FileManager();
        File file = fileManager.selectFile();

        fileManager.saveToTxt(file);       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        content = new javax.swing.JPanel();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea1 = new javax.swing.JTextArea();
        menubar = new javax.swing.JMenuBar();
        menu1 = new javax.swing.JMenu();
        item1 = new javax.swing.JMenuItem();
        modifyButton = new javax.swing.JMenuItem();
        item2 = new javax.swing.JMenuItem();
        itemExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        bg.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 400, 310));

        btnPrev.setText("Previous");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        bg.add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        bg.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        txtArea1.setColumns(20);
        txtArea1.setRows(5);
        jScrollPane1.setViewportView(txtArea1);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 180, 310));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 420));

        menu1.setText("Archivo");
        menu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu1ActionPerformed(evt);
            }
        });

        item1.setText("Abrir");
        item1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item1ActionPerformed(evt);
            }
        });
        menu1.add(item1);

        modifyButton.setText("Modify");
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });
        menu1.add(modifyButton);

        item2.setText("Guardar");
        item2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item2ActionPerformed(evt);
            }
        });
        menu1.add(item2);

        itemExit.setText("Salir");
        itemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExitActionPerformed(evt);
            }
        });
        menu1.add(itemExit);

        menubar.add(menu1);

        setJMenuBar(menubar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void item1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item1ActionPerformed
        // crear objeto de tipo filechooser que contendra el archivo txt
        if (evt.getSource() == this.item1) {
            this.displayFromFile();
        }

    }//GEN-LAST:event_item1ActionPerformed

    private void itemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExitActionPerformed
        if (evt.getSource() == this.itemExit) {
            System.exit(0);
        }
    }//GEN-LAST:event_itemExitActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // misma accion solo que con el anterior, posiblemente pasar a un metodo
        if (evt.getSource() == this.btnPrev) {
            this.ShowPanel(this.page1);
            this.btnPrev.setEnabled(false);
            this.btnNext.setEnabled(true);
        }

    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // Mostrar informacion del siguiente panel, habilitando y deshabilitando los botones segun sea el caso
        if (evt.getSource() == this.btnNext) {
            this.ShowPanel(this.page2);
            this.btnPrev.setEnabled(true);
            this.btnNext.setEnabled(false);
        }

    }//GEN-LAST:event_btnNextActionPerformed


    private void item2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item2ActionPerformed
        if (evt.getSource() == this.item2) {
            this.saveFile();
        }
    }//GEN-LAST:event_item2ActionPerformed

    private void menu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu1ActionPerformed

    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modifyButtonActionPerformed


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
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JPanel content;
    private javax.swing.JMenuItem item1;
    private javax.swing.JMenuItem item2;
    private javax.swing.JMenuItem itemExit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menu1;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JMenuItem modifyButton;
    private javax.swing.JTextArea txtArea1;
    // End of variables declaration//GEN-END:variables
}
