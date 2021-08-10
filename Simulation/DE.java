/**
  * FONDAMENTI DI INFORMATICA - CANALE
  * PROVA PRATICA DI PROGRAMMAZIONE DELLA SIMULAZIONE
  *
  * classe DE - dizionario esteso - contenitore di associazioni chiave/valore con chiave non
  * necessariamente univoca
  * la chiave e il valore sono di tipo parametrico
  *
  */
public class DE<K extends Comparable, V extends Comparable> extends D<K, V> // Dizionario esteso
{
   /**
      inizializza un dizionario esteso vuoto
   */
   public DE()
   {
      super();
   }

   /**
      @param k la chiave specificata
      @return true se la chiave specificata e' presente nel dizionario o false altrimenti.
   */
   public boolean containsKey(K k)
   {
      return getAll(k).length != 0;
   }

   /**
      @param k la chiave specificata
      @return true se il valore specificato e' presente nel dizionario o false altrimenti.
   */
   public boolean containsValue(V v)
   {
      Object[] r = valueSet();

      return search(r, 0, r.length - 1, v) >= 0;
   }

   // ricerca binaria
   private static int search(Object[] a, int from, int to, Object t)
   {
      if (from > to)
         return -1;

      int mid = (from + to) / 2;
      Comparable c = (Comparable)a[mid];
      if (c.compareTo(t) == 0)
         return mid;
      else if (c.compareTo(t) < 0)
         return search(a, mid + 1, to, t)   ;
      else
         return search(a, from, mid - 1, t);
   }

   /**
      inserisce l'associazione (k, v) nel dizionario
   */
   public void put(K k, V v)
   {
      super.put(k, v);
   }

   /**
      @return array contenente i valori della associazioni presenti nel dizionario disposti secondo
              il loro ordine naturale e senza duplicati.
   */
   public Comparable[] valueSet()
   {
      // accesso ai valori
      Object[] v = values();

      // uso della classe D per ottenere un array ordinato di valori
      D d = new D();
      for (int i = 0; i < v.length; i++)
         d.put((Comparable)v[i], ""); // inserimento di associazioni (v, "")

      Comparable[] t = d.keys(); // array ordinato contenente i valori con eventuali duplicati

      // eliminazione dei duplicati
      int j = 0;
      for (int i = 1; i < t.length; i++)
         if (!t[i].equals(t[j]))
            t[++j] = t[i];

      // ridimensionamento dell'array
      Comparable[] r = new Comparable[j + 1];
      System.arraycopy(t, 0, r, 0, r.length);

      return r;
   }
}
