// Chapter 4: Trees and Graphs
// Trees: root node, each root node as 0 + child nodes, each child has nodes, etc
// Tree cannot contain cycles, cannot have links to parent nodes
// Simple class definition for a Node:
class Node {
	public String name;
	public node[] children;
}
// Binary Tree vs. Binary Search Tree
// BST: bianry tree in which every node fits a specific ordering property
// all left decendants <= n < all right descendants

// Pre-Order traversal
void preOrder(TreeNode node) {
	if (node != null) {
		preOrder(node.left);
		preOrder(node.right);
		visit(node);
	}
}

// In-Order traversal
void inOrder(TreeNode node) {
	if (node != null) {
		inOrder(node.left);
		visit(node);
		inOrder(node.right);
	}
}

// Post-Order traversal
void postOrder(TreeNode node) {
	if (node != null) {
		visit(node);
		postOrder(node.left);
		postOrder(node.right);
	}
}

// Graphs: a collection of nodes with edges between (some of) them
// 1. Can be either directed or undirected
// 2. Graph might consist of multiple isolated subgraphs
// 3. If there is a path between every pair of verticies, it is called a "connected graph"
// 4. A graph can also have cycles (or not). An "acyclic graph" is one without cycles

// Adjacency list: most common way to represent a graph
// Every vertex (or node) stores a lsit of adjacent verticies
class Graph {
	public Node[] nodes;
}
class Node {
	public String name;
	public Node[] children;
}

// Graph Search: DFS / BFS
// DFS is preferred if we want to visit every node in the graph
// BFS is generall better to find the shortest path

// DFS Pseudocode: pre-order, etc are forms of DFS, recursive
// 1. Base case: if the root is null
// 2. Set current node as visited
// 3. Going through the adjacent nodes, if you haven't visited them, keep visiting and searching
//    treating it as the root
void search(Node root) {
	if (root == null) return;
	visit(root);
	root.visited = true;
	for each (node n in root.adjacent) {
		if (n.visited == false);
		search(n); // recursively search as long as you haven't visited
	}
}

// BFS Pseudocode: not recurisve, uses a queue
// 1. Create a queue and put the root into it
// 2. Iterate through this queue, and during each iteration
// 3. Look at the most current node, and visit it
// 4. For each of the nodes that are adjacent, and haven't been visit it, visit it and add to queue
void search(Node root) {
	Queue queue = new Queue();
	root.marked = true;
	queue.enqueue(root);

	while (!queue.isEmpty()) {
		Node r = queue.dequeue();
		visit(r);
		for each (Node n in r.adjacent) {
			if (n.marked == false) {
				n.marked = true;
				queue.enqueue(n);
			}
		}
	}
}

// PROBLEM 4.1 Route Between Nodes: Given a directed graph, is there a route between two nodes
// SOLUTION
// 1. Generate an enum, so that we can keep track of the states that nodes in the graph are in
// 2. Create a queue using a LinkedList
// 3. Set all of the nodes in the graph to unvisited
// 4. Add the first state to the queue, and do BFS
// 5. Same as BFS< remove the first element, iterate through the adjacent, if the node
//    is ever the same node as the ending node, return true, else you want to keep visiting
// 6. If you iterate through the whole loop and never reach end, then return false
enum State { Unvisited, Visited, Visiting; }

boolean search(Graph g, Node start, Node end) {
	if (start == end) {
		return true;
	} 
	LinkedList<Node> queue = new LinkedList<Node>();

	for (Node u : g.getNodes()) {
		u.state = State.Unvisited;
	}

	start.state = State.Visiting;
	queue.add(start);
	Node u;
	while(!queue.isEmpty()) {
		u = queue.removeFirst();
		if (u != null) {
			for (Node v : u.getAdjacent()) {
				if (v.state == State.Unvisited) {
					if (v == end) {
						return true;
					} else {
						v.state = State.Visiting;
						queue.add(v);
					}
				}
			}
			u.state = State.visited
		}
	}
	return false;
}

// PROBLEM 4.2 Minimal Tree: given a sorted array w unique integer elements
// create a BST with minimal height
// SOLUTION
// 1. The base case is when the end of the array is no longer less than the beginning of the array
// 2. Find the middle index of the array, and set this as a new node
// 3. The right and left children nodes are the recursive calls by changing start and end
//    to reflect on the array
TreeNode createMinBST(int arr[]) {
	return createMinBST(array, 0, array.length - 1);
}

TreeNode createMinBST(int arr[], int start, int end) {
	if (end < start) {
		return null;
	}
	int mid = (start + end)/2;
	TreeNode n = newTreNode(arr[mid]);
	n.left = createMinBST(arr, start, mid - 1);
	n.right = createMinBEST(arr, mid + 1, end);
	return n;
}

