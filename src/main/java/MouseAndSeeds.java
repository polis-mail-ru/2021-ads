import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class MouseAndSeeds {

  private MouseAndSeeds() {
    // Should not be instantiated
  }

  private static final String upDirection = "F";
  private static final String rightDirection = "R";

  private static class Position {

    public static final Position NULL = new Position(-1, -1);

    final int i;
    final int j;

    public Position(int i, int j) {
      this.i = i;
      this.j = j;
    }

  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int m = in.nextInt();
    int n = in.nextInt();
    int[][] floor = new int[m][n];
    fillFloorBySeeds(in, floor);
    findWayWithMaxSeeds(floor, out);
  }

  private static void fillFloorBySeeds(final FastScanner in, int[][] floor) {
    for (int i = 0; i < floor.length; ++i) {
      for (int j = 0; j < floor[0].length; ++j) {
        floor[i][j] = in.nextInt();
      }
    }
  }

  private static void findWayWithMaxSeeds(int[][] floor, final PrintWriter out) {
    int[][] d = new int[floor.length][floor[0].length];
    countValueOfEachCell(floor, d);
    int maxElem = d[0][d[0].length - 1];
    Position firstElemWithMaxValue = findFirstElemWithMaxValue(d, maxElem);
    StringBuilder rvsPath = new StringBuilder();
    constructReversePathFromFloorEndToFirstElemWithMaxValue(firstElemWithMaxValue, rvsPath, d.length, d[0].length);
    constructReversePathFromFirstElemWithMaxValueToFloorBegin(firstElemWithMaxValue, rvsPath, d);
    rvsPath.reverse();
    out.println(rvsPath);
  }

  private static void countValueOfEachCell(int[][] src, int[][] dest) {
    for (int i = src.length - 1; i >= 0; --i) {
      for (int j = 0; j < src[0].length; ++j) {
        int leftValuable = j - 1 >= 0 ? dest[i][j - 1] : 0;
        int downValuable = i + 1 < src.length ? dest[i + 1][j] : 0;
        dest[i][j] = Math.max(leftValuable, downValuable) + src[i][j];
      }
    }
  }

  private static Position findFirstElemWithMaxValue(int[][] d, int maxValue) {
    for (int i = 0; i < d.length; i++) {
      for (int j = 0; j < d[0].length; j++) {
        if (d[i][j] == maxValue) {
          return new Position(i, j);
        }
      }
    }
    return Position.NULL;
  }

  private static void constructReversePathFromFloorEndToFirstElemWithMaxValue
      (Position firstElemWithMaxValue, StringBuilder rvsPath, int n, int m) {
    int _n = 0;
    int _m = m - 1;
    while (_n < firstElemWithMaxValue.i) {
      rvsPath.append(upDirection);
      ++_n;
    }
    while (_m > firstElemWithMaxValue.j) {
      rvsPath.append(rightDirection);
      --_m;
    }
  }

  private static void constructReversePathFromFirstElemWithMaxValueToFloorBegin
      (Position firstElemWithMaxValue, StringBuilder rvsPath, int[][] d) {
    int n = firstElemWithMaxValue.i;
    int m = firstElemWithMaxValue.j;
    while (n + 1 < d.length && m - 1 >= 0) {
      if (d[n][m - 1] >= d[n + 1][m]) {
        rvsPath.append(rightDirection);
        --m;
        continue;
      }
      ++n;
      rvsPath.append(upDirection);
    }

    if (m > 0) {
      while (m > 0) {
        rvsPath.append(rightDirection);
        --m;
      }
      return;
    }
    while (n < d.length - 1) {
      rvsPath.append(upDirection);
      ++n;
    }
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
    return new PrintWriter(System.out, false);
  }

  public static void main(final String[] arg) {
    final FastScanner in = new FastScanner(System.in);
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
