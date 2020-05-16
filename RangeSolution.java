import tester.*;

class Range {
	int[] range(int start, int stop) {
		if (start >= stop) {
			// We can consider start >= stop as a valid input.
			// In those cases, the range is empty, so we can
			// simply return an empty array!
			return new int[0];
		}
		else {
			int size = stop - start;
			int [] nums = new int[size];	
			for (int index = start; index < stop; index += 1) {
				nums[index - start] = index;
			}
			return nums;
		}
	}
	
	int[] range1 = range(20, 30);
	int[] range2 = range(0, 5);
	int[] range3 = range(5, 5);
	int[] range4 = range(5, 4);
}
