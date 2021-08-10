// List
import java.util.NoSuchElementException;

public class ListaSuArray implements List
{
  //variabili di esemplare
  Object[] v;
  int vSize;

  //costruttore: inizializza una lista vuota
  public ListaSuArray()
  {
    v = new Object[1];
    vSize = 0;
  }

  /**
     @return true se il contenitore e' vuoto, false altrimenti
  */
  public boolean isEmpty()
  {
    return vSize == 0;
  }

  /**
     @return  numero di elementi nel contenitore
  */
  public int size()
  {
    return vSize;
  }

/**
 @return un iteratore
*/
  public Iterator iterator()
  {
    return new ArrayIterator();
  }

  // Classe Interna
  class ArrayIterator implements Iterator
  {
    private int position;
    private boolean state;

    // costruttore: inizializza un iteratore
    public ArrayIterator()
    {
      position = 0;
      state = false;
    }

   /*
      verifica se e' presente almeno un elemento dopo la posizione corrente
      dell'iteratore
      @return true se e' presente almeno un elemento, false altrimenti
   */
    public boolean hasNext()
    {
      return position < vSize;
    }

   /*
      ispeziona l'elemento DOPO la posizione corrente dell'iteratore, avanzando
      successivamente l'iteratore di una posizione nella lista
      @return l'elemento ispezionato, se presente
      throws java.util.NoSuchElementException se non ci sono elementi dopo la
      posizione corrente dell'iteratore
   */
    public Object next() throws NoSuchElementException
    {
      if (!hasNext())
        throw new NoSuchElementException();

      state = true;

      return v[position++];
    }

  /*
     inserisce un nuovo elemento dopo la posizione corrente dell'iteratore,
     l'iteratore si posiziona dopo il nuovo elemento
     @param x elemento da inserire
  */
    public void add(Object obj)
    {
      if (vSize >= v.length)
         resize();

      for (int i = vSize; i > position; i--)
        v[i] = v[i-1];

      v[position++] = obj;
      vSize++;
      state = false;
    }

  /*
     elimina l'ultimo nodo esaminato dal metodo next()
     puo' essere invocato solo dopo l'invocazione del metodo next()
     @throws java.lang.IllegalStateException se precedentemente non e' stato
     invocato il metodo next()
  */
    public void remove() throws IllegalStateException
    {
      if (state == false)
        throw new IllegalStateException();

      for (int i = position - 1; i < vSize-1; i++)
        v[i] = v[i+1];

      vSize--;
      position--;
      state = false;
    }
  }

   /**
     Restituisce l'elemento alla posizione specificata nella lista
     @index indice dell'elemento da restituire
   */
   public Object get(int index)
   {
      if (index < 0 || index >= vSize)
         throw new IllegalArgumentException();

      return v[index];
   }

   /**
     Sostituisce l'elemento alla posizione specificata nella lista con l'elemento x
     @param index posizione dell'elemento da sostituire
     @param x elemento da memorizzare alla posizione
      specificata
     @return l'elemento precedentemente memorizzato
      Alla posizione specificata
   */
   public Object set(int index, Object x)
   {
      if (index < 0 || index >= vSize)
         throw new IllegalArgumentException();

      Object o = v[index];
      v[index] = x;

      return o;
   }

  /**
      Inserisce l'elemento specificato alla posizione specificata nella lista
      Sposta a destra tutti gli elementi presenti
      di un posto
      @index rango dell'elemento da inserire
      @x elemento da inserire
   */
   public void add(int index, Object x)
   {
      if (index < 0 || index > vSize)
         throw new IllegalArgumentException();

      if (vSize >= v.length)
         resize();

      for (int i = vSize; i > index; i--)
         v[i] = v[i - 1];

      v[index] = x;
      vSize++;
   }

  /**
      Estrae l'elemento alla posizione specificata nella lista. Sposta a sinistra di
      un posto tutti gli elementi che seguono
      L'elemento estratto
      @index rango dell'elemento da sostituire

   */
   public Object remove(int index)
   {
      if (index < 0 || index >= vSize)
         throw new IllegalArgumentException();

      Object x = v[index];

      for (int i = index; i < vSize - 1; i++)
         v[i] = v[i + 1];

      v[vSize - 1] = null;
      vSize--;

      return x;
   }

   private void resize()
   {
     Object[] vTmp = new Object[2 * v.length];

     for (int i = 0; i < vSize; i++)
       vTmp[i] = v[i];

     v = vTmp;
   }
}
