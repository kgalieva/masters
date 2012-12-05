

public class BitManipulation {

	public static void testBitOperators(int n, int m) {
		System.out.printf("n =       %35s = %d %n", Integer.toBinaryString(n), n);
		System.out.printf("m =       %35s = %d %n", Integer.toBinaryString(m), m);
		System.out.printf("n & m =   %35s = %d %n", Integer.toBinaryString(n & m), n & m);
		System.out.printf("n | m =   %35s = %d %n", Integer.toBinaryString(n | m), n | m);
		System.out.printf("n ^ m =   %35s = %d %n", Integer.toBinaryString(n ^ m), n ^ m);
		System.out.printf("~n =      %35s = %d %n", Integer.toBinaryString(~n), ~n);
		System.out.printf("n >> 1 =  %35s = %d %n", Integer.toBinaryString(n >> 1), n >> 1);
		System.out.printf("n << 1 =  %35s = %d %n", Integer.toBinaryString(n << 1), n << 1);
		System.out.printf("n >>> 1 = %35s = %d %n", Integer.toBinaryString(n >>> 1), n >>> 1);
	}

	public static void main(String[] args) {
		testBitOperators(5, 11);
		testBitOperators(-5, 11);
		
		for (int i = 0; i < 1 << 5; i++) {
			System.out.printf("i = %35s = %d %n", Integer.toBinaryString(i), i);
		}
		
		for (int j = 0; j < 5; j++) {
			System.out.printf("1 << %d = %35s = %d %n", j, Integer.toBinaryString(1 << j), 1 << j);
		}
		
		System.out.println((Integer.MAX_VALUE + 15)/2);
		System.out.println((Integer.MAX_VALUE + 15)>>>1);
	}

}
