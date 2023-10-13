
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author andre
 */
public class FileManager {

    public FileManager() {
    }

    public void createTXT() {
        File directory = new File(".");
        File file = new File(directory, "archivos.txt");

        try {
            if (file.createNewFile()) {
                JOptionPane.showMessageDialog(null, "Archivo creado con exito!");
            }
        } catch (IOException excepcion) {
            excepcion.printStackTrace(System.out);
        }
        // cerrar archivo?
    }

    public void writeToTXT() {
        try {
            File directory = new File(".");
            File file = new File(directory, "archivos.txt");
            FileWriter write = new FileWriter(file);

            write.write("Saludos bra");
            write.write("\nHows going man");
            write.write("\nWould you ever take the opportunity?");

            write.close();
        } catch (IOException excepcion) {
            excepcion.printStackTrace(System.out);
        }
    }
    
    
    //WOIP importacion del lector de archivos
    public void readFile(){
        try {
        JFileChooser filechooser = new JFileChooser();
        // direccion y permisos de acceso?
        filechooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        filechooser.setAcceptAllFileFilterUsed(false);
        // Añadir filtros
        filechooser.addChoosableFileFilter(new FileNameExtensionFilter("Documentos de JSON (*.json)", "json"));
        filechooser.addChoosableFileFilter(new FileNameExtensionFilter("Documentos de texto (*.txt)", "txt"));
        // Mostrar ventana de seleccion de archivos
        filechooser.showOpenDialog(filechooser);
        System.out.println("Lo logre :D");
        } catch (Exception e){
            
            System.out.println("No lo logre D:");
            
        }
    }
        
}