
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
public class Exemplo05_Set {

   public static void main(String args[])
   {
        // Variáveis e seus tipos
        Set<String> eset = new HashSet<String>();
        // Principais operações        
       eset.add("Flávia");
       eset.add("Cristina");
       eset.add("Fabiana");
       eset.add("Carlos");
       eset.add("Rita");
       eset.add("José");
       // Verificando as duplicatas
       eset.add("Rita");
       eset.add("Carlos");

        System.out.println("--");                
        System.out.println("-- Resultado 1 --");
        System.out.println("--");            
        for (String s : eset)
        {
            System.out.print(s + "  ");            
        }   
        
/*
        System.out.println("--");    
        System.out.println("-- Resultado 2 (Iterator) --");
        System.out.println("--");    
        
        Iterator<String> iterator = eset.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }*/

    }
}  