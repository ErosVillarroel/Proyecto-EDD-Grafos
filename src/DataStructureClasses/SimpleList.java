/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructureClasses;

/**
 *
 * @author B-St
 */
public class SimpleList<T> {
    
    private Node<T> pFirst;
    private int size;
    
    public SimpleList(){
        this.pFirst = null;
        this.size = 0;
    }
    
    
    public boolean isEmpty(){
        return this.size == 0;
    }
    
    public void add(T data){
        Node<T> node = new Node<T>(data);
        
        if (this.isEmpty()){
            this.pFirst = node;
        } else {
            node.setpNext(this.pFirst);
            this.pFirst = node;
        }
        this.size++;
    }
    
    public void print(){
        
        Node<T> pAux = this.pFirst;
        
        while (pAux != null){
            System.out.println(pAux.getData());
            pAux = pAux.getpNext();;
        }
        
        
    }
}
