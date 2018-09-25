// CHAPTER 2: LINKED LISTS
// Implementing a basic singly linked list as a class
class Node {
	Node next = null;
	int data;

	public Node(int d) {
		data = d;
	}

	// Iterate until n is at the end, then append the new end
	void appendToTail(int d) {
		Node end = new Node(d);
		Node n = this;
		while (n.next != null) {
			n = n.next;
		}
		n.next = end;
	}
}

// Deleting a Node from a Singly Linked List
// Given a node n, we find the previous node prev and set prev.next = n.next
Node deleteNode(Node head, int d) {
	Node n = head;

	// If the first element is the node you want to remove, simply move head over 1 node
	if (n.data == d) {
		return head.next; 
	}

	while (n.next != null) {
		// If the next node's data is == d, then skip it by setting next to next.next
		if (n.next.data == d) {
			n.next = n.next.next;
			return head;
		}
		n = n.next;
	}
	// head didnt move
	return head;
}

// The "runner" technique means that you iterate through the linked list w two pointers
// Iterating through them at different speeds allows you to move pointers through the list and figure
// out important qualities without knowing length / size
// E.x. iterating one "fast" (node.next.next) and one "slow" (node.next), allows you to find the middle
// node when the fast one reaches the end
// Linked list problems also commonly involve recursion, and can be combined with other data structures
// Use while loops more often than for loops

// PROBLEM 2.1 Remove Dups: remove duplicates from an unsorted linked list
// SOLUTION
// 1. Create a hashset that stores all the values of the linked list
// 2. If a value already exists in the set, then you want to skip it using the deletion algorithm above
void removeDups(LinkedListNode node) {
	HashSet<Integer> set = new HashSet<Integer>();
	LinkedListNode previous = null;
	while (node != null) {
		if (set.contains(node.data)) {
			previous.next = node.next;
		} else {
			set.add(node.data);
			previous = node;
		}
		node = node.next;
	}
}

// PROBLEM 2.2 Return Kth to Last: return the kth to last element in a linked list
// SOLUTION: assuming you don't know the length of the linked list
// 1. Keep traveling along the linkedlist using next until you reach the kth element
// 2. Make a new pointer that starts at the beginning, and keep the other one at the kth index
// 3. Iterate until the first pointer reaches the end
// 4. At that point, the second pointer will have reached the kth to end (since they're always k apart)
LinkedListNode kthToLast(LinkedListNode node, int k) {
	LinkedListNode first = node;
	LinkedListNode second = node;
	for (int i = 0; i < k; i ++) {
		if (first == null) {
			return null;
		}
		first = first.next;
	}
	while (first != null) {
		first = first.next;
		second = second.next;
	}
	return second;
}

// PROBLEM 2.5 Sum Lists: adds two numbers and returns the sum as a linked list
// The input lists are stored in reverse order, 1 -> 2 -> 3 = 321
// SOLUTION:
// 1. A recursive algorithm that takes in the two lists, and if we're carrying an additional 1
// 2. Base case: both lists are empty and we have no more carry elements
// 3. The value of the digit being calculated is the carry + the digit that each linkedlist is on
// 4. Set the result.data to to the result after mod by 10
// 5. As long as the linkedlists are null, recursively call the algorithm, with the next elm
//    in the linked list, and a new carry depending on if the number exceeded 10
LinkedListNode sumLists(LinkedListNode num1, LinkedListNode num2, int carry) {
	if (num1 == null && num2 == null && carry == 0) {
		return null;
	}
	LinkedListNode result = new LinkedListNode();
	int val = carry;
	if (num1 != null) {
		val += num1.data;
	}
	if (num2 != null) {
		val += num2.data;
	}

	result.data = value % 10;
	if (num1 != null || num2 != null) {
		LinkedListNode more = sumLists(num1 == null ? null : num1.next,
			num2 == null ? null : num2.next, value >= 10 ? 1 : 0);
		result.setNext(more);
	}
	return result;
}

// PROBLEM 2.6 Palindrome: check if a linked list is a palindrome
// SOLUTION:
// 1. Create two pointers, fast and slow
// 2. Iterate until the fast one reaches the end and the slow one is at the middle
//    building a stack as we iterate with the slow one
// 3. If the fast one isn't at the end, it means it's an odd numbered linked list
//    so we skip the middle node 
// 4. Pop off values from the stack and check that they match the slow pointer as it moves
boolean isPalindrome(LinkedListNode head) {
	LinkedListNode fast = head;
	LinkedListNode slow = head;

	Stack<Integer> stack = new Stack<Integer>();

	while (fast != null && fast.next != null) {
		stack.push(slow.data);
		slow = slow.next;
		fast = fast.next.next;
	}

	// If it has an odd num of elms, then we skip the middle one
	if (fast != null) {
		slow = slow.next;
	}

	while (slow != null) {
		int val = stack.pop().intValue();
		if (val != slow.data) {
			return false;
		}
		slow = slow.next;
	}
	return true;
}

// PROBLEM 2.8 Loop Detection: if there's a loop in a linked list, return the node it loops at
// SOLUTION:
// 1. Create two pointers, fast and slow
// 2. Iterate them until they collide to the same node
// 3. If they never collide, which is when they both reach the end, return null
// 4. Reset the fast pointer at the head again
// 5. When they collide they'll be at the point where the cycle begins
LinkedListNode findLoop(LinkedListNode head) {
	LinkedListNode slow = head;
	LinkedListNode fast = head;

	while (fast != null && fast.next != null) {
		slow = slow.next;
		fast = fast.next.next;
		// The two pointers meet again
		if (slow == false) {
			break;
		}
	}

	// If they never meet then there is no loop
	if (fast == null || fast.next == null) {
		return null;
	}

	// Reset slow to be at head, while fast remains at the point where the two met
	// At this point, they will meet at the point where the loop starts because they are both
	// the same distance away from that point
	slow = head;
	while (slow != fast) {
		slow = slow.next;
		fast = fast.next;
	}

	return fast;
}