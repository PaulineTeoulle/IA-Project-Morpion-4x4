package code.Player;

import code.Cell;
import code.Windows.Circle;
import code.Grid;
import code.TicTacToe;

import java.util.*;

//Classe d'IA MinMax
public class AIMinMax extends Player {
    private final int gameChoice;
    private int symbol;
    private final int profondeur;

    public AIMinMax(Grid grid, Circle circle, TicTacToe ticTacToe, int gameChoice) {
        super(grid, circle, ticTacToe);
        this.gameChoice = gameChoice;
        this.profondeur = 3;
    }

    //Placement du pion
    @Override
    public void play() {
        if (gameChoice == 2) {
            symbol = 2;
        } else if (gameChoice == 4) {
            symbol = 1;
        }
        long tempsDebut = System.currentTimeMillis();
        Cell bestCell = minMax(grid, symbol);
        System.out.println("Best Cell : " + bestCell.toString());
        grid.grid[bestCell.column][bestCell.row] = symbol;
        super.circle.paint(grid.getGraphicsContext2D(), bestCell.column, bestCell.row, grid.getScale());
        System.out.println(Arrays.deepToString(grid.grid));
        long tempsFin = System.currentTimeMillis();
        float seconds = (tempsFin - tempsDebut) / 1000F;
        System.out.println("Process time : " + seconds + " seconds.");

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


    //Algorithme MinMax qui pour chaque case disponible, évalue son score et renvoie la meilleure solution possible
    private Cell minMax(Grid grid, int symbol) {
        int bestScoreMax = -1000;
        ArrayList<Cell> list = new ArrayList<>();
        ArrayList<Cell> possibleMoves = getPossibleMooves();

        for (Cell cell : possibleMoves) {
            if (cellIsEmpty(cell, grid)) {
                grid.grid[cell.column][cell.row] = symbol;
                int eval = Min(profondeur, symbol, symbol);
                if (eval > bestScoreMax) {
                    bestScoreMax = eval;
                    list.clear();
                    list.add(new Cell(cell.column, cell.row));
                    System.out.println("Cell : " + "[" + cell.column + "]" + "[" + cell.row + "]");
                    System.out.println("Eval : " + eval);
                } else if (eval == bestScoreMax) {
                    list.add(new Cell(cell.column, cell.row));
                    System.out.println("Cell : " + "[" + cell.column + "]" + "[" + cell.row + "]");
                    System.out.println("Eval : " + eval);
                }
                if (eval < bestScoreMax) {
                    System.out.println("Cell : " + "[" + cell.column + "]" + "[" + cell.row + "]");
                    System.out.println("Eval : " + eval);
                }
                grid.grid[cell.column][cell.row] = 0;
            }
        }
        System.out.println("List: " + list);
        Collections.shuffle(list);
        return list.get(0);
    }

    //Retourne la valeur maximum des cases disponibles
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

    //Retourne la valeur minimum des cases disponibles
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
