

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week2Test1 {
    private Week2Test1() {
        // Should not be instantiated
    }
    
    static class Member {
        final int id;
        final int score;

        public Member(int id, int score) {
            this.id = id;
            this.score = score;
        }
        
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Member[] array = new Member[in.nextInt()];
        
        for (int i = 0; i < array.length; i++) {
            array[i] = new Member(in.nextInt(), in.nextInt());
        }
        
        sort(array, new Member[array.length], 0, array.length - 1, (Member o1, Member o2) -> {
            if (o1.score != o2.score) {
                return o2.score - o1.score;
            }
            return o1.id - o2.id;
        });
        
        for (int i = 0; i < array.length; i++) {
            out.println(array[i].id + " " + array[i].score);
        }
    }

    static void sort(Member[] array, Member[] tempArray, int start, int end, Comparator<Member> comparator) {
        if (start < end) {
            int mid = start + ((end - start) >> 1);
            sort(array, tempArray, start, mid, comparator);
            sort(array, tempArray, mid + 1, end, comparator);
            
            for (int i = start; i <= end; ++i) {
                tempArray[i] = array[i];
            }
            
            int leftBound = mid + 1;
            int rightBound = end + 1;
            int i = start;
            int j = mid + 1;
            int k = start;
            while (i < leftBound && j < rightBound) {
                if (comparator.compare(tempArray[i], tempArray[j]) < 0) {
                    array[k] = tempArray[i];
                    i++;
                } else {
                    array[k] = tempArray[j];
                    j++;
                }
                k++;
            }
            while (i < leftBound) {
                array[k] = tempArray[i];
                i++;
                k++;
            }
            while (j < rightBound) {
                array[k] = tempArray[j];
                j++;
                k++;
            }
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
        
        boolean hasNext() {
            return tokenizer.hasMoreTokens();
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
