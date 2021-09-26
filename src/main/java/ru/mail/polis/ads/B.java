package ru.mail.polis.ads;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class B {
    private B() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        boolean isWorking=true;
        String input;
        LinkedList<Integer> linkedList=new LinkedList<Integer>();
        while (isWorking){
            input=in.next();
            switch (input){
                case "exit":
                    isWorking=false;
                    out.println("bye");
                    break;
                case "size":
                    out.println(linkedList.size());
                    break;
                case "front":
                case "pop":
                    if(linkedList.isEmpty())
                        out.println("error");
                    else if(input.equals("front"))
                        out.println(linkedList.getFirst());
                    else
                        out.println(linkedList.pop());
                    break;

                case "clear":
                    linkedList.clear();
                    out.println("ok");
                    break;
                default:
                    int n=in.nextInt();
                    linkedList.add(n);
                    out.println("ok");
                    break;
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