package code.Player;

import code.Cell;
import code.Grid;
import code.TicTacToe;
import code.Windows.Circle;

import java.util.ArrayList;

public class AiRandom extends Player {

    public AiRandom(Grid grid, Circle circle, TicTacToe ticTacToe) {
        super(grid, circle, ticTacToe);
    }

    //Placement du pion de manière aléatoire
    @Override
    public void play() {
        long tempsDebut = System.currentTimeMillis();
        ArrayList<Cell> possibleMooves = getPossibleMooves();
        int indexOfRandomCell = getRandomIndex(possibleMooves.size());
        Cell randomCell = possibleMooves.get(indexOfRandomCell);
        grid.grid[randomCell.column][randomCell.row] = 2;
        super.circle.paint(grid.getGraphicsContext2D(), randomCell.column, randomCell.row, grid.getScale());
        long tempsFin = System.currentTimeMillis();
        float seconds = (tempsFin - tempsDebut) / 1000F;
        System.out.println("Process time : " + seconds + " seconds.");
    }

    private static int getRandomIndex(int max) {
        return (int) (Math.random() * max);
    }

    private ArrayList<Cell> getPossibleMooves() {
        ArrayList<Cell> list = new ArrayList<>();
        for (int i = 0; i < grid.getColumnCount(); i++) {
            for (int j = 0; j < grid.getRowCount(); j++) {
                if (grid.grid[i][j] == 0) {
                    list.add(new Cell(i, j));
                }
            }
        }
        return list;
    }
}
