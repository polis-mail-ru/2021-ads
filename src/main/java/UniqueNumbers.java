import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class UniqueNumbers {
  private UniqueNumbers() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int N = in.nextInt();
    int[] numbers = new int[N];
    readNumbers(numbers, N, in);
    quickSort(numbers, 0, numbers.length);
    int nUniqueNumbers = countUniqueNumbers(numbers);
    out.println(nUniqueNumbers);
  }

  private static void readNumbers(int[] numbers, int N, final FastScanner in) {
    for (int i = 0; i < N; ++i) {
      numbers[i] = in.nextInt();
    }
  }

  // Используем быструю сортировку
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

  private static int countUniqueNumbers(int[] numbers) {
    int countUnique = 1;
    for (int i = 1; i < numbers.length; i++) {
      if (numbers[i - 1] != numbers[i]) {
        ++countUnique;
      }
    }
    return countUnique;
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
