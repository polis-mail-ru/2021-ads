package ru.mail.polis.ads;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class E {
    private E() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String s;
        /*StringBuilder stringBuilder=new StringBuilder("");
        while ((s=in.next())!=null){
        stringBuilder.append(s);
        }
        s=stringBuilder.toString();*/
        try {
            s=in.reader.readLine();
            out.print(parse(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int parse(String s) {
        ArrayList<Integer> stack=new ArrayList<>();
        boolean isNumber=false;
        int number=0;
        for (int i = 0; i <s.length() ; i++) {
            if(s.charAt(i)<='9' && s.charAt(i)>='0'){
                if(!isNumber){
                    isNumber=true;
                    number=s.charAt(i)-'0';
                }else number=number*10+s.charAt(i)-'0';

            }else if(s.charAt(i)==' ') {

                if (isNumber) {
                    isNumber = false;
                    stack.add(number);
                }
            }else {
                stack.set(stack.size()-2,operation(s.charAt(i),
                        stack.get(stack.size()-2),stack.get(stack.size()-1)));
                stack.remove(stack.size()-1);
            }
        }

        return stack.get(0);
    }

    private static int operation(char c,int a, int b) {
        return switch (c) {
            case '*' -> a * b;
            case '-' -> a - b;
            case '+' -> a + b;
            default -> 0;
        };
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