package code.Player;

import code.Windows.Cell;
import code.Windows.Circle;
import code.Grid;
import code.TicTacToe;

import java.util.*;

//Classe d'IA qui étend la super class Player
public class AIAlphaBeta extends Player {

    public AIAlphaBeta(Grid grid, Circle circle, TicTacToe ticTacToe) {
        super("O", grid, circle, ticTacToe);
    }

    public void play() {
        Cell bestCell = AlphaBeta(grid);
        System.out.println("Best Cell : " + bestCell.toString());
        grid.grid[bestCell.column][bestCell.line] = 2;
        super.circle.paint(grid.getGraphicsContext2D(), bestCell.column, bestCell.line, grid.getScale());
        System.out.println("Grid after IA TURN : "+Arrays.deepToString(grid.grid));
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

    public Cell AlphaBeta(Grid grid) {
        ArrayList<Cell> list = new ArrayList<>();
        int alpha = -1000;
        int beta = 1000;
        boolean continuer = true;

        for (int column = 0; column < grid.getColumnCount() && continuer; column++) {
            for (int row = 0; row < grid.getRowCount() & continuer; row++) {

                if (cellIsEmpty(this.grid, column, row)) {
                    grid.grid[column][row] = 2;
                    int eval = MinAlphaBeta(5, 2, 2, alpha, beta);

                    if (eval > alpha) {
                        alpha = eval;
                        list.clear();
                        list.add(new Cell(column, row));
                        System.out.println("Cell : " + "["+column +"]"+ "["+row +"]");
                        System.out.println("Eval : " +  eval);
                    }
                    grid.grid[column][row] = 0;
                    if(alpha >=beta){
                        continuer = false;
                    }
                }
            }
        }
        System.out.println("List of choice : " + list);
        Collections.shuffle(list);
        return list.get(0);

    }

    public int MaxAlphaBeta(int profondeur, int joueur, int joueurEnCours, int alpha, int beta) {
        if (joueur == 1) {
            joueur = 2;
        } else {
            joueur = 1;
        }

        if (profondeur == 0 || checkHumanWin(grid) || checkIaWin(grid) || isEquality(grid)) {
            return evalTerminalTest(joueurEnCours, profondeur);
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

    public int MinAlphaBeta(int profondeur, int joueur, int joueurEnCours, int alpha, int beta) {
        if (joueur == 1) {
            joueur = 2;
        } else {
            joueur = 1;
        }
        if (profondeur == 0 || checkHumanWin(grid) || checkIaWin(grid) || isEquality(grid)) {
            return evalTerminalTest(joueurEnCours, profondeur);
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

    public int evalTerminalTest(int joueur, int profondeur) {
        int vainqueur = checkWinner();

        if ((vainqueur == 2)) {
            return 10000 - profondeur;
        } else if ((vainqueur == 0)) {
            int adversaire;
            if (joueur == 2) adversaire = 1;
            else adversaire = 2;
            return giveValue(joueur, adversaire);
        } else {
            return -10000 + profondeur;
        }
    }

    private int giveValue(int joueur, int adversaire) {
        return aligne(joueur) - aligne(adversaire);
    }

    public int aligne(int joueur) {
        int poids = 0;
        for (int i = 0; i < grid.getColumnCount(); i++) {
            {
                if ((grid.grid[i][0] == 0 || grid.grid[i][0] == joueur)
                        && (grid.grid[i][1] == 0 || grid.grid[i][1] == joueur)
                        && (grid.grid[i][2] == 0 || grid.grid[i][2] == joueur)
                        && (grid.grid[i][3] == 0 || grid.grid[i][3] == joueur)) {

                    poids = (grid.grid[i][0] + grid.grid[i][1] + grid.grid[i][2] + grid.grid[i][3])/joueur;
                }

                if ((grid.grid[0][i] == 0 || grid.grid[0][i] == joueur)
                        && (grid.grid[1][i] == 0 || grid.grid[1][i] == joueur)
                        && (grid.grid[2][i] == 0 || grid.grid[2][i] == joueur)
                        && (grid.grid[3][i] == 0 || grid.grid[3][i] == joueur)) {

                    poids = (grid.grid[0][i] + grid.grid[1][i] + grid.grid[2][i] + grid.grid[3][i])/joueur;
                }
            }
        }

        if ((grid.grid[0][0] == joueur || (grid.grid[0][0] == 0)
                && (grid.grid[1][1] == joueur || grid.grid[1][1] == 0)
                && (grid.grid[2][2] == joueur || grid.grid[2][2] == 0)
                && (grid.grid[3][3] == joueur || grid.grid[3][3] == 0))) {
            poids = (grid.grid[0][0] + grid.grid[1][1] + grid.grid[2][2] + grid.grid[3][3])/joueur;
        }

        if ((grid.grid[3][0] == joueur || (grid.grid[3][0] == 0)
                && (grid.grid[2][1] == joueur || grid.grid[2][1] == 0)
                && (grid.grid[1][2] == joueur || grid.grid[1][2] == 0)
                && (grid.grid[0][3] == joueur || grid.grid[0][3] == 0))) {
            poids = (grid.grid[0][3] + grid.grid[1][2] + grid.grid[2][1] + grid.grid[3][0])/joueur;
        }
        return poids;
    }


    public int checkWinner() {
        if (grid.grid[0][0] == 1 && grid.grid[1][0] == 1 && grid.grid[2][0] == 1 && grid.grid[3][0] == 1) return 1;
        else if (grid.grid[0][1] == 1 && grid.grid[1][1] == 1 && grid.grid[2][1] == 1 && grid.grid[3][1] == 1) return 1;
        else if (grid.grid[0][2] == 1 && grid.grid[1][2] == 1 && grid.grid[2][2] == 1 && grid.grid[3][2] == 1) return 1;
        else if (grid.grid[0][3] == 1 && grid.grid[1][3] == 1 && grid.grid[2][3] == 1 && grid.grid[3][3] == 1) return 1;

        else if (grid.grid[0][0] == 1 && grid.grid[0][1] == 1 && grid.grid[0][2] == 1 && grid.grid[0][3] == 1) return 1;
        else if (grid.grid[1][0] == 1 && grid.grid[1][1] == 1 && grid.grid[1][2] == 1 && grid.grid[1][3] == 1) return 1;
        else if (grid.grid[2][0] == 1 && grid.grid[2][1] == 1 && grid.grid[2][2] == 1 && grid.grid[2][3] == 1) return 1;
        else if (grid.grid[3][0] == 1 && grid.grid[3][1] == 1 && grid.grid[3][2] == 1 && grid.grid[3][3] == 1) return 1;

        else if (grid.grid[0][0] == 1 && grid.grid[1][1] == 1 && grid.grid[2][2] == 1 && grid.grid[3][3] == 1) return 1;
        else if (grid.grid[3][0] == 1 && grid.grid[2][1] == 1 && grid.grid[1][2] == 1 && grid.grid[0][3] == 1) return 1;

        else if (grid.grid[0][0] == 2 && grid.grid[1][0] == 2 && grid.grid[2][0] == 2 && grid.grid[3][0] == 2) return 2;
        else if (grid.grid[0][1] == 2 && grid.grid[1][1] == 2 && grid.grid[2][1] == 2 && grid.grid[3][1] == 2) return 2;
        else if (grid.grid[0][2] == 2 && grid.grid[1][2] == 2 && grid.grid[2][2] == 2 && grid.grid[3][2] == 2) return 2;
        else if (grid.grid[0][3] == 2 && grid.grid[1][3] == 2 && grid.grid[2][3] == 2 && grid.grid[3][3] == 2) return 2;

        else if (grid.grid[0][0] == 2 && grid.grid[0][1] == 2 && grid.grid[0][2] == 2 && grid.grid[0][3] == 2) return 2;
        else if (grid.grid[1][0] == 2 && grid.grid[1][1] == 2 && grid.grid[1][2] == 2 && grid.grid[1][3] == 2) return 2;
        else if (grid.grid[2][0] == 2 && grid.grid[2][1] == 2 && grid.grid[2][2] == 2 && grid.grid[2][3] == 2) return 2;
        else if (grid.grid[3][0] == 2 && grid.grid[3][1] == 2 && grid.grid[3][2] == 2 && grid.grid[3][3] == 2) return 2;

        else if (grid.grid[0][0] == 2 && grid.grid[1][1] == 2 && grid.grid[2][2] == 2 && grid.grid[3][3] == 2) return 2;
        else if (grid.grid[3][0] == 2 && grid.grid[2][1] == 2 && grid.grid[1][2] == 2 && grid.grid[0][3] == 2) return 2;
        else return 0;
    }

}