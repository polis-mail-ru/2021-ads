import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Postfix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedList<Integer> list = new LinkedList<>();
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
                    if (list.size() < 2) {
                        System.err.println("Incorrect input");
                        return;
                    }
                    Integer[] operands = new Integer[2];
                    operands[1] = list.pop();
                    operands[0] = list.pop();

                    list.push(switch (temp) {
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
                    list.push(Character.getNumericValue(temp));
                    break;
                }
            }
        }
        if (list.size() != 1) {
            System.err.println("Incorrect input");
        } else {
            System.out.println(list.pop());
        }
    }
}
