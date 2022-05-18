import java.util.ArrayList;
import java.util.Collections;

public class Grid {
    public static boolean [][] bombGrid;
    public static int [][] countGrid;
    public static int numRows, numColumns, numBombs;

    public Grid() {
        this(10, 10, 25);
    }

    public Grid(int rows, int columns) {
        this(rows, columns, 25);
    }

    public Grid(int rows, int columns, int numBombs) {
        this.numRows = rows;
        this.numColumns = columns;
        this.numBombs = numBombs;

        createBombGrid();
        createCountGrid();
    }

    public static boolean[][] getBombGrid() {
        boolean [][] newBombGrid = new boolean[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            System.arraycopy(bombGrid[i],0, newBombGrid[i], 0, bombGrid[i].length);
        }

        return newBombGrid;
    }

    public static int[][] getCountGrid() {
        int [][] newCountGrid = new int[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            System.arraycopy(countGrid[i],0, newCountGrid[i], 0, countGrid[i].length);
        }

        return newCountGrid;
    }

    public static int getNumRows() {
        return numRows;
    }

    public static int getNumColumns() {
        return numColumns;
    }

    public static int getNumBombs() {
        return numBombs;
    }

    public static boolean isBombAtLocation(int row, int column){
        return (bombGrid[row][column]);
    }

    public static int getCountAtLocation(int row, int column) {
        int bombCount = 0;

        if (isBombAtLocation(row,column)){
            bombCount++;
        }

        if (row + 1 < numRows){
            if (isBombAtLocation(row+1, column))
                bombCount++;
            if (column+1 < numColumns){
                if (isBombAtLocation(row+1, column+1))
                    bombCount++;
            }
            if (column-1 >= 0){
                if (isBombAtLocation(row+1,column-1))
                    bombCount++;
            }
        }

        if (row-1 >= 0){
            if (isBombAtLocation(row-1,column))
                bombCount++;
            if (column -1 >= 0){
                if (isBombAtLocation(row-1, column -1))
                    bombCount++;
            }

            if (column+1 < numColumns){
                if (isBombAtLocation(row - 1, column + 1))
                    bombCount++;
            }
        }

        if (column+1 < numColumns){
            if (isBombAtLocation(row, column+1))
                bombCount++;
        }

        if (column - 1 >= 0){
            if (isBombAtLocation(row, column-1))
                bombCount++;
        }

        return bombCount;
    }

    public static void createBombGrid(){
        bombGrid = new boolean[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                bombGrid[i][j] = false;
            }
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < numRows*numColumns; i++) {
            list.add(i);
        }

        Collections.shuffle(list);
        for (int i = 0; i < numBombs; i++) {
            int number = list.get(i);
            int row = number / numColumns;
            int column = number % numColumns;

            bombGrid[row][column] = true;
        }
    }

    public static void createCountGrid(){
        countGrid = new int[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                countGrid[i][j] = getCountAtLocation(i,j);
            }
        }
    }

    public static void main(String[] args) {
        Grid game = new Grid();

        int rows = game.getNumRows();
        int columns = game.getNumColumns();

        boolean[][] bombGrid = game.getBombGrid();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(bombGrid[i][j] ? "T " : "F ");
            }
            System.out.println();
        }

        System.out.println();

        int[][] countGrid = game.getCountGrid();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(countGrid[i][j] + " ");
            }
            System.out.println();
        }

    }
}
