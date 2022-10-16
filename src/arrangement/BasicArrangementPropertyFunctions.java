package arrangement;

import shapes.*;

import java.util.Collections;
import java.util.LinkedList;

import static arrangement.Arrangement.*;

public class BasicArrangementPropertyFunctions {

    public static LinkedList<Integer> getIntersectionsPerSide()
    {
        LinkedList<Integer> intersectionsPerSide = new LinkedList<Integer>();

        intersectionsPerSide.addAll(getCirclesUnsortedIntersectionsPerSide());

        intersectionsPerSide.addAll(getLineShapesUnsortedIntersectionsPerSide());

        Collections.sort(intersectionsPerSide);
        Collections.reverse(intersectionsPerSide);

        return intersectionsPerSide;
    }

    public static LinkedList<Integer> getCirclesUnsortedIntersectionsPerSide()
    {
        LinkedList<Integer> intersectionsPerSide = new LinkedList<Integer>();

        for(Circle circle1 : circles)
        {
            int intersections = 0;
            for(Circle circle2 : circles)
            {
                if(!circle1.equals(circle2))
                {
                    if(circle1.intersects(circle2))
                    {
                        intersections += 2;
                    }
                }
            }
            intersectionsPerSide.add(intersections);
        }

        return intersectionsPerSide;
    }

    public static  LinkedList<Integer> getLineShapesUnsortedIntersectionsPerSide()
    {
        LinkedList<Integer> intersectionsPerSide = new LinkedList<Integer>();

        LinkedList<LineShape> lineShapes = Arrangement.getLineShapes();
        for(LineShape lineShape1 : lineShapes)
        {
            Line[] lines1 = lineShape1.getLines();
            for(Line line1 : lines1)
            {
                int intersections = 0;
                for(LineShape lineShape2 : lineShapes)
                {
                    if(!lineShape1.equals(lineShape2))
                    {
                        Line[] lines2 = lineShape2.getLines();
                        for(Line line2 : lines2)
                        {
                            if(!line1.equals(line2))
                            {
                                if(line1.intersects(line2))
                                {
                                    intersections++;
                                }
                            }
                        }
                    }
                }
                intersectionsPerSide.add(intersections);
            }
        }

        return intersectionsPerSide;
    }

    public static LinkedList<Point> getIntersections()
    {
        LinkedList<Point> points = new LinkedList<Point>();

        points.addAll(getCircleIntersections());

        points.addAll(getLineShapeIntersections(getLineShapes()));

        return points;
    }

    public static LinkedList<Point> getWholeShapeIntersections()
    {
        LinkedList<Point> points = new LinkedList<Point>();

        points.addAll(getCircleIntersections());

        points.addAll(getLineShapeIntersections(getTrianglesAndSquares()));

        return points;
    }

    public static  int getNumberOfWholeShapeIntersections()
    {
        return getWholeShapeIntersections().size();
    }

    public static LinkedList<Point> getCircleIntersections()
    {
        LinkedList<Point> intersections = new LinkedList<>();
        for(int x = 0 ; x < circles.size() ; x++)
        {
            for (int y = x + 1 ; y < circles.size() ; y++)
            {
                if(
                        circles.get(x).intersects(circles.get(y)))
                {
                    intersections.addAll(circles.get(x).getIntersectionsWith(circles.get(y)));
                }
            }
        }

        return intersections;
    }

