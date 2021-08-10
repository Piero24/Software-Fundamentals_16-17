public class ArraySet implements Set
{
private final static int INITIAL_CAPACITY = 1;

private Object[] v;
private int vSize;

public ArraySet()
{
	makeEmpty();
 }

public void makeEmpty()
   {
	v = new Object[INITIAL_CAPACITY];
	vSize = 0;
   }
   public boolean isEmpty()
  {
	return vSize == 0;
  }
   public int size()
  {
	return vSize;
  }
   public boolean contains(Object x) // O(n)
   {
    for (int i = 0; i < vSize; i++)
        if (v[i].equals(x))
          return true;
      return false;
   }

 public Object[] toArray()     // O(n)
   {
    Object[] x = new Object[vSize];
      for (int i = 0; i < vSize; i++)

        x[i] = v[i]; // non si puo’ fare meglio!
      return x; }    // su un oggetto generico

// O(n) (usa contains)
   public void add(Object x)
   {
    if (contains(x)) return;
      if (vSize >= v.length)
         v = resize(v, 2 * v.length);
      v[vSize++] = x;
   }
private Object[] resize(Object[] a, int length)
   {…}




public static Set union(Set s1, Set s2)
{
   Set x = new ArraySet();

// inseriamo gli elementi del primo insieme
   Object[] v = s1.toArray();
   for (int i = 0; i < v.length; i++)
      x.add(v[i]);

 // inseriamo tutti gli elementi del
   // secondo insieme, sfruttando le
   // proprietà di add (niente duplicati...)
   v = s2.toArray();
   for (int i = 0; i < v.length; i++)
      x.add(v[i]);
   return x;
}
public static Set intersection(Set s1, Set s2)
{
	Set x = new ArraySet();
}
Object[] v = s1.toArray();
for (int i = 0; i < v.length; i++)
   if (s2.contains(v[i]))
      x.add(v[i]);
return x;

public static Set subtract(Set s1, Set s2)
{
Set x = new ArraySet();
Object[] v = s1.toArray();
for (int i = 0; i < v.length; i++)
if (!s2.contains(v[i]))
      x.add(v[i]);
return x;
}
