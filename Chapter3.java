// CHAPTER 3: STACKS AND QUEUES
// STACK: uses LIFO (last in first out) order, think like a stack of plates
// The first plate placed, is the last one that you would get out, and the last plate placed
// would be the first plate someone would pick up
// METHODS
// pop(): removes the top item from the stack
// push(item): adds an item to the top of the stack
// peek(): returns the top of the stack
// isEmpty(): returns true iff the stack is empty

// Implement a basic stack class
public class MyStack<T> {
	private static class StackNode<T> {
		private T data;
		private StackNOde<T> next;

		public StackNode(T data) {
			this.data = data;
		}
	}

	private StackNode<T> top;

	// Remove the top data, and return what was stored in it
	public T pop() {
		if (top == null) throw new EmptyStackException();
		T item = top.data;
		top = top.next;
		return item;
	}

	// You want to make the next element of the new element the rest of the stack
	public void push(T item) {
		StackNode<T> t = new StackNode<T>(item);
		t.next = top;
		top = t;
	}

	public T peek() {
		if (top == null) throw new EmptyStackException();
		return top.data;
	}

	public boolean isEmpty() {
		return top == null;
	}
}

// Queue: a queue implements FIFO (first in first out) ordering
// add(item): Add an item to the end of the list
// remove(): remove the first item in the list
// peek(): return the top of the queue
// isEmpty(): return true iff the queue is empty
// Often used in BFS algorithms, use a queue to store a list of nodes we need to process
// Each time we process a node, add its adjacent nodes to the end of a queue
// Allows us to process nodes in the order in which they are viewed

// Implemented with a linked list (basicaly the same thing)
// Implement a Queue
public class MyQueue<T> {
	private static class QueueNode<T> {
		private T data;
		private QueueNode<T>;

		public QueueNode(T data) {
			this.data = data;
		}
	}

	private QueueNode<T> first;
	private QueueNode<T> last;

	public void add(T item) {
		QueueNode<T> t = new QueueNode<T>(item);
		if (last != null) {
			last.next = t;
		}
		last = t;
		if (first == null) {
			first = last;
		}
	}

	public T remove() {
		if (first == null) throw new NoSuchElementException();
		T data = first.data;
		first = first.next;
		if (first == null) {
			last = null;
		}
		return data;
	}

	public T peek() {
		if (first == null) throw new NoSuchElementException();
		return first.data;
	}

	public boolean isEmpty() {
		return first == null;
	}
}

// PROBLEM 3.2 Stack Min: in addition to push and pop, has a function min which returns min element
// SOLUTION:
// 1. This class extends the original stack class we have, and implements an additional stack
// 2. Push: we make sure that we push only when the value is less than the min
//    and always push for our normal stack, so we call the super class
// 3. Pop: we pop the value off from the super class, and check to see if it's equal to min
//    if it is, then we need to pop it off the min stack as well
// 4. Min: this function gives us the min value of the stack at all times
//    When it's empty, it's set to Integer.MAX_VALUE, and when it's not, we're peeking at the top element
public class minStack extends Stack<Integer> {
	Stack<Integer> stack2;
	public minStack() {
		stack2 = new Stack<Integer>();
	}

	public void push(int value) {
		if (value <= min()) {
			stack2.push(value);
		}
		super.push(value);
	}

	public void pop() {
		int val = super.pop();
		if (val == min()) {
			stack2.pop();
		}
		return val;
	}

	public int min() {
		if (stack2.isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			return s2.peek();
		}
	}
}

// PROBLEM 3.4 Queue via Stacks: implement a MyQueue class using two stacks
// SOLUTION:
// 1. We have two stacks, one that behaves like a normal stack, and one that will behave like a queue
//    because we'll be flipping it 
// 2. Size: simply both added together
// 3. Add: add to the normal stack
// 4. Shift Stacks: we take the normal stack, and as long as the stack behaving like a queue is empty
//    we flip it over so it turns into a queue
// 5. Peek: To peek correctly, we call shift stacks then call peek as normal
// 6. Remove: to remove correctly, we call shift stacks then call pop as normal
public class MyQueue<T> {
	Stack<T> stackNewest, stackOldest;

	public MyQueue() {
		stackNewest = new Stack<T>();
		stackOldest = new Stack<T>();
	}

	public int size() {
		return stackNewest.size() + stackOldest.size();
	}

	public void add(T value) {
		stackNewest.push(value);
	}

	private void shiftStacks() {
		if (stackOldest.isEmpty()) {
			while(!stackNewest.isEmpty()) {
				stackOldest.push(stackNewest.pop());
			}
		}
	}

	public T peek() {
		shiftStacks();
		return stackOldest.peek();
	}

	public T remove() {
		shiftStacks();
		return stackOldest.pop();
	}
}

// PROBLEM 3.5 Sort Stack: sorts a stack s.t. the smallest items are on top
// You can use an additional temporary stack, but cannot use another data structure
// Stack supports push, pop, peek, and isEmpty
// SOLUTION:
// 1. Create a new stack, which will be the sorted stack
// 2. Before pushing values onto the sorted stack, we want to make sure that we pop off
//    all the values greater than the value we're pushing
// 3. Move them all back to the original stack
void sort(Stack<Integer> stack1) {
	Stack<Integer> stack2 = new Stack<Integer>();
	while(!stack1.isEmpty()) {
		int tmp = stack1.pop();
		while(!stack2.isEmpty() && stack2.peek() > tmp) {
			stack1.push(stack2.pop());
		}
		stack2.push(tmp);
	}
	while(!stack2.isEmpty()) {
		stack1.push(stack2.pop());
	}
}

// EXTRA PROBLEMS:
// PROBLEM Balanced Brackets: Check to see if the brackets in a string are balanced
// SOLUTION:
// 1. Go through and push all the brackets on the stack if they're opening facing right
// 2. When you encounter a facing left bracket, make sure that you check and see that the 
//    corresponding right facing one is at the top of the stack
// Balanced Bracketsstatic 
boolean isBalanced(String s) {
	Stack<String> bracket = new Stack<String>();
	for (int i = 0; i < s.length(); i ++) {
		if (s.charAt(i) == "(" || s.charAt(i) == "[" || s.charAt(i) == "{") {
			bracket.push(s.charAt(i));
		}

		top = bracket.pop();
		if (s.charAt(i) == ")") {
			if (top != "(") {
				return false;
			}
		}

		if (s.charAt(i) == "]") {
			if (top != "[") {
				return false;
			}
		}

		if (s.charAt(i) == "}") {
			if (top != "{") {
				return false;
			}
		}
	}

}