/**
  * FONDAMENTI DI INFORMATICA - CANALE _
  * PROVA PRATICA DI PROGRAMMAZIONE DELLA SIMULAZIONE
  *
  * interfaccia Dictionary - ADT dizionario (o multi-mappa) - contenitore di associazioni
  * chiave/valore con chiave non necessariamente univoca
  * la chiave e il valore sono di tipo parametrico
  * 
  */
public interface Dictionary<K extends Comparable, V> //ADT dizionario di chiave e valore parametrici
{
   /**
      restituisce i valori associati alla chiave specificata k, se nel dizionario esistono
      associazioni di chiave specificata o altrimenti restituisce un array di dimensione nulla
      @param k la chiave specificata
      @return array contenenti i valori associati alla chiave specificata k o un array di dimensione
              nulla se la chiave k non e' presente. La dimensione dell'array e' pari al numero di
              associazioni di chiave specificata
   */
   Object[] getAll(K k);

   /**
      @return true se questo dizionario e' vuoto, false altrimenti
   */
   boolean isEmpty();

   /**
      @return un array contenente tutte le chiavi presenti nel dizionario se questo non e' vuoto, o
              altrimenti un array di dimensione nulla. Le chiavi sono disposte nell'array
              ordinatamente secondo il loro ordine naturale
   */
   Comparable[] keys();

   /**
      aggiunge l'associazione (k, v) al dizionario
      @param k la chiave specificata
      @param v il valore specificato
      @throws IllegalArgumentException se k o v valgono null
   */
   void put(K k, V v);

   /**
      Elimina dal dizionario le associazioni di chiave specificata k e ne restituisce i valori; se
      il dizionario non contiene associazioni di chiave k restituisce un array di dimensione nulla
      @param k la chiave specificata
      @return un array di dimensione nulla se nel dizionario non sono presenti associazioni di
              chiave specificata k o altrimenti un array contenente i valori associati alla chiave
              specificata k
   */
   Object[] removeAll(K k);

   /**
      @return il numero di associazioni contenute in questo dizionario
   */
   int size();

   /**
      @return array contenente i valori della associazioni presenti nel dizionario o un array di
              dimensione nulla se il dizionario e' vuoto
   */
   Object[] values();
}