    public static LinkedList<Point> getLineShapeIntersections(LinkedList<LineShape> lineShapes)
    {
        LinkedList<Point> points = new LinkedList<Point>();

        for(int x = 0 ; x <lineShapes.size() ; x++)
        {
            Line[] lines1 = lineShapes.get(x).getLines();
            for(Line line1 : lines1)
            {
                for(int y = x + 1 ; y < lineShapes.size() ; y++)
                {
                    if(!lineShapes.get(x).equals(lineShapes.get(y)))
                    {
                        Line[] lines2 = lineShapes.get(y).getLines();
                        for(Line line2 : lines2)
                        {
                            if(!line1.equals(line2))
                            {
                                if(line1.intersects(line2))
                                {
                                    Point point = line1.getIntersectionWith(line2);
                                    if(point != null) {
                                        points.add(point);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return points;
    }

    //For all whole shapes
    public static int getNumberOfCompleteOverlaps()
    {
        return getNumberOfCompleteOverlaps(Arrangement.getShapes());
    }

    public static int getNumberOfCompleteOverlapsForDistinctCircles()
    {
        int total = 0;

        for(Circle circle1 : circles)
        {
            for(Circle circle2 : circles)
            {
                if(!circle1.equals(circle2))
                {
                    if(circle2.completelyOverlaps(circle1))
                    {
                        total++;
                    }
                }
            }
        }

        return total;
    }

    public static int getNumberOfCompleteOverlaps(LinkedList<Shape> shapes)
    {
        int total = 0;

        for(Shape shape1 : shapes)
        {
            for(Shape shape2 : shapes)
            {
                if(!shape1.equals(shape2))
                {
                    if(shape2.completelyOverlaps(shape1))
                    {
                        total++;
                        break;
                    }
                }
            }
        }

        return total;
    }

    //For all shapes, separately
    public static int getTotalNumberOfEdges(int numberOfIntersections)
    {
        int numberOfLineSegments = 0;

        numberOfLineSegments += lines.size();
        numberOfLineSegments += triangles.size() * 3;
        numberOfLineSegments += squares.size() * 4;

        numberOfLineSegments += numberOfIntersections * 2;

        numberOfLineSegments += getNumberOfSoloShapes(Arrangement.getCirclesAsShapes());


        return numberOfLineSegments;
    }

    public static int getNumberOfSoloShapes(LinkedList<Shape> shapes)
    {
        int numberOfSoloShapes = 0;

        for(int x = 0 ; x < shapes.size() ; x++)
        {
            boolean intersects = false;
            for (int y = 0 ; y < shapes.size() ; y++)
            {
                if(!shapes.get(x).equals(shapes.get(y)))
                {
                    if(shapes.get(x).getNumberOfIntersections(shapes.get(y)) > 0)
                    {
                        intersects = true;
                    }
                }
            }
            if(!intersects)
            {
                numberOfSoloShapes++;
            }
        }

        return  numberOfSoloShapes;
    }

    public static int getNumberOfWholeShapeSections()
    {
        return getNumberOfWholeShapeIntersections() + getNumberOfWholeShapeClusters();
    }

    public static LinkedList<LinkedList<Shape>> getShapeClusters(LinkedList<Shape> shapes)
    {
        LinkedList<LinkedList<Shape>> clusters = new LinkedList<>();
        for(Shape shape : shapes)
        {
            LinkedList<Shape> cluster = new LinkedList<>();
            cluster.add(shape);
            clusters.add(cluster);
        }

        for (int x = clusters.size() - 1; x >= 0; x--) {
            for (int y = clusters.size() - 1; y >= 0; y--) {
                if (x > clusters.size() - 1) {
                    x = clusters.size() - 1;
                }

                if (y > clusters.size() - 1) {
                    y = clusters.size() - 1;
                }


                if (x != y) {
                    boolean doBreak = false;
                    for (Shape shapeX : clusters.get(x)) {
                        for (Shape shapeY : clusters.get(y)) {
                            if (!shapeX.equals(shapeY)) {
                                if (shapeX.getNumberOfIntersections(shapeY) > 0) {
                                    clusters.get(x).addAll(clusters.get(y));
                                    clusters.remove(y);
                                    y++;
                                    doBreak = true;
                                    break;
                                }
                            }
                        }
                        if (doBreak) {
                            break;
                        }
                    }
                    if (doBreak) {
                        break;
                    }
                }
            }
        }

        return clusters;
    }

    public static LinkedList<LinkedList<Circle>> getCircleClusters()
    {
        LinkedList<LinkedList<Circle>> clusters = new LinkedList<>();

        for(Circle circle : circles)
        {
            LinkedList<Circle> cluster = new LinkedList<>();
            cluster.add(circle);
            clusters.add(cluster);
        }

        for (int x = clusters.size() - 1; x >= 0; x--) {
            for (int y = clusters.size() - 1; y >= 0; y--) {
                if (x > clusters.size() - 1) {
                    x = clusters.size() - 1;
                }

                if (y > clusters.size() - 1) {
                    y = clusters.size() - 1;
                }


                if (x != y) {
                    boolean doBreak = false;
                    for (Circle circleX : clusters.get(x)) {
                        for (Circle circleY : clusters.get(y)) {
                            if (!circleX.equals(circleY)) {
                                if (circleX.getNumberOfIntersections(circleY) > 0) {
                                    clusters.get(x).addAll(clusters.get(y));
                                    clusters.remove(y);
                                    y++;
                                    doBreak = true;
                                    break;
                                }
                            }
                        }
                        if (doBreak) {
                            break;
                        }
                    }
                    if (doBreak) {
                        break;
                    }
                }
            }
        }

        return clusters;
    }

    public static int getNumberOfWholeShapeClusters()
    {
        return getShapeClusters(getWholeShapes()).size();
    }

}
