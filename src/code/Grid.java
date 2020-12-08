package code;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

//Classe Grid qui représente la grille du jeu
public class Grid extends Canvas {
    public Label label; //nom
    public int[][] grid; //matrice de la grille
    private final int rowCount; //nombre de lignes
    private final int columnCount; //nombre de colonne
    public int scale; //ajustement à la fenetre
    public int player = 1; //joueur qui joue en premier : ici l'humain joue en premier

    public Grid(Label label, double width, double height, int rowCount, int columnCount) {
        this.label = label;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.scale = (int) (Math.min(width / columnCount, height / columnCount) * 0.8);
        grid = new int[columnCount][rowCount];
        widthProperty().addListener(evt -> drawGrid());
        heightProperty().addListener(evt -> drawGrid());
    }

    //Peint la grille de jeu
    public void drawGrid() {
        getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
        Point2D origin = new Point2D(10, 10);
        this.scale = (int) (Math.min(getWidth() / getColumnCount(), getHeight() / getRowCount()) * 0.8);
        for (int i = 0; i < columnCount; i++) {
            for (int j = 0; j < rowCount; j++) {
                getGraphicsContext2D().strokeRect(origin.getX() + (scale * i), origin.getY() + (scale * j), scale, scale);
            }
        }
    }

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

