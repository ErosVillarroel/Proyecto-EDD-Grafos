
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileManager;

import DataStructureClasses.SimpleList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author andre
 */
public class FileManager {

    private final String SAVED_DIRECTORY = ".saved";

    public FileManager() {
//        System.out.println("Me hice filemanager :)");
    }

    public boolean savedPathExists() {
        Path path = Paths.get(this.SAVED_DIRECTORY);
        return Files.exists(path);
    }

    public int fileCounter() {
        File directory = new File(this.SAVED_DIRECTORY);
        int fileCount = directory.list().length;
        return fileCount;
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

    public void saveFileToTxt(File inFile) {

        if (this.savedPathExists()) {

            try {
                String newFileName = "saved_" + this.fileCounter() + ".txt";
                File outFile = new File(this.SAVED_DIRECTORY, newFileName);
                FileWriter fileWriter = new FileWriter(outFile);
                String line;
                try {
                    FileReader filereader = new FileReader(inFile);
                    BufferedReader reader = new BufferedReader(filereader);

                    while ((line = reader.readLine()) != null) {
                        fileWriter.write(line);
                        fileWriter.write("\n");
                        line = reader.readLine();
                    }

                    reader.close();
                    JOptionPane.showMessageDialog(null, "El archivo ha sido cargado exitosamente!");

                } catch (IOException e) {
                    e.printStackTrace(System.out);
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún archivo");
                }

                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Algo salio mal :(");
            }
        } else {

            try {
                File parentFolder = new File(this.SAVED_DIRECTORY);
                parentFolder.mkdir();
                File outFile = new File(parentFolder, "saved_0.txt");
                FileWriter fileWriter = new FileWriter(outFile);
                String line;

                try {
                    FileReader filereader = new FileReader(inFile);
                    BufferedReader reader = new BufferedReader(filereader);

                    while ((line = reader.readLine()) != null) {
                        fileWriter.write(line);
                        fileWriter.write("\n");
                        line = reader.readLine();
                    }

                    reader.close();
                    JOptionPane.showMessageDialog(null, "El archivo ha sido cargado exitosamente!");

                } catch (IOException e) {
                    e.printStackTrace(System.out);
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún archivo");
                }

                fileWriter.close();
            } catch (IOException excepcion) {
                excepcion.printStackTrace(System.out);
            }

        }
    }

    public void saveStringToTxt(String string) {

        try {
            File outFile = new File("Prueba_String.txt");
            FileWriter fileWriter = new FileWriter(outFile);
            
            fileWriter.write(string);
            fileWriter.close();
//            System.out.println(":)");
        } catch (Exception e) {
            System.out.println(":(");
        }

    }

    //WOIP importacion del lector de archivos
    public File selectFile() {
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
//        System.out.println("Lo logre :D");

            File file = filechooser.getSelectedFile();

            return file;

        } catch (Exception e) {

            System.out.println("Algo salio mal:(");
            return null;

        }
    }

    public SimpleList parseUsersFromFile(File file){
        return new Simplelist();
    }
    
}
