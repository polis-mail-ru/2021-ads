import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class BracesChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedList<Character> list = new LinkedList<>();
        if (!scanner.hasNext()) {
            System.err.println("Incorrect input");
        }
        String string = scanner.next();
        for (char temp : string.toCharArray()) {
            switch (temp) {
                case '(':
                case '[':
                case '{': {
                    list.push(temp);
                    break;
                }
                case ')':
                case ']':
                case '}': {
                    if (list.isEmpty()) {
                        System.out.println("no");
                        return;
                    }
                    Character bracket = list.pop();
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
        if (!list.isEmpty()) {
            System.out.println("no");
        } else {
            System.out.println("yes");
        }
    }
}
