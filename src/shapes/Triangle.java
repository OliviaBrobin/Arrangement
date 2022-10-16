package shapes;

import game.Display;
import game.GraphicsDisplay;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Triangle extends LineShape {
    int xa;
    int xb;
    int xc;
    int ya;
    int yb;
    int yc;

    public Triangle(){
        type = "triangle";
    }

    public Triangle(Triangle triangle) {
        type = "triangle";

        setX1(triangle.getX1());
        setX2(triangle.getX2());
        setY1(triangle.getY1());
        setY2(triangle.getY2());

        xa = triangle.xa;
        xb = triangle.xb;
        xc = triangle.xc;

        ya = triangle.ya;
        yb = triangle.yb;
        yc = triangle.yc;
    }

    public Triangle(int xa, int ya, int xb, int yb, int xc, int yc)
    {
        this.xa = xa;
        this.ya = ya;
        this.xb = xb;
        this.yb = yb;
        this.xc = xc;
        this.yc = yc;
    }

    public Triangle(Point pointA, Point pointB, Point pointC)
    {
        this.xa = pointA.getX();
        this.ya = pointA.getY();
        this.xb = pointB.getX();
        this.yb = pointB.getY();
        this.xc = pointC.getX();
        this.yc = pointC.getY();
    }

    public Point[] getPoints()
    {
        Point[] points = {new Point(xa, ya), new Point(xb, yb), new Point(xc, yc)};
        return points;
    }

    public LinkedList<Point> getPointsAsList()
    {
        Point[] points = {new Point(xa, ya), new Point(xb, yb), new Point(xc, yc)};
        LinkedList<Point> pointsAsList = new LinkedList<Point>();
        pointsAsList.addAll(java.util.List.of(points));
        return pointsAsList;
    }

    @Override
    public int compareTo(Line line) {
        return 0;
    }

    public Line[] getLines()
    {
        Line line1 = new Line(xa, ya, xb, yb);
        Line line2 = new Line(xb, yb, xc, yc);
        Line line3 = new Line(xc, yc, xa, ya);

        return new Line[] {line1, line2, line3};
    }


    public int getXa() {
        return xa;
    }

    public int getXb() {
        return xb;
    }

    public int getXc() {
        return xc;
    }

    public int getYa() {
        return ya;
    }

    public int getYb() {
        return yb;
    }

    public int getYc() {
        return yc;
    }

    public void update()
    {
        if(Math.abs(x2 - x1) < 8 && Math.abs(y2 - y1) < 8)
        {
            largeEnough = false;
        }
        else {
            largeEnough = true;
        }

        /*
        if(x2 >= x1  - 1 && x2 <= x1 + 1)
        {
            x2 = x1 + 1;
        }

        if(y2 >= y1  - 1 && y2 <= y1 + 1)
        {
            y2 = y1 + 1 ;
        }
         */

        int x2AtOrigin = x2-x1;
        int y2AtOrigin = y2-y1;

        int x2AtOriginRotatedOnce = (int) (x2AtOrigin * Math.cos(Math.toRadians(120)) - y2AtOrigin * Math.sin(Math.toRadians(120)));
        int y2AtOriginRotatedOnce = (int) (x2AtOrigin * Math.sin(Math.toRadians(120)) + y2AtOrigin * Math.cos(Math.toRadians(120)));

        int x2RotatatedOnce = x2AtOriginRotatedOnce + x1;
        int y2RotatatedOnce = y2AtOriginRotatedOnce + y1;

        int x2AtOriginRotatedTwice = (int) (x2AtOriginRotatedOnce * Math.cos(Math.toRadians(120)) - y2AtOriginRotatedOnce * Math.sin(Math.toRadians(120)));
        int y2AtOriginRotatedTwice = (int) (x2AtOriginRotatedOnce * Math.sin(Math.toRadians(120)) + y2AtOriginRotatedOnce * Math.cos(Math.toRadians(120)));

        int x2RotatatedTwice = x2AtOriginRotatedTwice + x1;
        int y2RotatatedTwice = y2AtOriginRotatedTwice + y1;

        xa = x2;
        xb = x2RotatatedOnce;
        xc = x2RotatatedTwice;

        ya = y2;
        yb = y2RotatatedOnce;
        yc = y2RotatatedTwice;
    }

    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Map<RenderingHints.Key, Object> hints = new HashMap<RenderingHints.Key, Object>();
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(hints);

        //Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        //Draw Triangle from a point
        /*
        int xc = (x1 + x2)/2  ;
        int yc = (y1 + y2)/2  ;    // Center point
        int xd = (x1 - x2)/2  ;
        int yd = (y1 - y2)/2  ;    // Half-diagonal
        int x3 = xc - yd  ;
        int y3 = yc + xd;    // Third corner


        //int x3 = x1 + ((x2-x1)  / 2);
        //int y3 = (y2 + (x2-x1));

        int[] xPoints = {x1 + Display.LEFT_BUFFER, x2 + Display.LEFT_BUFFER, x3 + Display.LEFT_BUFFER};
        int[] yPoints = {y1 + Display.TOP_BUFFER, y2 + Display.TOP_BUFFER, y3 + Display.TOP_BUFFER};
        */

        int[] xPoints = {(int) (xa * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (xb * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (xc * GraphicsDisplay.scalingFactor) +GraphicsDisplay.LEFT_BUFFER};
        int[] yPoints = {(int) (ya * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (yb * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (yc * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER};

        g2.drawPolygon(xPoints, yPoints, 3);
    }

    public int getNumberOfIntersections(Triangle triangle)
    {
        int numOfInteresections = 0;
        Line2D line1 = new Line2D.Float(xa, ya, xb, yb);
        Line2D line2 = new Line2D.Float(triangle.getXa(), triangle.getYa(), triangle.getXb(), triangle.getYb());

        Line2D line3 = new Line2D.Float(xa, ya, xc, yc);
        Line2D line4 = new Line2D.Float(triangle.getXa(), triangle.getYa(), triangle.getXc(), triangle.getYc());

        Line2D line5 = new Line2D.Float(xb, yb, xc, yc);
        Line2D line6 = new Line2D.Float(triangle.getXb(), triangle.getYb(), triangle.getXc(), triangle.getYc());

        if(line1.intersectsLine(line2))
        {
            numOfInteresections++;
        }
        if(line1.intersectsLine(line4))
        {
            numOfInteresections++;
        }
        if(line1.intersectsLine(line6))
        {
            numOfInteresections++;
        }
        if(line3.intersectsLine(line2))
        {
            numOfInteresections++;
        }
        if(line3.intersectsLine(line4))
        {
            numOfInteresections++;
        }
        if(line3.intersectsLine(line6))
        {
            numOfInteresections++;
        }
        if(line5.intersectsLine(line2))
        {
            numOfInteresections++;
        }
        if(line5.intersectsLine(line4))
        {
            numOfInteresections++;
        }
        if(line5.intersectsLine(line6))
        {
            numOfInteresections++;
        }

        return numOfInteresections;
    }


    double sign (Point p1, Point p2, Point p3)
    {
        return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p2.getX() - p3.getX()) * (p1.getY() - p3.getY());
    }

    public boolean contains(Point point)
    {
        Point v1 = new Point(xa, ya);
        Point v2 = new Point(xb, yb);
        Point v3 = new Point(xc, yc);

        double d1, d2, d3;
        boolean has_neg, has_pos;

        d1 = sign(point, v1, v2);
        d2 = sign(point, v2, v3);
        d3 = sign(point, v3, v1);

        has_neg = (d1 <= 0) || (d2 <= 0) || (d3 <= 0);
        has_pos = (d1 >= 0) || (d2 >= 0) || (d3 >= 0);

        boolean intersects1 = !(has_neg && has_pos);

        v1 = new Point(xa+6, ya+6);
        v2 = new Point(xb+6, yb+6);
        v3 = new Point(xc+6, yc+6);

        d1 = sign(point, v1, v2);
        d2 = sign(point, v2, v3);
        d3 = sign(point, v3, v1);

        has_neg = (d1 <= 0) || (d2 <= 0) || (d3 <= 0);
        has_pos = (d1 >= 0) || (d2 >= 0) || (d3 >= 0);

        boolean intersects2 = !(has_neg && has_pos);

        v1 = new Point(xa-6, ya-6);
        v2 = new Point(xb-6, yb-6);
        v3 = new Point(xc-6, yc-6);

        d1 = sign(point, v1, v2);
        d2 = sign(point, v2, v3);
        d3 = sign(point, v3, v1);

        has_neg = (d1 <= 0) || (d2 <= 0) || (d3 <= 0);
        has_pos = (d1 >= 0) || (d2 >= 0) || (d3 >= 0);

        boolean intersects3 = !(has_neg && has_pos);

        return intersects1 && intersects2 && intersects3;
    }

    public boolean completelyOverlaps(Triangle triangle)
    {
        Point[] points = triangle.getPoints();
        if(contains(points[0]) && contains(points[1]) && contains(points[2]))
        {
            return true;
        }

        return false;
    }

    @Override
    public boolean completelyOverlaps(shapes.Shape shape2) {
        return completelyOverlaps((Triangle) shape2);
    }

    @Override
    public int getNumberOfIntersections(Shape shape2) {
        return getNumberOfIntersections((Triangle) shape2);
    }
}
