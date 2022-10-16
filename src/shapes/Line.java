package shapes;

import game.Display;
import game.GraphicsDisplay;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;

public class Line extends LineShape implements Comparable{

    public Line(){
        type = "line";
    }

    public Line(int x1, int y1, int x2, int y2) {
        super();
        type = "line";
        setX1(x1);
        setX2(x2);
        setY1(y1);
        setY2(y2);
    }

    public Line(Line line){
        super();
        type = "line";
        setX1(line.getX1());
        setX2(line.getX2());
        setY1(line.getY1());
        setY2(line.getY2());
    }

    public Point[] getPoints()
    {
        Point[] points = {new Point(x1, y1), new Point(x2, y2)};
        return points;
    }

    @Override
    public int compareTo(Line line) {
        if(getX1() > line.getX1())
        {
            return -1;
        }
        return 1;
    }

    public Line[] getLines()
    {
        Line line1 = new Line(x1, y1, x2, y2);

        return new Line[] {line1};
    }

    public Line(Line2D line)
    {
        x1 = (int) line.getX1();
        x2 = (int) line.getX2();
        y1 = (int) line.getY1();
        y2 = (int) line.getY2();
    }

    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Map<RenderingHints.Key, Object> hints = new HashMap<RenderingHints.Key, Object>();
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(hints);

        //Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke((int) (3 * GraphicsDisplay.scalingFactor)));
        g2.drawLine((int) (x1 * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER,(int) (y1 * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (x2 * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (y2 * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER);
    }


    public void update() {
        if(Math.abs(x2 - x1) < 8 && Math.abs(y2 - y1) < 8)
        {
            largeEnough = false;
        }
        else {
            largeEnough = true;
        }
    }

    public boolean intersects(Line line)
    {
        Line2D line1 = new Line2D.Double(x1, y1, x2, y2);
        Line2D line2 = new Line2D.Double(line.getX1(), line.getY1(), line.getX2(), line.getY2());
        boolean result = line2.intersectsLine(line1);
        return result;
    }

    public Point getIntersectionWith(Line line)
    {
        double x11 = x1;
        double y11 = y1;
        double x12 = x2;
        double y12 = y2;
        double x21 = line.getX1();
        double y21 = line.getY1();
        double x22 = line.getX2();
        double y22 = line.getY2();



        double dx1 = x12-x11;
        double dy1 = y12-y11;
        double dx2 = x22-x21;
        double dy2 = y22-y21;
        double dxx = x11-x21;
        double dyy = y11-y21;
        double div, t, s;

        div = dy2*dx1-dx2*dy1;
        if (Math.abs(div) < 1.0e-10)  //better to compare abs(div) with small Eps
            return null;  //collinear case

        t = (dx1*dyy-dy1*dxx) / div;
        if (t < 0 || t > 1.0)
            return null; //intersection outside the first segment

        s = (dx2*dyy-dy2*dxx) / div;
        if (s < 0 || s > 1.0)
            return null;  //intersection outside the second segment

        Point point = new Point((int) (x11 + s * dx1),(int) (y11 + s * dy1));
        return point;
    }

    public static LinkedList<Line> cloneList(LinkedList<Line> lineList) {
        LinkedList<Line> clonedList = new LinkedList<>();
        for (Line line : lineList) {
            clonedList.add(new Line(line));
        }
        return clonedList;
    }

    public static LinkedList<Line> removeElement(LinkedList<Line> lineList, Line lineb) {
        for (int i = lineList.size() - 1; i >= 0; i--) {
            Line linea = lineList.get(i);
            if(linea.getX1() == lineb.getX1() && linea.getX2() == lineb.getX2() && linea.getY1() == lineb.getY1() && linea.getY2() == lineb.getY2())
            {
                lineList.remove(linea);
            }
        }

        return lineList;
    }

    public static boolean compareArrays(Line[] arr1, Line[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }

    @Override
    public int compareTo(Object line) {
        Line line1 = (Line) line;
        if(getX1() > line1.getX1())
        {
            return -1;
        }
        return 1;
    }

    @Override
    public boolean completelyOverlaps(shapes.Shape shape2) {
        return false;
    }

    @Override
    public int getNumberOfIntersections(Shape shape2) {
        return getNumberOfIntersections((Line) shape2);
    }
}
