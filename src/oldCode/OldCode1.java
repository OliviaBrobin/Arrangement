package oldCode;

import shapes.Circle;

import java.util.LinkedList;

import static arrangement.Arrangement.circles;

public class OldCode1 {
    //For circles and line shapes separately
    //getNumberOfIntersections
    /*
    public static int getNumberOfIntersections()
    {
        //TODO Make each for loop only loop through the options once and use getNumberOfIntersections.
        int numberOfIntersections = 0;
        for(int x = 0 ; x < circles.size() ; x++)
        {
            for (int y = x + 1 ; y < circles.size() ; y++)
            {
                if(!circles.get(x).equals(circles.get(y)))
                {
                    numberOfIntersections += circles.get(x).getNumberOfIntersections(circles.get(y));
                }
            }
        }

        int numberOfLineIntersections = 0;

        LinkedList<LineShape> lineShapes = getLineShapes();

        for(LineShape lineShape1 : lineShapes)
        {
            Line[] lines1 = lineShape1.getLines();
            for(Line line1 : lines1)
            {
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
                                    numberOfLineIntersections++;
                                }
                            }
                        }
                    }
                }
            }
        }

        numberOfIntersections += numberOfLineIntersections / 2;

        return numberOfIntersections;
    }
     */

    /*
    public static LinkedList<Line[]> getQuadrilateralSectionsAlternative()
    {
        LinkedList<Line[]> triangleSections = new LinkedList<>();
        LinkedList<Line> allLines = Arrangement.getAllLines();
        for(int a = 0 ; a < allLines.size() ; a++)
        {
            for(int b = a + 1 ; b < allLines.size() ; b++)
            {
                if(b > allLines.size() - 1)
                {
                    b = allLines.size() - 1;
                }
                for(int c = b + 1 ; c < allLines.size() ; c++)
                {
                    if(c > allLines.size() - 1)
                    {
                        c = allLines.size() - 1;
                    }
                    for(int d = c + 1 ; d < allLines.size() ; d++)
                    {
                        Line triangleLine1 = allLines.get(a);
                        Line triangleLine2 = allLines.get(b);
                        Line triangleLine3 = allLines.get(c);
                        Line triangleLine4 = allLines.get(d);

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
                                triangleSections.add(cutOffTriangleLines);

                                //Remove hourglass sections
                                if(cutOffTriangleLine1.intersects(cutOffTriangleLine3) || cutOffTriangleLine2.intersects(cutOffTriangleLine4))
                                {
                                    triangleSections.remove(cutOffTriangleLines);
                                }

                                //Remove triangle sections that have a single line that passes through two sides of them.
                                for (Line line : allLines) {
                                    if (!line.equals(triangleLine1) && !line.equals(triangleLine2) && !line.equals(triangleLine3) && !line.equals(triangleLine4)) {
                                        if (((line.intersects(cutOffTriangleLine1) && line.intersects(cutOffTriangleLine2)) || (line.intersects(cutOffTriangleLine1) && line.intersects(cutOffTriangleLine3)) || (line.intersects(cutOffTriangleLine1) && line.intersects(cutOffTriangleLine4)) || (line.intersects(cutOffTriangleLine2) && line.intersects(cutOffTriangleLine3)) || (line.intersects(cutOffTriangleLine2) && line.intersects(cutOffTriangleLine4)) || (line.intersects(cutOffTriangleLine3) && line.intersects(cutOffTriangleLine4)))) {
                                            triangleSections.remove(cutOffTriangleLines);
                                        }
                                    }
                                }


                                LinkedList<Line> allLinesClone = Line.cloneList(allLines);
                                Line.removeElement(allLinesClone, triangleLine1);
                                Line.removeElement(allLinesClone, triangleLine2);
                                Line.removeElement(allLinesClone, triangleLine3);
                                Line.removeElement(allLinesClone, triangleLine4);

                                //Remove triangle sections that intersect a line that intersects a side of the triangle and a line that intersects another line that intersect a side of the triangle.
                                //TODO The recursive element currently isn't working properly.
                                for (Line line : allLinesClone) {
                                    if (line.intersects(cutOffTriangleLine1) || line.intersects(cutOffTriangleLine2) || line.intersects(cutOffTriangleLine3) || line.intersects(cutOffTriangleLine4)) {
                                        LinkedList<Line> allLinesClonedTwice = Line.cloneList(allLinesClone);
                                        Line.removeElement(allLinesClonedTwice, line);
                                        if (lineIntersectsChainThatIntersectsQuadrilateral(line, cutOffTriangleLines, allLinesClonedTwice.size() + 4, allLinesClonedTwice)) {
                                            triangleSections.remove(cutOffTriangleLines);
                                        }
                                    }
                                }


                                /*
                                //Functional code
                                for(Line linea : allLines)
                                {
                                    for(Line lineb : allLines)
                                    {
                                        if(!linea.equals(lineb)) {
                                            if (!linea.equals(line1) && !linea.equals(line2) && !linea.equals(line3)) {
                                                if (!lineb.equals(line1) && !lineb.equals(line2) && !lineb.equals(line3)) {
                                                    if (linea.intersects(lineb)) {
                                                        //Remove triangle sections that have two lines going into one side and intersecting each other
                                                        if (((linea.intersects(line4) && lineb.intersects(line4)) || (linea.intersects(line5) && lineb.intersects(line5)) || (linea.intersects(line6) && lineb.intersects(line6)))) {
                                                            triangleSections.remove(triangleLines);
                                                        }
                                                        //Remove triangle sections that have two lines going into two different sides and intersecting each other.
                                                        if (((linea.intersects(line4) && lineb.intersects(line5)) || (linea.intersects(line5) && lineb.intersects(line6)) || (linea.intersects(line6) && lineb.intersects(line4)))) {
                                                            triangleSections.remove(triangleLines);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                 */
    /*
                            }
                        }
                    }
                }
            }
        }

        return triangleSections;
    }
    */

