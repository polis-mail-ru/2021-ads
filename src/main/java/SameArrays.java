import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class SameArrays {
  private SameArrays() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int[] A = new int[in.nextInt()];
    readNumbers(A, in);
    quickSort(A, 0, A.length);

    int[] B = new int[in.nextInt()];
    readNumbers(B, in);
    quickSort(B, 0, B.length);

    int i = 0;
    int j = 0;
    while (i < A.length && j < B.length) {
      if (A[i] != B[j]) {
        out.println("NO");
        return;
      }
      while (i < A.length - 1 && A[i] == A[i + 1]) {
        ++i;
      }
      while (j < B.length - 1 && B[j] == B[j + 1]) {
        ++j;
      }
      ++i;
      ++j;
    }
    boolean areAllElementsChecked = i == A.length && j == B.length;
    out.println(areAllElementsChecked ? "YES" : "NO");
  }

  private static void readNumbers(int[] numbers, final FastScanner in) {
    for (int i = 0; i < numbers.length; ++i) {
      numbers[i] = in.nextInt();
    }
  }

  private static void quickSort(int[] numbers, int start, int end) {
    if (start >= end - 1) {
      return;
    }
    int i = partition(numbers, start, end);
    quickSort(numbers, start, i);
    quickSort(numbers, i + 1, end);
  }

  private static int partition(int[] numbers, int start, int end) {
    int pivotal = numbers[start];
    int i = start;
    for (int j = start + 1; j < end; ++j) {
      if (numbers[j] <= pivotal) {
        swap(numbers, ++i, j);
      }
    }
    swap(numbers, i, start);
    return i;
  }

  private static void swap(int[] numbers, int indexA, int indexB) {
    int tmp = numbers[indexA];
    numbers[indexA] = numbers[indexB];
    numbers[indexB] = tmp;
  }

  private static void uniqueNumbers(int[] numbers, ArrayList<Integer> uniqueNumbers) {
    int position = 1;
    uniqueNumbers.add(numbers[0]);
    while (position != numbers.length) {
      if (numbers[position - 1] != numbers[position]) {
        uniqueNumbers.add(numbers[position]);
      }
      ++position;
    }
  }

  public static class FastScanner {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    public FastScanner(final InputStream in) {
      reader = new BufferedReader(new InputStreamReader(in));
    }

    String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return tokenizer.nextToken();
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }
  }

  public static PrintWriter createPrintWriterForLocalTests() {
    return new PrintWriter(System.out, true);
  }

  public static void main(final String[] arg) {
    final FastScanner in = new FastScanner(System.in);
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
