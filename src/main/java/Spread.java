import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Spread {
  private Spread() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int N = in.nextInt();
    ArrayList<Integer> numbers = new ArrayList<>(N);
    readNumbers(numbers, N, in);
    countingSort(numbers);
    printNumbers(numbers, out);
  }

  private static void readNumbers(ArrayList<Integer> numbers, int N, final FastScanner in) {
    for (int i = 0; i < N; ++i) {
      numbers.add(in.nextInt());
    }
  }

  private static void countingSort(ArrayList<Integer> numbers) {
    if (numbers.size() <= 1) {
      return;
    }

    int minElem = Collections.min(numbers);
    int k = Collections.max(numbers) - minElem + 1;
    ArrayList<Integer> C = new ArrayList<>(k);
    for (int i = 0; i < k; i++) {
      C.add(0);
    }
    // Все элементы должны быть а) не меньше нуля и б) в диапазоне от 0 до k-1
    if (minElem < 0) {
      for (int i = 0; i < numbers.size(); ++i) {
        numbers.set(i, numbers.get(i) + Math.abs(minElem));
      }
    } else if (minElem > 0) {
      for (int i = 0; i < numbers.size(); ++i) {
        numbers.set(i, numbers.get(i) - minElem);
      }
    }

    // Считаем для C[i], сколько чисел из numbers равны i
    for (Integer elem : numbers) {
      C.set(elem, C.get(elem) + 1);
    }

    int iterationNumber = 0;
    for (int iterationIndex = 0; iterationIndex < C.size(); ++iterationIndex) {
      int curElemValue = iterationIndex;
      for (int j = 0; j < C.get(iterationIndex); ++j) {
        numbers.set(iterationNumber, curElemValue);
        ++iterationNumber;
      }
    }

    // Возвращаем значения элементов массива в исходное состояние
    if (minElem < 0) {
      for (int i = 0; i < numbers.size(); ++i) {
        numbers.set(i, numbers.get(i) - Math.abs(minElem));
      }
    } else if (minElem > 0) {
      for (int i = 0; i < numbers.size(); ++i) {
        numbers.set(i, numbers.get(i) + minElem);
      }
    }
  }

  private static void printNumbers(ArrayList<Integer> numbers, final PrintWriter out) {
    for (Integer elem: numbers) {
      out.print(elem + " ");
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
