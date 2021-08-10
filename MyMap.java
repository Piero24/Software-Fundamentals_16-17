// Map
public class MyMap
{

   // variabili di esemplare
   private Entry[] v;
   private int vSize;

   // Costruttori
   public MyMap()
   {
      v = new Entry[1];
      vSize = 0;
   }
      // restituisce il valore associato alla chiave
      // @param la chiave associata al valore da restituire
      // @return il valore associato alla chiave specificata
      // @throws IllegalArgumentException se key vale null
   public Object get(Object key)
   {
      if (key == null)
         throw new IllegalArgumentException();

      int n = search(key);

      if (n < 0)
         return null;

      return v[n].value;
   }
      // inserisce l'associazione chiave valore e ritorna il valore precedentemente associato a quella chiave
      // @param chiave
      // @param valore da associare alla chiave
      // @return il valore precedentemente associato alla chiave specificata
      // @throws IllegalArgumentException se key o value valgono null
   public Object put(Object key, Object value)
   {
      if (key == null || value == null)
         throw new IllegalArgumentException();

      Object x = remove(key);

      if (x == null && vSize >= v.length)
      {
         Entry[] newV = new Entry[2 * v.length];
         System.arraycopy(v, 0, newV, 0, vSize);
         v = newV;
      }

      v[vSize++] = new Entry(key, value);

      return x;
   }
      // rimuove l'associazione con la chiave specificata
      // @param la chiave dell'asociazione da eliminare
      // @return il valore della chiave specificata
      // @throws IllegalArgumentException se key vale null
   public Object remove(Object key)
   {
      if (key == null)
         throw new IllegalArgumentException();

      int n = search(key);

      if (n < 0)
         return null;

      Object x = v[n].value;

      for (int i = n; i < vSize - 1; i++)
         v[i] = v[i + 1];

      v[vSize - 1] = null;
      vSize--;

      return x;
   }

      // restituisce un array contenente le chiavi della mappa
      // @return array contenente tutte le chiavi della mappa
   public Object[] keys()
   {
      Object[] p = new Object[vSize];

      for (int i = 0; i < vSize; i++)
      {
         p[i] = v[i].key;
      }

      return p;
   }

      //controlla se la doppia coda è vuota
      public boolean isEmpty()
      {
         return (vSize == 0);
      }
      //restituisce i valori all'interno della doppia coda
      public int size()
      {
         return vSize;
      }
      // ricerca lineare O(n)
      private int search(Object key)
      {
         for (int i = 0; i < vSize; i++)
            if (key.equals(v[i].key))
               return i;

         return -1;
      }

      private class Entry
      {
         Object key;
         Object value;

         Entry(Object k, Object v)
         {
            key = k;
            value = v;
         }
      }

   }
