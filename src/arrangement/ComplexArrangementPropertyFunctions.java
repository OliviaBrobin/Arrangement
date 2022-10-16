package arrangement;

import shapes.*;

import java.util.*;

import static arrangement.Arrangement.*;
import static shapes.PointFunctions.*;

public class ComplexArrangementPropertyFunctions {
    public static LinkedList<Point> getContainedPoints(LinkedList<Point> points, LinkedList<LinkedList<Point>> regions)
    {
        LinkedList<Point> containedPoints = new LinkedList<>();
        containedPoints.addAll(getDefinitelyContainedPointsForRegions(points, regions));
        return containedPoints;
    }

    public static LinkedList<Point> getDefinitelyContainedPointsForRegions(LinkedList<Point> points, LinkedList<LinkedList<Point>> regions)
    {
        LinkedList<Point> pointsInside = new LinkedList<>();

        for(Point point : points)
        {
            for(LinkedList<Point> region : regions)
            {
                if(isPointDefinitelyInPolygon(point, region))
                {
                    pointsInside.add(point);
                    break;
                }
            }
        }

        return pointsInside;
    }

    public static LinkedList<Point> getPrettyMuchContainedPointsForRegions(LinkedList<Point> points, LinkedList<LinkedList<Point>> regions)
    {
        LinkedList<Point> pointsInside = new LinkedList<>();

        for(Point point : points)
        {
            for(LinkedList<Point> region : regions)
            {
                if(PointFunctions.isPointPrettyMuchInPolygon(point, region))
                {
                    pointsInside.add(point);
                    break;
                }
            }
        }

        return pointsInside;
    }

