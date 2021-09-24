import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Stack {
  private static final String PUSH = "push", POP = "pop", BACK = "back",
      SIZE = "size", CLEAR = "clear", EXIT = "exit", OK = "ok", ERROR = "error";

  private Stack() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    LinkedList<Integer> stack = new LinkedList<>();
    int stackSize = 0;
    String command = "";
    while (!command.equals(EXIT))
    {
      command = in.next();
      if (command.equals(PUSH)) {
        stack.offerLast(in.nextInt());
        ++stackSize;
        out.println(OK);
      } else if (command.equals(POP)) {
        if (stack.isEmpty()) {
          out.println(ERROR);
        } else {
          out.println(stack.pollLast());
          --stackSize;
        }
      } else if (command.equals(BACK)) {
        if (stack.isEmpty()) {
          out.println(ERROR);
        } else {
          out.println(stack.getLast());
        }
      } else if (command.equals(SIZE)) {
        out.println(stackSize);
      } else if (command.equals(CLEAR)) {
        stack.clear();
        stackSize = 0;
        out.println(OK);
      } else if (command.equals(EXIT)) {
        out.println("bye");
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
