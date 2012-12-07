package exam.combinations;

public class CombinationsSimple {

	public static void print(int[] binaryArray) {
		for (int i = 0; i < binaryArray.length; i++) {
			System.out.printf("%d ", binaryArray[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int n = 10;
		int[] a = new int[n];
		for (int j = 0; j < Math.pow(2, n); j++) {		
			a[n - 1] = j;
			for(int i = n - 1; i > 0; i--){
				a[i - 1] = a[i]/2;
				a[i] %= 2;
			}
			print(a);			
		}		
	}
}