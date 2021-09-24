package homework1;

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
public final class Postfix {
    private Postfix() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner scan = new Scanner(System.in);
        LinkedList<Integer> variables = new LinkedList<Integer>();
        String line = scan.nextLine().trim();
        for(int i = 0; i < line.length(); i+=2) {
            char character = line.charAt(i);
            if(character == '+') {
                variables.addLast(variables.removeLast() + variables.removeLast());
            }
            else if (character == '-') {
                variables.addLast((- variables.removeLast()) + variables.removeLast());
            }
            else if(character == '*') {
                variables.addLast(variables.removeLast() * variables.removeLast());
            }
            else {
                int num = 0;
                while (character != ' ') {
                    num = num * 10 + Character.getNumericValue(character);
                    i++;
                    character = line.charAt(i);
                }
                i--;
                variables.addLast(num);
            }
        }
        out.println(variables.pop());
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
