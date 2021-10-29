package hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9671757
public final class PatternAndWord {

  private static final String PATTERN_STRING = ".*[?*]+.*";

  private PatternAndWord() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    String str1 = in.next();
    String str2 = in.next();
    char[] pattern;
    char[] analyzed;
    if (str1.matches(PATTERN_STRING)) {
      pattern = str1.toCharArray();
      analyzed = str2.toCharArray();
    } else if (str2.matches(PATTERN_STRING)) {
      pattern = str2.toCharArray();
      analyzed = str1.toCharArray();
    } else {
      out.println(str1.equals(str2) ? "YES" : "NO");
      return;
    }

    boolean[][] dynamic = new boolean[pattern.length][analyzed.length];
    fillDynamic(dynamic, pattern, analyzed);
    out.println(dynamic[pattern.length - 1][analyzed.length - 1] ? "YES" : "NO");
  }

  private static void fillDynamic(boolean[][] d, char[] pattern, char[] analyzed) {
    for (int patternPosition = 0; patternPosition < d.length; ++patternPosition) {
      if (pattern[patternPosition] == '*') {
        if (patternPosition == 0) {
          Arrays.fill(d[0], true);
          continue;
        }
        d[patternPosition][0] = d[patternPosition - 1][0];
        for (int analyzedPosition = 1; analyzedPosition < d[0].length; ++analyzedPosition) {
          d[patternPosition][analyzedPosition] = d[patternPosition][analyzedPosition - 1]
              || d[patternPosition - 1][analyzedPosition];
        }
      } else if (pattern[patternPosition] == '?') {
        if (patternPosition == 0) {
          d[0][0] = true;
          continue;
        }
        System.arraycopy(d[patternPosition - 1], 0, d[patternPosition], 1, d[0].length - 1);
      } else {
        if (patternPosition == 0) {
          d[0][0] = pattern[0] == analyzed[0];
          continue;
        }
        for (int analyzedPosition = 1; analyzedPosition < d[0].length; ++analyzedPosition) {
          d[patternPosition][analyzedPosition] = d[patternPosition - 1][analyzedPosition - 1]
              && pattern[patternPosition] == analyzed[analyzedPosition];
        }
      }
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
    return new PrintWriter(System.out, true);
  }

  public static void main(final String[] arg) {
    final FastScanner in = new FastScanner(System.in);
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
