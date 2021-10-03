import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 */



public final class TaskA {
    private TaskA() {
        // Should not be instantiated
    }
    static class Pair<T,Y> {
        T first;
        Y second;
        public Pair(T first, Y second) {
            this.first = first;
            this.second = second;
        }
        public T getFirst(){
            return first;
        }
        public Y getSecond() {
            return second;
        }
        @Override
        public String toString() {
            return first + " " + second;
        }
    }
    static <T> void swap(ArrayList<T> array, int i, int j) {
        T tmp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, tmp);
    }

    public static <T> ArrayList<T> sort(ArrayList<T> array, int begin, int end, Comparator<T> cmp) {
        if (array.size() <= 1 || end <= 0) {
            return array;
        }
        if (begin >= end) {
            return array;
        }
        int pivot = begin + (end - begin) / 2;
        T opora = array.get(pivot);
        swap(array, pivot, end);
        for (int i = pivot = begin; i < end; i++) {
            if (cmp.compare(array.get(i), opora) <= 0) {
                swap(array, pivot++, i);
            }
        }
        swap(array, pivot, end);
        sort(array, begin, pivot - 1, cmp);
        sort(array, pivot + 1, end, cmp);
        return array;
    }

//public class Quicksort {
//    private void swap(Object[] array, int i, int j) {
//        Object tmp = array[i];
//        array[i] = array[j];
//        array[j] = tmp;
//    }
//}

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        ArrayList<Pair<Integer,Integer>> mass = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            mass.add(new Pair(in.nextInt(), in.nextInt()));
        }
        sort(mass, 0, mass.size()-1, (a, b) -> {
            if(a.getSecond() < b.getSecond()) {
                return -1;
            }
            if(a.getSecond() > b.getSecond()) {

                return 1;
            } else {
                return b.getFirst() - a.getFirst();
            }
        });
        //mass.sort(Comparator.comparingInt(Pair::getSecond));
        Collections.reverse(mass);
        for (int i = 0; i < mass.size(); i++) {
            System.out.println(mass.get(i).getFirst() + " " + mass.get(i).getSecond());
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
