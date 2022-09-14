public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private int N;

    /* CONSTRUCTOR */
    public BST() {
	this.root = null;
	this.N = 0;
    }

    /* PUBLIC METHODS */

    /***
     *insert a new (key, val) into tree
     *or replace value of existing key
     */
    public void insert(Key key, Value val) {
	//TO BE IMPLEMENTED
		Node curr = this.root;
		int count = 0;
		if (curr == null) {
			this.root = new Node(key, val);
			this.root.height = count;
			N = 1;
			return;
		}
		while (true) {
			if (key.compareTo(curr.key) < 0) {
				if (curr.left == null) {
					curr.left = new Node(key, val);
					curr.left.height = 0;
					N++;
					curr = curr.left;
					break;
				}
				else {
					curr = curr.left;
					count++;
				}
			}
			else if (key.compareTo(curr.key) > 0) {
				if (curr.right == null) {
					curr.right = new Node(key, val);
					curr.right.height = 0;
					N++;
					curr = curr.right;
					break;
				}
				else {
					curr = curr.right;
					count++;
				}
			}
			else {
				curr.val = val;
				return;
			}
		}
		Node parent = this.root;
		for (int i = count; i >= 0 ; i--) {
			if (i == parent.height) {
				parent.height++;
			}
			if (curr.key.compareTo(parent.key) < 0) {
				parent = parent.left;
			}
			else if (curr.key.compareTo(parent.key) > 0) {
				parent = parent.right;
			}
		}
    }

    
    /***
     *get the value associated with the given key;
     *return null if key doesn't exist
     */
    public Value get(Key key) {
	//TO BE IMPLEMENTED
		Node curr = this.root;
		while (curr != null) {
			if (key.compareTo(curr.key) < 0) {
				curr = curr.left;
			}
			else if (key.compareTo(curr.key) > 0) {
				curr = curr.right;
			}
			else {
				return curr.val;
			}
		}
		return null;
    }

    /***
     *return true if the tree
     *is empty and false 
     *otherwise
     */
    public boolean isEmpty() {
	return root == null;
    }

    /***
     *return the number of Nodes
     *in the tree
     */
    public int size() {
	return N;
    }

    /***
     *returns the height of the tree
     */
    public int height() {
	return height(root);
    }

    /***
     *returns the height of node 
     *with given key in the tree;
     *return -1 if the key does
     *not exist in the tree
     */
    public int height(Key key) {
	    //TO BE IMPLEMENTED
		Node curr = this.root;
		while (curr != null) {
			if (key.compareTo(curr.key) < 0) {
				curr = curr.left;
			}
			else if (key.compareTo(curr.key) > 0) {
				curr = curr.right;
			}
			else {
				return curr.height;
			}
		}
		return -1;
    }

    /***
     *return a String version of the tree
     *level by level
     */
    public String toString() {
	String str = "";
	Pair x = null;
	Queue<Pair> queue = new Queue<Pair>(N);
	int level = 0;
	queue.enqueue(new Pair(root, level));
	str += "Level 0: ";
	while(!queue.isEmpty()) {
	    x = queue.dequeue();
	    Node n = x.node;
	    if(x.depth > level) {
		level++;
		str += "\nLevel " + level + ": ";
	    }
	    if(n != null) {
		str += "|" + n.toString() + "|";
		queue.enqueue(new Pair(n.left, x.depth + 1));
		queue.enqueue(new Pair(n.right, x.depth + 1));
	    } else  
		str += "|null|";
	}
	str += "\n";
	return str;
    }
		

    /* PRIVATE METHODS */

    /***
     *return the height of x
     *or -1 if x is null
     */
    private int height(Node x) {
	if(x == null)
	    return -1;
	return x.height;
    }

    /* NODE CLASS */
    public class Node {
	Key key;
	Value val;
	Node left, right;
	int height;

	public Node(Key key, Value val) {
	    this.key = key;
	    this.val = val;
	}

	public String toString() {
	    return "(" + key + ", " + val + "): " + height;
	}
    }

    /* PAIR CLASS */
    public class Pair {
	Node  node;
	int depth;
	
	public Pair(Node node, int depth) {
	    this.node = node;
	    this.depth = depth;
	}
    }
}    