    /*
    //TODO This can be generalized to work for any shape of any order?
    public static boolean lineIntersectsChainThatIntersectsTriangle(Line linea, Line[] cutoffTriangleLines, int linesRemaining, LinkedList<Line> allLines)
    {
        linesRemaining--;
        if(linesRemaining == 0) {
            return false;
        }

        Line cutOffTriangleLine1 = cutoffTriangleLines[0];
        Line cutOffTriangleLine2 = cutoffTriangleLines[1];
        Line cutOffTriangleLine3 = cutoffTriangleLines[2];

        Triangle triangle = new Triangle(cutOffTriangleLine1.getX1(), cutOffTriangleLine1.getY1(), cutOffTriangleLine2.getX1(), cutOffTriangleLine2.getY1(), cutOffTriangleLine3.getX1(), cutOffTriangleLine3.getY1());

        for(Line lineb : allLines)
        {
            if (linea.intersects(lineb)) {
                if(triangle.contains(linea.getIntersectionWith(lineb)))
                {
                    if (lineb.intersects(cutOffTriangleLine1) || lineb.intersects(cutOffTriangleLine2) || lineb.intersects(cutOffTriangleLine3)) {
                        return true;
                    } else {
                        LinkedList<Line> allLinesClone = Line.cloneList(allLines);
                        Line.removeElement(allLinesClone,lineb);
                        return lineIntersectsChainThatIntersectsTriangle(lineb, cutoffTriangleLines, linesRemaining, allLinesClone);
                    }
                }
            }
        }
        return false;
    }

    //TODO This code isn't working properly
    public static boolean lineIntersectsChainThatIntersectsQuadrilateral(Line linea, Line[] cutoffTriangleLines, int linesRemaining, LinkedList<Line> allLines)
    {
        linesRemaining--;
        if(linesRemaining == 0) {
            return false;
        }

        Line line4 = cutoffTriangleLines[0];
        Line line5 = cutoffTriangleLines[1];
        Line line6 = cutoffTriangleLines[2];
        Line line7 = cutoffTriangleLines[3];

        //Square quadrilateral = new Square(line4.getX1(), line4.getY1(), line5.getX1(), line5.getY1(), line6.getX1(), line6.getY1(), line7.getX1(), line7.getY1());

        for(Line lineb : allLines)
        {
            if (linea.intersects(lineb)) {
                //TODO Fix this so that it online works for quadrilaterals containing intersections rather than squares, which includes the convex case. (Possibly make a quadrilateral class?)
                //if(quadrilateral.contains(linea.getIntersectionWith(lineb)))
                //{
                if (lineb.intersects(line4) || lineb.intersects(line5) || lineb.intersects(line6) || lineb.intersects(line7)) {
                    return true;
                } else {
                    LinkedList<Line> allLinesClone = Line.cloneList(allLines);
                    Line.removeElement(allLinesClone,lineb);
                    return lineIntersectsChainThatIntersectsTriangle(lineb, cutoffTriangleLines, linesRemaining, allLinesClone);
                }
                //}
            }
        }
        return false;
    }

    //For all line shapes.
    public static LinkedList<Line[]> getTriangleSectionsAlternative()
    {
        LinkedList<Line[]> triangleSections = new LinkedList<>();
        LinkedList<Line> allLines = Arrangement.getAllLines();
        for(int a = 0 ; a < allLines.size() ; a++)
        {
            for(int b = a + 1 ; b < allLines.size() ; b++)
            {
                if(b > allLines.size() - 1)
                {
                    b = allLines.size() - 1;
                }
                for(int c = b + 1 ; c < allLines.size() ; c++)
                {
                    if(c > allLines.size() - 1)
                    {
                        c = allLines.size() - 1;
                    }
                    Line triangleLine1 = allLines.get(a);
                    Line triangleLine2 = allLines.get(b);
                    Line triangleLine3 = allLines.get(c);

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

                            //Remove triangle sections that have a single line that passes through two sides of them.
                            for (Line line : allLines) {
                                if (!line.equals(triangleLine1) && !line.equals(triangleLine2) && !line.equals(triangleLine3)) {
                                    if (((line.intersects(cutOffTriangleLine1) && line.intersects(cutOffTriangleLine2)) || (line.intersects(cutOffTriangleLine2) && line.intersects(cutOffTriangleLine3)) || (line.intersects(cutOffTriangleLine3) && line.intersects(cutOffTriangleLine1)))) {
                                        triangleSections.remove(cutOffTriangleLines);
                                    }
                                }
                            }


                            LinkedList<Line> allLinesClone = Line.cloneList(allLines);
                            Line.removeElement(allLinesClone,triangleLine1);
                            Line.removeElement(allLinesClone,triangleLine2);
                            Line.removeElement(allLinesClone,triangleLine3);

                            //Remove triangle sections that intersect a line that intersects a side of the triangle and a line that intersects another line that intersect a side of the triangle.
                            for(Line line : allLinesClone)
                            {
                                if(line.intersects(cutOffTriangleLine1) || line.intersects(cutOffTriangleLine2) || line.intersects(cutOffTriangleLine3))
                                {
                                    LinkedList<Line> allLinesClonedTwice = Line.cloneList(allLinesClone);
                                    Line.removeElement(allLinesClonedTwice,line);
                                    if(lineIntersectsChainThatIntersectsTriangle(line, cutOffTriangleLines, allLinesClonedTwice.size() + 1, allLinesClonedTwice))
                                    {
                                        triangleSections.remove(cutOffTriangleLines);
                                    }
                                }
                            }


                            /*
                            //Functional code
                            for(Line linea : allLines)
                            {
                                for(Line lineb : allLines)
                                {
                                    if(!linea.equals(lineb)) {
                                        if (!linea.equals(line1) && !linea.equals(line2) && !linea.equals(line3)) {
                                            if (!lineb.equals(line1) && !lineb.equals(line2) && !lineb.equals(line3)) {
                                                if (linea.intersects(lineb)) {
                                                    //Remove triangle sections that have two lines going into one side and intersecting each other
                                                    if (((linea.intersects(line4) && lineb.intersects(line4)) || (linea.intersects(line5) && lineb.intersects(line5)) || (linea.intersects(line6) && lineb.intersects(line6)))) {
                                                        triangleSections.remove(triangleLines);
                                                    }
                                                    //Remove triangle sections that have two lines going into two different sides and intersecting each other.
                                                    if (((linea.intersects(line4) && lineb.intersects(line5)) || (linea.intersects(line5) && lineb.intersects(line6)) || (linea.intersects(line6) && lineb.intersects(line4)))) {
                                                        triangleSections.remove(triangleLines);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                             */
    /*
                        }
                    }
                }
            }
        }
        */

