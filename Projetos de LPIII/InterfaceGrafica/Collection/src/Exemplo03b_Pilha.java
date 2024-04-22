/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exemplos;

import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author Admin
 */
public class Exemplo03b_Pilha {
    
   public static void main(String args[])
   {
       Stack<String> aux = new Stack();
       
       aux.push("A");   aux.push("B");
       aux.push("C");   aux.push("D");
       aux.pop();       aux.push("E");
       aux.pop();       aux.push("F");       
       
       System.out.println("-- Resultado --");
       for (String s : aux)
       {
           System.out.println(s);
       }
       
       
       System.out.println("-- Resultado com iterator --");       
       Iterator<String> it = aux.iterator();
       while(it.hasNext())
       {
           System.out.print(it.next());
       }
           
       
       
   }   
}
