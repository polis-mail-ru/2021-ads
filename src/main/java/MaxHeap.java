import java.util.Scanner;

public class MaxHeap {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    Heap maxHeap = new Heap(N, Heap.HeapType.MAX_ORDERED);
    for (int i = 0; i < N; i++) {
      int num = in.nextInt();
      maxHeap.insert(num);
    }
    for (int i = 0; i < N; i++) {
      int num = maxHeap.extract();
      System.out.println(num);
    }
  }
}
