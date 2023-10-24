/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructureClasses;

import Classes.Vertex;

/**
 *
 * @author B-St
 * @param <T>
 */
public class SimpleList<T> {

    private SimpleNode<T> pFirst;
    private SimpleNode<T> pLast;
    private int size;

    public SimpleList() {
        this.pFirst = null;
        this.pLast = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.pFirst == null;
    }

    //Anadir al inicio
    public void addStart(T data) {

        SimpleNode<T> node = new SimpleNode<>(data);

        if (this.isEmpty()) {
            this.pFirst = node;
            this.pLast = node;
        } else {
            node.setpNext(this.pFirst);
            this.pFirst = node;
        }

        this.size++;

    }

    //Anadir al final
    public void addAtTheEnd(T data) {

        SimpleNode<T> node = new SimpleNode<>(data);
        if (this.isEmpty()) {
            this.pFirst = node;
            this.pLast = node;
        } else {
            this.pLast.setpNext(node);
            this.pLast = node;
        }
        this.size++;
    }

    //recorrer hasta nulo
    public void printList() {
        SimpleNode<T> pAux = this.pFirst;
        System.out.println("Lista==============================");
        while (pAux != null) {
            System.out.println(pAux.getData());
            pAux = pAux.getpNext();
        }
        System.out.println("===================================");
    }

    //Print para lista especifica de vertices
    public void printVertexList() {
        try {

            SimpleNode<Vertex> pAux = (SimpleNode<Vertex>) this.pFirst;
            System.out.println("Lista De vertices =============");
            while (pAux != null) {
                System.out.println(pAux.getData().vertexToString());
                pAux = pAux.getpNext();
            }
            System.out.println("===================================");

        } catch (Exception e) {
            System.out.println("La lista no es de vertices.");
        }
    }

    public String printToString() {
        if (this.isEmpty()) {
            return "//";
        }
        SimpleNode<T> pAux = this.pFirst;
        //int contador = 0;
        String chain = "";
        while (pAux != null) {
            //System.out.print(contador + ".[ " + pAux.getData() + " ]" + " ->  ");
            chain += pAux.getData() + "->";
            pAux = pAux.getpNext();
            //contador++;
        }
        return chain + "//";
    }

    public T getValueByIndex(int index) {
        SimpleNode<T> pAux = this.pFirst;
        int count = 0;

        while (pAux != null && count != index) {
            pAux = pAux.getpNext();
            count++;
        }

        if (pAux != null) {
            return pAux.getData();
        } else {
            return null;
        }

    }

    public SimpleNode<T> searchByValue(T value) {
        SimpleNode<T> pAux = this.pFirst;

        while (pAux != null && pAux.getData() != value) {
            pAux = pAux.getpNext();
        }

        if (pAux != null) {
            return pAux;
        } else {
            return null;
        }

    }

    /*
        - Metodo para retornar la posicion de un elemento en la lista
     */
    public int indexOf(T valorBuscado) {
        SimpleNode<T> actual = this.pFirst;
        int index = 0;

        while (actual != null) {
            if (actual.getData().equals(valorBuscado)) {
                return index;
            }

            actual = actual.getpNext();
            index++;
        }

        return -1; // Si no se encuentra el valor, devolvemos -1
    }

    public void deleteFirst() {
        if (this.isEmpty()) {
            System.out.println("Lista vacia.");
        } else {
            pFirst = pFirst.getpNext();
        }
    }

    public void deleteLast() {

        if (this.pFirst == this.pLast) {
            this.pFirst = null;
            this.pLast = null;
            this.size = 0;

        } else {

            SimpleNode<T> pAux = this.pFirst;

            while (pAux.getpNext().getpNext() != null) {
                pAux = pAux.getpNext();
            }

            pAux.setpNext(null);
            this.pLast = pAux;
        }
    }

    public boolean isValidIndex(int index) {
        return this.getValueByIndex(index) != null;
    }

    public void insertAtIndex(int index, T data) {

        if (!isValidIndex(index)) {
            System.out.println("No existe el indice");
        } else {

            SimpleNode<T> pAux = this.pFirst;
            int counter = 0;

            while (counter != index) {
                pAux = pAux.getpNext();
                counter++;
            }

            SimpleNode<T> node = new SimpleNode<>(data);
            node.setpNext(pAux.getpNext());

            pAux.setpNext(node);
        }
    }

    public void deleteAtIndex(int index) {
        
        if (!isValidIndex(index)) {
            System.out.println("No existe el indice");
        } else {
            if (index == 0) {
                this.pFirst = this.pFirst.getpNext();
            } else {
                SimpleNode<T> pAux = this.pFirst;
                int counter = 0;

                while (counter < index - 1) {
                    pAux = pAux.getpNext();
                    counter++;
                }

                SimpleNode<T> current = pAux.getpNext();
                pAux.setpNext(current.getpNext());
                current.setpNext(null);
            }
        }
    }

    public void fillWith(int size, T data) {

        for (int i = 0; this.size < size; i++) {
            this.addAtTheEnd(data);
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
