import java.util.Scanner;

public final class Week2Task4 {
    private Week2Task4() {
        // Should not be instantiated
    }

    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[] nums = new int[n];
        String[] numbers = in.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(numbers[i]);
        }
        System.out.println(DifferentCount(nums));
    }

    static int DifferentCount(int[] nums) {
        int n = nums.length;
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++)
        {
            if (nums[i] < min)
            {
                min = nums[i];
            }
            if (nums[i] > max)
            {
                max = nums[i];
            }
        }
        var correction = 0;
        if (min != 0) {
            correction = -min;
        }
        max += correction;
        var c = new int[max + 1];
        for (var i = 0; i < n; i++)
        {
            c[nums[i] + correction]++;
        }
        int count = 0;
        for (var i = 0; i <= max; i++)
        {
            if (c[i] > 0)
            {
                count++;
            }
        }
        return count;
    }
}