// PROBLEM 4.3 List of Depths: creates a linked list of all nodes at each depth
// SOLUTION:
// 1. Create a linkedlist of tree nodes to host the current node, and add the root to it
// 2. While there are still elements in current, add current to the resulting arraylist 
// 3. Set the current to the parent, and iterate through these parents
// 4. For each parent that has a left and a right child, add this to current
ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
	ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
	LinkedList<TreeNode> current = new LinkedList<TreNode>();
	if (root != null) {
		current.add(root);
	}

	while(current.size() > 0) {
		result.add(current);
		LinkedList<TreeNode> parent = current;
		current = new LinkedList<TreeNode>();
		for (TreeNode parent: parents) {
			if (parent.left != null) {
				current.add(parent.left);
			}
			if (parent.right != null) {
				current.add(parent.right);
			}
		}
	}
	return result;
}

// PROBLEM 4.4 Check Balanced: check if a binary tree is balanced
// Balanced Tree: heights of the two subtrees of any node never differ by more than 1
// SOLUTION: 
// 1. Create a helper function getHeight that returns the height of the tree
// 2. getHeight recursively increments by 1 on the left and right hand side, base case root == null
// 3. Recursive function to check balanced, base case is root == null again, returns true in this step
// 4. Calculate the height difference with getHeight
// 5. If the heightDiff exceeds 1, return false, and if it doesn't, check balanced recursively
//    on the left and right hand subtrees
int getHeight(TreeNode root) {
	if (root == null) {
		return -1;
	}
	return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
}

boolean isBalanced(TreeNode root) {
	if (root == null) {
		return true;
	}

	int heightDiff = getHeight(root.left) - getHeight(root.right);
	if (Math.abs(heightDiff) > 1) {
		return false;
	} else {
		return isBalanced(root.left) && isBalanced(root.right);
	}
}

// PROBLEM 4.5 Validate BST: check if a binary tree is a binary search tree
// SOLUTION:
// 1. Base case: the node involved == null, in which case returns true
// 2. If there's a min value, and the data is less than the min OR 
//    there's a max value, and the data is great than the max, we return false because
//    this violates our definition of a BST
// 3. Recursively call checkBST on the left with the min being the current min, and the max
//    value being the node that we're currently on, and on the right with the min value 
//    being the node that we're currently on, and the max
// 4. Basically, if the left hand side doesn't meet the definition of having values < current node
//    or the right hand side doesn't meeting the definition of having values > current node
//    we return false
boolean checkBST(TreeNode n) {
	return checkBST(n, null, null);
}

boolean checkBST(TreeNode n, Integer min, Integer max) {
	if (n == null) {
		return true;
	}
	if ((min != null && n.data <= min) || (max != null && n.data > max)) {
		return false;
	}
	if (!checkBST(n.left, min, n.data) || !checkBST(n.right, n.data, max)) {
		return false;
	}
	return true;
}

// PROBLEM 4.6 Successor: find the successor of a given node in a BST
// You may assume that each node has a link to its parent
// SOLUTION:
// 1. Base case: if the node is null, then we return null
// 2. If the right hand side is not null, then we have to return the left most child on the right hand side
// 3. Helper function for finding the left most child: 
// 	  Base case: n == null, return null
//    While there exists a left node, keep on moving n to the left, and when n is null return n
// 4. If the right hand side is null, then we want to return the upmost left hand side child
// 5. This is done by going to the node's parent node, and as long as the left node is not
//    the same as the current node and the parent isn't null, we update the current node
//    to the parent node, and the parent node to the parent's parent, return the node outside of while
TreeNode inOrderSucc(TreeNode n) {
	if (n == null) {
		return null;
	}

	if (n.right != null) {
		return leftMostChild(n.right);
	} else {
		TreeNode q = n;
		TreeNode x = q.parent;
		while (x != null && x.left != q) {
			q = x;
			x = x.parent;
		}
		return x;
	}
}

TreeNode leftMostChild(TreeNode n) {
	if (n == null) {
		return null;
	}
	while (n.left != null) {
		n = n.left;
	}
	return n;
}

