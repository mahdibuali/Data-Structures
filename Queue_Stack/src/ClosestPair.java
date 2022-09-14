public class ClosestPair {
    private Grid grid;
    private Queue<Loc> queue;
    private int[][] visited;

    //constructor: create a new ClosestPair object for grid
    public ClosestPair(Grid grid) {
	this.grid = grid;
	this.queue = new Queue<Loc>();
	this.visited = new int[this.grid.size()][this.grid.size()];
    }

    //search for the closest location with a value 
    //that is equal to the value at start
    //return null if no match is found
    public Loc search(int x, int y) {
	//To be implemented
        if ((x >= this.grid.size()) || (y >= this.grid.size()) || (this.grid.getLoc(x, y) == null)) {
            return null;
        }
        int val = this.grid.getLoc(x, y).val;
        this.queue.enqueue(this.grid.getLoc(x, y));
        this.visited[x][y] = 1;
        while (!this.queue.isEmpty()) {
            Loc curr = null;
            try {
                curr = this.queue.dequeue();
            } catch (EmptyQueueException e) {
                e.printStackTrace();
            }
            Loc up = this.grid.getLoc(curr.row - 1, curr.col);
            Loc right = this.grid.getLoc(curr.row, curr.col + 1);
            Loc down = this.grid.getLoc(curr.row + 1, curr.col);
            Loc left = this.grid.getLoc(curr.row, curr.col - 1);
            if ((up != null) && (this.visited[up.row][up.col] == 0)) {
                if (up.val == val) {
                    return up;
                }
                else {
                    this.queue.enqueue(up);
                    this.visited[up.row][up.col] = 1;
                }
            }
            if ((right != null) && (this.visited[right.row][right.col] == 0)) {
                if (right.val == val) {
                    return right;
                }
                else {
                    this.queue.enqueue(right);
                    this.visited[right.row][right.col] = 1;
                }
            }
            if ((down != null) && (this.visited[down.row][down.col] == 0)) {
                if (down.val == val) {
                    return down;
                }
                else {
                    this.queue.enqueue(down);
                    this.visited[down.row][down.col] = 1;
                }
            }
            if ((left != null) && (this.visited[left.row][left.col] == 0)) {
                if (left.val == val) {
                    return left;
                }
                else {
                    this.queue.enqueue(left);
                    this.visited[left.row][left.col] = 1;
                }
            }
        }
        return null;
    }
}
	