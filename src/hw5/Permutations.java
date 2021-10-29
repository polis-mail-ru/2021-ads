package hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9677694
public final class Permutations {

  private Permutations() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int[] bannedNumbers = new int[n];
    outAllPermutations(bannedNumbers, 0, out);
  }

  private static void outAllPermutations(int[] bannedNumbers,
      int positionToBeFilledByNumber, final PrintWriter out) {
    if (positionToBeFilledByNumber == bannedNumbers.length) {
      print(out, bannedNumbers);
      return;
    }
    int nVacantPlaces = bannedNumbers.length - positionToBeFilledByNumber;
    for (int j = 1; j <= nVacantPlaces; ++j) {
      bannedNumbers[positionToBeFilledByNumber] = getNewNumber(bannedNumbers,
          positionToBeFilledByNumber, j);
      outAllPermutations(bannedNumbers, positionToBeFilledByNumber + 1, out);
    }
  }

  private static void print(final PrintWriter out, int[] a) {
    for (int i = 0; i < a.length - 1; ++i) {
      out.print(a[i] + " ");
    }
    out.println(a[a.length - 1]);
  }

  private static int getNewNumber(int[] bannedNumbers, int positionToBeFilledByNumber,
      int orderOfUniqueness) {
    int curOrderOfUniqueness = 0;
    for (int i = 1; i <= bannedNumbers.length; ++i) {
      if (!contains(bannedNumbers, i, positionToBeFilledByNumber)) {
        ++curOrderOfUniqueness;
        if (curOrderOfUniqueness == orderOfUniqueness) {
          return i;
        }
      }
    }
    throw new RuntimeException("No new number");
  }

  private static boolean contains(int[] a, int num, int positionToBeFilledByNumber) {
    boolean containsNum = false;
    for (int i = 0; i < positionToBeFilledByNumber; ++i) {
      if (a[i] == num) {
        containsNum = true;
        break;
      }
    }
    return containsNum;
  }

  private static class FastScanner {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    FastScanner(final InputStream in) {
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

    int nextInt() {
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
