/**
  * FONDAMENTI DI INFORMATICA - CANALE
  * PROVA PRATICA DI PROGRAMMAZIONE DELLA SIMULAZIONE
  *
  * classe D - realizza l'ADT dizionario (o multi-mappa) - contenitore di associazioni chiave/valore
  * con chiave non necessariamente univoca
  * la chiave e il valore sono di tipo parametrico
  *
  * realizzazione con array parzialmente riempito ordinato
  *
  * getAll O(log n), removeAll O(n) nei casi medi
  *
  * @see Dictionary
  */
public class D<K extends Comparable, V> implements Dictionary<K, V>
{
   // parte privata
   private static final int INITIAL_CAPACITY = 1;
   private Object[] w;
   private int wSize;

   private class Entry // classe interna
   {
      private K key;
      private V value;

      public Entry(K k, V v)
      {
         setKey(k);
         setValue(v);
      }

      public K getKey()  { return key;}
      public V getValue() { return value;}

      void setKey(K k) { key = k; }
      void setValue (V v) { value = v; }
   }

   /**
      inizializza un dizionario vuoto
   */
   public D()
   {
      w = new Object[INITIAL_CAPACITY];
      wSize = 0;
   }


   /**
      restituisce i valori associati alla chiave specificata k, se nel dizionario esistono
      associazioni di chiave specificata o altrimenti restituisce un array di dimensione nulla
      @param k la chiave specificata
      @return array contenenti i valori associati alla chiave specificata k o un array di dimensione
              nulla se la chiave k non e' presente. La dimensione dell'array e' pari al numero di
              associazioni di chiave specificata
   */
   public Object[] getAll(K k) //O(n)
   {
      // array per memorizzazione temporanea
      Object[] t = new Object[wSize];

      // ricerca
      int n = search(w, 0, wSize - 1, k);

      // nesuna associazione presente
      if (n < 0)
         return new Object[0];

      // ricerca e copia dei valori
      int i = n;
      int j = 0;
      while (i >= 0 && ((Entry)w[i]).getKey().compareTo(k) == 0) // cerca a sinistra
      {
         t[j++] = ((Entry)w[i]).getValue();
         i--;
      }

      i = n + 1;
      while (i < wSize && ((Entry)w[i]).getKey().compareTo(k) == 0) // cerca a destra
      {
         t[j++] = ((Entry)w[i]).getValue();
         i++;
      }

      // ridimensionamento dell'array
      Object[] r = new Object[j];
      System.arraycopy(t, 0, r, 0, r.length);

      return r;
   }

   /**
      @return true se questo dizionario e' vuoto, false altrimenti
   */
   public boolean isEmpty()
   {
      return wSize <= 0;
   }

   /**
      @return un array contenente tutte le chiavi presenti nel dizionario se questo non e' vuoto, o
              un array di dimensione nulla altrimenti. Le chiavi siano disposte nell'array
              ordinate secondo il loro ordine naturale e senza duplicati
   */
   public Comparable[] keys() // O(n)
   {
      // array temporanea
      Comparable[] t = new Comparable[wSize];

      // accesso alle chiavi
      for (int i = 0; i < wSize; i++)
         t[i] = ((Entry)w[i]).getKey();

      return t; // l'array e' gia' ordinato
   }

   /**
      aggiunge l'associazione (k, v) al dizionario
      @param k la chiave specificata
      @param v il valore specificato
      @throws IllegalArgumentException se k o v valgono null
   */
   public void put(K k, V v) //O(n) nel caso peggiore, O(i nel caso medio e migliore)
   {
      // precondizioni
      if (k == null || v == null)
          throw new IllegalArgumentException();

      // ridimensionamento dinamico
      if (wSize >= w.length)
      {
         Object[] newW = new Object[2 * w.length];
         System.arraycopy(w, 0, newW, 0, w.length);

         w = newW;
      }

      // inserimento ordinato
      int j = wSize - 1;
      while(j >= 0 && k.compareTo(((Entry)w[j]).getKey()) < 0)
      {
         w[j + 1] = w[j];
         j--;
      }

      w[j + 1] = new Entry(k, v);

      // aggirnamento del numero delle associazioni
      wSize++;
   }

   /**
      Elimina dal dizionario tutte le associazione di chiave specificata k e ne restituisce i valori
      in un array
      @param k la chiave specificata
      @return un array di dimensione nulla se nel dizionario non sono presenti associazioni di
      chiave specificata k, altrimenti un array contenente i valori associati alla chiave
      specificata
   */
   public Object[] removeAll(K k) // O(m x n) dove m e' il numero di associazioni di chiave k
   {
      // elemento di ritorno
      Object[] r = getAll(k);

      // eliminazione delle associazioni di chiave specificata
      while(remove(k) != null)
         ;

      return r;
   }

   // rimuove una delle associazioni di chiave specificata
   private Object remove(K k) // O(n) nel caso medio e peggiore
   {
      // ricerca
      int n = search(w, 0, wSize - 1, k);

      // associazione non presente
      if (n < 0)
         return null;

      // memorizzazione temporanea
      Object ret = ((Entry)w[n]).getValue();

      // spostamento a sinistra delle associazioni
      for (int i = n; i < wSize - 1; i++)
          w[i] = w[i + 1];

      w[wSize - 1] = null; // eliminazione per garbage collector

      // aggiornamento del numero di associazioni
      wSize--;

      return ret;
   }

   // ricerca binaria
   private int search(Object[] a, int from, int to, Object t)
   {
      if (from > to)
         return -1;

      int mid = (from + to) / 2;
      K c = ((Entry)a[mid]).getKey();
      if (c.compareTo(t) == 0)
         return mid;
      else if (c.compareTo(t) < 0)
         return search(a, mid + 1, to, t)   ;
      else
         return search(a, from, mid - 1, t);
   }

   /**
      @return il numero di elementi contenuti in questo dizionario
   */
   public int size()
   {
      return wSize;
   }

   /**
      @return array contenente i valori della associazioni presenti nel dizionario o un array di
              dimensione nulla se il dizionario e' vuoto
   */
   public Object[] values()
   {
      // array di appoggio
      Object[] r = new Object[wSize];

      // accesso ai valori
      for (int i = 0; i < wSize; i++)
         r[i] = ((Entry)w[i]).getValue();

      return r;
   }
}
