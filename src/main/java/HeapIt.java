import java.util.Scanner;

public class HeapIt {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    Heap heap = new Heap(N, Heap.HeapType.MAX_ORDERED);
    for (int i = 0; i < N; i++) {
      int commandNumber = in.nextInt();
      switch (commandNumber) {
        case 0: {
          int numForInserting = in.nextInt();
          heap.insert(numForInserting);
          break;
        }
        case 1: {
          int max = heap.extract();
          System.out.println(max);
          break;
        }
        default: throw new RuntimeException("Invalid command number");
      }
    }
  }
}
