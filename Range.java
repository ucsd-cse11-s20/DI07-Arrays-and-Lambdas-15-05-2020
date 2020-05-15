import tester.*;

class Range {
	int[] range(int start, int stop) {
		int size = stop - start;
		int [] nums = new int[size];	
		for (int index = 0; index < size; index += 1) {
			nums[index] = index + start;
		}
		return nums;
	}
	
	int[] range1 = range(20, 30);
	int[] range2 = range(0, 5);
}
