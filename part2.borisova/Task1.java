import java.io.*;
import java.util.StringTokenizer;

public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int number = in.nextInt();
        Participant part[] = new Participant[number];
        for (int i = 0; i < number; i++) {
            int id = in.nextInt();
            int points = in.nextInt();
            part[i] = new Participant(id, points);
        }
        quickSort(part, 0, number - 1);
        for (Participant p : part) {
            out.println(p.getId() + " " + p.getPoints());
        }
    }

    public static void quickSort(Participant[] source, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        Participant pivot = source[(leftMarker + rightMarker) / 2];
        do {
            int value = pivot.compareTo(source[leftMarker]);
            while (value < 0) {
                leftMarker++;
                value = pivot.compareTo(source[leftMarker]);
            }
            value = pivot.compareTo(source[rightMarker]);
            while (value > 0) {
                rightMarker--;
                value = pivot.compareTo(source[rightMarker]);
            }
            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker) {
                    Participant tmp = source[leftMarker];
                    source[leftMarker] = source[rightMarker];
                    source[rightMarker] = tmp;
                }
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        if (leftMarker < rightBorder) {
            quickSort(source, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort(source, leftBorder, rightMarker);
        }
    }

    public static class Participant implements Comparable{
        private final int id;
        private final int points;

        public Participant(int id, int points) {
            this.id = id;
            this.points = points;
        }

        public int getId() {
            return id;
        }
        public int getPoints() {
            return points;
        }

        public int compare(Participant o) {
            if (points == o.getPoints()) {
                if (id == o.getId()) {
                    return 0;
                }
                else {
                    return (o.getId() > id ? 1 : -1);
                }
            }
            else {
                return (o.getPoints() < points ? 1 : -1);
            }
        }

        @Override
        public int compareTo(Object o) {
            return this.compare((Participant) o);
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
