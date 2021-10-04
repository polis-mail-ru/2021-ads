import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 */
public final class TaskE {
    private TaskE() {
        // Should not be instantiated
    }

    public static final Random RND = new Random();
    static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static int[] sort(int[] array, int begin, int end) {
        if (array.length <= 1 || end <= 0) {
            return array;
        }
        if (begin >= end) {
            return array;
        }
        int pivot = begin + RND.nextInt(end - begin + 1);
        int opora = array[pivot];
        swap(array, pivot, end);
        for (int i = pivot = begin; i < end; i++) {
            if ( array[i] <= opora ) {
                swap(array, pivot++, i);
            }
        }
        swap(array, pivot, end);
        sort(array, begin, pivot - 1);
        sort(array, pivot + 1, end);
        return array;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n1 = in.nextInt();
        //ArrayList<Integer> mass1 = new ArrayList<>(n1);
        int[] mass1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            mass1[i] = in.nextInt();
        }
        int n2 = in.nextInt();
        //ArrayList<Integer> mass2 = new ArrayList<>(n2);
        int[] mass2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            mass2[i] = in.nextInt();
        }
        //mass1.sort(Integer::compare);
        //mass2.sort(Integer::compare);
        //Arrays.sort(mass1);
        //Arrays.sort(mass2);
        sort(mass1, 0, n1 - 1);
        sort(mass2, 0, n2 - 1);
        ArrayList<Integer> newMass1 = new ArrayList<>();
        newMass1.add(mass1[0]);
        for (int k = 1; k < n1; k++) {
            if(mass1[k] != mass1[k-1]) {
                newMass1.add(mass1[k]);
            }
        }
        ArrayList<Integer> newMass2 = new ArrayList<>();
        newMass2.add(mass2[0]);
        for (int k = 1; k < n2; k++) {
            if(mass2[k] != mass2[k-1]) {
                newMass2.add(mass2[k]);
            }
        }
        if(newMass1.size()!=newMass2.size()) {
            out.println("NO");
            return;
        }
        System.out.println(Arrays.equals(newMass1.toArray(),newMass2.toArray()) ? "YES" : "NO");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}