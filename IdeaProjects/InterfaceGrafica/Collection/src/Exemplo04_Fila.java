/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exemplos;

import java.util.PriorityQueue;
import java.util.Queue;

public class Exemplo04_Fila {
    
   public static void main(String args[])
   {
       Queue<String> fila = new PriorityQueue();
       
       fila.add("A");        fila.offer("B");
       fila.add("C");        fila.offer("D");

       System.out.println("-- Resultado 1 --");
       for (String s : fila)
       {
           System.out.println(s);
       }

       System.out.println("-- Itens retirados --");
       System.out.println(fila.poll()); 
       System.out.println(fila.poll());
       
       System.out.println("-- Resultado 2 --");
       for (String s : fila)
       {
           System.out.println(s);
       }      
   }   
}
