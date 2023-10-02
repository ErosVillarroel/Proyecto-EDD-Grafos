/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructureClasses;

/**
 *
 * @author B-St
 * @param <T>
 */
public class BookStack<T>{
    
    private static final int STACK_SIZE = 49;
    private int top;
    
    /*
    Solucion encontrada en internet para hacer un array con datos dinamicos: Utilizar la clase object para guardar 
    referencias a los lugares de memoria donde estan guardados los datos de lo que se quiera guardar, por tanto, se pudiera hacer un array de 
    cualquier tipo de dato.
    
    Solucion pensada: 
    
        a.-Hacer el array directamente con las referencias (Jaja no se como)
        b.-hacer el array con el tipo de dato Vertice/User/El que se vaya a usar. 
    
    Posibles problemas:
    
        Los array son de tamanos determinados y por tanto, es tecnicamente imposible hacer un array con el espacio necesario para una cantidad indefinida
        de usuarios, por tanto, la creacion de la otra clase:  TypeStack.
    */
    private Object[] arrStack;
    
    public BookStack(T data){
        
        this.top = -1;
        this.arrStack = new Object[STACK_SIZE];
        
    }
    
    /*
    Hacer Metodos
    */

}
