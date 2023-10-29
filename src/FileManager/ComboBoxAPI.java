/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileManager;

/**
 *
 * @author B-St
 */
public class ComboBoxAPI {

    private String userName;
    private int relationIndex;

    public ComboBoxAPI(String userName, int relationIndex) {
        this.userName = userName;
        this.relationIndex = relationIndex;
    }

    public void printBoxApi() {

        System.out.println("Nombre registrado: " + this.userName);
        System.out.println("Indice de relacion: " + this.relationIndex);

    }

    public String getUserName() {
        return userName;
    }

    public int getRelationIndex() {
        return relationIndex;
    }

}
