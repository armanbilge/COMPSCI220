import java.io.*;
import java.util.*;

public class a2_quick {
   private static int[] unsorted;  // Integers to sort

   /* This version of get_unsorted() reads the integers directly
      from file a2_in.txt.  Use it if you're developing your version
      of a2_quick using DrJava on Windows.

      >> Don't forget to switch back to the other version (which
         reads from stdin) before you submit your program to the
         www.cs.auckland.ac.nz/automated-marker <<<
    
   private static int get_unsorted() {
      File inf = new File("a2_in.txt");
      int n = 0;
      Scanner scanner;
      try {
         scanner = new Scanner(inf);
         n = scanner.nextInt();
         unsorted = new int[n];
         for (int j = 0; j != n; j += 1)  // Read the integers into unsorted
            unsorted[j] = scanner.nextInt();
      } catch (FileNotFoundException e) {
	 System.out.printf("Couldn't find file a2_in.txt\n");    
      }
      return n;
    } */

    private static int get_unsorted() {
      Scanner sysin = new Scanner(System.in);
      int n = sysin.nextInt();
      unsorted = new int[n];
      for (int j = 0; j != n; j += 1)  // Read the integers into unsorted
         unsorted[j] = sysin.nextInt();
      sysin.close();
      return n;
      }

    private static void get_values(int[] a, int n) {
      // Copy values from unsorted to a
      System.arraycopy(unsorted,0, a,0, n);
   }


   private static void swap(int[]a, int i, int j) {
      int tmp = a[i];  a[i] = a[j];  a[j] = tmp;
   }

   private static void percolateDown(int []a, int i, int size) {
      int child;
      int parent = i + 1;
      for (child = parent*2; child < size; child = parent*2) {
         if (a[parent-1] < a[child-1] || a[ parent-1] < a[child] ) {
            if (a[child-1] < a[child]) {
               swap(a, parent-1, child);
               parent = child+1;
            } else {
	       swap(a, parent-1, child-1);
               parent = child;
            }
         } else break;
      }
      if (child == size && a[parent-1] < a[child-1])
         swap(a, parent-1, child-1);
   }

   public static void heapSort(int[] a) {
      for (int i = a.length/2 - 1; i >= 0; i--)
         percolateDown(a, i, a.length);
      for(int i = a.length-1; i >= 1; i--) {
         swap(a, 0, i);
         percolateDown(a, 0, i);
      }
   }


   private static void quicksort(int[] a, int left, int right, int which) {
      int px;
      if (which == 1) px = p_index1(left, right);
      else px = p_index2(left, right);

      if (px < left || px > right) {
	  System.out.printf("p_index failed");
	  System.exit(111);
      }

      int pivot = a[px];
      int lx = left, rx = right;
      while (lx <= rx) {
	 while (a[lx] < pivot) lx += 1;
         while (a[rx] > pivot) rx -= 1;
         if (lx <= rx) {
    	    swap(a, lx, rx);
     	    lx += 1;  rx -= 1;
	 }
      }
      
      if (left < lx-1)
      	  quicksort(a, left, lx-1, which);
      if (right > lx)
	  try {
	      quicksort(a, lx, right, which);
	  } catch (StackOverflowError e) {
	     System.out.printf(
	        "StackOverflow:  left=%d, lx=%d, right=%d\n",
		left, lx, right);
             System.out.flush();
	     return;
	  }
   }

   private static int p_index1(int left, int right) {
      return left+(right-left)/200;
   }

   private static int p_index2(int left, int right) {
      return left+(right-left)/200;
   }

   public static void quickSort(int[] a, int which) {
      quicksort(a, 0, a.length-1, which);
   }

   private static void test_algorithms(int a[], int n) {
      long start;
      double TimeInSec_h = 0, TimeInSec_q1 = 0, TimeInSec_q2 = 0;
      String speedup;

      get_values(a, n);
      start = System.nanoTime();
      heapSort(a);
      TimeInSec_h = (System.nanoTime()-start) * 1.0e-9;

      get_values(a, n);
      start = System.nanoTime();
      quickSort(a, 1);
      TimeInSec_q1 = (System.nanoTime()-start) * 1.0e-9;

      get_values(a, n);
      start = System.nanoTime();
      quickSort(a, 2);
      TimeInSec_q2 = (System.nanoTime()-start) * 1.0e-9;
      
      speedup = String.format("%.1f", TimeInSec_q1/TimeInSec_q2);
      System.out.printf("%8d   %9.6f   %9.6f  %9.6f     %s\n", n,
      TimeInSec_h, TimeInSec_q1, TimeInSec_q2, speedup);  /* */
   }

   public static void main(String[] args) {
      int n = get_unsorted();
      System.out.printf("%d integers read\n", n);

      int nv[] = new int[6];
      int m = n, d = 1;
      for (int j = 5; j >= 0; j -= 1) {
         nv[j] = m/d;  d *= 2;
      }

      int a[] = new int[n];
      System.out.printf(
	 "#     n       heap        quick1     quick2   speedup\n");
      for (int x = 0; x != nv.length; x += 1) {
         test_algorithms(a, nv[x]);
      }
   }
}
