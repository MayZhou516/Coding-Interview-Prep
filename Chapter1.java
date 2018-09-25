// CHAPTER 1: ARRAYS AND STRINGS
// PROBLEM 1.1 IsUnique: implement an algorithm to determine if a string has all unique characters
// SOLUTION:
// 1. If the length of the string exceeds the num. unique characters, automatically return false
// 2. Create a boolean array, each index represents the boolean for a diff char, initialized as false
// 3. Iterate through the string, flipping the false indices to true
// 4. If it already was flipped to true, it's a duplicate, return false
// 5. If it goes through the whole array, there's no duplicates, return true
public boolean isUnique(String str) {
	if (str.length() > 128) {
		return false;
	}
	boolean[] charArr = new boolean[128];
	for (int i = 0; i < str.length(); i ++) {
		if (charArr[str.charAt[i]]) {
			return false;
		}
		charArr[str.charAt[i]] = true;
	}
	return true;
}

// SOLUTION: Repeat but use a hashset instead
public boolean isUnique(String str) {
	if (str.length() > 128) {
		return false;
	}
	HashSet<Character> charSet = new HashSet<Character>();
	for (int i = 0; i < str.length(); i ++) {
		if (charSet.get(str.charAt(i))) {
			return false;
		} else {
			charSet.put(str.charAt(i));
		}
	}
	return true;
}

// PROBLEM 1.2 Check permutation: check if two strings are permutations of one another
// SOLUTION 1: If you can use sort
// 1. Convert the array to a char array, use Java's built in sort, and return it cast as a String 
// 2. Check that they are of equal length
// 3. Sort both strings and check if they're equal to one another
String sort(String s) {
	char[] content = s.toCharArray();
	java.util.Arrays.sort(content);
	return new String(content);
}

boolean permutation(String str1, String str2) {
	if (str1.length() != str2.length()) {
		return false;
	}
	return sort(s).equals(sort(t));
}

// SOLUTION 2: If you cannot use sort
// 1. Create an array for letters in the string
// 2. Check that they are the same length
// 3. Iterate through str1 converted to a char array, incrementing the character index in the letters array
// 4. Iterate through str2, decreasing the character index, and if it reaches a negative value return false
public boolean checkPermutation(String str1, String str2) {
	int[] letterArr = new int[128];
	if (str1.length() != str2.length()) {
		return false;
	}
	char[] str1Arr = str1.toCharArray();
	for (char c : str1Arr) {
		letterArr[c]++;
	}

	for (int i = 0; i < str2.length(); i ++) {
		int c = (int) t.charAt(i);
		letterArr[c]--;
		if(letterArr[c] < 0) {
			return false;
		}
	}
	return true;
}

// SOLUTION #3: Using a hashmap
public boolean checkPermutation(String str1, String str2) {
	HashMap<Character, Integer> charIntMap = new HashMap<Character, Integer>();
	char[] str1Arr = str1.toCharArray();
	for (char c : str1Arr) {
		val = charIntMap.get(c);
		if (val != null) {
			charIntMap.put(c, val + 1);
		} else {
			charIntMap.put(c, 1);
		}
	}

	for (int i = 0; i < str2.length(); i ++) {
		val = charIntMap.get(c);
		if (val == null || val = 0) {
			return false;
		} else {
			charIntMap.put(c, val - 1);
		}
	}
}

// PROBLEM 1.4 Palindrome Permutation: given a string, check if it is a permutation of a palindrome
// SOLUTION:
// 1. Build a table, incremented so that each index represents how many characters -> numeric values exists
// 2. Uses a helper function called getCharNumber, returns the index adjusted a -> z
// 3. Checks that in this char table, that there's max one odd value, because if there's even values
//    and at most one odd value, that means that you can turn it into a permutation
boolean isPermutationOfPalindrome(String str) {
	int[] table = buildCharFrequencyTable(str);
	return checkMaxOneOdd(table);
}

boolean checkMaxOneOdd(int[] table) {
	boolean foundOdd = false;
	for (int count : table) {
		if (count % 2 == 1) {
			if (foundOdd) {
				return false;
			}
			foundOdd = true;
		}
	}
	return true;
}

