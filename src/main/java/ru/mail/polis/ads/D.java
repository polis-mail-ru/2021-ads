package ru.mail.polis.ads;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class D {
    private D() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String s=in.next();
        if(parse(s))
            out.print("yes");
        else
            out.print("no");

        // Write me
    }

    private static boolean parse(String s) {
        int counts[] =new int[3];
        char values[]={'(','[','{',')',']','}'};
        ArrayList<Character> stack=new ArrayList<>();
        for (int i = 0; i < counts.length; i++) {
            counts[i]=0;
        }

        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i < values.length; i++) {
                if(s.charAt(j)==values[i])
                    if(i<values.length/2){
                        stack.add(s.charAt(j));
                    }
                    else {
                        if(stack.isEmpty())
                            return false;
                        if(stack.get(stack.size()-1)==values[i-values.length/2])
                            stack.remove(stack.size()-1);
                        else return false;
                    }
            }
        }
        return stack.isEmpty();
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