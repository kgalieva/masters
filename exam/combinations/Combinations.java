package exam.combinations;

public class Combinations {
	
	public static void main(String[] args) {
		int[] a = { 10, 10, 4, 2, 1, 3, 5, 10, 21, 21, 100 };
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum = sum + a[i];
		}
		int best = sum;
		int result = 0;
		for (int i = 0; i < 1 << a.length; i++) {
			int d = 0;
			for (int j = 0; j < a.length; j++) {
				if ((i & (1 << j)) == 0) {
					d = d + a[a.length - 1 - j];
				}
			}
			int current = Math.abs(sum - 2 * d);
			if (best > current) {
				best = current;
				result = i;
			}
		}
		System.out.println(best);
		System.out.println(Integer.toBinaryString(result));
	}
}
