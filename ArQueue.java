// Queue
public class ArQueue implements Queue
{
      private Object[] v;
      private int vSize;

      public ArQueue()
      {
         makeEmpty();
      }
      //inserisce l'elemento all'ultimo posto della coda
      public void enqueue(Object obj)
      {
         if (vSize == v.length)
         {
            Object[] newV = new Object[2 * v.length];

            for (int i = 0; i < v.length; i++)
            {
               v[i] = newV[i];
               v = newV;
            }

         }
         
         v[vSize] = obj;
         vSize++;

      }
      //ispeziona l'elemento al primo posto della coda
      public Object front() throws EmptyQueueExcetion
      {
         if (isEmpty())
         {
            throw new EmptyQueueExcetion();
         }
         return v[0];
      }
      //rimuove l'elemento al primo posto della coda
      public Object dequeue() throws EmptyQueueExcetion
      {
         if (isEmpty())
         {
            throw new EmptyQueueExcetion();
         }

         Object obj = front();
         v[0] = null;

         for(int i = 1; i < v.length; i++)
         {
            v[i - 1] = v[i];
         }
         vSize --;
         return obj;
      }
      // controlla se la coda è vuota
      public boolean isEmpty()
      {
         return (vSize == 0);
      }
      // crea un contenitore vuoto
      public void makeEmpty()
      {
         Object[] v = new Object[1];
         vSize = 0;
      }
      // restituisce il numero di elementi
      public int size()
      {
         return vSize;
      }

}
