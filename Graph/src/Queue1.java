public class Queue1<Item> {
    private Item[] queue;
    private int front;
    private int back;
    private int initial;

    //constructor: create an empty Queue with initial
    //capacity of 1
    public Queue1() {
        //To be implemented
        this.queue = (Item[]) new Object[1];
        this.front = 0;
        this.back = 0;
        this.initial = 1;
    }

    //constructor: create an empty Queue with initial
    //capacity of n
    public Queue1(int n) {
        //To be implemented
        this.queue = (Item[]) new Object[n];
        this.front = 0;
        this.back = 0;
        this.initial = n;
    }

    //add an item to the back of the Queue
    //double the array capacity if the Queue is full
    public void enqueue(Item item) {
        //To be implemented
        if ((this.queue[this.front] == null) || (this.front != this.back)) {
            this.queue[this.back % this.queue.length] = item;
            this.back = (this.back + 1) % this.queue.length;
        }
        else {
            Item[] newArray = (Item[]) new Object[2 * this.queue.length];
            for (int i = 0; i < this.queue.length; i++) {
                newArray[i] = this.queue[(this.front + i) % this.queue.length];
            }
            newArray[this.queue.length] = item;
            this.back = (this.queue.length + 1) % newArray.length;
            this.queue = newArray;
            this.front = 0;
        }
        //this.print();
    }

    //remove and return the front item from the Queue
    //throw EmptyQueueException if the Queue is empty
    //reduce the array capacity by half if the size
    //of the Queue falls below 1/4 full
    public Item dequeue() throws EmptyQueueException {
        //To be implemented
        if (this.queue[this.front] == null) {
            throw new EmptyQueueException();
        }
        Item result = this.queue[this.front];
        this.queue[this.front] = null;
        this.front = (this.front + 1) % this.queue.length;
        if ((this.size() <= (this.queue.length / 4)) && (this.queue.length > this.initial)) {
            Item[] newArray = (Item[]) new Object[this.queue.length / 2];
            for (int i = 0; i < this.size(); i++) {
                newArray[i] = this.queue[(this.front + i) % this.queue.length];
            }
            this.back = this.size();
            this.queue = newArray;
            this.front = 0;
        }
        return result;
    }

    //return true if the Queue is empty
    //return false if the Queue is not empty
    public boolean isEmpty() {
        //To be implemented
        if (this.queue[this.front] == null) {
            return true;
        }
        return false;
    }

    //return the size of the Queue (i.e. the
    //number of elements currently in the Queue)
    public int size() {
        //To be implemented
        if (this.queue[this.front] == null) {
            return 0;
        }
        if (this.front < this.back) {
            return this.back - this.front;
        }
        return  this.queue.length - (this.front - this.back);
    }

    //return but do not remove the front item in the Queue
    //throw an exception if the Queue is empty
    public Item peek() throws EmptyQueueException {
        //To be implemented
        if (this.queue[this.front] == null) {
            throw new EmptyQueueException();
        }
        return this.queue[this.front];
    }

    //return the underlying array
    public Item[] getArray() {
        return queue;
    }

    public Item value(int i) {
        return this.queue[i];
    }

    public int length() {
        return this.queue.length;
    }

}