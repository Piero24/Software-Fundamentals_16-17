/**
  * FONDAMENTI DI INFORMATICA - CANALE 
  * PROVA PRATICA DI PROGRAMMAZIONE DELLA SIMULAZIONE
  *
  * classe DEMain - classe di prova della classe DE
  *
  *
  */
public class DEMain // Classe di prova del dizionario esteso
{
   public static void main(String[] args) throws java.io.IOException
   {
      DE<String,String> d = new DE<String, String>();
      java.util.Scanner in = new java.util.Scanner(new java.io.FileReader(args[0]));
      while (in.hasNextLine())
      {
         java.util.Scanner tok = new java.util.Scanner(in.nextLine());
         d.put(tok.next(), tok.next()); // prova del metodo put
      }

      System.out.println("SIZE = " + d.size()); // prova del metodo size

      System.out.print("ENTRIES: ");
      Object[] keys = d.keys();  // prova del metodo keys
      for (int i = 0; i < keys.length; i++)
      {
         Object[] r = d.getAll((String) keys[i]); // prova del metodo getAll
         for ( int j = 0; j < r.length; j++)
            System.out.print(keys[i] + "/" + r[j] + " ");
      }
      System.out.println();

      System.out.print("VALUES: ");
      Object[] values = d.values(); // prova del metodo values
      for (Object e: values)
         System.out.print(e + " ");
      System.out.println();

      System.out.print("VALUESET: ");
      values = d.valueSet();  // prova del metodo valueSet
      for (Object e: values)
         System.out.print(e + " ");
      System.out.println();

      System.out.print("REMOVEALL: ");
      int i = 0;
      while (!d.isEmpty()) // prova del metodo isEmpty
      {
         Object[] r = d.removeAll((String)keys[i]); // prova del metodo removeAll
         for ( int j = 0; j < r.length; j++)
            System.out.print(keys[i] + "/" + r[j] + " ");

         i++;
      }
      System.out.println();

      System.out.println("SIZE = " + d.size());    // prova del metodo size
   }
}
