import java.util.LinkedList;
import java.util.Scanner;

public final class Week1Task5 {
    private Week1Task5() {
        // Should not be instantiated
    }

    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(ReversePolishNotation(input.trim().split(" ")));
    }

    public static int ReversePolishNotation(String[] input) {
        LinkedList<Integer> numbers = new LinkedList<Integer>();
        int length = input.length;
        int firstOp;
        int secondOp;
        String curSymbols;
        for (int i = 0; i < length; i++) {
            curSymbols = input[i];
            if (curSymbols.charAt(0) >= '0' && curSymbols.charAt(0) <= '9') {
                numbers.push(Integer.parseInt(curSymbols));
            }
            else {
                switch (curSymbols) {
                    case "+":
                        firstOp = numbers.pop();
                        secondOp = numbers.pop();
                        numbers.push(firstOp + secondOp);
                        break;
                    case "-":
                        secondOp = numbers.pop();
                        firstOp = numbers.pop();
                        numbers.push(firstOp - secondOp);
                        break;
                    case "*":
                        firstOp = numbers.pop();
                        secondOp = numbers.pop();
                        numbers.push(firstOp * secondOp);
                        break;
                }
            }
        }
        return numbers.pop();
    }
}