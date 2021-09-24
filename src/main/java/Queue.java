import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Queue {
  private static final String PUSH = "push", POP = "pop", FRONT = "front",
      SIZE = "size", CLEAR = "clear", EXIT = "exit", OK = "ok", ERROR = "error";

  private Queue() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    LinkedList<Integer> queue = new LinkedList<>();
    int listSize = 0;
    String command = "";
    while (!command.equals(EXIT))
    {
      command = in.next();
      if (command.equals(PUSH)) {
        queue.offerLast(in.nextInt());
        ++listSize;
        out.println(OK);
      } else if (command.equals(POP)) {
        if (queue.isEmpty()) {
          out.println(ERROR);
        } else {
          out.println(queue.pollFirst());
          --listSize;
        }
      } else if (command.equals(FRONT)) {
        if (queue.isEmpty()) {
          out.println(ERROR);
        } else {
          out.println(queue.getFirst());
        }
      } else if (command.equals(SIZE)) {
        out.println(listSize);
      } else if (command.equals(CLEAR)) {
        queue.clear();
        listSize = 0;
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
