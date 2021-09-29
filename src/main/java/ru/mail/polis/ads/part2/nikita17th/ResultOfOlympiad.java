package ru.mail.polis.ads.part2.nikita17th;

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
public final class ResultOfOlympiad {
    private ResultOfOlympiad() {
        // Should not be instantiated
    }

    private static class OlympiadParticipant{
        public final int numberOfParticipant;
        public final int pointsOfParticipant;

        OlympiadParticipant(int numberOfParticipant, int pointsOfParticipant) {
            this.numberOfParticipant = numberOfParticipant;
            this.pointsOfParticipant = pointsOfParticipant;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        OlympiadParticipant[] array = new OlympiadParticipant[n];

        for (int i = 0; i < n; i++) {
            array[i] = new OlympiadParticipant(in.nextInt(), in.nextInt());
        }

        sort(array, (OlympiadParticipant o1, OlympiadParticipant o2) -> {
            if (o1.pointsOfParticipant != o2.pointsOfParticipant) {
                return o1.pointsOfParticipant - o2.pointsOfParticipant;
            }
            return o2.numberOfParticipant - o1.numberOfParticipant;
        });

        for (int i = 0; i < n; i++) {
            out.println(array[i].numberOfParticipant + " " + array[i].pointsOfParticipant);
        }

    }

    private static void sort(OlympiadParticipant[] array, Comparator<OlympiadParticipant> comparator) {
        int n = array.length;
        OlympiadParticipant tmp;
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(array[j], array[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }

            if (maxIndex != i) {
                tmp = array[i];
                array[i] = array[maxIndex];
                array[maxIndex] = tmp;
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
