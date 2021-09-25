
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week1Test3 {
	private Week1Test3() {
		// Should not be instantiated
	}

	private static class ArrayStack {
		private int[] array;
		private int last;

		public ArrayStack() {
			this.array = new int[1];
			this.last = 0;
		}

		private void expand() {
			int[] expanded = new int[(this.array.length << 2) + 1];
			System.arraycopy(this.array, 0, expanded, 0, this.last);
			this.array = expanded;

		}

		public void push(int n) {
			if (this.last == this.array.length) {
				this.expand();
			}
			this.array[this.last++] = n;
		}

		public int pop() {
			if (this.last == 0) {
				throw new RuntimeException();
			}
			int removed = this.array[--this.last];
			return removed;
		}

		public int back() {
			if (this.last == 0) {
				throw new RuntimeException();
			}
			return this.array[this.last - 1];
		}

		public int size() {
			return this.last;
		}

	}

	private static void solve(final FastScanner in, final PrintWriter out) {
		ArrayStack stack = new ArrayStack();
		loop: while (true) { // infinity loop moment
			try {
				switch (in.next()) {
				case "push":
					stack.push(Integer.valueOf(in.next()));
					out.println("ok");
					continue loop;
				case "pop":
					out.println(stack.pop());
					continue loop;
				case "back":
					out.println(stack.back());
					continue loop;
				case "size":
					out.println(stack.size());
					continue loop;
				case "clear":
					stack = new ArrayStack();
					out.println("ok");
					continue loop;
				case "exit":
				default:
					out.println("bye");
					break loop;

				}
			} catch (RuntimeException e) {
				out.println("error");
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