        //Remove a triangle that has  a line inside of it.
        /*
        for(int x = triangleSections.size() - 1 ; x >= 0 ; x--)
        {
            Line[] triangleLines1 = triangleSections.get(x);
            Triangle triangle = new Triangle(triangleLines1[0].getX1(), triangleLines1[0].getY1(), triangleLines1[1].getX1(), triangleLines1[1].getY1(), triangleLines1[2].getX1(), triangleLines1[2].getY1());
            for(Line line : allLines) {
                if (triangle.contains(line.getPoints()[0]) && triangle.contains(line.getPoints()[1])) {
                    triangleSections.remove(triangleLines1);
                }
            }
        }
         */


        /*
        //remove triangle sections if they contain triangle sections inside of them. (And eventually sections of any order.) We could google to see if there's an algorithm for what we're doing.
        //This code doesn't work because the lines that are on top of each other are considered nto with in the triangle. Maybe, we could fix the contains method in triangle?
        for(int x = triangleSections.size() - 1 ; x >= 0 ; x--)
        {
            for(int y = triangleSections.size() - 1 ; y >= 0 ; y--)
            {
                Line[] triangleLines1 = triangleSections.get(x);
                Line[] triangleLines2 = triangleSections.get(x);

                if(!triangleLines1.equals(triangleLines2))
                {
                    Triangle triangle1 = new Triangle(triangleLines1[0].getX1(), triangleLines1[0].getY1(), triangleLines1[1].getX1(), triangleLines1[1].getY1(), triangleLines1[2].getX1(), triangleLines1[2].getY1());
                    Triangle triangle2 = new Triangle(triangleLines2[0].getX1(), triangleLines2[0].getY1(), triangleLines2[1].getX1(), triangleLines2[1].getY1(), triangleLines2[2].getX1(), triangleLines2[2].getY1());
                    if(triangle1.completelyOverlaps(triangle2))
                    {
                        triangleSections.remove(triangleLines1);
                    }
                }
            }
        }
         */
    /*
        return triangleSections;
    }
     */

