import java.util.Scanner;
import java.util.Stack;

public class Postfix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Stack<Integer> stack = new Stack<>();
        if (!scanner.hasNext()) {
            System.err.println("Incorrect input");
        }
        String string = scanner.nextLine();
        for (Character temp : string.toCharArray()) {
            switch (temp) {
                case ' ': {
                    break;
                }
                case '+':
                case '*':
                case '-': {
                    if (stack.size() < 2) {
                        System.err.println("Incorrect input");
                        return;
                    }
                    Integer[] operands = new Integer[2];
                    operands[1] = stack.pop();
                    operands[0] = stack.pop();

                    stack.push(switch (temp) {
                        case '+' -> operands[0] + operands[1];
                        case '-' -> operands[0] - operands[1];
                        case '*' -> operands[0] * operands[1];
                        default -> 0;
                    });
                    break;
                }
                default: {
                    if (!Character.isDigit(temp)) {
                        System.err.println("Incorrect input");
                        return;
                    }
                    stack.push(Character.getNumericValue(temp));
                    break;
                }
            }
        }
        if (stack.size() != 1) {
            System.err.println("Incorrect input");
        } else {
            System.out.println(stack.pop());
        }
    }
}
