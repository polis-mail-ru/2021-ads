package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import ru.mail.polis.ads.bst.AvlBst;
import ru.mail.polis.ads.bst.Bst;

/**
 * Problem solution template.
 */
public final class SolveTemplate {
    private SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
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
        Bst<String, String> bst = new AvlBst<>();

        bst.put("testStringKey3", "testStringValue3");
        bst.put("testStringKey4", "testStringValue4");
        bst.put("testStringKey2", "testStringValue2");
        bst.put("testStringKey5", "testStringValue5");
        bst.put("testStringKey1", "testStringValue1");
        bst.put("testStringKey0", "testStringValue0");

        bst.remove("testStringKey4");

        bst.remove("testStringKey1");
        bst.containsKey("testStringKey1");
    }
}
