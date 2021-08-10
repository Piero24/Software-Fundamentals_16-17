public class D implements Dictionary  //--Dizionario
{
   // parte privata
   private Entry[] v;
   private int vSize;
   private static final int INITIAL_CAPACITY = 1;

   // classe interna
   class Entry
   {
      private Object key;
      private Object value;

      public Entry(Object k, Object x)
      {
         key = k;
         value = x;
      }

      public Object getKey() { return key; }
      public Object getValue() { return value; }
   }


   /**
      Costruttore - inizializza un dizionario vuoto
   */
   public D()
   {
      makeEmpty();
   }

  /**
     @return true se il contenitore e' vuoto, false altrimenti
  */
  public boolean isEmpty()
  {
     return vSize == 0;
  }

  /**
     inizializza un contenitore vuoto
  */
  public void makeEmpty()
  {
     v = new Entry[INITIAL_CAPACITY];
     vSize = 0;
  }

  /**
     @return numero di elementi presenti nel contenitore
  */
   public int size()
   {
      return vSize;
   }

   /**
      inserisce l'associazione key/value nel dizionario.
      @param key la chiave specificata
      @param value il valore specificato
      @throws IllegalArgumentException se key o value valgono null
   */
   // O(1) nel caso medio
   public void insert(Object key, Object value)
   {
      // gestione precondizioni
      if (key == null || value == null)
         throw new IllegalArgumentException();

      // ridimensionamento dinamico
      if (vSize >= v.length)
      {
         Entry[] newV = new Entry[2 * v.length];
         System.arraycopy(v, 0, newV, 0, vSize);
         v = newV;
      }

      // inserimento e incremento contatore
      v[vSize++] = new Entry(key, value);
   }

   /**
      rimuove dal dizionario una delle associazione di chiave specificata
      @param key la chiave specificata
      @return il valore dell'associazione rimossa se la chiave specificata e'
              presente, null altrimenti
   */
   // O(n) nel caso medio
   public Object remove(Object key)
   {
      // ricerca
      int n = search(key);

      // chiave non presente
      if (n < 0)
         return null;

      // memorizzazione temporanea del valore
      Object x = v[n].getValue();

      // rimozione dell'associazione
      v[n] = v[vSize - 1];
      v[vSize - 1] = null; // garbage collector

      // decremento del contatore
      vSize--;

      return x;
   }

   /**
      restituisce il valore di una delle associazione di chiave specificata
      presenti nel dizionario
      @param key la chiave specificata
      @return il valore di una delle associazione di chiave specificata se la
              chiave e' presente, null altrimenti
   */
   // O(n) nel caso medio
   public Object find(Object key)
   {
      int n = search(key);

      if (n < 0)
         return null;

      return v[n].getValue();
   }

   /**
      fornisce i valori associati alla chiave specificata
      @param key la chiave specificata
      @return un array contenente tutti i valori associati alla chiave
              specificata ordinati in senso lessicografico crescente se la
              chiave e' presente, un array di dimensione nulla altrimenti
   */
   // O(m*n) n numero di associazioni, m numero si associazioni di chiave specificata
   public Object[] findAll(Object key)
   {
      // rimozione
      Object[] t = removeAll(key);

      // re-inserimento
      for (int i = 0; i < t.length; i++)
         insert(key, t[i]);

      return t;
   }

   /**
      rimuove le associazioni key/value di chiave specificata dal dizionario
      @param key la chiave specificata
      @return un array contenente tutti i valori associati alla chiave
              specificata ordinati in senso lessicografico crescente se la
              chiave e' presente, un array di dimensione nulla altrimenti
   */
   // O(m*n) n numero di associazioni, m numero si associazioni di chiave specificata
   public Object[] removeAll(Object key)
   {
      Object[] t = new Object[vSize]; // al massimo ci sono vSize associazioni

      // rimozione associazioni e collezione dei valori
      int j = 0; // contatore
      boolean done = false; // gestione ciclo e mezzo
      while (!done)
      {
         Object x = remove(key);
         if (x != null)
            t[j++] = x; // memorizzazione valore
         else
            done = true; // fine ciclo
      }

      // ridimensionamento dell'array
      Object[] r = new Object[j];
      System.arraycopy(t, 0, r, 0, j);

      return r;
   }

   /**
      @return array contenente tutte le chiavi del dizionario.
   */
   // O(nlogn)
   public Object[] keys()
   {
      Object[] t = new Object[vSize]; // al massimo ci sono vSize associazioni

      // collezione delle chiavi
      for (int i = 0; i < vSize; i++)
         t[i] = v[i].getKey();


      // eliminazione doppioni
      int j = 1;
      for (int i = 1; i < t.length; i++)
      {
        int k;
        for (k = 0; k < j; k++)
         if (t[i].equals(t[k]))
            break;

        if (k == j)
           t[j++] = t[i];
      }

      // ridimensionamento dell'array
      Object[] r = new Object[j];
      System.arraycopy(t, 0, r, 0, j);

      return r;
   }

   // ricerca lineare O(n) nel caso medio
   private int search(Object key)
   {
      for (int i = 0; i < vSize; i++)
         if (v[i].getKey().equals(key))
            return i;

      return -1;
   }
}
