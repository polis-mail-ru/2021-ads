import java.util.LinkedList;
import java.util.Scanner;

public class Posfix {
    static LinkedList<Integer> stack = new LinkedList<>();

    private static void solve(final Scanner in) {
        int n, i = 0;
        String input = in.nextLine();
        String[] arg = input.split(" ");
        stack.add(Integer.parseInt(arg[0]));
        while (i < arg.length) {
            switch (arg[i]) {
                case ("-") -> {
                    n = -stack.pollLast();
                    n += stack.pollLast();
                    stack.add(n);
                }
                case ("+") -> {
                    n = stack.pollLast();
                    n += stack.pollLast();
                    stack.add(n);
                }
                case ("*") -> {
                    n = stack.pollLast();
                    n *= stack.pollLast();
                    stack.add(n);
                }
                default -> stack.add(Integer.parseInt(arg[i]));
            }
            i++;
        }
        System.out.println(stack.peekLast());
    }

    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        solve(in);
    }
}