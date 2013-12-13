package serialization;

import java.io.*;

class Point2d implements Serializable {
    // The X and Y coordinates of the point
    private int x;
    private int y;

    public Point2d(int px, int py) {
        x = px;
        y = py;
    }

    public Point2d() {
        this(0, 0);
    }

    public void setX(int px) {
        x = px;
    }

    public int getX() {
        return x;
    }

    public void setY(int py) {
        y = py;
    }

    public int getY() {
        return y;
    }

    public void setXY(int px, int py) {
        setX(px);
        setY(py);
    }

    public double distanceFrom(Point2d pt) {
        int dx = Math.abs(x - pt.getX());
        int dy = Math.abs(y - pt.getY());
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    public double distanceFromOrigin() {
        return distanceFrom(new Point2d());
    }

    public String toStringForXY() {
        return "(" + x + ", " + y;
    }

    public String toString() {
        return toStringForXY() + ")";
    }
}
