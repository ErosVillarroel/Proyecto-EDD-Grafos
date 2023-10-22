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
 * -ask about making the Stack out of an array?
 *
 */
public class TypeStack<T> {

    private SimpleNode<T> pTop;
    private int size;

    public TypeStack() {
        this.pTop = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    //Anadir al stack
    public void push(T data) {

        SimpleNode<T> node = new SimpleNode<T>(data);

        if (this.isEmpty()) {
            this.pTop = node;
        } else {
            node.setpNext(this.pTop);
            this.pTop = node;
        }

        this.size++;

    }

    public SimpleNode<T> pop() {
        SimpleNode top = this.pTop;

        if (!this.isEmpty()) {
            this.pTop = this.pTop.getpNext();
            this.size--;
        }

        return top;
    }

    //Printea el elemento al tope
    public void print() {
        SimpleNode<T> pAux = this.pTop;
        System.out.println("Lista==============================");
        while (pAux != null) {
            System.out.println(pAux.getData());
            pAux = pAux.getpNext();
        }
        System.out.println("===================================");
    }

    public SimpleNode<T> getpTop() {
        return pTop;
    }

    public void setpTop(SimpleNode<T> pTop) {
        this.pTop = pTop;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
