package code;

//Classe abstraite qui contient les méthodes pour vérifier certaines conditions
public abstract class Check {

    //Les coordonées sont bien dans la grille : renvoie oui ou non
    public boolean coordsAreInGrid(int col, int row, int maxCol, int maxRow) {
        return (col >= 0 && col < maxCol) && (row >= 0 && row < maxRow);
    }

    //L'humain gagne car il a aligné 4 pions (horizontalement ou verticalement ou diagonalement) : renvoie oui ou non
    public boolean checkHumanWin(Grid grid){
        return (grid.grid[0][0] == 1 && grid.grid[1][0] == 1 && grid.grid[2][0] == 1 && grid.grid[3][0] == 1)
                || (grid.grid[0][0] == 1 && grid.grid[0][1] == 1 && grid.grid[0][2] == 1 && grid.grid[0][3] == 1)
                || (grid.grid[0][0] == 1 && grid.grid[1][1] == 1 && grid.grid[2][2] == 1 && grid.grid[3][3] == 1)
                || (grid.grid[3][0] == 1 && grid.grid[2][1] == 1 && grid.grid[1][2] == 1 && grid.grid[0][3] == 1);

    }

    //L'ia gagne car elle a aligné 4 pions (horizontalement ou verticalement ou diagonalement) : renvoie oui ou non
    public boolean checkIaWin(Grid grid){
        return (grid.grid[0][0] == 2 && grid.grid[1][0] == 2 && grid.grid[2][0] == 2 && grid.grid[3][0] == 2)
                || (grid.grid[0][0] == 2 && grid.grid[0][1] == 2 && grid.grid[0][2] == 2 && grid.grid[0][3] == 2)
                || (grid.grid[0][0] == 2 && grid.grid[1][1] == 2 && grid.grid[2][2] == 2 && grid.grid[3][3] == 2)
                || (grid.grid[3][0] == 2 && grid.grid[2][1] == 2 && grid.grid[1][2] == 2 && grid.grid[0][3] == 2);
    }

}