    /*
    public static LinkedList<Line[]> getTriangleRegions()
    {
        //LinkedList<int[][]> triangleRegions = new LinkedList<>();
        LinkedList<Line[]> triangleRegions = new LinkedList<>();
        for(Line line1 : lines)
        {
            for(Line line2 : lines)
            {
                for(Line line3: lines)
                {
                    if(!line1.equals(line2) && !line2.equals(line3) && !line3.equals(line1))
                    {
                        if(line1.intersects(line2) && line2.intersects(line3) && line3.intersects(line1))
                        {
                            Point intersection1 = line1.getIntersectionWith(line2);
                            Point intersection2 = line2.getIntersectionWith(line3);
                            Point intersection3 = line3.getIntersectionWith(line1);
                            Point[] points = {intersection1, intersection2, intersection3};

                            Line line4 = new Line();
                            line4.setX1(points[0].getX());
                            line4.setY1(points[0].getY());
                            line4.setX2(points[1].getX());
                            line4.setY2(points[1].getY());

                            Line line5 = new Line();
                            line5.setX1(points[1].getX());
                            line5.setY1(points[1].getY());
                            line5.setX2(points[2].getX());
                            line5.setY2(points[2].getY());

                            Line line6 = new Line();
                            line6.setX1(points[2].getX());
                            line6.setY1(points[2].getY());
                            line6.setX2(points[0].getX());
                            line6.setY2(points[0].getY());
                            Line[] triangleLines = {line4, line5, line6};
                            triangleRegions.add(triangleLines);
                            //triangleRegions.add(intersections);
                        }
                    }
                }
            }
        }
        return triangleRegions;
    }

    //For lines, gets all number of quadrilateral regions
    public static int getNumberOfQuadrilateralRegions()
    {
        int numberOfSquareRegions = 0;

        for(Line line1 : lines)
        {
            for(Line line2 : lines)
            {
                for(Line line3: lines)
                {
                    for(Line line4: lines)
                    {
                        if (!line1.equals(line2) && !line2.equals(line3) && !line3.equals(line4) && !line4.equals(line1)) {
                            if (line1.intersects(line2) && line2.intersects(line3) && line3.intersects(line4) && line4.intersects(line1)) {
                                numberOfSquareRegions++;
                            }
                        }
                    }
                }
            }
        }

        return numberOfSquareRegions / 24;
    }

    //For lines, gets all number of triangle regions
    /*
    public static int getNumberOfTriangleRegions()
    {
        int numberOfTriangleRegions = 0;

        for(Line line1 : lines)
        {
            for(Line line2 : lines)
            {
                for(Line line3: lines)
                {
                    if(!line1.equals(line2) && !line2.equals(line3) && !line3.equals(line1))
                    {
                        if(line1.intersects(line2) && line2.intersects(line3) && line3.intersects(line1))
                        {
                            numberOfTriangleRegions++;
                        }
                    }
                }
            }
        }

        return numberOfTriangleRegions / 6;
    }

    public static int getNumberOfPointsInside()
    {
        int total = 0;

        LinkedList<Point> points = ArrangementFunctions.getPoints();

        for(Triangle triangle1 : triangles)
        {
            for(Triangle triangle2 : triangles)
            {
                if(!triangle1.equals(triangle2))
                {
                    for(Point point : points)
                    {
                        if(triangle1.contains(point))
                        {
                            total++;
                        }
                    }
                }
            }
        }

        for(Square square1 : squares)
        {
            for(Square square2 : squares)
            {
                if(!square1.equals(square2))
                {
                    points = square2.getPointsAsList();
                    for(Point point : points)
                    {
                        if(square1.contains(point))
                        {
                            total++;
                        }
                    }
                }
            }
        }


        points = ArrangementFunctions.getPoints();
        LinkedList<LinkedList<Point>> regionPoints = ArrangementPropertyFunctions.getLineRegions();
        for(Point point : points)
        {
            for(LinkedList<Point> region : regionPoints) {
                if (ArrangementPropertyFunctions.isPointDefinitelyInPolygon(point, region)) {
                    total++;
                    break;
                }
            }
        }

        return total;
    }
    */

