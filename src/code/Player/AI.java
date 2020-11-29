package code.Player;

import code.Windows.Cell;
import code.Windows.Circle;
import code.Grid;
import code.TicTacToe;

import java.util.*;

//Classe d'IA qui étend la super class Player
//Pour l'instant, joue comme un humain normal
public class AI extends Player {

    public int column;
    public int row;


    public AI(Grid grid, Circle circle, TicTacToe ticTacToe) {
        super("O", grid, circle, ticTacToe);
    }

    //Manière de jouer
    @Override
    public void play() {

        Cell bestCell = minimax(grid);
        System.out.println(bestCell.toString());
        grid.grid[bestCell.column][bestCell.line] = 2;
        super.circle.paint(grid.getGraphicsContext2D(), bestCell.column, bestCell.line, grid.getScale());
        System.out.println(Arrays.deepToString(grid.grid));
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

    public Cell minimax(Grid grid) {
        int bestScoreMax = -1000;
        ArrayList<Cell> list = new ArrayList<>();


        for (int column = 0; column < grid.getColumnCount(); column++) {
            for (int row = 0; row < grid.getRowCount(); row++) {

                if (cellIsEmpty(this.grid, column, row) && grid.grid[column][row] != 2 && grid.grid[column][row] != 1) {
                    System.out.println("différent de zéro : " + column + row);
                    grid.grid[column][row] = 2;
                    int eval = Min(3, 2, 2);

                    if (eval > bestScoreMax) {
                        bestScoreMax = eval;
                        list.clear();
                        list.add(new Cell(column, row));
                        System.out.println("Cellule : " + column + row + " et eval : " + eval);
                    } else if (eval == bestScoreMax) {
                        list.add(new Cell(column, row));
                        System.out.println("Cellule : " + column + row + " et eval : " + eval);
                    }
                    if (eval < bestScoreMax) System.out.println("Cellule : " + column + row + " et eval : " + eval);

                    grid.grid[column][row] = 0;
                }
            }
        }
        System.out.println("list: " + list);
        Collections.shuffle(list);
        return list.get(0);

    }

    public int Max(int profondeur, int joueur, int joueurEnCours) {
        if (joueur == 1) {
            joueur = 2;
        } else {
            joueur = 1;
        }

        if (profondeur == 0 || checkHumanWin(grid) || checkIaWin(grid) || isEquality(grid)) {
            return eval2(joueurEnCours, profondeur);
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

    public int Min(int profondeur, int joueur, int joueurEnCours) {
        if (joueur == 1) {
            joueur = 2;
        } else {
            joueur = 1;
        }
        if (profondeur == 0 || checkHumanWin(grid) || checkIaWin(grid) || isEquality(grid)) {
            return eval2(joueurEnCours, profondeur);
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

    public int eval2(int joueur, int profondeur) {
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
        int cpt = 0;
        for (int i = 0; i < grid.getColumnCount(); i++) {
            {
                if ((grid.grid[i][0] == 0 || grid.grid[i][0] == joueur)
                        && (grid.grid[i][1] == 0 || grid.grid[i][1] == joueur)
                        && (grid.grid[i][2] == 0 || grid.grid[i][2] == joueur)
                        && (grid.grid[i][3] == 0 || grid.grid[i][3] == joueur)) {
                    cpt++;
                }

                if ((grid.grid[0][i] == 0 || grid.grid[0][i] == joueur)
                        && (grid.grid[1][i] == 0 || grid.grid[1][i] == joueur)
                        && (grid.grid[2][i] == 0 || grid.grid[2][i] == joueur)
                        && (grid.grid[3][i] == 0 || grid.grid[3][i] == joueur)) {
                    cpt++;
                }
            }
        }

        if ((grid.grid[0][0] == joueur || (grid.grid[0][0] == 0)
                && (grid.grid[1][1] == joueur || grid.grid[1][1] == 0)
                && (grid.grid[2][2] == joueur || grid.grid[2][2] == 0)
                && (grid.grid[3][3] == joueur || grid.grid[3][3] == 0))) {
            cpt++;
        }

        if ((grid.grid[3][0] == joueur || (grid.grid[3][0] == 0)
                && (grid.grid[2][1] == joueur || grid.grid[2][1] == 0)
                && (grid.grid[1][2] == joueur || grid.grid[1][2] == 0)
                && (grid.grid[0][3] == joueur || grid.grid[0][3] == 0))) {
            cpt++;
        }
        return cpt;
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
