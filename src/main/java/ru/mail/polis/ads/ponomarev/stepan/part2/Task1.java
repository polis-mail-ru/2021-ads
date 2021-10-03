package ru.mail.polis.ads.ponomarev.stepan.part2;

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
public final class Task1 {
    private static final Comparator<OlympChel> COMPARATOR = Comparator.comparingInt(l -> l.score);
    private static OlympChel[] aux;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();

        OlympChel[] olympChels = new OlympChel[amount];
        for (int i = 0; i < amount; i++) {
            int numb = in.nextInt();
            int score = in.nextInt();

            olympChels[i] = new OlympChel(numb, score);
        }

        aux = new OlympChel[olympChels.length];
        sort(olympChels, 0, olympChels.length - 1);

        for (int i = 0; i < olympChels.length; i++) {
            out.println(olympChels[i].toString());
        }
    }

    public static void sort(OlympChel[] array, int lb, int rb) {
        if (rb <= lb) {
            return;
        }

        int mid = lb + (rb - lb) / 2;

        sort(array, lb, mid);
        sort(array, mid + 1, rb);
        merge(array, lb, mid, rb);
    }

    public static void merge(OlympChel[] array, int lb, int mid, int rb) {
        int l = lb, r = mid + 1;

        for (int i = lb; i <= rb; i++) {
            aux[i] = array[i];
        }

        for (int i = lb; i <= rb; i++) {
            if (l > mid) {
                array[i] = aux[r];
                r++;
            } else if (r > rb) {
                array[i] = aux[l];
                l++;
            } else if (COMPARATOR.compare(aux[r], aux[l]) < 0) {
                array[i] = aux[r];
                r++;
            } else {
                array[i] = aux[l];
                l++;
            }
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

    private static class OlympChel {
        private final int number;
        private final int score;

        public OlympChel(int number, int score) {
            this.number = number;
            this.score = score;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new OlympChel(number, score);
        }

        @Override
        public String toString() {
            return number + " " + score;
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
