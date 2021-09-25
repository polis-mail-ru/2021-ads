
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
public final class Week1Test2 {
	private Week1Test2() {
		// Should not be instantiated
	}

	private static class ArrayQueue {
		private int[] array;
		private int first;
		private int last;

		public ArrayQueue() {
			this.array = new int[1];
			this.first = 0;
			this.last = 0;
		}

		private void expand() {
			int[] expanded = new int[(this.array.length << 2) + 1];
			System.arraycopy(this.array, this.first, expanded, 0, this.array.length - this.first);
			if (this.first > this.last) {
				System.arraycopy(this.array, 0, expanded, this.array.length - this.first, this.last + 1);
			}
			this.array = expanded;
			this.first = 0;
			this.last = this.size();
		}

		public void push(int n) {
			this.array[this.last] = n;
			if ((this.last + 1) % this.array.length == this.first) {
				this.expand();
			}
			this.last = (this.last + 1) % this.array.length;
		}

		public int pop() {
			if (this.first == this.last) {
				throw new RuntimeException();
			}
			int removed = this.array[this.first];
			this.first = (this.first + 1) % this.array.length;
			return removed;
		}

		public int front() {
			if (this.first == this.last) {
				throw new RuntimeException();
			}
			return this.array[this.first];
		}

		public int size() {
			return (this.last - this.first + this.array.length) % this.array.length;
		}

	}

	private static void solve(final FastScanner in, final PrintWriter out) {
		ArrayQueue queue = new ArrayQueue();
		loop: while (true) { // heh
			try {
				switch (in.next()) {
				case "push":
					queue.push(Integer.valueOf(in.next()));
					out.println("ok");
					continue loop;
				case "pop":
					out.println(queue.pop());
					continue loop;
				case "size":
					out.println(queue.size());
					continue loop;
				case "front":
					out.println(queue.front());
					continue loop;
				case "clear":
					queue = new ArrayQueue();
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
