// Chapter 8: Recursion and Dynamic Programming
// If a problem can be built of subproblems, it's usually recursive
// 1. Design an algorithm to compute the nth
// 2. Write code to list the first n
// 3. Implement a method to compute all
// Bottom up approach: knowing how to solve the problem for a simple case, like a list with only 1 elem
// Top down approach: Divide the problem into n subproblems
// Half and half approach: divide the data set in half (i.e. binary search)
// Recursive vs. Iterative Solutions
// Recursive algorithms are very space inefficient, each recursive call adds a new layer to the stack, @ least O(n) memory

// Dynamic Programming & Memoization
// Taking a recursive algorithm and finding the overlapping subproblems
// Fibonacci Numbers
// Runtime: since we are making two calls to fib (drawing out the tree gives us 2 branches per node)
// the runtime is O(2^n)
int fib(int i) {
	if (i == 0) return 0;
	if (i == 1) return 1;
	return fib(i - 1) + fib(i - 2);
}
// If you look at the recursion tree, you see lots of identical nodes
// When we call fib(n) we shouldn't have to do much more than O(n) calls, since there's only O(n) possible values
// Runtime O(n)
int fib(int n) {
	return fib(n, new int[n + 1]);
}

int fib(int i, int[] memo) {
	if (i == 0 || i == 1) return i;

	if (memo[i] == 0) {
		memo[i] = fib(i - 1, memo) + fib (i - 2, memo);
	}
	return memo[i]
}

// PROBLEM 3.1: Triple step: child can hop up 1, 2, or 3 steps up the stairs
// How many ways can a child run up k stairs
// SOLUTION 1: Recursive
// 1. Base cases are when there's 0 steps, or 1 step
// 2. Recursively sum when you take 1, 2, or 3 steps
int tripleStep(int k) {
	if (k < 0) {
		return 0;
	} else if (k == 0) {
		return 1;
	} else {
		return tripleStep(n - 1) + tripleStep(n - 2) + tripleStep(n - 3);
	}
}

// SOLUTION 2: Memoized triple step
// 1. Start by putting -1 for all the values in an array of size n + 1
// 2. Base case are the same as before
// 3. If the there's already a value other than -1 there, that means that we've
//    already calculated up to that many steps the different variations, so return that value
//    from the same index value in the memoized array
// 4. If there isn't a value there yet, we want to calculate it recursively, then return it
int tripleStep(int n) {
	int[] memo = new int[n + 1];
	Arrays.fill(memo, -1);
	return tripleStep(n, memo);
}

int tripleStep(int n, int[] memo) {
	if (n < 0) {
		return 0;
	} else if (n == 0) {
		return 1;
	} else if (memo[n] > -1) {
		return memo[n];
	} else {
		memo[n] = tripleStep(n - 1, memo) + tripleStep(n - 2, memo) + tripleStep(n - 3, memo);
		return memo[n];
	}
}

// Problem 8.3 Magic Index: a magic index is defined s.t. A[i] = i
// Given a sorted array of distinct integers, write a method to find a magic index, 
// SOLUTION: similar to Binary Search
// 1. Base case: if we've reached the end, so end < start, we want to return that there's no such index
// 2. Find the middle point
// 3. If the magic index is greater than mid, we search in the first half of the array recursively
// 4. If the magic index is less than mid, we search in the second half of the array recursively
int magicFast(int[] array) {
	return magicFast(array, 0, array.length - 1);
}

int magicFast(int[] array, int start, int end) {
	if (end < start) {
		return -1;
	}
	int mid = (start + end) / 2;
	if (array[mid] == mid) {
		return mid;
	} else if (arr[mid] > mid) {
		return magicFast(array, start, mid - 1);
	} else {
		return magicFast(array, mid + 1, end)
	}
}

// PROBLEM 8.4 Power Set: Return all subsets of a set
// SOLUTION:
// 1. Base case: adding the empty set to the set of all subsets
// 2. Basically, each time we add a new element to a subset, we have all the possible combinations from
//    the previous index, along with the new ones
//    E.x. {a, b, c} has all from {a, b} = {}, {a}, {b}, {ab} ALONG with all the ones with c added
//         {c}, {a, c}, {b, c}, {a, b, c}
ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index) {
	ArrayList<ArrayList<Integer>> allSubsets;
	if (set.size() == index) {
		allSubsets = new ArrayList<ArrayList<Integer>>();
		allSubsets.add(new ArrayList<Integer>());
	} else {
		allSubsets = getSubsets(set, index + 1);
		int item = set.get(index);
		ArrayList<ArrayList<Integer>> moreSubsets = new ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer>> subset : allSubsets) {
			ArrayList<Integer> newSubset = new ArrayList<Integer>();
			newSubset.addAll(subset);
			newSubset.add(item);
			moreSubsets.add(newSubset);
		}
		allSubsets.addAll(moreSubsets);
	}
	return allSubsets;
}

// PROBLEM 8.7 Permutations without dups: all permutations of a string of unique characters
// SOLUTION: 
// 1. Base case: str == null, and if it's the empty string, adding that to the set of permutations
// 2. Similar to previous algorithm
ArrayList<String> getPerms(String str) {
	if (str == null) {
		return null;
	}
	ArrayList<String> permutations = new ArrayList<String>();
	if (str.length() == 0) {
		permtations.add("");
		return permutations;
	}

	char first = str.charAt(0);
	String remainder = str.substring(1);
	ArrayList<String> words = getPerms(remainder);
	for (String word : words) {
		for (int j = 0; j <= word.length(); j++) {
			String s = insertCharAt(word, first, j);
			permutations.add(s);
		}
	}
}

String insertCharAt(String word, char c, int i) {
	String start = word.substring(0, i);
	String end = word.substring(i);
	return start + c + end;
}