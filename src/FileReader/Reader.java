/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author andre
 */
public class Reader {

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

    public void readFromTXT() {
        //lista:

        String line;

        try {
            //File dir = new File("C:\\Archivo");
            File directory = new File(".");
            File file = new File(directory, "usuarios.txt");
            FileReader filereader = new FileReader(file);
            BufferedReader reader = new BufferedReader(filereader);

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException excepcion) {
            excepcion.printStackTrace(System.out);
        }
    }

}
