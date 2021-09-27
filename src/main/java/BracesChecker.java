import java.util.Scanner;
import java.util.Stack;

public class BracesChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Stack<Character> stack = new Stack<>();
        if (!scanner.hasNext()) {
            System.err.println("Incorrect input");
        }
        String string = scanner.next();
        for (char temp : string.toCharArray()) {
            switch (temp) {
                case '(':
                case '[':
                case '{': {
                    stack.push(temp);
                    break;
                }
                case ')':
                case ']':
                case '}': {
                    if (stack.isEmpty()) {
                        System.out.println("no");
                        return;
                    }
                    Character bracket = stack.pop();
                    if (!(((bracket == '[') && (temp == ']')) || ((bracket == '(') && (temp == ')')) || ((bracket == '{') && (temp == '}')))) {
                        System.out.println("no");
                        return;
                    }
                    break;
                }
                default: {
                    System.out.println("no");
                    return;
                }
            }
        }
        if (!stack.isEmpty()) {
            System.out.println("no");
        } else {
            System.out.println("yes");
        }
    }
}
