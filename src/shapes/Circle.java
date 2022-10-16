package shapes;

import game.Display;
import game.GraphicsDisplay;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static java.lang.Math.atan2;
import static java.lang.Math.sqrt;

public class Circle extends shapes.Shape {

    int radius;
    int centerX;
    int centerY;

    public Circle() {
        type = "circle";
    }

    public Circle(Circle circle) {
        type = "circle";

        setX1(circle.getX1());
        setX2(circle.getX2());
        setY1(circle.getY1());
        setY2(circle.getY2());

        radius = circle.radius;
        centerX = circle.centerX;
        centerY = circle.centerY;
    }

    public int getRadius()
    {
        return  radius;
    }

    public int getCenterX()
    {
        return  centerX;
    }

    public int getCenterY()
    {
        return  centerY;
    }

    public void setRadius(int radius)
    {
        this.radius = radius;
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


        int possibleRadius = (int) sqrt(
                ((x2 - x1) * (x2 - x1)
                        +
                ((y2 - y1) * (y2 - y1))
                ));

        /*
        if(possibleRadius > 6)
        {
            radius = possibleRadius;
        }
        else
        {
            radius = 6;
        }
         */


        radius = (int) sqrt(
                ((x2 - x1) * (x2 - x1)
                        +
                        ((y2 - y1) * (y2 - y1))
                ));



        centerX = x1;
        centerY = y1;
    }

    @Override
    public boolean completelyOverlaps(shapes.Shape shape2) {
        return completelyOverlaps((Circle) shape2);
    }

    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Map<RenderingHints.Key, Object> hints = new HashMap<RenderingHints.Key, Object>();
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(hints);

        g2.setStroke(new BasicStroke((int) (3 * GraphicsDisplay.scalingFactor)));
        g2.drawOval((int) ((centerX - radius) * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) ((centerY - radius) * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (radius * 2 * GraphicsDisplay.scalingFactor), (int) (radius * 2 * GraphicsDisplay.scalingFactor));
    }

    public boolean containsPoint(Point point)
    {
        int distanceSquared = (int) (Math.pow(point.getX() - centerX,2) + Math.pow(point.getY() - centerY, 2));
        int distance = (int) sqrt(distanceSquared);

        if((distance + 10) < radius)
        {
            return true;
        }

        return false;
    }

    public boolean containsPointForGraph(Point point)
    {
        int distanceSquared = (int) (Math.pow(point.getX() - centerX,2) + Math.pow(point.getY() - centerY, 2));
        int distance = (int) sqrt(distanceSquared);

        if(distance + 5 < radius)
        {
            return true;
        }

        return false;
    }

    public boolean definitelyContainsPoint(Point point)
    {
        int distanceSquared = (int) (Math.pow(point.getX() - centerX,2) + Math.pow(point.getY() - centerY, 2));
        int distance = (int) sqrt(distanceSquared);

        if((distance + 30) < radius)
        {
            return true;
        }

        return false;
    }

    public boolean hasPoint(Point point)
    {
        int distanceToRadius = (int) Math.sqrt(Math.pow(point.getY() - centerY, 2) + Math.pow(point.getX() - centerX, 2));

        if(distanceToRadius + 5 > radius && distanceToRadius - 5 < radius)
        {
            return  true;
        }
        return false;
    }


    public boolean intersects(Circle circle)
    {
        int radiusSumSquared = (radius + circle.getRadius()) * (radius + circle.getRadius());
        int radiusDifferenceSquared = (radius - circle.getRadius()) * (radius - circle.getRadius());
        int distanceSquared = (centerX - circle.getCenterX()) * (centerX - circle.getCenterX()) + (centerY - circle.getCenterY()) * (centerY - circle.getCenterY());

        if(radiusDifferenceSquared <= distanceSquared && distanceSquared <= radiusSumSquared) {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getNumberOfIntersections(Circle circle)
    {
        if(intersects(circle))
        {
            return 2;
        }
        return 0;
    }

    public boolean completelyOverlaps(Circle circle)
    {
        double d = sqrt(Math.pow(circle.getCenterX() - centerX , 2) + Math.pow(circle.getCenterY() - centerY, 2));
        if(radius > (d + circle.getRadius()))
        {
            return  true;
        }
        else
        {
            return false;
        }
    }

    public LinkedList<Point> getIntersectionsWith(Circle circle)
    {
        Point P0 = new Point(centerX, centerY);
        Point P1 = new Point(circle.getCenterX(), circle.getCenterY());
        double d, a, h;
        d = P0.distance(P1);
        a = (Math.pow(radius, 2) - Math.pow(circle.getRadius(), 2) + Math.pow(d, 2)) / (2 * d);
        h = sqrt(Math.pow(radius, 2) - Math.pow(a, 2));
        Point P2 = P1.subtract(P0).scale(a/d).add(P0);
        int x3, y3, x4, y4;
        x3 = (int) (P2.getX() + h*(P1.getY() - P0.getY())/d);
        y3 = (int) (P2.getY() - h*(P1.getX() - P0.getX())/d);
        x4 = (int) (P2.getX() - h*(P1.getY() - P0.getY())/d);
        y4 = (int) (P2.getY() + h*(P1.getX() - P0.getX())/d);

        LinkedList<Point> points = new LinkedList<>();
        points.add(new Point(x3, y3));
        points.add(new Point(x4, y4));

        return points;
    }

    public double getDegreesFromCenter(Point point)
    {
        return atan2(point.getY() - centerY, point.getX() - centerX);
    }

    @Override
    public int getNumberOfIntersections(Shape shape2) {
        return getNumberOfIntersections((Circle) shape2);
    }
}