import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IsHeap {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    List<Integer> nums = new ArrayList<>(N + 1);
    nums.add(0); // Неиспользуемый первый (нулевой) элемент (сокращение бинарных операций после)
    for (int i = 0; i < N; i++) {
      nums.add(in.nextInt());
    }
    for (int i = 1; i <= N; i++) {
      if (2 * i <= N && nums.get(i) > nums.get(2 * i)) {
        System.out.println("NO");
        return;
      }

      if (2 * i + 1<= N && nums.get(i) > nums.get(2 * i + 1)) {
        System.out.println("NO");
        return;
      }
    }
    System.out.println("YES");
  }
}
