package ru.mail.polis.ads;

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
public final class A {
    private A() {
        // Should not be instantiated
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Student[] students = new Student[n];

        for (int j = 0; j != n; ++j) {
            students[j] = new Student();
            students[j].set(in.nextInt(), in.nextInt());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (students[j].compareTo(students[j + 1]) > 0) {
                    students[j].swap(students[j + 1]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            out.println(students[i].id + " " + students[i].score);
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

class Student implements Comparable {
    int score;
    int id;

    public void set(int id, int score) {
        this.id = id;
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        if (((Student) o).score - score == 0)
            return -((Student) o).id + id;
        else return ((Student) o).score - score;
    }

    public void swap(Student student) {
        score += student.score;
        id += student.id;
        student.score = score - student.score;
        student.id = id - student.id;
        score = score - student.score;
        id = id - student.id;
    }
}
