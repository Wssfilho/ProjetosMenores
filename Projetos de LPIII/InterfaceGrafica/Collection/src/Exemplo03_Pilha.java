/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exemplos;

import java.util.Stack;

/**
 *
 * @author Admin
 */
public class Exemplo03_Pilha {
    
   public static void main(String args[])
   {
       Stack<String> pilha = new Stack();
       
       pilha.push("Este é um ");       pilha.push("teste com ");
       pilha.push("edição de ");       pilha.push("texto como ");
       pilha.push("exemplo de ");      pilha.push("erro 1 "); 
       pilha.push("erro 2.");

       System.out.println("-- Resultado 1 --");
       System.out.println(pilha.pop());
       System.out.println(pilha.pop());
      
       pilha.push("fazer e "); 
       pilha.push("desfazer.");
       
       System.out.println("-- Resultado 2 --");
       for (String s : pilha)
       {
           System.out.println(s);
       }
   }   
}
