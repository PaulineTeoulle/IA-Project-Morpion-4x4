package code.Player;

import code.Cell;
import code.Windows.Circle;
import code.Grid;
import code.TicTacToe;

import java.util.*;

//Classe d'IA qui étend la super class Player
public class AIMinMax extends Player {
    private final int gameChoice;
    private int symbol;

    public AIMinMax(Grid grid, Circle circle, TicTacToe ticTacToe, int gameChoice) {
        super(grid, circle, ticTacToe);
        this.gameChoice = gameChoice;
    }

    //Manière de jouer
    @Override
    public void play() {
        if (gameChoice == 2) {
            symbol = 2;
        } else if (gameChoice == 4) {
            symbol = 1;
        }

        Cell bestCell = minimax(grid, 5, symbol);
        System.out.println(bestCell.toString());
        grid.grid[bestCell.column][bestCell.row] = symbol;
        super.circle.paint(grid.getGraphicsContext2D(), bestCell.column, bestCell.row, grid.getScale());
        System.out.println(Arrays.deepToString(grid.grid));

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

    private Cell minimax(Grid grid, int profondeur, int symbol) {
        int bestScoreMax = -1000;
        ArrayList<Cell> list = new ArrayList<>();

        for (int column = 0; column < grid.getColumnCount(); column++) {
            for (int row = 0; row < grid.getRowCount(); row++) {

                if (cellIsEmpty(this.grid, column, row)) {
                    grid.grid[column][row] = symbol;
                    int eval = Min(profondeur, symbol, symbol);

                    if (eval > bestScoreMax) {
                        bestScoreMax = eval;
                        list.clear();
                        list.add(new Cell(column, row));
                        System.out.println("Cell : " + "[" + column + "]" + "[" + row + "]");
                        System.out.println("Eval : " + eval);
                    } else if (eval == bestScoreMax) {
                        list.add(new Cell(column, row));
                        System.out.println("Cell : " + "[" + column + "]" + "[" + row + "]");
                        System.out.println("Eval : " + eval);
                    }
                    if (eval < bestScoreMax) {
                        System.out.println("Cell : " + "[" + column + "]" + "[" + row + "]");
                        System.out.println("Eval : " + eval);
                    }

                    grid.grid[column][row] = 0;
                }
            }
        }
        System.out.println("list: " + list);
        Collections.shuffle(list);
        return list.get(0);

    }

    private int Max(int profondeur, int joueur, int joueurEnCours) {
        if (joueur == 1) {
            joueur = 2;
        } else {
            joueur = 1;
        }

        if (profondeur == 0 || checkHumanWin(grid) || checkIaWin(grid) || isDraw(grid)) {
            return evaluateTerminalTest(joueurEnCours, profondeur, symbol, grid);
        }

        int max = -1000;
        for (int column = 0; column < grid.getColumnCount(); column++) {
            for (int row = 0; row < grid.getRowCount(); row++) {
                if (cellIsEmpty(this.grid, column, row)) {

                    grid.grid[column][row] = joueur;
                    int eval = Min(profondeur - 1, joueur, joueurEnCours);
                    if (eval > max) {
                        max = eval;
                    }
                    grid.grid[column][row] = 0;
                }
            }
        }
        return max;
    }

    private int Min(int profondeur, int joueur, int joueurEnCours) {
        if (joueur == 1) {
            joueur = 2;
        } else {
            joueur = 1;
        }
        if (profondeur == 0 || checkHumanWin(grid) || checkIaWin(grid) || isDraw(grid)) {
            return evaluateTerminalTest(joueurEnCours, profondeur, symbol, grid);
        }

        int min = 1000;
        for (int column = 0; column < grid.getColumnCount(); column++) {
            for (int row = 0; row < grid.getRowCount(); row++) {
                if (cellIsEmpty(this.grid, column, row)) {

                    grid.grid[column][row] = joueur;
                    int eval = Max(profondeur - 1, joueur, joueurEnCours);

                    if (eval < min) {
                        min = eval;
                    }
                    grid.grid[column][row] = 0;

                }
            }
        }
        return min;
    }
}
