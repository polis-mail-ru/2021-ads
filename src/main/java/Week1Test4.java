
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
public final class Week1Test4 {
	private Week1Test4() {
		// Should not be instantiated
	}

	private static boolean isValid(char[] sequence) {
		Deque<Character> stack = new ArrayDeque<Character>();
		for (int i = 0; i < sequence.length; ++i) {

			final char ch = sequence[i];
			if (!stack.isEmpty()) {
				if (ch == ')') {
					if (stack.pop() == '(')
						continue;
					else
						return false;
				} else if (ch == ']') {
					if (stack.pop() == '[')
						continue;
					else
						return false;
				} else if (ch == '}') {
					if (stack.pop() == '{')
						continue;
					else
						return false;
				}
			}

			stack.push(ch);
		}
		return stack.isEmpty();
	}

	private static void solve(final FastScanner in, final PrintWriter out) {
		char[] sequence = in.next().toCharArray();
		out.println(isValid(sequence) ? "yes" : "no");

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