int getCharNumber(Character c) {
	int a = Character.getNumericValue('a');
	int z = Character.getNumericValue('z');
	int val = Character.getNumericValue(c);
	if (a <= val && val <= z) {
		return val - a;
	}
	return -1;
}

int[] buildCharFrequencyTable(String str) {
	int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
	for (char c : str.toCharArray()) {
		int x = getCharNumber(c);
		if (x != -1) {
			table[x] ++;
		}
	}
	return table;
}

// PROBLEM 1.5 One Away: check if two strings are one or 0 edits away from each other
// An edit is defined as an insertion, deletion, or replacement
// SOLUTION:
// 1. Check to make sure that the strings are max 1 in lengtha way from each other
// 2. Get the longer and shorter string of the two inputs
// 3. Use a boolean called foundDifference, which you turn true when you find two characters being different
// 4. You can have at most one of these, so when it appears again you want to return false
// 5. If you found a difference, and they're the same length, increment the shorter index
// 6. If you didn't find a difference, regardless of different lengths, increment by 1
// 7. Always increment the longer one
boolean oneEditAway(String str1, String str2) {
	if (Math.abs(first.length() - second.length()) > 1) {
		return false;
	}

	String shorter = str1.length() < str2.length() ? str1 : str2; // shorter string
	String longer = str1.length() < str2.length() ? str2 : str1; // longer string

	int index1 = 0;
	int index2 = 0;
	boolean foundDifference = false;
	while (index2 < longer.length() && index1 < shorter.length()) {
		if (shorter.charAt(index1) != longer.charAt(index2)) {
			if (foundDifference) {
				return false;
			}
			foundDifference = true;
			if (shorter.length() == longer.length()) {
				index1++;
			}
		} else {
			index1++;
		} 
		index2++;
	}
	return true;
}

// PROBLEM 1.6 String Compression: aabcccccaaa -> a2b1c5a3 iff compressed is shorter
// SOLUTION:
// 1. Keep a counter of how many times the same letter has appeared consecutively
// 2. Either when you reach the end of the string, or the character isn't repeating anymore
//    Add onto the string the character that's repeating and the counter
// 3. Reset the counter to 0
// 4. Check that the compressed version is longer than the original
String stringCompression(String str) {
	String compressedString = "";
	int counter = 0;
	for (int i = 0; i < str.length(); i ++) {
		counter ++;
		if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
			compressedString += "" + str.charAt(i) + counter;
			counter = 0;
		}
	}
	return compressedString.length() < str.length() ? compressedString : str;
}

// PROBLEM 1.7 Rotate Matrix: NxN matrix, rotate by 90 degrees
// SOLUTION:
// 1. We want to rotate this in "layers" starting from outside in
// LMFAO TBH I have done this problem like 100x I still don't get it oh well I just memorized it
boolean rotate(int[][] matrix) {
	if (matrix.length == 0 || matrix.length != matrix[0].length()) {
		return false;
	}
	int n = matrix.length;
	for (int layer = 0; layer < n / 2; layer ++) {
		int first = layer;
		int last = n - 1 - layer;
		for (int i = first; i < last; i ++) {
			int offset = i - first;
			int top  = matrix[first][i];

			// left -> top
			matrix[first][i] = matrix[last-offset][first];

			// bottom -> left
			matrix[last - offset][first] = matrix[last][last - offset];

			// right -> bottom
			matrix[last][last - offset] = matrix[i][last];

			// top -> right
			matrix[i][last] = top;
		}
	}
}

// PROBLEM 1.8 isRotation: given the function isSubstring() which returns whether or not two strings 
// substrings of one another, and using isSubstring once, check if two strings are a
// rotation of one another. For example: APPLE and PPLEA are rotations, but APPLE and PLEPA are not
// SOLUTION:
// 1. Make sure that they're the same length
// 2. Create a new string that's the first string repeated
// 3. If it's a rotation, then the second string, s2, should appear as a substring in this repeated string
// E.x. APPLEAPPLE contains A*PPLEA*PPLE
public static boolean isRotation (String s1, String s2) {
	int s1Len = s1.length();
	int s2Len = s2.length();
	if (s1Len == s2Len && s1Len > 0 && s2Len > 0) {
		String repeat = s1 + s1;
		return isSubstring(repeat, s2);
	}
	return false;
}