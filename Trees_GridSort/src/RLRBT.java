public class RLRBT<Key extends Comparable<Key>, Value> {
    private Node root;
    private int N;

    /* CONSTRUCTOR */
    public RLRBT() {
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
            this.root.isRed = false;
            N = 1;
            return;
        }
        while (true) {
            if (key.compareTo(curr.key) < 0) {
                if (curr.left == null) {
                    curr.left = new Node(key, val);
                    curr.left.height = 0;
                    curr.left.isRed = true;
                    N++;
                    if (curr.right == null || !curr.right.isRed) {
                        curr = rotateRight(curr);
                        break;
                    }
                    else {
                        colorFlip(curr);
                        break;
                    }
                }
                else {
                    if (key.compareTo(curr.left.key) > 0) {
                        if (curr.left.right == null) {
                            curr.left.right = new Node(key, val);
                            curr.left.right.height = 0;
                            curr.left.right.isRed = true;
                            N++;
                            break;
                        }
                        else {
                            curr = curr.left;
                        }
                    }
                    else if (key.compareTo(curr.left.key) < 0) {
                        if (curr.left.left == null) {
                            curr.left.left = new Node(key, val);
                            curr.left.left.height = 0;
                            curr.left.left.isRed = true;
                            N++;
                                if (curr.left.right == null || !curr.left.right.isRed) {
                                    curr.left = rotateRight(curr.left);
                                    break;
                                }
                                else {
                                    colorFlip(curr.left);
                                    break;
                                }

                        }
                        else {
                            curr = curr.left;
                        }
                    }
                    else {
                        curr.left.val = val;
                        return;
                    }
                }
            }
            else if (key.compareTo(curr.key) > 0) {
                if (curr.right == null) {
                    curr.right = new Node(key, val);
                    curr.right.height = 0;
                    curr.right.isRed = true;
                    N++;
                    break;
                }
                else {
                    if (key.compareTo(curr.right.key) > 0) {
                        if (curr.right.right == null) {
                            curr.right.right = new Node(key, val);
                            curr.right.right.height = 0;
                            curr.right.right.isRed = true;
                            N++;
                            if (!curr.right.isRed) {
                                break;
                            }
                            else {
                                curr = rotateLeft(curr);
                                colorFlip(curr);
                                break;
                            }
                        }
                        else {
                            curr = curr.right;
                        }
                    }
                    else if (key.compareTo(curr.right.key) < 0) {
                        if (curr.right.left == null) {
                            curr.right.left = new Node(key, val);
                            curr.right.left.height = 0;
                            curr.right.left.isRed = true;
                            N++;
                            if (!curr.right.isRed) {
                                if (curr.right.right == null || !curr.right.right.isRed) {
                                    curr.right = rotateRight(curr.right);
                                    break;
                                }
                                else {
                                    colorFlip(curr.right);
                                    break;
                                }
                            }
                            else {
                                curr.right = rotateRight(curr.right);
                                curr = rotateLeft(curr);
                                colorFlip(curr);
                                break;
                            }
                        }
                        else {
                            curr = curr.right;
                        }
                    }
                    else {
                        curr.right.val = val;
                        return;
                    }
                }
            }
            else {
                curr.val = val;
                return;
            }
        }
        //System.out.println(toString());
        /*Node parent = this.root;
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
        }*/
        this.root.isRed = false;
    }
    
    /***
     *get the value associated with the given key;
     *return null if key doesn't exist
     */
    public Value get(Key key) {
	//COPY FROM BST
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

    public Node findParent(Node node) {
        //COPY FROM BST
        Node curr = this.root;
        while (curr != null) {
            if (node.key.compareTo(curr.key) < 0) {
                if (curr.left.key.compareTo(node.key) == 0) {
                    return curr;
                }
                curr = curr.left;
            }
            else if (node.key.compareTo(curr.key) > 0) {
                if (curr.right.key.compareTo(node.key) == 0) {
                    return curr;
                }
                curr = curr.right;
            }
            else return null;
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
     *return String representation of tree                                      
     *level by level                                                            
     */
    public String toString() {
        String ret = "Level 0: ";
        Pair x = null;
        Queue<Pair> queue = new Queue<Pair>(N);
        int level = 0;
        queue.enqueue(new Pair(root, level));
	while(!queue.isEmpty()) {
            x = queue.dequeue();
            Node n = x.node;
            if(x.depth > level) {
                level++;
                ret += "\nLevel " + level + ": ";
            }
            if(n != null) {
                ret += "|" + n.toString() + "|";
                queue.enqueue(new Pair(n.left, x.depth + 1));
                queue.enqueue(new Pair(n.right, x.depth + 1));
            } else
                ret += "|null|";
        }
        ret += "\n";
	return ret;
    }


    /***
     *return the black height of the tree
     */
    public int blackHeight() {
	//TO BE IMPLEMENTED
        return 0;
    }
		
    /* PRIVATE METHODS */

    /***
     *swap colors of two Nodes
     */
    private void swapColors(Node x, Node y) {
	if(x.isRed == y.isRed)
	    return;
	boolean temp = x.isRed;
	x.isRed = y.isRed;
	y.isRed = temp;
    }

    /***
     *rotate a link to the right
     */
    private Node rotateRight(Node x) {

	Node temp = x.left;
        if (x == this.root) {
            this.root= temp;
        }
	x.left = temp.right;
	temp.right = x;
	swapColors(x, temp);
	x.height = Math.max(height(x.left), height(x.right)) + 1;
	temp.height = Math.max(height(temp.left), x.height) + 1;
	return temp;
    }

    /***
     *rotate a link to the left
     */
    private Node rotateLeft(Node x) {

	Node temp = x.right;
        if (x == this.root) {
            this.root= temp;
        }
	x.right = temp.left;
	temp.left = x;
	swapColors(x, temp);
	x.height = Math.max(height(x.left), height(x.right)) + 1;
	temp.height = Math.max(height(temp.right), x.height) + 1;
	return temp;
    }

    /***
     *color flip
     */
    private Node colorFlip(Node x) {
	if(x.left == null || x.right == null)
	    return x;
	if(x.left.isRed == x.right.isRed) {
	    x.left.flip();
	    x.right.flip();
	    x.flip();
	}
	return x;
    }

    /***
     *return the neight of Node x
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
	boolean isRed;

	public Node(Key key, Value val) {
	    this.key = key;
	    this.val = val;
	    this.isRed = true;
	}
	
	public String toString() {
	    return "(" + key + ", " + val + "): " 
		+ height + "; " + (this.isRed?"R":"B");
	}

	public void flip() {
	    this.isRed = !this.isRed;
	}
    }

    /* PAIR CLASS */
    public class Pair {
	Node node;
	int depth;
	
	public Pair(Node node, int depth) {
	    this.node = node;
	    this.depth = depth;
	}
    }
}    