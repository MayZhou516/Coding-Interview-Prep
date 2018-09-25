// Java HashSet & HashMap methods
// containsKey(Object key), containsValue(Object value)
// get(Object key)
// isEmpty()
// keySet(): returns a type Set of all the keys
// put(Object key, Object value)
// remove(Object key), remove (Object key, Object value)

// PROBLEM Ransom Note: can you make a ransom note out of a given magazine
boolean ransomNote(String[] magazine, String[] note) {
	HashMap<String, Integer> magazineMap = new HashMap<String, Integer> ();
	for (String s : magazine) {
		if (magazineMap.containsKey(s)) {
			magazineMap.put(s, magazine.get(s) + 1);
		} else {
			magazine.put(s, 1);
		}
	}

	for (String s : ransom) {
		if (!magazineMap.containsKey(k) || magazineMap.get(s) < 0) {
			return false;
		} else {
			magazineMap.put(s, magazineMap.get(s) - 1);
		}
	}
	return true;
}

// PROBLEM Target Sum: there are many variations of this problem, but
// it generally looks like trying to find number(s) that sum, sub, mult
// to a specific target, and they all follow the same pattern
// SOLUTION:
// 1. We want to build a map that maps all the values in the array to how many times they appear
// 2. Next, we want to iterate through the array again, and this time we check to see if it
//    contains the target - the current value (meaning that they sum to the target)
//    and we increment our counter if so
// 3. We don't want to double count values if the target sum is even, i.e. if it's 4
//    but the set contains one 2, we would've accidentally said that's a pair, so if that's
//    the case we decrement counter by 1
// 4. We are counting each pair twice, so in the end we half the value we get.
int targetSum(int[] arr, int target) {
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	for (int i = 0; i < arr.length; i ++) {
		if (map.contains(arr[i])) {
			map.put(arr[i], map.get(arr[i]) + 1);
		} else {
			map.put(arr[i], 1);
		}
	}
	int counter = 0;

	for (int i = 0; i < arr.length; i ++) {
		if (map.contains(target - arr[i])) {
			counter += map.get(target - arr[i]);
		}
		if (target - arr[i] == arr[i]) {
			counter --;
		}
	}
	return counter/2;
} 