// PROBLEM 4.8 First Common Ancestor: find the first common ancestor of two nodes in a binary tree
// SOLUTION 1: If you have links to parents
// 1. Calculate the difference in depth between the two nodes, using helper function
// 2. Depth is implemented by having a counter and going up to parents until parent is null
// 3. Taking the deeper node, we want to go up until they reach the same depth
// 4. The helper function to go up uses the depth difference, keeps on going up to parents
//    and decrementing depth until it's 0, returning the node it remains on
// 5. While the two nodes aren't the same node, it will keep on going up via parents until they do
// 6. They either reach the same node, or one of them is null, if either are null return null
//    and if not, you can return either node
TreeNode commonAncestor(TreeNode p, TreeNode q) {
	int depthDiff = depth(p) - depth(q);
	TreeNode shallower = depthDiff > 0 ? q : p;
	TreeNode deeper = depthDiff > 0 ? p : q;
	deeper = goUpBy(deeper, Math.abs(depthDiff));

	while (shallower != deeper && shallower != null && deeper != null) {
		shallower = shallower.parent;
		deeper = deeper.parent;
	}
	return deeper == null || shallower == null ? null : shallower;
}

TreeNode goUpBy(TreeNode node, int depthDiff) {
	while (depthDiff > 0 && node != null) {
		node = node.parent;
		depthDiff --;
	}
	return node;
}

int depth(TreeNode node) {
	int depth = 0;
	while (node != null) {
		node = node.parent;
		depth ++;
	}
	return depth;
}

// SOLUTION #2: Cannot access parents
// 1. Use the helper function covers, which when it takes in root and the nodes 
//    we're checking will make sure that both nodes are in the same tree
// 2. Use a helper function, which takes root, and the two nodes that you're checking
//    Base case: if the root is null, or it's one of the two nodes, return root
// 3. Calls covers on the left hand side of root, and node 1, as well as root and node 2
// 4. Since covers returns a boolean, if it returns false for both, it means 
//    both are on the right hand side, if it returns true for both, it means both 
//    are on the right hand side
// 5. If it returns values that don't equal each other, it must mean the current
//    node, root node, is the one that is the ancestor
// 6. If not, we want to see if we're traversing the right or left side of the nodes
// 7. Recursively call this helper on the side we just got from (6), and the two nodes
TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	if (!covers(root, p) || !covers(root, q)) {
		return null;
	}
	return ancestorHelper(root, p, q);
}

TreeNode ancestorHelper(TreeNode root, TreeNode p, TreeNode q) {
	if (root == null || root == p || root == q) {
		return root;
	}

	boolean pIsOnLeft = covers(root.left, p);
	boolean qIsOnLeft = covers(root.left, q);
	if (pIsOnLeft != qIsOnLeft) {
		return root;
	}
	TreeNode childSide = pIsOnLeft ? root.left : root.right;
	return ancestorHelper(childside, p, q);
}

boolean covers(TreeNode root, TreeNode p) {
	if (root == null) {
		return false;
	} 
	if (root == p) {
		return true;
	}

	return covers(root.left, p) || covers(root.right, p);
}

// PROBLEM 4.12 Paths with Sum: given a binary tree where nodes are integer values (postive & negative)
// Count the number of paths that sum to a given value
// 1. We want to keep track of the node, target sum, sum at the moment, and a hashmap that keeps 
//    count of all the paths
// 2. Base case: node is null, so we return that there are 0 paths
// 3. We update the sum at the moment with the node's value
// 4. The sum we're trying to find how is the sum at the moment - target
// 5. Our total path count is the hashmaps's value with key being the sum from (4) or 0
// 6. If our current sum is equal to our target, we will increment our total path count
// 7. Call on helper function to increment the hashtable
// 8. The helper is implementedby taking in a hashtable, key, new number, updates hashtable
// 9. Recursively call the main function on the left and right hand sides of the tree
//    then increment the hashmap again
int countPathsWithSum(TreeNode root, int targetSum) {
	return countPathsWithSum(root, targetSum, 0, new HashMap<Integer, Integer>());
}

int countPathsWithSum(TreeNode node, int targetSum, int runningSum, HashMap<Integer, Integer> pathCount) {
	if (node == null) {
		return 0;
	}
	runningSum += node.data;
	int sum = runningSum - targetSum;
	int totalPaths = pathCount.getOrDefault(sum, 0);

	if (runningSum == targetSum) {
		totalPaths ++;
	}

	incrementHashTable(pathCount, runningSum, 1);
	totalPaths += countPathsWithSum(node.left, targetSum, runningSum, pathCount);
	totalPaths += countPathsWithSum(node.right, targetSum, runningSum, pathCount);
	incrementHashTable(pathCount, runningSum, -1);

	return totalPaths;
}

void incrementHashTable(HashMap<Integer, Integer> hashTable, int key, int diff) {
	int newCount = hashTable.getOrDefault(key, 0) + diff;
	if (newCount == 0) {
		hashTable.remove(key);
	} else {
		hashTable.put(key, newCount);
	}
}