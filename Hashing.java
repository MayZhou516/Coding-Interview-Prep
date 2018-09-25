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

