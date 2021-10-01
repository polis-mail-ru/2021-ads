package ru.mail.polis.ads;

import java.io.*;
import java.util.Calendar;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array1 = new int[n];
        IntStream.range(0, n).forEach((i) -> array1[i] = in.nextInt());
        int k = in.nextInt();
        int[] array2 = new int[k];
        IntStream.range(0, k).forEach((i) -> array2[i] = in.nextInt());
        quickSort(array1);
        quickSort(array2);
        int i = 0;
        int j = 0;
        while (true) {
            if (array1[i] != array2[j]) {
                out.println("NO");
                return;
            }
            i++;
            while (i != array1.length && array1[i] == array1[i - 1]) {
                i++;
            }
            j++;
            while (j != array2.length && array2[j] == array2[j - 1]) {
                j++;
            }
            if (i == array1.length && j == array2.length) {
                out.println("YES");
                return;
            }
            if (i == array1.length || j == array2.length){
                out.println("NO");
                return;
            }
        }
    }

    private static final Random random = new Random(Calendar.getInstance().getTimeInMillis());

    private static int partition(int[] elements, int min, int max) {
        int x = elements[min + random.nextInt(max - min + 1)];
        int left = min;
        int right = max;
        while (left <= right) {
            while (elements[left] < x) {
                left++;
            }
            while (elements[right] > x) {
                right--;
            }
            if (left <= right) {
                int temp = elements[left];
                elements[left] = elements[right];
                elements[right] = temp;
                left++;
                right--;
            }
        }
        return right;
    }

    private static void quickSort(int[] elements, int min, int max) {
        if (min < max) {
            int border = partition(elements, min, max);
            quickSort(elements, min, border);
            quickSort(elements, border + 1, max);
        }
    }

    public static void quickSort(int[] elements) {
        quickSort(elements, 0, elements.length - 1);
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

