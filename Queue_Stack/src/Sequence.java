
public class Sequence {
    private Grid grid;
    private Stack<Loc> path;//stores the sequence path
    private boolean found;
    private int[][] visited;

    //constructor: create a new Sequence for the 
    //specified grid
    public Sequence(Grid grid) {
	this.grid = grid;
	this.path = new Stack<Loc>();
	this.found = false;
	this.visited = new int[grid.size()][grid.size()];
    }

    //resets the grid and the path
    public void reset(Grid grid) {
	this.grid = grid;
	this.path = new Stack<Loc>();
	this.found = false;
	this.visited = new int[this.grid.size()][this.grid.size()];
    }

    //(i, j)  is the starting location
    //val is the value that should end the sequence
    public void getSeq(int i, int j, int val) throws EmptyStackException {
	    //To be implemented
        if ((i >= this.grid.size()) || (j >= this.grid.size())) {
            return;
        }
        //System.out.println("n = "+this.grid.size());
        this.path.push(this.grid.getLoc(i, j));
        //System.out.printf("i = %d, j = %d\n", i, j);
        this.visited[i][j] = 1;
        if (this.grid.getLoc(i, j).val == val) {
            this.found = true;
            return;
        }
        if ((this.grid.getLoc(i-1, j) != null) &&
                (this.grid.getLoc(i-1, j).val == this.grid.getLoc(i, j).val +1) &&
                (this.visited[i-1][j] == 0)) {
            getSeq(i-1, j, val);
        }
        if (this.found) {
            return;
        }
        if ((this.grid.getLoc(i, j+1) != null) &&
                (this.grid.getLoc(i, j+1).val == this.grid.getLoc(i, j).val +1) &&
                (this.visited[i][j+1] == 0)) {
            getSeq(i, j+1, val);
        }
        if (this.found) {
            return;
        }
        if ((this.grid.getLoc(i+1, j) != null) &&
                (this.grid.getLoc(i+1, j).val == this.grid.getLoc(i, j).val +1) &&
                (this.visited[i+1][j] == 0)) {
            getSeq(i+1, j, val);
        }
        if (this.found) {
            return;
        }
        if ((this.grid.getLoc(i, j-1) != null) &&
                (this.grid.getLoc(i, j-1).val == this.grid.getLoc(i, j).val +1) &&
                (this.visited[i][j-1] == 0)) {
            getSeq(i, j-1, val);
        }
        if (this.found) {
            return;
        }
        this.path.pop();
    }

    //return a String representation of the sequence
    //starting at the starting location and listing
    //all locations in the sequence in order
    public String toString() {
	    //To be implemented
        String x = "";
        while (!this.path.isEmpty()) {
            try {
                x = this.path.pop().toString() + x;
            } catch (EmptyStackException e) {
                e.printStackTrace();
            }
        }
        return x;
    }
}