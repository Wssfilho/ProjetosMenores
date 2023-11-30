/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.*;

/**
 *
 * @author Admin
 */
public class Exemplo11_Hashmap {
    
   public static void main(String args[])
   {    // Variáveis e seus tipos
        Map<Integer, String> emap = new HashMap<>(); //podemos usar tree para ordenar
        
        int ID;
        String Nome;
        
        ID = 10;
        Nome = "João";
        
        if (emap.containsKey(ID))
        { System.out.print("Erro: CPF já cadastrado.");        }
        else
        { emap.put(ID, Nome);  }
            
        // Apenas para outros exemplos
        ID = 2; Nome = "Wilson"; emap.put(ID, Nome);
        ID = 21;  Nome = "Maria";  emap.put(ID, Nome);
        ID = 30;  Nome = "Samuel"; emap.put(ID, Nome);    
        
        Set<Integer> keys = emap.keySet();
        Iterator<Integer> it = keys.iterator();
        int key; String valor;
        while (it.hasNext())
        {
            key = it.next();
            valor = emap.get(key);
            System.out.println(" Key: " + key + " - Valor: " + valor);
        }
    }     
}
