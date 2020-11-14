package code.Windows;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//Class Circle qui représente graphiquement un pion
public class Circle
{
    public double width, height;
    public Color color;

    public Circle(double width, double height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void paint(GraphicsContext context, int column, int row, double scale) {
        context.setFill(this.color);
        context.fillOval(column * scale + 12, row * scale + 12, scale - 4, scale - 4);
    }

}