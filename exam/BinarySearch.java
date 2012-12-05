package exam;

public class BinarySearch {

	public static int binarySearch(int[] a, int key) {
		int low = 0;
		int high = a.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			int midVal = a[mid];
			if (midVal < key) {
				low = mid + 1;
			} else if (midVal > key) {
				high = mid - 1;
			} else {
				return mid; // key found
			}
		}
		return -(low + 1); // key not found.
	}
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		System.out.println(binarySearch(array, 1));
		System.out.println(binarySearch(array, 2));
		System.out.println(binarySearch(array, 3));
		System.out.println(binarySearch(array, 5));
		System.out.println(binarySearch(array, 9));
		System.out.println(binarySearch(array, 99));
	}
}
