// Stack
public class ArStack implements Stack
{
   private Object[] v;
   private int vSize;

   public ArStack()
   {
      makeEmpty();
   }

   // rimuove l'elemento in cima alla pila
   public Object pop() throws EmptyStackException
   {
      if (isEmpty())
      {
         throw new EmptyStackException();
      }
      Object obj = top();
      v[vSize - 1] = null;
      vSize--;
      return obj;
   }

   // inserisce un elemento in cima alla pila
   public void push(Object obj)
   {
      if ( vSize == v.length)
      {
         Object[] newV = new Object[v.length * 2];

         for(int i = 0; i < v.length; i++)
         {
            v[i] = newV[i];
            v = newV;
         }
      }
      v[vSize] = obj;
      vSize++;
   }

   // ispeziona l'elemeno in cima alla pila
   public Object top() throws EmptyStackException
   {
      if (isEmpty())
      {
         throw new EmptyStackException();
      }
      return (vSize - 1);
   }

   //controlla se il contenitore è vuoto
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

   //restituisce il numero di elementi
   public int size()
   {
      return vSize;
   }
}
