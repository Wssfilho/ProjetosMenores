/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exemplos;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Exemplo01_Lista {
    
   public static void main(String args[])
   {
       List<String> lista = new ArrayList();
       
       lista.add("Adriana");
       lista.add("Fábio");
       lista.add("Cristiane");
       lista.add(1, "Marcelo");
       lista.add(1, "Zenildo");
       lista.remove(2);
       System.out.println("-- Resultado --");       
       for (String s : lista)
       {
           System.out.println(s);
       }    
   }   
}
