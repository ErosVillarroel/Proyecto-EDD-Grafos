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
    
    private SimpleNode<T> pFirst;
    private SimpleNode<T> pLast;
    private int size;

    public SimpleList(){
        this.pFirst = null;
        this.pLast = null;
        this.size = 0;            
    }
    
    public boolean isEmpty(){
        return this.pFirst == null;
    }
    
    //Anadir al inicio
    public void addStart(T data){
        
        SimpleNode<T> node = new SimpleNode<>(data);
        
        if (this.isEmpty()){
            this.pFirst = node;
            this.pLast = node;
        } else {
            node.setpNext(this.pFirst);
            this.pFirst = node;
        }
        
        this.size ++;
        
    }
    
    //Anadir al final
    public void addAtTheEnd(T data){
        
        SimpleNode<T> node = new SimpleNode<>(data);
        if (this.isEmpty()){
            this.pFirst = node;
            this.pLast = node;
        } else {
            this.pLast.setpNext(node);
            this.pLast = node;
        }
        this.size ++;
    }

    //recorrer hasta nulo
    public void printList(){
        SimpleNode<T> pAux = this.pFirst;
        while (pAux != null){
            System.out.println(pAux.getData());
            pAux = pAux.getpNext();
        }
    }
    
    public SimpleNode<T> searchByIndex(int index){
       SimpleNode<T> pAux = this.pFirst;
       int count = 0;
       
       while (pAux != null && count != index){ 
           pAux = pAux.getpNext();
           count ++;
       }
       
       if (pAux != null){
           return pAux;
       } else {
           return null;
       }
    
    }
    
    public SimpleNode<T> searchByValue(T value){
       SimpleNode<T> pAux = this.pFirst;
       
       while (pAux != null && pAux.getData() != value){ 
           pAux = pAux.getpNext();
       }
       
       if (pAux != null){
           return pAux;
       } else {
           return null;
       }
    
    }
    
    public void deleteFirst(){
        if (this.isEmpty()) {
            System.out.println("Lista vacia.");
        } else {
            pFirst = pFirst.getpNext();
        }
    }
    
    public void deleteLast(){
        
        if (this.pFirst == this.pLast){
            this.pFirst = null;
            this.pLast = null;
            this.size = 0;
            
        } else {
         
            SimpleNode<T> pAux = this.pFirst;
       
            while (pAux.getpNext().getpNext() != null){ 
                pAux = pAux.getpNext();
            }
           
            pAux.setpNext(null);
            this.pLast = pAux;
        }
    }
    
    public boolean isValidIndex(int index){
        return this.searchByIndex(index) != null;
    }
    
    public void insertAtIndex(int index, T data) {
     
        if (!isValidIndex(index)){
            System.out.println("No existe el indice");
        } else {
            
            SimpleNode<T> pAux = this.pFirst;
            int counter = 0;
            
            while (counter != index){
                pAux = pAux.getpNext();
                counter ++;
            }   
            
            SimpleNode <T> node = new SimpleNode<>(data);
            node.setpNext(pAux.getpNext());
            
            pAux.setpNext(node);
        }
    }
    
    public void deleteAtIndex(int index) {
     
        if (!isValidIndex(index)){
            System.out.println("No existe el indice");
        } else {
            
            SimpleNode<T> pAux = this.pFirst;
            int counter = 0;
            
            while (counter != index-1){
                pAux = pAux.getpNext();
                counter ++;
            }   
            
            SimpleNode<T> current = pAux.getpNext();
            pAux.setpNext(current.getpNext());
            current.setpNext(null);

        }
    }
    
    public SimpleNode<T> getpFirst() {
        return pFirst;
    }

    public void setpFirst(SimpleNode<T> pFirst) {
        this.pFirst = pFirst;
    }

    public SimpleNode<T> getpLast() {
        return pLast;
    }

    public void setpLast(SimpleNode<T> pLast) {
        this.pLast = pLast;
    }

    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }

}
