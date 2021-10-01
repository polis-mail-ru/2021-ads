package ru.mail.polis.ads;

import java.io.*;
import java.util.Calendar;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        IntStream.range(0, n).forEach((i) -> array[i] = in.nextInt());
        quickSort(array);
        int count = (array.length == 0) ? 0 : 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[i - 1]) {
                count++;
            }
        }
        out.println(count);
    }

    //Давайте здесь я напишу QuickSort, вместо MergeSort.
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

