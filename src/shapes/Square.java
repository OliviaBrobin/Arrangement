package shapes;

import game.Display;
import game.GraphicsDisplay;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Square extends LineShape {
    int xa;
    int ya;
    int xb;
    int yb;
    int xc;
    int yc;
    int xd;
    int yd;


    public Square(){
        type = "square";
    }

    public Square(Square square) {
        type = "square";

        setX1(square.getX1());
        setX2(square.getX2());
        setY1(square.getY1());
        setY2(square.getY2());

        xa = square.xa;
        xb = square.xb;
        xc = square.xc;
        xd = square.xd;

        ya = square.ya;
        yb = square.yb;
        yc = square.yc;
        yd = square.yd;
    }

    public Square(int xa, int ya, int xb, int yb, int xc, int yc, int xd, int yd) {
        this.xa = xa;
        this.ya = ya;
        this.xb = xb;
        this.yb = yb;
        this.xc = xc;
        this.yc = yc;
        this.xd = xd;
        this.yd = yd;
    }

    public Point[] getPoints()
    {
        Point[] points = {new Point(xa, ya), new Point(xb, yb), new Point(xc, yc), new Point(xd, yd)};
        return points;
    }

    public LinkedList<Point> getPointsAsList()
    {
        Point[] points = {new Point(xa, ya), new Point(xb, yb), new Point(xc, yc), new Point(xd, yd)};
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
        Line line3 = new Line(xc, yc, xd, yd);
        Line line4 = new Line(xd, yd, xa, ya);

        return new Line[] {line1, line2, line3, line4};
    }

    public int getXa() {
        return xa;
    }

    public int getYa() {
        return ya;
    }

    public int getXb() {
        return xb;
    }

    public int getYb() {
        return yb;
    }

    public int getXc() {
        return xc;
    }

    public int getYc() {
        return yc;
    }

    public int getXd() {
        return xd;
    }

    public int getYd() {
        return yd;
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
        if(Math.abs(x2 - x1) < 6 && Math.abs(y2 - y1) < 6)
        {
            if(Math.abs(x2 - x1) == 0 && Math.abs(y2 - y1) == 0) {
                x2 = x1 + 6;
                y2 = y1 + 6;
            }
            //else {
            double degrees = Math.atan2(y2-y1, x2-x1);
            x2 = (int) (x1 + 12 * Math.cos(degrees));
            y2 = (int) (y1 + 12 * Math.sin(degrees));
            //}
        }
         */

        /*
        if(Math.abs(x2 - x1) < 6)
        {
            x2 = x1 + 6;
        }

        if(Math.abs(y2 - y1) < 6)
        {
            y2 = y1 + 6;
        }
        */

        xa = x2;
        ya = y2;

        xc = x1 - (xa - x1);
        yc = y1 - (ya - y1);

        int centerX = (xc + xa)/2;
        int centerY = (yc + ya)/2;    // Center point
        int midDiagonalX = (xc - xa)/2;
        int midDiagonalY = (yc - ya)/2;    // Half-diagonal
        xb = centerX - midDiagonalY;
        yb = centerY + midDiagonalX;    // Third corner
        xd = centerX + midDiagonalY;
        yd = centerY - midDiagonalX;    // Fourth corner
    }

    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Map<RenderingHints.Key, Object> hints = new HashMap<RenderingHints.Key, Object>();
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(hints);

        //Drawing square from a point
        /*
        int xc = (x1 + x2)/2  ;
        int yc = (y1 + y2)/2  ;    // Center point
        int xd = (x1 - x2)/2  ;
        int yd = (y1 - y2)/2  ;    // Half-diagonal
        x3 = xc - yd  ;
        y3 = yc + xd;    // Third corner
        x4 = xc + yd  ;
        y4 = yc - xd;    // Fourth corner

        int[] xPoints = {x1 + Display.LEFT_BUFFER, x3 + Display.LEFT_BUFFER, x2 + Display.LEFT_BUFFER, x4 + Display.LEFT_BUFFER};
        int[] yPoints = {y1 + Display.TOP_BUFFER, y3 + Display.TOP_BUFFER, y2 + Display.TOP_BUFFER, y4 + Display.TOP_BUFFER};
         */


        g2.setStroke(new BasicStroke(3));


        int[] xPoints = {(int) (xa * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (xb * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (xc * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (xd * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER};
        int[] yPoints = {(int) (ya* GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (yb * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (yc * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (yd * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER};


        g2.drawPolygon(xPoints, yPoints, 4);
    }

    public int getNumberOfIntersections(Square square)
    {
        int numOfInteresections = 0;
        Line2D line1 = new Line2D.Float(xa, ya, xb, yb);
        Line2D line2 = new Line2D.Float(square.getXa(), square.getYa(), square.getXb(), square.getYb());

        Line2D line3 = new Line2D.Float(xb, yb, xc, yc);
        Line2D line4 = new Line2D.Float(square.getXb(), square.getYb(), square.getXc(), square.getYc());

        Line2D line5 = new Line2D.Float(xc, yc, xd, yd);
        Line2D line6 = new Line2D.Float(square.getXc(), square.getYc(), square.getXd(), square.getYd());

        Line2D line7 = new Line2D.Float(xd, yd, xa, ya);
        Line2D line8 = new Line2D.Float(square.getXd(), square.getYd(), square.getXa(), square.getYa());

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
        if(line1.intersectsLine(line8))
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
        if(line3.intersectsLine(line8))
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
        if(line5.intersectsLine(line8))
        {
            numOfInteresections++;
        }
        if(line7.intersectsLine(line2))
        {
            numOfInteresections++;
        }
        if(line7.intersectsLine(line4))
        {
            numOfInteresections++;
        }
        if(line7.intersectsLine(line6))
        {
            numOfInteresections++;
        }
        if(line7.intersectsLine(line8))
        {
            numOfInteresections++;
        }

        return numOfInteresections;
    }

    //Work on this so that we can determine if a square is completely contained by the other squares.
    double sign (Point p1, Point p2, Point p3)
    {
        return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p2.getX() - p3.getX()) * (p1.getY() - p3.getY());
    }

    boolean contains(Point point)
    {
        Point v1 = new Point(xa, ya);
        Point v2 = new Point(xb, yb);
        Point v3 = new Point(xc, yc);
        Point v4 = new Point(xd, yd);

        double d1, d2, d3, d4;
        boolean has_neg, has_pos;

        d1 = sign(point, v1, v2);
        d2 = sign(point, v2, v3);
        d3 = sign(point, v3, v4);
        d4 = sign(point, v4, v1);

        has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0) || (d4 < 0);
        has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0) || (d4 > 0);

        boolean intersects1 = !(has_neg && has_pos);

        v1 = new Point(xa + 6, ya + 6);
        v2 = new Point(xb + 6, yb + 6);
        v3 = new Point(xc + 6, yc + 6);
        v4 = new Point(xd + 6, yd + 6);

        d1 = sign(point, v1, v2);
        d2 = sign(point, v2, v3);
        d3 = sign(point, v3, v4);
        d4 = sign(point, v4, v1);

        has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0) || (d4 < 0);
        has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0) || (d4 > 0);

        boolean intersects2 = !(has_neg && has_pos);

        v1 = new Point(xa - 6, ya - 6);
        v2 = new Point(xb - 6, yb - 6);
        v3 = new Point(xc - 6, yc - 6);
        v4 = new Point(xd - 6, yd - 6);

        d1 = sign(point, v1, v2);
        d2 = sign(point, v2, v3);
        d3 = sign(point, v3, v4);
        d4 = sign(point, v4, v1);

        has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0) || (d4 < 0);
        has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0) || (d4 > 0);

        boolean intersects3 = !(has_neg && has_pos);

        return intersects1 && intersects2 && intersects3;
    }

    public boolean completelyOverlaps(Square square)
    {
        Point[] points = square.getPoints();
        if(contains(points[0]) && contains(points[1]) && contains(points[2]) && contains(points[3]))
        {
            return true;
        }

        return false;
    }

    @Override
    public boolean completelyOverlaps(Shape shape2) {
        return completelyOverlaps((Square) shape2);
    }

    @Override
    public int getNumberOfIntersections(Shape shape2) {
        return getNumberOfIntersections((Square) shape2);
    }
}