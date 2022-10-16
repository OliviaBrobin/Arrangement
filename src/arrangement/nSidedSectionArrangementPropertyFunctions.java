package arrangement;

import shapes.Line;
import shapes.Point;

import java.util.Arrays;
import java.util.LinkedList;

import static shapes.PointFunctions.isPointDefinitelyInPolygon;

public class nSidedSectionArrangementPropertyFunctions {
    //For all line shapes.
    public static LinkedList<LinkedList<Point>> getTriangleAndSquareTriangleSectionsExpanded()
    {
        LinkedList<Line[]> triangleSections = new LinkedList<>();
        LinkedList<Line> allLines = Arrangement.getTriangleAndSquareLines();
        for(Line triangleLine1 : allLines)
        {
            for(Line triangleLine2 : allLines)
            {
                for(Line triangleLine3 : allLines)
                {

                    if(!triangleLine1.equals(triangleLine2) && !triangleLine2.equals(triangleLine3) && !triangleLine3.equals(triangleLine1)) {
                        if (triangleLine1.intersects(triangleLine2) && triangleLine2.intersects(triangleLine3) && triangleLine3.intersects(triangleLine1)) {
                            //Create a new set of lines that are cut off where the triangle has its intersections.
                            Point intersection1 = triangleLine1.getIntersectionWith(triangleLine2);
                            Point intersection2 = triangleLine2.getIntersectionWith(triangleLine3);
                            Point intersection3 = triangleLine3.getIntersectionWith(triangleLine1);
                            Point[] points = {intersection1, intersection2, intersection3};

                            Line cutOffTriangleLine1 = new Line();
                            cutOffTriangleLine1.setX1(points[0].getX());
                            cutOffTriangleLine1.setY1(points[0].getY());
                            cutOffTriangleLine1.setX2(points[1].getX());
                            cutOffTriangleLine1.setY2(points[1].getY());

                            Line cutOffTriangleLine2 = new Line();
                            cutOffTriangleLine2.setX1(points[1].getX());
                            cutOffTriangleLine2.setY1(points[1].getY());
                            cutOffTriangleLine2.setX2(points[2].getX());
                            cutOffTriangleLine2.setY2(points[2].getY());

                            Line cutOffTriangleLine3 = new Line();
                            cutOffTriangleLine3.setX1(points[2].getX());
                            cutOffTriangleLine3.setY1(points[2].getY());
                            cutOffTriangleLine3.setX2(points[0].getX());
                            cutOffTriangleLine3.setY2(points[0].getY());

                            Line[] cutOffTriangleLines = {cutOffTriangleLine1, cutOffTriangleLine2, cutOffTriangleLine3};
                            triangleSections.add(cutOffTriangleLines);

                            //Remove triangle sections that have a single line that passes through a single edge
                            for (Line line : allLines) {
                                if (!line.equals(triangleLine1) && !line.equals(triangleLine2) && !line.equals(triangleLine3)) {
                                    if ((line.intersects(cutOffTriangleLine1)) || (line.intersects(cutOffTriangleLine2))  || (line.intersects(cutOffTriangleLine3))) {
                                        triangleSections.remove(cutOffTriangleLines);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return RegionArrangementPropertyFunctions.getPointsFromRegions(triangleSections);
    }

    //For all line shapes, almost works except for that one convex case for lines
    public static LinkedList<LinkedList<Point>> getTriangleAndSquareQuadrilateralSectionsExpanded()
    {
        LinkedList<Line[]> triangleSections = new LinkedList<>();
        LinkedList<Line> allLines = Arrangement.getTriangleAndSquareLines();

        for(Line triangleLine1 : allLines)
        {
            for(Line triangleLine2 : allLines)
            {
                for(Line triangleLine3 : allLines)
                {
                    for(Line triangleLine4 : allLines)
                    {

                        if(!triangleLine1.equals(triangleLine2) && !triangleLine1.equals(triangleLine3) && !triangleLine1.equals(triangleLine4) && !triangleLine2.equals(triangleLine3) && !triangleLine2.equals(triangleLine4) && !triangleLine3.equals(triangleLine4)) {
                            if (triangleLine1.intersects(triangleLine2) && triangleLine2.intersects(triangleLine3) && triangleLine3.intersects(triangleLine4) && triangleLine4.intersects(triangleLine1)) {
                                // && !triangleLine1.intersects(triangleLine3) && !triangleLine2.intersects(triangleLine4)

                                //Create a new set of lines that are cut off where the triangle has its intersections.
                                Point intersection1 = triangleLine1.getIntersectionWith(triangleLine2);
                                Point intersection2 = triangleLine2.getIntersectionWith(triangleLine3);
                                Point intersection3 = triangleLine3.getIntersectionWith(triangleLine4);
                                Point intersection4 = triangleLine4.getIntersectionWith(triangleLine1);
                                Point[] points = {intersection1, intersection2, intersection3, intersection4};

                                LinkedList<Point> pointsForQuadrilateral = new LinkedList<>();
                                pointsForQuadrilateral.add(intersection1);
                                pointsForQuadrilateral.add(intersection2);
                                pointsForQuadrilateral.add(intersection3);
                                pointsForQuadrilateral.add(intersection4);


                                Line cutOffTriangleLine1 = new Line();
                                cutOffTriangleLine1.setX1(points[0].getX());
                                cutOffTriangleLine1.setY1(points[0].getY());
                                cutOffTriangleLine1.setX2(points[1].getX());
                                cutOffTriangleLine1.setY2(points[1].getY());

                                Line cutOffTriangleLine2 = new Line();
                                cutOffTriangleLine2.setX1(points[1].getX());
                                cutOffTriangleLine2.setY1(points[1].getY());
                                cutOffTriangleLine2.setX2(points[2].getX());
                                cutOffTriangleLine2.setY2(points[2].getY());

                                Line cutOffTriangleLine3 = new Line();
                                cutOffTriangleLine3.setX1(points[2].getX());
                                cutOffTriangleLine3.setY1(points[2].getY());
                                cutOffTriangleLine3.setX2(points[3].getX());
                                cutOffTriangleLine3.setY2(points[3].getY());

                                Line cutOffTriangleLine4 = new Line();
                                cutOffTriangleLine4.setX1(points[3].getX());
                                cutOffTriangleLine4.setY1(points[3].getY());
                                cutOffTriangleLine4.setX2(points[0].getX());
                                cutOffTriangleLine4.setY2(points[0].getY());

                                Line[] cutOffTriangleLines = {cutOffTriangleLine1, cutOffTriangleLine2, cutOffTriangleLine3, cutOffTriangleLine4};
                                LinkedList<Line> cutOffTriangleLinesAsList = new LinkedList<Line>();
                                cutOffTriangleLinesAsList.addAll(Arrays.stream(cutOffTriangleLines).toList());
                                triangleSections.add(cutOffTriangleLines);

                                //Remove hourglass sections
                                if(cutOffTriangleLine1.intersects(cutOffTriangleLine3) || cutOffTriangleLine2.intersects(cutOffTriangleLine4))
                                {
                                    triangleSections.remove(cutOffTriangleLines);
                                }

                                //Remove triangle sections that have a single line that passes through two sides of them.
                                for (Line line : allLines) {
                                    if (!line.equals(triangleLine1) && !line.equals(triangleLine2) && !line.equals(triangleLine3) && !line.equals(triangleLine4)) {
                                        if (line.intersects(cutOffTriangleLine1) || line.intersects(cutOffTriangleLine2)|| line.intersects(cutOffTriangleLine3)|| line.intersects(cutOffTriangleLine4)) {
                                            triangleSections.remove(cutOffTriangleLines);
                                        }
                                    }
                                }

                                for(Point point : Arrangement.getPoints())
                                {
                                    if(isPointDefinitelyInPolygon(point, pointsForQuadrilateral))
                                    {
                                        triangleSections.remove(cutOffTriangleLines);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return RegionArrangementPropertyFunctions.getPointsFromRegions(triangleSections);
    }

    public static LinkedList<LinkedList<Point>> getTriangleAndSquareQuadrilateralSections()
    {
        return  RegionArrangementPropertyFunctions.getSections(getTriangleAndSquareQuadrilateralSectionsExpanded());
    }

    public static LinkedList<LinkedList<Point>> getTriangleAndSquareTriangleSections()
    {
        return  RegionArrangementPropertyFunctions.getSections(getTriangleAndSquareTriangleSectionsExpanded());
    }
}
