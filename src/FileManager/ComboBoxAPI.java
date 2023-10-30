/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileManager;

/**
 *
 * @author B-St
 * 
 * Sirve para facilitar el manejo de la informacion entre la interfaz main y la interfaz de nuevo usuario
 */
public class ComboBoxAPI {

    private String userName;
    private int relationIndex;

    //crea el objeto que sirve como conexion 
    public ComboBoxAPI(String userName, int relationIndex) {
        this.userName = userName;
        this.relationIndex = relationIndex;
    }

    //imprime la informacion guardada en el objeto
    public void printBoxApi() {

        System.out.println("Nombre registrado: " + this.userName);
        System.out.println("Indice de relacion: " + this.relationIndex);

    }

    //devuelve una string de username
    public String getUserName() {
        return userName;
    }

    //devuelve el indice del vertice al que se quiere relacionar el nuevo vertice
    public int getRelationIndex() {
        return relationIndex;
    }

}
