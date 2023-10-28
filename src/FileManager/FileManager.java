package FileManager;

import Classes.OurGraph;
import Classes.Vertex;
import DataStructureClasses.SimpleList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
//            filechooser.addChoosableFileFilter(new FileNameExtensionFilter("Documentos de JSON (*.json)", "json"));
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

    /*
        | Prueba preliminar adquirir los usuarios del txt
            retorna los usuarios adquiridos del txt como
            una lista de objetos de la clase vertices (nodos para le grafo)  
     */
//  YA SIRVE
    @SuppressWarnings("empty-statement")
    public SimpleList parseUsersFromFile(File fileSelected) {
        SimpleList usersList = new SimpleList();
        String line;
        try {
            FileReader fileReader = new FileReader(fileSelected.toString());
            BufferedReader bufferReader = new BufferedReader(fileReader);

            while ((line = bufferReader.readLine()) != null) {
                //Revisa si la linea tiene "Relaciones" y rompe el ciclo si lo tiene, WOIP
                if (line.contains("relaciones")) {
                    ;
                    break;
//                    revisa si la linea contiene "usuarios" y la ignora si lo tiene
                } else if (line.contains("usuarios")) {
                    ;
                } else {

                    // despues de buscar: si la linea NO esta en blanco se añade el usuario a la lista                    
                    if (!line.isEmpty()) {
                        //Elimina el @ al inicio de los usuarios
                        String absoluteUserName = line.replace("@", "");

//                      Anade a la lista ordenado por orden de llegada                        
                        usersList.addAtTheEnd(absoluteUserName);
//                        System.out.println("Usuario anadido: " + absoluteUserName + ".");

                    }
                }
            }

            // cerrar archivo
            fileReader.close();
            bufferReader.close();
//            usersList.printList();
            return usersList;

        } catch (FileNotFoundException e) {
            System.out.println("// El archivo no se encontró");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } catch (NullPointerException e) {
            System.out.println("No se selecciono ningun archivo");
        }

        return null;
    }

    public SimpleList parseRelationshipsFromFile(File inFile) {
        SimpleList relationsList = new SimpleList();
        String line;
        try {
            FileReader fileReader = new FileReader(inFile.toString());
            BufferedReader bufferReader = new BufferedReader(fileReader);

            while ((line = bufferReader.readLine()) != null) {
                if (line.contains("usuarios") || !line.contains(",")) {
                    ;
                } else if (line.contains(",")) {
                    String absoluteUserNames = line.replace("@", "");
                    relationsList.addAtTheEnd(absoluteUserNames);
                }
            }

            // cerrar archivo
            fileReader.close();
            bufferReader.close();
            return relationsList;

        } catch (FileNotFoundException e) {
            System.out.println("// El archivo no se encontró");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } catch (NullPointerException e) {
            System.out.println("No se selecciono ningun archivo");
        }

        return null;
    }

    public void saveGraphToFile(OurGraph graph) {

        if (this.savedPathExists()) {
            try {
                String fileName = "graph_data.txt";
                File outFile = new File(this.SAVED_DIRECTORY, fileName);
                FileWriter fileWriter = new FileWriter(outFile);
                BufferedWriter writer = new BufferedWriter(fileWriter);

                // Guardar usuarios
                writer.write("usuarios\n");
                SimpleList<Vertex> vertices = graph.getVertexsList();
                for (int i = 0; i < vertices.getSize(); i++) {
                    writer.write("@" + vertices.getValueByIndex(i).getName() + "\n");
                }

                // Guardar relaciones
                writer.write("relaciones\n");
                int[][] matrix = graph.getMatrix();
                for (int i = 0; i < graph.getNumVertexs(); i++) {
                    for (int j = 0; j < graph.getNumVertexs(); j++) {
                        if (matrix[i][j] == 1) {
                            writer.write("@" + graph.getVertexName(i) + ", @" + graph.getVertexName(j) + "\n");
                        }
                    }
                }

                writer.close();
                JOptionPane.showMessageDialog(null, "El grafo ha sido guardado exitosamente!");
            } catch (IOException e) {
                e.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Ocurrio un error al guardar el grafo.");
            }

        } else {
            try {
                File parentFolder = new File(this.SAVED_DIRECTORY);
                parentFolder.mkdir();
                String fileName = "graph_data.txt";
                File outFile = new File(parentFolder, fileName);
                FileWriter fileWriter = new FileWriter(outFile);
                BufferedWriter writer = new BufferedWriter(fileWriter);

                writer.write("usuarios\n");
                SimpleList<Vertex> vertices = graph.getVertexsList();
                for (int i = 0; i < vertices.getSize(); i++) {
                    writer.write("@" + vertices.getValueByIndex(i).getName() + "\n");
                }

                writer.write("relaciones\n");
                int[][] matrix = graph.getMatrix();
                for (int i = 0; i < graph.getNumVertexs(); i++) {
                    for (int j = 0; j < graph.getNumVertexs(); j++) {
                        if (matrix[i][j] == 1) {
                            writer.write("@" + graph.getVertexName(i) + ", @" + graph.getVertexName(j) + "\n");
                        }
                    }
                }

                writer.close();
                JOptionPane.showMessageDialog(null, "El grafo ha sido guardado exitosamente!\n\nSe ha creado un nuevo directorio en:\n" + outFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, "Ocurrio un error al guardar el grafo.");
            }

        }

    }

}
