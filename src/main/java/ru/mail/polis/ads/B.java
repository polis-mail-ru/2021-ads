package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class B {
    private B() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n=in.nextInt();
        int enter=-1;
        int count=0;
        int[] heap=new int[n+1];
        for (int i = 0; i < n; i++) {
            enter=in.nextInt();
            if(enter==0){
                int num=in.nextInt();
                addElement(num,heap,count);
                count++;
            }else {
                out.println(removeElement(heap,count));
                count--;
            }
        }
        // Write me
    }

    private static int removeElement(int[] heap, int count) {
        int max = heap[1];
        swap(heap, 1, count);
        down(heap, 1, count - 1);
        return max;
    }

    private static void down(int[] heap, int k, int count) {
        while (2 * k <= count) {
            int j = 2 * k;
            if (j < count && heap[j] < heap[j + 1])
                j++;
            if (heap[k] >= heap[j])
                break;
            swap(heap, k, j);
            k = j;
        }
    }

    private static void addElement(int num, int[] heap, int count) {
        heap[count + 1] = num;
        up(heap, count + 1);
    }

    private static void up(int[] heap, int i) {
        int ind = (i);
        while (ind > 1 && heap[ind] > heap[ind / 2]) {
            swap(heap, ind, ind / 2);
            ind /= 2;
        }
    }

    private static void swap(int[] heap, int ind, int i) {
        int tmp = heap[ind];
        heap[ind] = heap[i];
        heap[i] = tmp;
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

