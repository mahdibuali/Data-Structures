import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Puzzle {
    private char[][] grid;
    private ArrayList table[];
    /***
     *constructor: fn is the filename
     *where the puzzle is stored
     ***/
    public Puzzle(String fn) {
	//TO BE IMPLEMENTED
        File f = new File(fn);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert s != null;
        int n = s.nextInt();
        this.grid = new char[n][n];
        this.table = new ArrayList[26];
        for (int i = 0; i < this.table.length; i++) {
            this.table[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < n; i++) {
            String l = s.next();
            for (int j = 0; j < n; j++) {
                char c = l.charAt(j);
                this.grid[i][j] = c;
                int[] p = new int[2];
                p[0] = i;
                p[1] = j;
                this.table[c - 65].add(p);
            }
        }
    }

    /***
     *search the puzzle for the given word
     *return {a, b, x, y} where (a, b) is
     *the starting location and (x, y) is 
     *the ending location
     *return null if the word can't be found
     *in the puzzle
     ***/
    public int[] search(String word) {
	//TO BE IMPLEMENTED
        int m = word.length();
        int n = this.grid.length;
        for (int i = 0; i < this.table[word.charAt(0) - 65].size(); i++) {
            int[] loc = (int[]) this.table[word.charAt(0) - 65].get(i);

            // get right
            if (loc[1] <= n - m) {
                for (int j = 1; j < m; j++) {
                    if (this.grid[loc[0]][loc[1] + j] != word.charAt(j)) {
                        break;
                    }
                    if (j == m-1) {
                        int[] x = new int[4];
                        x[0] = loc[0];
                        x[1] = loc[1];
                        x[2] = loc[0];
                        x[3] = loc[1] + j;
                        return x;
                    }
                }
            }
            //get left
            if (loc[1] >= m - 1) {
                for (int j = 1; j < m; j++) {
                    if (this.grid[loc[0]][loc[1] - j] != word.charAt(j)) {
                        break;
                    }
                    if (j == m-1) {
                        int[] x = new int[4];
                        x[0] = loc[0];
                        x[1] = loc[1];
                        x[2] = loc[0];
                        x[3] = loc[1] - j;
                        return x;
                    }
                }
            }
            //get up
            if (loc[0] >= m - 1) {
                for (int j = 1; j < m; j++) {
                    if (this.grid[loc[0] - j][loc[1]] != word.charAt(j)) {
                        break;
                    }
                    if (j == m-1) {
                        int[] x = new int[4];
                        x[0] = loc[0];
                        x[1] = loc[1];
                        x[2] = loc[0] - j;
                        x[3] = loc[1];
                        return x;
                    }
                }
            }
            //get down
            if (loc[0] <= n - m) {
                for (int j = 1; j < m; j++) {
                    if (this.grid[loc[0] + j][loc[1]] != word.charAt(j)) {
                        break;
                    }
                    if (j == m-1) {
                        int[] x = new int[4];
                        x[0] = loc[0];
                        x[1] = loc[1];
                        x[2] = loc[0] + j;
                        x[3] = loc[1];
                        return x;
                    }
                }
            }
            // right down
            if (loc[1] <= n - m && loc[0] <= n - m) {
                for (int j = 1; j < m; j++) {
                    if (this.grid[loc[0] + j][loc[1] + j] != word.charAt(j)) {
                        break;
                    }
                    if (j == m-1) {
                        int[] x = new int[4];
                        x[0] = loc[0];
                        x[1] = loc[1];
                        x[2] = loc[0] + j;
                        x[3] = loc[1] + j;
                        return x;
                    }
                }
            }
            //right up
            if (loc[1] <= n - m && loc[0] >= m - 1) {
                for (int j = 1; j < m; j++) {
                    if (this.grid[loc[0] - j][loc[1] + j] != word.charAt(j)) {
                        break;
                    }
                    if (j == m-1) {
                        int[] x = new int[4];
                        x[0] = loc[0];
                        x[1] = loc[1];
                        x[2] = loc[0] - j;
                        x[3] = loc[1] + j;
                        return x;
                    }
                }
            }
            // left down
            if (loc[1] >= m - 1 && loc[0] <= n - m) {
                for (int j = 1; j < m; j++) {
                    if (this.grid[loc[0] + j][loc[1] - j] != word.charAt(j)) {
                        break;
                    }
                    if (j == m-1) {
                        int[] x = new int[4];
                        x[0] = loc[0];
                        x[1] = loc[1];
                        x[2] = loc[0] + j;
                        x[3] = loc[1] - j;
                        return x;
                    }
                }
            }
            //left up
            if (loc[1] >= m - 1 && loc[0] >= m - 1) {
                for (int j = 1; j < m; j++) {
                    if (this.grid[loc[0] - j][loc[1] - j] != word.charAt(j)) {
                        break;
                    }
                    if (j == m-1) {
                        int[] x = new int[4];
                        x[0] = loc[0];
                        x[1] = loc[1];
                        x[2] = loc[0] - j;
                        x[3] = loc[1] - j;
                        return x;
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int i = 0;
        while (i < 100) {
            int j = i + 1;
            int c = 0;
            while (j < 100) {
                j = j *3;
                c++;
            }
            i+=3;
            System.out.println(c);
        }
    }
}