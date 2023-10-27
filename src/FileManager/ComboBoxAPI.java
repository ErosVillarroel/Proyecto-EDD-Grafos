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
    private boolean isConfirmed;

    public ComboBoxAPI(String userName, int relationIndex, boolean isConfirmed) {
        this.userName = userName;
        this.relationIndex = relationIndex;
        this.isConfirmed = isConfirmed;
    }

    public String getUserName() {
        return userName;
    }

    public int getRelationIndex() {
        return relationIndex;
    }

    public boolean getIsConfirmed() {
        return isConfirmed;
    }

}
