package shapes;


import shapes.Line;
import shapes.Point;
import shapes.Shape;

import java.awt.geom.Line2D;
import java.util.LinkedList;

public abstract class LineShape extends Shape {
    public abstract Line[] getLines();
    public abstract Point[] getPoints();

    public abstract int compareTo(Line line);

    public int getNumberOfIntersections(LineShape lineShape)
    {
        int numOfInteresections = 0;
        for(int x = 0 ; x < getLines().length; x++)
        {
            Line2D line1 = new Line2D.Float(getLines()[x].x1, getLines()[x].y1, getLines()[x].x2, getLines()[x].y2);

            for(int y = 0 ; y < lineShape.getLines().length; y++)
            {
                Line2D line2 = new Line2D.Float(lineShape.getLines()[y].x1, lineShape.getLines()[y].y1, lineShape.getLines()[y].x2, lineShape.getLines()[y].y2);

                if(line1.intersectsLine(line2))
                {
                    numOfInteresections++;
                }

            }
        }

        return numOfInteresections;
    }

    public LinkedList<Point> getIntersectionsWith(LineShape lineShape)
    {
        LinkedList<Point> intersections = new LinkedList<>();
        for(int x = 0 ; x < getLines().length; x++)
        {
            Line line1 = getLines()[x];

            for(int y = 0 ; y < lineShape.getLines().length; y++)
            {
                Line line2 = lineShape.getLines()[y];

                if(line1.intersects(line2))
                {
                    intersections.add(line1.getIntersectionWith(line2));
                }

            }
        }

        return intersections;
    }

    public boolean intersects(LineShape lineShape)
    {
        if(getNumberOfIntersections(lineShape) > 0)
        {
            return  true;
        }
        return  false;
    }

    public boolean containsPointForGraph(Point pointToCheck) {
        if(type.equals("triangle"))
        {
            return ((Triangle) this).contains(pointToCheck);
        }
        else if(type.equals("square"))
        {
            return ((Square) this).contains(pointToCheck);
        }
        else
        {
            return false;
        }
    }
}
