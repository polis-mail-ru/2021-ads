import java.util.*;

public class Queue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Integer> queue = new LinkedList<Integer>();
        String string = "";
        do {
            string = scanner.next();
            switch (string) {
                case "push": {
                    queue.add(scanner.nextInt());
                    System.out.println("ok");
                    break;
                }
                case "pop": {
                    if (queue.isEmpty()) {
                        System.out.println("error");
                        break;
                    }
                    System.out.println(queue.remove());
                    break;
                }
                case "front": {
                    if (queue.isEmpty()) {
                        System.out.println("error");
                        break;
                    }
                    System.out.println(queue.peek());
                    break;
                }
                case "size": {
                    System.out.println(queue.size());
                    break;
                }
                case "clear": {
                    queue.clear();
                    System.out.println("ok");
                    break;
                }
                case "exit": {
                    System.out.println("bye");
                    break;
                }
                default: {
                    System.out.println("error");
                    return;
                }
            }
        } while (!string.equals("exit"));
    }
}