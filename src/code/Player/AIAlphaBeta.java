package code.Player;

import code.Cell;
import code.Windows.Circle;
import code.Grid;
import code.TicTacToe;

import java.util.*;

//Classe d'IA qui étend la super class Player
public class AIAlphaBeta extends Player {

    private final int gameChoice;
    private int symbol;

    public AIAlphaBeta(Grid grid, Circle circle, TicTacToe ticTacToe, int gameChoice) {
        super (grid, circle, ticTacToe);
        this.gameChoice = gameChoice;
    }

    public void play() {
        if(gameChoice ==3){
            symbol = 2;
        }
        else if(gameChoice ==4){
            symbol = 2;
        }
        long tempsDebut = System.currentTimeMillis();
        Cell bestCell = AlphaBeta(grid,7, symbol);
        System.out.println("Best Cell : " + bestCell.toString());
        grid.grid[bestCell.column][bestCell.row] = symbol;
        super.circle.paint(grid.getGraphicsContext2D(), bestCell.column, bestCell.row, grid.getScale());
        long tempsFin = System.currentTimeMillis();
        float seconds = (tempsFin - tempsDebut) / 1000F;
        System.out.println("Process time : "+ seconds + " seconds.");
    }

    private ArrayList<Cell> getPossibleMooves() {
        ArrayList<Cell> list = new ArrayList<>();
        for(int i=0;i < grid.getColumnCount();i++){
            for(int j=0;j < grid.getRowCount();j++){
                if(grid.grid[i][j]==0){
                    list.add(new Cell(i,j));
                }
            }
        }
        return list;
    }

    private Cell AlphaBeta(Grid grid,int profondeur, int symbol) {
        ArrayList<Cell> list = new ArrayList<>();
        int alpha = -1000;
        int beta = 1000;
        ArrayList<Cell> possibleMoves = getPossibleMooves();

        for (Cell cell : possibleMoves) {
            if (cellIsEmpty(cell, grid)) {
                    grid.grid[cell.column][cell.row] = symbol;
                    int eval = MinAlphaBeta(profondeur, symbol, symbol, alpha, beta);

                    if (eval > alpha) {
                        alpha = eval;
                        list.clear();
                        list.add(new Cell(cell.column, cell.row));
                        System.out.println("Cell : " + "["+cell.column +"]"+ "["+cell.row +"]");
                        System.out.println("Eval : " +  eval);
                    }
                    grid.grid[cell.column][cell.row] = 0;

                }
            }

        System.out.println("List of choice : " + list);
        Collections.shuffle(list);
        return list.get(0);

    }

    private int MaxAlphaBeta(int profondeur, int joueur, int joueurEnCours, int alpha, int beta) {
        if (joueur == 1) {
            joueur = 2;
        } else {
            joueur = 1;
        }

        if (profondeur == 0 || checkHumanWin(grid) || checkIaWin(grid) || isDraw(grid)) {
            return evaluateTerminalTest(joueurEnCours, profondeur, symbol, grid);
        }

        for (int column = 0; column < grid.getColumnCount(); column++) {
            for (int row = 0; row < grid.getRowCount(); row++) {
                if (cellIsEmpty(this.grid, column, row)) {

                    grid.grid[column][row] = joueur;
                    int eval = MinAlphaBeta(profondeur - 1, joueur, joueurEnCours, alpha, beta);
                    if (eval > alpha) {
                        alpha = eval;
                    }
                    grid.grid[column][row] = 0;
                    if(alpha >= beta){
                        return beta;
                    }
                }
            }
        }
        return alpha;
    }

    private int MinAlphaBeta(int profondeur, int joueur, int joueurEnCours, int alpha, int beta) {
        if (joueur == 1) {
            joueur = 2;
        } else {
            joueur = 1;
        }
        if (profondeur == 0 || checkHumanWin(grid) || checkIaWin(grid) || isDraw(grid)) {
            return evaluateTerminalTest(joueurEnCours, profondeur,symbol, grid);
        }

        for (int column = 0; column < grid.getColumnCount(); column++) {
            for (int row = 0; row < grid.getRowCount(); row++) {
                if (cellIsEmpty(this.grid, column, row)) {

                    grid.grid[column][row] = joueur;
                    int eval = MaxAlphaBeta(profondeur - 1, joueur, joueurEnCours, alpha,beta);

                    if (eval < beta) {
                        beta = eval;
                    }
                    grid.grid[column][row] = 0;
                    if(beta <= alpha){
                        return alpha;
                    }

                }
            }
        }
        return beta;
    }
}