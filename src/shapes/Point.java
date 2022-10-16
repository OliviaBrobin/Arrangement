package shapes;

import java.util.LinkedList;

public class Point{

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point point)
    {
        if(x == point.getX() && y == point.getY())
        {
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point subtract(Point p2) {
        return new Point(x - p2.x, y - p2.y);
    }

    public Point add(Point p2) {
        return new Point(x + p2.x, y + p2.y);
    }

    public double distance(Point p2) {
        return Math.sqrt((x - p2.x)*(x - p2.x) + (y - p2.y)*(y - p2.y));
    }

    public Point normal() {
        double length = Math.sqrt(x*x + y*y);
        return new Point((int) (x/length), (int) (y/length));
    }

    public Point scale(double s) {
        return new Point((int) (x*s), (int) (y*s));
    }

    public static Point getAveragePoint(LinkedList<Point> points)
    {
        int x = 0;
        int y = 0;

        for(Point point : points)
        {
            x += point.getX();
            y += point.getY();
        }
        if(points.size() > 0) {
            x /= points.size();
            y /= points.size();
        }
        return new Point(x, y);
    }

    @Override
    public String toString()
    {
        return  "(" + x + ", " + y + ")";
    }
}
