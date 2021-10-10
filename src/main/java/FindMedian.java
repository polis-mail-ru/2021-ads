import java.io.PrintWriter;
import java.util.*;

public class FindMedian {
  private static final int maxNumbersQuantity = (int)(Math.pow(10, 6));

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
    Heap maxOrderedHeap = new Heap(maxNumbersQuantity/2, Heap.HeapType.MAX_ORDERED);
    Heap minOrderedHeap = new Heap(maxNumbersQuantity/2, Heap.HeapType.MIN_ORDERED);

    int median = in.nextInt();
    out.println(median);
    boolean wasMedianSingular = true;
    while (in.hasNextInt()) {
      int num = in.nextInt();
      if (wasMedianSingular) {
        maxOrderedHeap.insert(num > median ? median : num);
        minOrderedHeap.insert(num > median ? num : median);
        median = (maxOrderedHeap.peek() + minOrderedHeap.peek())/2;
      } else {
        int maxElemOfMaxOrderedHeap = maxOrderedHeap.peek();
        int minElemOfMinOrderedHeap = minOrderedHeap.peek();
        if (num > minElemOfMinOrderedHeap) {
          median = minOrderedHeap.extract();
          minOrderedHeap.insert(num);
        } else if (num < maxElemOfMaxOrderedHeap) {
          median = maxOrderedHeap.extract();
          maxOrderedHeap.insert(num);
        } else {
          median = num;
        }
      }
      wasMedianSingular = !wasMedianSingular;
      out.println(median);
    }
    out.flush();
  }
}
