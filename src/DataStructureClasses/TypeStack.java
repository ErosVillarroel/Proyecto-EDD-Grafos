/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructureClasses;

/**
 *
 * @author B-St
 * @param <T>
 * 
 * To do:
 * 
 *  -ask about making the Stack out of an array?
 * 
 */
public class TypeStack<T> {
    
    private Node<T> pTop;
    private int size;

    public TypeStack(){
        this.pTop = null;
        this.size = 0;            
    }
    
    public boolean isEmpty(){
        return this.size == 0;
    }
    
    //Anadir al stack
    public void add(T data){
        
        Node<T> node = new Node<T>(data);
        
        if (this.isEmpty()){
            this.pTop = node;
        } else {
            node.setpNext(this.pTop);
            this.pTop = node;
        }
        
        this.size ++;
        
    }

    //Printea el elemento al tope
    public void printTop(){
      if (this.isEmpty()){
          System.out.println("El stack esta vacio.");
      } else {
          System.out.println("Elemento al tope: " + this.pTop.getData());
      }
    }
}