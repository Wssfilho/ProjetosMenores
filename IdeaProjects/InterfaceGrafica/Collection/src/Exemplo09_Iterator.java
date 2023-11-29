/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exemplos;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class Exemplo09_Iterator {
   public static void main(String args[])
   {
        // Variáveis e seus tipos
        Set<String> eset = new LinkedHashSet<String>();

        // Principais operações        
        eset.add("Fabiana");
	eset.add("Carlos");
	eset.add("Cristina");
	eset.add("Rita");
	eset.add("Flávia");
	eset.add("José");        
       
        // Eventuais erros
       
       
        // Impressão
        System.out.println("--");    
        System.out.println("-- Resultado 1 (Iterator) --");
        System.out.println("--");    
        Iterator<String> iterator = eset.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("--");                
        System.out.println("-- Resultado 2 (For) --");
        System.out.println("--");    
        for (String s : eset)
        {
            System.out.println(s);
        }
    }   

    
}