    public static boolean isPointPrettyMuchContainedInRegions(Point point, LinkedList<LinkedList<Point>> regions)
    {
        for(LinkedList<Point> region : regions)
        {
            if(isPointPrettyMuchInPolygon(point, region))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean isPointDefinitelyContainedInRegions(Point point, LinkedList<LinkedList<Point>> regions)
    {
        for(LinkedList<Point> region : regions)
        {
            if(isPointDefinitelyInPolygon(point, region))
            {
                return true;
            }
        }

        return false;
    }

    public static LinkedList<Point> getContainedPointsForCirclesForDistinct(LinkedList<Point> points)
    {
        LinkedList<Point> containedPoints = new LinkedList<Point>();

        for(Point point : points)
        {
            for(Circle circle : circles)
            {
                if(circle.containsPoint(point))
                {
                    containedPoints.add(point);
                    break;
                }
            }
        }

        return containedPoints;
    }

    public static boolean checkIntersectsCircleAndLine(Circle circle, Line line)
    {
        // Find the points of intersection.
        int cx = circle.getCenterX();
        int cy = circle.getCenterY();
        int radius = circle.getRadius();

        Point point1 = new Point(line.getX1(), line.getY1());
        Point point2 = new Point(line.getX2(), line.getY2());

        float dx, dy, A, B, C, det, t;

        dx = point2.getX() - point1.getX();
        dy = point2.getY() - point1.getY();

        A = dx * dx + dy * dy;
        B = 2 * (dx * (point1.getX() - cx) + dy * (point1.getY() - cy));
        C = (point1.getX() - cx) * (point1.getX() - cx) + (point1.getY() - cy) * (point1.getY() - cy) - radius * radius;

        det = B * B - 4 * A * C;
        if ((A <= 0.0000001) || (det < 0))
        {
            // No real solutions.
            //intersection1 = new PointF(float.NaN, float.NaN);
            //intersection2 = new PointF(float.NaN, float.NaN);
            //return 0;
            return false;
        }
        else if (det == 0)
        {
            // One solution.
            //t = -B / (2 * A);
            //Point intersection1 = new Point((int) (point1.getX() + t * dx), (int) (point1.getY() + t * dy));
            //intersection2 = new PointF(float.NaN, float.NaN);
            //return 1;
            //return true;
            //if(isOnLine(line, intersection1, 20)) {
            //    return true;
            //}
            //else
            //{
            //    return false;
            //}
        }
        else
        {
            //Two solutions.
            t = (float)((-B + Math.sqrt(det)) / (2 * A));
            Point intersection1 = new Point((int) (point1.getX() + t * dx), (int) (point1.getY() + t * dy));
            t = (float)((-B - Math.sqrt(det)) / (2 * A));
            Point intersection2 = new Point((int) (point1.getX() + t * dx), (int) (point1.getY() + t * dy));
            //return 2;
            if(isPointOnLine(line, intersection1, 3) || isPointOnLine(line, intersection2, 3)) {
                return true;
            }
            else
            {
                return false;
            }
        }

        return true;
    }

    public static boolean isPointOnLine(Line line, Point C, double tolerance) {
        Point A = new Point(line.getX1(), line.getY1());
        Point B = new Point(line.getX2(), line.getY2());

        double minX = Math.min(A.getX(), B.getX()) - tolerance;
        double maxX = Math.max(A.getX(), B.getX()) + tolerance;
        double minY = Math.min(A.getY(), B.getY()) - tolerance;
        double maxY = Math.max(A.getY(), B.getY()) + tolerance;

        //Check C is within the bounds of the line
        if (C.getX() >= maxX || C.getX() <= minX || C.getY() <= minY || C.getY() >= maxY) {
            return false;
        }

        // Check for when AB is vertical
        if (A.getX() == B.getX()) {
            if (Math.abs(A.getX() - C.getX()) >= tolerance) {
                return false;
            }
            return true;
        }

        // Check for when AB is horizontal
        if (A.getY() == B.getY()) {
            if (Math.abs(A.getY() - C.getY()) >= tolerance) {
                return false;
            }
            return true;
        }


        // Check distance of the point form the line
        double distFromLine = Math.abs(((B.getX() - A.getX()) * (A.getY() - C.getY())) - ((A.getX() - C.getX()) * (B.getY() - A.getY()))) / Math.sqrt((B.getX() - A.getX()) * (B.getX() - A.getX()) + (B.getY() - A.getY()) * (B.getY() - A.getY()));

        if (distFromLine >= tolerance) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isTestedVariableTrue()
    {
        return true;
    }

    public static int getNumberOfNotContainedSoloCircles()
    {
        int numberOfNotContainedSoloCircles = 0;

        for(Circle circle1: circles){
            boolean skip = false;
            for(Circle circle2: circles){
                if(!circle1.equals(circle2)) {
                    if (circle1.intersects(circle2)) {
                        skip = true;
                    }
                }
            }

            for(Circle circle2: circles){
                if(!circle1.equals(circle2)) {
                    if (circle2.completelyOverlaps(circle1)) {
                        skip = true;
                    }
                }
            }

            if(!skip)
            {
                numberOfNotContainedSoloCircles++;
            }
        }

        return numberOfNotContainedSoloCircles;
    }

    public static int getNumberOfContainedSoloCircles()
    {
        int numberOfNotContainedSoloCircles = 0;

        for(Circle circle1: circles){
            boolean skip = false;
            for(Circle circle2: circles){
                if(!circle1.equals(circle2)) {
                    if (circle1.intersects(circle2)) {
                        skip = true;
                    }
                }
            }

            for(Circle circle2: circles){
                if(!circle1.equals(circle2)) {
                    if (circle2.completelyOverlaps(circle1)) {
                        skip = false;
                    }
                }
            }

            if(!skip)
            {
                numberOfNotContainedSoloCircles++;
            }
        }

        return numberOfNotContainedSoloCircles;
    }

    public static int getNumberOfPointsFromSections(LinkedList<LinkedList<Point>> sections)
    {
        return getPointsFromSections(sections).size();
    }

    public static LinkedList<Point> getPointsFromSections(LinkedList<LinkedList<Point>> sections)
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
}
