import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class PostfixRecord {
  private PostfixRecord() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
    LinkedList<Integer> stack = new LinkedList<>();
    Scanner input = new Scanner(System.in);
    while (input.hasNext()) {
      String curToken = input.next();
      if (isOperation(curToken)) {
        int operand2 = stack.pollLast(), operand1 = stack.pollLast();
        if (curToken.equals("+")) {
          stack.addLast(operand1 + operand2);
        } else if (curToken.equals("-")) {
          stack.addLast(operand1 - operand2);
        } else if (curToken.equals("*")) {
          stack.addLast(operand1 * operand2);
        }
        continue;
      }

      int parsedNum = Integer.parseInt(curToken);
      stack.addLast(parsedNum);
    }

    out.println(stack.getFirst());
  }

  private static boolean isOperation(String chars) {
    return (chars.equals("+") || chars.equals("-") || chars.equals("*"));
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
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
