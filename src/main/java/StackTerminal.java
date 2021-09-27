import java.util.Scanner;
import java.util.Stack;

public class StackTerminal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<Integer>();
        String string = "";
        while (!string.equals("exit")) {
            string = scanner.next();
            switch (string) {
                case "push": {
                    stack.push(scanner.nextInt());
                    System.out.println("ok");
                    break;
                }
                case "pop": {
                    if (stack.isEmpty()) {
                        System.out.println("error");
                        break;
                    }
                    System.out.println(stack.pop());
                    break;
                }
                case "back": {
                    if (stack.isEmpty()) {
                        System.out.println("error");
                        break;
                    }
                    System.out.println(stack.peek());
                    break;
                }
                case "size": {
                    System.out.println(stack.size());
                    break;
                }
                case "clear": {
                    stack.clear();
                    System.out.println("ok");
                    break;
                }
                case "exit": {
                    System.out.println("bye");
                    return;
                }
                default: {
                    System.out.println("error");
                    return;
                }
            }
        }
    }
}
