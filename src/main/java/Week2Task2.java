import java.util.Scanner;

public final class Week2Task2 {
    private Week2Task2() {
        // Should not be instantiated
    }

    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String[] numbers = in.nextLine().split(" ");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
        {
            nums[i] = Integer.parseInt(numbers[i]);
        }
        nums = Sort(nums);
        for (int i = 0; i < n - 1; i++)
        {
            System.out.print(nums[i] + " ");
        }
        System.out.print(nums[n - 1]);
    }

    static int[] Sort(int[] nums) {
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
        var p = 0;
        var len = c.length;
        for (var i = 0; i < len; i++)
        {
            for (var j = 0; j < c[i]; j++)
            {
                nums[p] = i - correction;
                p++;
            }
        }
        return nums;
    }
}