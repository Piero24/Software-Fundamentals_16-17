// Queue X2
public class MyDeque implements Deque
   {
      private Object [] v;
      private int vSize;

      public MyDeque()
      {
         makeEmpty();
      }
      // inserisce l'elemento all'inizio della doppia coda
      // @param elemento da inserire
      public void addFirst(Object element)
      {
         if (vSize == v.length)
         {
            Object [] newV = new Object[ 2 * v.length];

            for (int i = 0; i < v.length; i++)
            {
               v[i] = newV[i];
               v = newV;
            }
         }
         for (int i = vSize - 1; i > 0; i--)
         {
            v[i] = v[i + 1];
         }
         v[0] = element;
         vSize++;
      }
      // inserisce l'elemento alla fine della doppia coda
      // @param elemento da inserire
      public void addLast(Object element)
      {
         if (vSize == v.length)
         {
            Object [] newV = new Object[ 2 * v.length];

            for (int i = 0; i < v.length; i++)
            {
               v[i] = newV[i];
               v = newV;
            }
         }
         v[vSize] = element;
         vSize++;
      }
      // restituisce l'elemento all'inizio della doppia coda
      // @return il primo elemento della doppia coda
      // @throws EmptyDequeException se la coda è vuota
      public Object getFirst() throws EmptyDequeException
      {
         if (vSize == 0)
         {
            throw new EmptyDequeException();
         }

         return v[0];
      }
      // restituisce l'elemento alla fine della doppia coda
      // @return l'ultimo elemento della doppia coda
      // @throws EmptyDequeException se la coda è vuota
      public Object getLast() throws EmptyDequeException
      {
         if (vSize == 0)
         {
            throw new EmptyDequeException();
         }
         return v[vSize - 1];
      }
      // rimuove l'elemento all'inizio della doppia coda
      // @return il primo elemento della doppia coda
      // @throws EmptyDequeException se la coda è vuota
      public Object removeFirst() throws EmptyDequeException
      {
         if (vSize == 0)
         {
            throw new EmptyDequeException();
         }
         Object obj = v[0];
         v[0] = null;

         for ( int i = 1; i < v.length; i++)
         {
            v[i - 1] = v[i];
         }
         vSize--;
         return obj;
      }
      // rimuove l'elemento alla fine della doppia coda
      // @return l'ulimo elemento della doppia coda
      // @throws EmptyDequeException se la coda è vuota
      public Object removeLast() throws EmptyDequeException
      {
         if (vSize == 0)
         {
            throw new EmptyDequeException();
         }
         Object obj = v[vSize - 1];
         v[vSize - 1] = null;
         vSize--;
         return obj;
      }
      //controlla se la doppia coda è vuota
      public boolean isEmpty()
      {
         return (vSize == 0);
      }
      //crea una doppia coda vuota
      public void makeEmpty()
      {
         Object [] v = new Object[1];
         int vSize = 0;
      }
      //restituisce i valori all'interno della doppia coda
      public int size()
      {
         return vSize;
      }
}
