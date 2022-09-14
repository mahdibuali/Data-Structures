import java.util.Arrays;

public class SortGrid {
    private static int compares = 0;
    private static int[][] grid;

    /***PUBLIC METHODS***/
    public static int sort(int[][] thisGrid) {
	compares = 0;
	//TO BE IMPLEMENTED
        grid = thisGrid;
        int n = thisGrid.length;
        //sort1(0, n * n -1);
        for (int x = 0; x < n; x++) {
            for (int i = 1; i < n; i++) {
                int j = i - 1;
                int k = i;
                while (j >= 0 && greaterThan(x, j, x, k)) {
                    swap(x, j, x, k);
                    j--;
                    k--;
                }
            }
        }
        int[][] grid2 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid2[i][j] = grid[i][j];
            }
        }
        for (int t = 0; t < n-1; t++) {

            int i = 0;
            int j = (t+1)*n;
            int first = 0;
            while (first <= (t+2)*n - 1) {
                if (j > (t+2)*n - 1 || (i <= (t+1)*n - 1 && !greaterThan(i / n, i % n, j / n, j % n))) {
                    grid2[first/n][first %n] = grid[i/n][i % n];
                    i++;
                } else {
                    grid2[first/n][first %n] = grid[j / n][j % n];
                    j++;
                }
                first++;
            }
            for (int s = 0; s < first/n; s++) {
                for (int o = 0; o < n; o++) {
                    grid[s][o] = grid2[s][o];
                }
            }
        }

        /*for (int i = 1; i < n * n; i++) {
            int j = i - 1;
            int k = i;
            while (j >= 0 && greaterThan(j / n, j % n, k / n, k % n)) {
                swap(j/n, j % n, k/n, k % n);
                j--;
                k--;
            }
        }*/
	return compares;
    }

    public static void merge(int f, int m, int e) {
        int n = grid.length;
        int[][] grid2 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid2[i][j] = grid[i][j];
            }
        }
        int first = f;
        int i = f;
        int j = m;
        while (first <= e) {
            if (j > f || (i < m && !greaterThan(i / n, i % n, j / n, j % n))) {
                grid2[first/n][first %n] = grid[i/n][i % n];
                i++;
            } else {
                grid2[first/n][first %n] = grid[j / n][j % n];
                j++;
            }
            first++;
        }
        for (int s = 0; s < n; s++) {
            for (int t = 0; t < n; t++) {
                grid[s][t] = grid2[s][t];
            }
        }
    }

    public static void sort1(int f, int e) {
        if (f < e) {
            int m = (f + e) / 2;
            sort1(f, m-1);
            sort1(m, e);
            merge(f, m, e);
        }
    }

    public static void main(String[] args) {
        int[][] x = {{4, 1, 0, 2, 8}, {10, 3, 24, 2, 8}, {9, 0, 37, 29, 3}, {43, 22, 7, 11, 0}, {5, 67, 3, 2, 0}};
        sort(x);
        for (int i = 0; i < x.length; i++) {
            System.out.println(Arrays.toString(x[i]));
        }
    }

    /*** HELPER METHODS ***/
    //returns true if value at (r1, c1) is less
    //than value at (r2, c2) and false otherwise;
    //counts as 1 compare
    private static boolean lessThan(int r1, int c1, int r2, int c2) {
	compares++;
	if(grid[r1][c1] < grid[r2][c2])
	    return true;
	return false;
    }

    //returns true if value at (r1, c1) is greater than
    //value at (r2, c2) and false otherwise;
    //counts as 1 compare
    private static boolean greaterThan(int r1, int c1, int r2, int c2) {
	compares++;
        if(grid[r1][c1] > grid[r2][c2])
	    return true;
	return false;
    }
    
    //swaps values in the grid
    //at (r1, c1) and (r2, c2);
    //assumes that the parameters
    //are within the bounds
    private static void swap(int r1, int c1, int r2, int c2) {
	int temp = grid[r1][c1];
	grid[r1][c1] = grid[r2][c2];
	grid[r2][c2] = temp;
    }
}
