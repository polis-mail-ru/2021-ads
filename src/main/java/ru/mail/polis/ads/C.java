package ru.mail.polis.ads;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class C {
    private C() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        boolean isWorking=true;
        String input;
        ArrayList<Integer> list=new ArrayList<>();
        while (isWorking){
            input=in.next();
            switch (input) {
                case "exit" -> {
                    isWorking = false;
                    out.println("bye");
                }
                case "size" -> out.println(list.size());
                case "back", "pop" -> {
                    if (list.isEmpty())
                        out.println("error");
                    else if (input.equals("back"))
                        out.println(list.get(list.size() - 1));
                    else {
                        out.println(list.get(list.size() - 1));
                        list.remove(list.get(list.size() - 1));
                    }
                }
                case "clear" -> {
                    list.clear();
                    out.println("ok");
                }
                default -> {
                    int n = in.nextInt();
                    list.add(n);
                    out.println("ok");
                }
            }
        }
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
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}