package week3;

import java.util.Arrays;

public class MergeSort {
	
	public static void sort(int[] array) {
		partition(array, 0, array.length-1);
	}
	
	private static void partition(int[] array, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			partition(array, low, mid);
			partition(array, mid+1, high);
			merge(array, low, mid, high);
		}
	}
	
	private static void merge(int[] array, int low, int mid, int high) {
		int[] helper = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			helper[i] = array[i];
		}
		int i = low;
		int j = mid+1;
		int k = low;
		
		while (i <= mid && j <= high) {
			if (helper[i] <= helper[j]) {
				array[k] = helper[i];
				i++;
			}
			else {
				array[k] = helper[j];
				j++;
			}
			k++;
		}
		
		while (i <= mid) {
			array[k] = helper[i];
			i++;
			k++;
		}
		
		while (j <= high) {
			array[k] = helper[j];
			j++;
			k++;
		}
	}
	
	public static void main (String[] args) {
		int[] array = new int[] {3, 4, 5, 2, 5, 9, 10, 1, 3};
		int[] array2 = Arrays.copyOf(array, array.length);
		long startTime = System.nanoTime();
		sort(array);
		System.out.println(System.nanoTime() - startTime);
		long startTime1 = System.nanoTime();
		Arrays.sort(array2);
		System.out.println(System.nanoTime() - startTime1);
		for (int s : array) {
			System.out.print(s + " ");
		}
	}
}