    //isPointInPolygon alt function
    /*
    public static boolean isPointInPolygon(Point point, LinkedList<Point> polygon)
    {
        Point p = new Point(point.getX(), point.getY());
        double minX = polygon.get(0).getX();
        double maxX = polygon.get(0).getX();
        double minY = polygon.get(0).getY();
        double maxY = polygon.get(0).getY();
        for ( int i = 1 ; i < polygon.size() ; i++ )
        {
            Point q = polygon.get(i);
            minX = Math.min( q.getX(), minX );
            maxX = Math.max( q.getX(), maxX );
            minY = Math.min( q.getY(), minY );
            maxY = Math.max( q.getY(), maxY );
        }

        if ( p.getX() < minX || p.getX() > maxX || p.getY() < minY || p.getY() > maxY )
        {
            return false;
        }

        boolean inside = false;
        for ( int i = 0, j = polygon.size() - 1 ; i < polygon.size() ; j = i++ )
        {
            if ( ( polygon.get(i).getY() > p.getY() ) != ( polygon.get(j).getY() > p.getY() ) &&
                    p.getX() < ( polygon.get(j).getX() - polygon.get(i).getX() ) * ( p.getY() - polygon.get(i).getY() ) / ( polygon.get(j).getY() - polygon.get(i).getY() ) + polygon.get(i).getX() )
            {
                inside = !inside;
            }
        }

        boolean contained1 = inside;

        //TODO Add cloning here.
        p.setX(p.getX() + 16);
        p.setY(p.getY() + 16);

        minX = polygon.get(0).getX();
        maxX = polygon.get(0).getX();
        minY = polygon.get(0).getY();
        maxY = polygon.get(0).getY();
        for ( int i = 1 ; i < polygon.size() ; i++ )
        {
            Point q = polygon.get(i);
            minX = Math.min( q.getX(), minX );
            maxX = Math.max( q.getX(), maxX );
            minY = Math.min( q.getY(), minY );
            maxY = Math.max( q.getY(), maxY );
        }

        if ( p.getX() < minX || p.getX() > maxX || p.getY() < minY || p.getY() > maxY )
        {
            return false;
        }

        inside = false;
        for ( int i = 0, j = polygon.size() - 1 ; i < polygon.size() ; j = i++ )
        {
            if ( ( polygon.get(i).getY() > p.getY() ) != ( polygon.get(j).getY() > p.getY() ) &&
                    p.getX() < ( polygon.get(j).getX() - polygon.get(i).getX() ) * ( p.getY() - polygon.get(i).getY() ) / ( polygon.get(j).getY() - polygon.get(i).getY() ) + polygon.get(i).getX() )
            {
                inside = !inside;
            }
        }

        boolean contained2 = inside;

        p.setX(p.getX() - 32);
        p.setY(p.getY() - 32);

        minX = polygon.get(0).getX();
        maxX = polygon.get(0).getX();
        minY = polygon.get(0).getY();
        maxY = polygon.get(0).getY();
        for ( int i = 1 ; i < polygon.size() ; i++ )
        {
            Point q = polygon.get(i);
            minX = Math.min( q.getX(), minX );
            maxX = Math.max( q.getX(), maxX );
            minY = Math.min( q.getY(), minY );
            maxY = Math.max( q.getY(), maxY );
        }

        if ( p.getX() < minX || p.getX() > maxX || p.getY() < minY || p.getY() > maxY )
        {
            return false;
        }

        inside = false;
        for ( int i = 0, j = polygon.size() - 1 ; i < polygon.size() ; j = i++ )
        {
            if ( ( polygon.get(i).getY() > p.getY() ) != ( polygon.get(j).getY() > p.getY() ) &&
                    p.getX() < ( polygon.get(j).getX() - polygon.get(i).getX() ) * ( p.getY() - polygon.get(i).getY() ) / ( polygon.get(j).getY() - polygon.get(i).getY() ) + polygon.get(i).getX() )
            {
                inside = !inside;
            }
        }

        boolean contained3 = inside;

        p.setX(p.getX() + 16);
        p.setY(p.getY() + 16);

        return contained1 && contained2 && contained3;
    }
     */

