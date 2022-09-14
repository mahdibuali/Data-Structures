public class Stack<Item> {
    private int n;
    private Node first;

    //constructor: creates an empty Stack
    public Stack() {
        this.n = 0;
        this.first = null;
    }

    //adds item to the top of the Stack
    public void push(Item item) {
        this.n++;
        Node newNode = new Node();
        newNode.next = this.first;
        newNode.item = item;
        this.first = newNode;
    }

    //removes and returns the top item from the Stack
    //throw EmptyStackException if the Stack is empty
    public Item pop() throws EmptyStackException {
        if (this.n == 0) {
            throw new EmptyStackException();
        }
        Item result = this.first.item;
        this.first = this.first.next;
        this.n--;
        return result;
    }
    
    //return true if the Stack is empty, false else
    public boolean isEmpty() {
        if (this.n == 0) {
            return true;
        }
        return false;
    }

    //return the size (number of items) of the Stack
    public int size() {
        return this.n;
    }

    //return but do not remove the top item from the Stack
    //throw EmptyStackException if the Stack is empty
    public Item peek() throws EmptyStackException {
        if (this.n == 0) {
            throw new EmptyStackException();
        }
        return this.first.item;
    }

    private class Node {
	Item item;
	Node next;
    }
}