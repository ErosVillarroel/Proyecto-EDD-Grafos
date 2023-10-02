/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructureClasses;

/**
 * @author B-St
 * @param <T>
 */
public class Node<T>{
    
    private T data;
    private Node pNext;
    
    public Node(T data){
        
        this.data = data;
        this.pNext = null;
        
    }
    // Sobrecarga de metodos: Dos metodos con el mismo nombre pueden ser distintos si tienen argumentos diferentes
    public Node(T data, Node<T> pNext){
        
        this.data = data;
        this.pNext = pNext;
        
    }  
    
    // Insert code: Getters and Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getpNext() {
        return pNext;
    }

    public void setpNext(Node<T> pNext) {
        this.pNext = pNext;
    }
    
}
