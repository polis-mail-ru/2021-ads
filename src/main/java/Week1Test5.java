
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week1Test5 {
	private Week1Test5() {
		// Should not be instantiated
	}

	private static int calculate(String operator, int a, int b) {
		switch (operator) {
		case "+":
			return b + a;
		case "-":
			return b - a;
		case "*":
			return b * a;
		default:
			return 0; // throw new Exception() is not needed so it's return 0
		}
	}

	private static void solve(final FastScanner in, final PrintWriter out) {
		Deque<Integer> stack = new ArrayDeque<Integer>();
		String s;
		for (s = in.next(); in.hasNext(); s = in.next()) {
			if (stack.size() > 1) {
				if (s.equals("+") || s.equals("-") || s.equals("*")) {
					stack.push(calculate(s, stack.pop(), stack.pop()));
					continue;
				}
			}
			stack.push(Integer.valueOf(s));
		}
		out.println(calculate(s, stack.pop(), stack.pop()));
	}

	private static class FastScanner {
		private final BufferedReader reader;
		private StringTokenizer tokenizer;

		FastScanner(final InputStream in) {
			reader = new BufferedReader(new InputStreamReader(in));
		}

		boolean hasNext() {
			return tokenizer.hasMoreTokens();
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
