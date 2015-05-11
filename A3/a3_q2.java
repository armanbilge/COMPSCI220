import java.io.*;
import java.util.*;

public class a3_p2 {
   public static class N_simpleHT {
      public Hashtable<String, String> ht;
      public Integer m_count;

      public N_simpleHT() {
	 ht = new Hashtable<String, String>();
	 m_count = 0;
      }

      public void lookup(String key, String value) {
	 String v = ht.get(key);
	 if (v == null) {   // Not there yet
	    ht.put(key, value);
	 } else {
	    ht.put(key, value);  // Set its new value
	 }
      }

      public Integer size() {
	 return ht.size();
      }

      public void dump() {
	 Enumeration e = ht.keys();
	 while (e.hasMoreElements() ) {
	    String key = e.nextElement().toString();
	    System.out.printf("%s: %s\n", ht.get(key), key);
	 }
      }
   }


   public static class DHT {
      public int nn = 0;
      public N_simpleHT ha[];

      public DHT(int pnn) {
	 nn = pnn;
	 ha = new N_simpleHT [nn];
	 for (int j = 0; j != pnn; j += 1)  // Create the hash tables
	    ha[j] = new N_simpleHT();
      }

      public void loose_ht(int w) {
         ha[w] = null;
      }

      public int place(String key) {
	 char[] ca = key.toCharArray();
	 int val = 0, klen = key.length();
	 for (int j = 0; j != klen; j += 1) val += (int)ca[j];
	 return val % nn;  // Index of table to use
      }

      public void update(String key, String value) {
	 int p = place(key);
	 ha[p].lookup(key, value);
      }

      public String find(String key) {
	 int p = place(key);
	 return "";  // Complete this line
	 }

      public void statistics() {
	 }
   }


    public static void main(String[] args) throws IOException {
      int nn = 8;
      DHT dht = new DHT(nn);

      Scanner scanner = new Scanner(System.in);
      String fqdn, src, val;
      ArrayList<String>keys = new ArrayList<String>(1000);
      int nl = 0, n_missing = 0;
      try {  // Build the DHT
         for (nl = 0; ; nl += 1) {
            fqdn = scanner.next();  src = scanner.next();
            dht.update(fqdn, src);  // fqdn is DHT key
	    keys.add(fqdn);
         }
      } catch (NoSuchElementException e) {
	  System.out.printf("%d lines\n", nl);    
      } finally {
         scanner.close();
      }
      dht.statistics();

      // Code to make tables 6 and 7 unavailable goes here
      for (int n = 0; n != nl; n += 1) {
	 System.out.printf("  %d", n);
	 fqdn = keys.get(n);
         val = dht.find(fqdn);  // fqdn is DHT key
         if (val == null) {
	    System.out.printf("n=%d, fqdn=%s, val=%s\n",
			      n, fqdn, val);
	    n_missing += 1;
	 }
      }
      System.out.printf("\n%d entries now missing\n", n_missing);
   }
}
