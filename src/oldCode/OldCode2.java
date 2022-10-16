package oldCode;

import shapes.Circle;
import shapes.Point;
import shapes.Square;
import shapes.Triangle;

import java.util.LinkedList;

import static arrangement.Arrangement.*;
import static shapes.PointFunctions.*;

public class OldCode2 {

    //For circles and line shapes separately
    public static LinkedList<Point> getContainedIntersections(LinkedList<Point> intersections, LinkedList<LinkedList<Point>> lineRegions)
    {
        LinkedList<Point> containedIntersections = new LinkedList<>();
        LinkedList<Point> points = intersections;

        for(Point point : points)
        {
            for(Circle circle : circles)
            {
                if(circle.containsPoint(point))
                {
                    containedIntersections.add(point);
                    break;
                }
            }
        }


        for(Point point : points)
        {
            for(Triangle triangle: triangles)
            {
                if(isPointDefinitelyInPolygon(point, triangle.getPointsAsList()))
                {
                    containedIntersections.add(point);
                    break;
                }
            }
        }


        for(Point point : points)
        {
            for(Square square: squares)
            {
                if(isPointDefinitelyInPolygon(point, square.getPointsAsList()))
                {
                    containedIntersections.add(point);
                    break;
                }
            }
        }

        for(Point point : points)
        {
            for(LinkedList<Point> region : lineRegions)
            {
                if(isPointDefinitelyInPolygon(point, region))
                {
                    containedIntersections.add(point);
                    break;
                }
            }
        }

        return containedIntersections;
    }

    /*
    public static LinkedList<Point> getContainedIntersectionsAndPointsForWholeShapes(LinkedList<Point> allPoints)
    {
        LinkedList<Point> containedPoints = new LinkedList<Point>();


        for(Point point : allPoints)
        {
            for(Triangle triangle: triangles)
            {
                if(isPointDefinitelyInPolygon(point, triangle.getPointsAsList()))
                {
                    containedPoints.add(point);
                    break;
                }
            }
        }


        for(Point point : allPoints)
        {
            for(Square square: squares)
            {
                if(isPointDefinitelyInPolygon(point, square.getPointsAsList()))
                {
                    containedPoints.add(point);
                    break;
                }
            }
        }


        return containedPoints;
    }
     */

    /*
    public static int getNumberOfOutsideEdgesForLines(LinkedList<LinkedList<Point>> sections)
    {
        LinkedList<Point> points = new LinkedList<>();
        LinkedList<Point> allPoints = new LinkedList<>();

        for(LinkedList<Point> section : sections)
        {
            for(Point sectionPoint : section)
            {
                allPoints.add(sectionPoint);

                boolean alreadyContained = false;
                for(Point point : points)
                {
                    if(sectionPoint.getY() == point.getY() && sectionPoint.getX() == point.getX())
                    {
                        alreadyContained = true;
                        break;
                    }
                }

                if(!alreadyContained)
                {
                    points.add(sectionPoint);
                }
            }
        }

        int[] numberOfPointsPerPoint = new int[points.size()];

        for(int x = 0 ; x < numberOfPointsPerPoint.length ; x++)
        {
            numberOfPointsPerPoint[x] = 0;
        }

        for(Point sectionPoint : allPoints)
        {
            for(int x = 0 ; x < points.size() ; x++)
            {
                Point point = points.get(x);
                if(sectionPoint.getY() == point.getY() && sectionPoint.getX() == point.getX())
                {
                    numberOfPointsPerPoint[x]++;
                }
            }
        }

        int numberOfOutsideEdges = 0;

        for(int x = 0 ; x < numberOfPointsPerPoint.length ; x++)
        {
            if(numberOfPointsPerPoint[x] < 3)
            {
                numberOfOutsideEdges++;
            }
        }


        return numberOfOutsideEdges;
    }
     */

    /*
    public static HashSet<Point> getPointsFromSections(LinkedList<LinkedList<Point>> sections)
    {
        HashSet<Point> points = new HashSet<>();

        for(LinkedList<Point> section : sections)
        {
            for(Point sectionPoint : section)
            {
                points.add(sectionPoint);
            }
        }

        return points;
    }
     */

    /*
    public static LinkedList<Point> getNumberOfOutsideEdgesFromSections(LinkedList<LinkedList<Point>> sections)
    {
        LinkedList<Point> points = new LinkedList<>();

        for(LinkedList<Point> section : sections)
        {
            for(Point sectionPoint : section)
            {
                boolean alreadyContained = false;
                for(Point point : points)
                {
                    if(sectionPoint.getY() == point.getY() && sectionPoint.getX() == point.getX())
                    {
                        alreadyContained = true;
                        break;
                    }
                }

                if(!alreadyContained)
                {
                    points.add(sectionPoint);
                }
            }
        }

        return points;
    }
     */
}
