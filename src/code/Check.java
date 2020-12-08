package code;

//Classe abstraite qui contient les méthodes pour vérifier certaines conditions
public abstract class Check {

    public boolean coordsAreNotInGrid(int col, int row, int maxCol, int maxRow) {
        return (col < 0 || col >= maxCol) || (row < 0 || row >= maxRow);
    }

    public boolean cellIsEmpty(Grid grid, int column, int row) {
        return grid.grid[column][row] == 0;
    }

    public boolean cellIsEmpty(Cell cell, Grid grid) {
        return grid.grid[cell.column][cell.row] == 0;
    }

    //L'humain gagne car il a aligné 4 pions (horizontalement ou verticalement ou diagonalement) : renvoie oui ou non
    public boolean checkHumanWin(Grid grid) {
        return (
                grid.grid[0][0] == 1 && grid.grid[1][0] == 1 && grid.grid[2][0] == 1 && grid.grid[3][0] == 1)
                || (grid.grid[0][1] == 1 && grid.grid[1][1] == 1 && grid.grid[2][1] == 1 && grid.grid[3][1] == 1)
                || (grid.grid[0][2] == 1 && grid.grid[1][2] == 1 && grid.grid[2][2] == 1 && grid.grid[3][2] == 1)
                || (grid.grid[0][3] == 1 && grid.grid[1][3] == 1 && grid.grid[2][3] == 1 && grid.grid[3][3] == 1)


                || (grid.grid[0][0] == 1 && grid.grid[0][1] == 1 && grid.grid[0][2] == 1 && grid.grid[0][3] == 1)
                || (grid.grid[1][0] == 1 && grid.grid[1][1] == 1 && grid.grid[1][2] == 1 && grid.grid[1][3] == 1)
                || (grid.grid[2][0] == 1 && grid.grid[2][1] == 1 && grid.grid[2][2] == 1 && grid.grid[2][3] == 1)
                || (grid.grid[3][0] == 1 && grid.grid[3][1] == 1 && grid.grid[3][2] == 1 && grid.grid[3][3] == 1)

                || (grid.grid[0][0] == 1 && grid.grid[1][1] == 1 && grid.grid[2][2] == 1 && grid.grid[3][3] == 1)
                || (grid.grid[3][0] == 1 && grid.grid[2][1] == 1 && grid.grid[1][2] == 1 && grid.grid[0][3] == 1);

    }

    //L'ia gagne car elle a aligné 4 pions (horizontalement ou verticalement ou diagonalement) : renvoie oui ou non
    public boolean checkIaWin(Grid grid) {
        return (
                grid.grid[0][0] == 2 && grid.grid[1][0] == 2 && grid.grid[2][0] == 2 && grid.grid[3][0] == 2)
                || (grid.grid[0][1] == 2 && grid.grid[1][1] == 2 && grid.grid[2][1] == 2 && grid.grid[3][1] == 2)
                || (grid.grid[0][2] == 2 && grid.grid[1][2] == 2 && grid.grid[2][2] == 2 && grid.grid[3][2] == 2)
                || (grid.grid[0][3] == 2 && grid.grid[1][3] == 2 && grid.grid[2][3] == 2 && grid.grid[3][3] == 2)

                || (grid.grid[0][0] == 2 && grid.grid[0][1] == 2 && grid.grid[0][2] == 2 && grid.grid[0][3] == 2)
                || (grid.grid[1][0] == 2 && grid.grid[1][1] == 2 && grid.grid[1][2] == 2 && grid.grid[1][3] == 2)
                || (grid.grid[2][0] == 2 && grid.grid[2][1] == 2 && grid.grid[2][2] == 2 && grid.grid[2][3] == 2)
                || (grid.grid[3][0] == 2 && grid.grid[3][1] == 2 && grid.grid[3][2] == 2 && grid.grid[3][3] == 2)

                || (grid.grid[0][0] == 2 && grid.grid[1][1] == 2 && grid.grid[2][2] == 2 && grid.grid[3][3] == 2)
                || (grid.grid[3][0] == 2 && grid.grid[2][1] == 2 && grid.grid[1][2] == 2 && grid.grid[0][3] == 2);
    }


    //Retourne le gagnant
    public int checkWinner(Grid grid) {
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

    //Donne des valeurs aux possibilités
    public int evaluateTerminalTest(int joueur, int profondeur, int symbol, Grid grid) {
        int vainqueur = checkWinner(grid);

        if ((vainqueur == symbol)) {
            return 10000 - profondeur;
        } else if ((vainqueur == 0)) {
            int adversaire;
            if (joueur == 2) adversaire = 1;
            else adversaire = 2;
            return giveValue(joueur, adversaire, grid);
        } else {
            return -10000 + profondeur;
        }
    }

    //Donne la valeur pour une case non terminale
    private int giveValue(int joueur, int adversaire, Grid grid) {
        return evaluate(joueur, grid) - evaluate(adversaire, grid);
    }

    //Calcule la valeur d'une possibilité
    public int evaluate(int joueur, Grid grid) {
        int poids = 0;
        for (int i = 0; i < grid.getColumnCount(); i++) {
            if ((grid.grid[i][0] == 0 || grid.grid[i][0] == joueur)
                    && (grid.grid[i][1] == 0 || grid.grid[i][1] == joueur)
                    && (grid.grid[i][2] == 0 || grid.grid[i][2] == joueur)
                    && (grid.grid[i][3] == 0 || grid.grid[i][3] == joueur)) {

                poids += (grid.grid[i][0] + grid.grid[i][1] + grid.grid[i][2] + grid.grid[i][3]) / joueur;
            }

            if ((grid.grid[0][i] == 0 || grid.grid[0][i] == joueur)
                    && (grid.grid[1][i] == 0 || grid.grid[1][i] == joueur)
                    && (grid.grid[2][i] == 0 || grid.grid[2][i] == joueur)
                    && (grid.grid[3][i] == 0 || grid.grid[3][i] == joueur)) {

                poids += (grid.grid[0][i] + grid.grid[1][i] + grid.grid[2][i] + grid.grid[3][i]) / joueur;
            }

        }
        if ((grid.grid[0][0] == joueur || (grid.grid[0][0] == 0)
                && (grid.grid[1][1] == joueur || grid.grid[1][1] == 0)
                && (grid.grid[2][2] == joueur || grid.grid[2][2] == 0)
                && (grid.grid[3][3] == joueur || grid.grid[3][3] == 0))) {
            poids += (grid.grid[0][0] + grid.grid[1][1] + grid.grid[2][2] + grid.grid[3][3]) / joueur;
        }
        if ((grid.grid[3][0] == joueur || (grid.grid[3][0] == 0)
                && (grid.grid[2][1] == joueur || grid.grid[2][1] == 0)
                && (grid.grid[1][2] == joueur || grid.grid[1][2] == 0)
                && (grid.grid[0][3] == joueur || grid.grid[0][3] == 0))) {
            poids += (grid.grid[0][3] + grid.grid[1][2] + grid.grid[2][1] + grid.grid[3][0]) / joueur;
        }
        return poids;
    }

    public boolean gridIsFull(Grid grid) {
        int cpt = 0;
        for (int column = 0; column < grid.getColumnCount(); column++) {
            for (int row = 0; row < grid.getRowCount(); row++) {
                if (grid.grid[column][row] != 0) {
                    cpt++;
                }
            }
        }
        return cpt == 16;
    }

    public boolean isDraw(Grid grid) {
        return gridIsFull(grid) && !(checkIaWin(grid)) && !(checkHumanWin(grid));
    }
}
