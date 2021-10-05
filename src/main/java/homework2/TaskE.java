package homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskE {

    private TaskE() {
        // Should not be instantiated
    }

    private static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int[] swap = new int[arr.length];

        int middle = arr.length / 2;
        subMergeSort(arr, swap, 0, middle);
        subMergeSort(arr, swap, middle, arr.length);
        merge(arr, swap, 0, middle, arr.length);
    }

    private static void subMergeSort(int[] arr, int[] swap, int start, int end) {
        if (end - start == 1) {
            return;
        }
        int middle = start + ((end - start) / 2);

        subMergeSort(arr, swap, start, middle);
        subMergeSort(arr, swap, middle, end);

        merge(arr, swap, start, middle, end);
    }

    private static void merge(int[] arr, int[] swap, int start, int middle, int end) {
        int length = end - start;
        int lI = start;
        int rI = middle;
        for (int i = 0; i < length; i++) {
            if (lI == middle) {
                for (int j = i; j < length; j++) {
                    swap[j] = arr[rI++];
                }
                break;
            } else if (rI == end) {
                for (int j = i; j < length; j++) {
                    swap[j] = arr[lI++];
                }
                break;
            }
            if (arr[lI] < arr[rI]) {
                swap[i] = arr[lI++];
            } else {
                swap[i] = arr[rI++];
            }
        }
        for (int i = start; i < end; i++) {
            arr[i] = swap[i - start];
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n1 = in.nextInt();
        int[] arr1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            arr1[i] = in.nextInt();
        }
        int n2 = in.nextInt();
        int[] arr2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            arr2[i] = in.nextInt();
        }

        mergeSort(arr1);
        mergeSort(arr2);

        LinkedList<Integer> unique1 = new LinkedList<Integer>();
        unique1.push(arr1[0]);
        for (int i = 1; i < arr1.length; i++) {
            if (unique1.peek() != arr1[i]) {
                unique1.push(arr1[i]);
            }
        }
        LinkedList<Integer> unique2 = new LinkedList<Integer>();
        unique2.push(arr2[0]);
        for (int i = 1; i < arr2.length; i++) {
            if (unique2.peek() != arr2[i]) {
                unique2.push(arr2[i]);
            }
        }

        if (unique1.size() != unique2.size()) {
            out.println("NO");
        } else {
            for (int i = 0; i < unique1.size(); i++) {
                if (!Objects.equals(unique2.pop(), unique1.pop())) {
                    out.println("NO");
                    return;
                }
            }
            out.println("YES");
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
}
