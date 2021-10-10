import java.util.Scanner;

public class HeapSort {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    int[] nums = readArrayFromInput(in, N);
    Heap.heapSort(nums, 0, nums.length - 1, Heap.HeapType.MAX_ORDERED);
    printArray(nums, 1, N + 1);
  }

  private static int[] readArrayFromInput(Scanner in, int N) {
    int[] tmp = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      int num = in.nextInt();
      tmp[i] = num;
    }
    return tmp;
  }

  private static void printArray(int[] a, int start, int end) {
    for (int i = start; i < end; i++) {
      System.out.print(a[i] + " ");
    }
  }
}
