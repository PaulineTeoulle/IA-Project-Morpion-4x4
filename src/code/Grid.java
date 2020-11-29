package code;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

//Classe Grid qui représente la grille du jeu
public class Grid extends Canvas {
    public Label label; //nom
    public int[][] grid; //matrice de la grille
    private final int rowCount; //ligne
    private final int columnCount; //colonne
    public int scale; //ajustement à la fenetre
    public int player = 1; //joueur qui joue en premier : 1=humain et 2=ia

    public Grid(Label label, double width, double height, int rowCount, int columnCount) {
        this.label = label;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.scale = (int) (Math.min(width / columnCount, height / columnCount) * 0.8);
        grid = new int[columnCount][rowCount];
        widthProperty().addListener(evt -> drawGrid());
        heightProperty().addListener(evt -> drawGrid());
    }

    public Grid(int rowCount,int columCount){
        this.rowCount = rowCount;
        this.columnCount =columCount;
    }


    //Peint la grille de jeu
    public void drawGrid() {
        getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
        Point2D origin = new Point2D(10, 10);
        this.scale = (int) (Math.min(getWidth() / columnCount, getHeight() / columnCount) * 0.8);
        for (int i = 0; i < columnCount; i++) {
            for (int j = 0; j < rowCount; j++) {
                getGraphicsContext2D().strokeRect(origin.getX() + (scale * i), origin.getY() + (scale * j), scale, scale);
            }
        }
    }

    //Check si la grille est remplie ou pas : renvoie oui ou non
    public boolean gridIsFull() {
        int cpt = 0;
        for (int column = 0; column < columnCount; column++) {
            for (int row = 0; row < rowCount; row++) {
                if (grid[column][row] != 0) {
                    cpt++;
                }
            }
        }
        return cpt == 16;
    }


    //Getter pour avoir accès à des données
    public int getScale() {
        return scale;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

}

