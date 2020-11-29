package code.Windows;

import code.Grid;

public class Cell {
    public int line;
    public int column;

    public Cell(int column, int line){
        this.column = column;
        this.line = line;

    }

    @Override
    public String toString() {
        return "["+column+"]"+"["+line +"]";
    }

    public boolean isEmpty(Cell cell, Grid grid){
        return grid.grid[cell.column][cell.line] ==0;
    }
    public boolean isHuman(Cell cell, Grid grid){
        return grid.grid[cell.column][cell.line] ==1;
    }
    public boolean isIA(Cell cell, Grid grid){
        return grid.grid[cell.column][cell.line] ==2;
    }
}