    //Doesn't work perfectly.
    /*
    static boolean isPointInPolygon(Point point, LinkedList<Point> polygon)
    {
        double testx = point.getX();
        double testy = point.getY();
        int nvert = polygon.size();
        int i, j = 0;
        boolean c = false;
        for (i = 0, j = nvert-1; i < nvert; j = i++) {
            if ( ((polygon.get(i).getY()>testy) != (polygon.get(j).getY()>testy)) &&
                    (testx < (polygon.get(j).getX()-polygon.get(i).getX()) * (testy-polygon.get(i).getY()) / (polygon.get(j).getY()-polygon.get(i).getY()) + polygon.get(i).getX()) )
                c = !c;
        }

        return c;
    }
     */

    //This doesn't work
    //getShapeClusters
    /*
    public static LinkedList<Shape> getShapeClusters()
    {
        LinkedList<LinkedList<Triangle>> triangleClusters = new LinkedList<>();
        LinkedList<Triangle> firstCluster = new LinkedList<>();
        firstCluster.add(triangles.getFirst());
        triangleClusters.add(firstCluster);
        triangles.remove(triangles.getFirst());
        for (int i = triangles.size() - 1; i >= 0; i--) {
            Triangle triangle1 = triangles.get(i);
            for(LinkedList<Triangle> triangleCluster : triangleClusters)
            {
                for(Triangle triangle2 : triangleCluster)
                {
                    if(triangle1.getNumberOfIntersections(triangle2) > 0)
                    {
                        triangles.remove(triangle1);
                        triangleCluster.add(triangle1);
                    }
                }
            }
            if(triangles.contains(triangle1))
            {
                triangles.remove(triangle1);
            }
        }
        for (int i = triangles.size() - 1; i >= 0; i--) {
            Triangle triangle = triangles.get(i);
            LinkedList<Triangle> newCluster = new LinkedList<>();
            newCluster.add(triangles.getFirst());
            triangleClusters.add(firstCluster);
        }
    }
    */


    /*
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


                if(x!= y) {
                    boolean doBreak = false;
                    for (Circle circleX : clusters.get(x)) {
                        for (Circle circleY : clusters.get(y)) {
                            if (!circleX.equals(circleY)) {
                                if (circleX.intersects(circleY)) {
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
     */

    /*


    public static LinkedList<Point> getContainedPointsForShapesSeparately(LinkedList<LinkedList<Point>> regions)
    {
        LinkedList<Point> pointsInside = new LinkedList<>();
        LinkedList<Point> points = Arrangement.getPoints();

        for(Point point : points)
        {
            for(Circle circle : circles)
            {
                if(circle.containsPoint(point))
                {
                    pointsInside.add(point);
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
                    pointsInside.add(point);
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
                    pointsInside.add(point);
                    break;
                }
            }
        }

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
     */
}
