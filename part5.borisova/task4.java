import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class task4 {
    private static void solve(final PrintWriter out) {
        Scanner in = new Scanner(System.in);
        String template = in.next().toUpperCase();
        String word = in.next().toUpperCase();
        if (word.contains("?") || word.contains("*")) {
            String temp = word;
            word = template;
            template = temp;
        }
        int[] prev;
        boolean wasLetterFlag = false;
        int[] current = new int[word.length()];

        //заполнение current
        if (template.charAt(0) != '*') {
            if (template.charAt(0) == '?' || template.charAt(0) == word.charAt(0)) {
                current[0] = 1;
                wasLetterFlag = true;
            } else {
                out.print("NO");
                return;
            }
        } else {
            Arrays.fill(current, 1);
        }

        for (int i = 1; i < template.length(); i++) {
            prev = current.clone();
            Arrays.fill(current, 0);
            if (template.charAt(i) != '*') { // если в шаблоне буква или ?
                for (int j = 0; j < word.length(); j++) {
                    if (template.charAt(i) == '?' || template.charAt(i) == word.charAt(j)) {
                        if (!wasLetterFlag && prev[j] == 1) {
                            current[j] = 1;
                        }
                        if (j != 0 && prev[j - 1] == 1) {
                            current[j] = 1;
                        }
                        wasLetterFlag = true;
                    }
                }

            } else { // если в шаблоне *
                int j = 0;
                while (j < prev.length && prev[j] != 1) {
                    j++;
                }
                if (j == prev.length) {
                    out.print("NO");
                    return;
                }
                for (int k = j; k < word.length(); k++) {
                    current[k] = 1;
                }
            }
        }
        out.print(current[current.length - 1] == 1 ? "YES" : "NO");
    }

    public static void main(final String[] arg) {
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(out);
        }
    }
}
