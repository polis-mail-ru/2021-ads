package utils;

public class Utils {
  public static int maxInInterval(int[] a, int start, int end) {
    if (a.length == 0 || start < 0 || start > end || end > a.length) {
      throw new IllegalArgumentException();
    }

    int max = a[start];
    for (int i = start + 1; i < end; i++) {
      max = Math.max(max, a[i]);
    }
    return max;
  }
}
