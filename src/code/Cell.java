package code;

//Classe qui définie une cellule possible pour que l'ia joue
public class Cell {
    public int row;
    public int column;

    public Cell(int column, int row){
        this.column = column;
        this.row = row;

    }

    @Override
    public String toString() {
        return "["+column+"]"+"["+row +"]";
    }

}