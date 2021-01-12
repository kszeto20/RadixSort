public class Radix {

  public static int nth(int n, int col) {
    n = (int)(n / Math.pow(10, col));
    return n;
  }

}
