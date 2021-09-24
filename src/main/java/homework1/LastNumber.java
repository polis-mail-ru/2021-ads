package homework1;

import java.util.Scanner;

public class LastNumber {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num  = scan.nextInt();
        System.out.println(num % 10);
    }
}
