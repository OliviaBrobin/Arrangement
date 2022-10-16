package arrangement;

import shapes.Line;
import shapes.Point;

import java.util.LinkedList;

import static shapes.PointFunctions.isPointDefinitelyInPolygon;
import static shapes.PointFunctions.isPointPrettyMuchInPolygon;

public class RegionArrangementPropertyFunctions {
    public static int getNumberOfOrderLineSections(int order, LinkedList<LinkedList<Point>> sections)
    {
        int numberOfOrderLineSections = 0;
        for(LinkedList<Point> lineSection : sections)
        {
            if(lineSection.size() == order)
            {
                numberOfOrderLineSections++;
            }
        }

        return numberOfOrderLineSections;
    }

    public static LinkedList<LinkedList<Point>> getSections(LinkedList<LinkedList<Point>> regions)
    {
        for(int i = regions.size() - 1 ; i >= 0 ; i--)
        {
            for(int j = regions.size() - 1 ; j >= 0 ; j--)
            {
                if(i > regions.size() - 1)
                {
                    i = regions.size() - 1;
                }

                if(j > regions.size() - 1)
                {
                    j = regions.size() - 1;
                }

                if(i != j)
                {
                    boolean contained = true;
                    for(Point point : regions.get(j))
                    {
                        if(!isPointPrettyMuchInPolygon(point, regions.get(i)))
                        {
                            contained = false;
                        }
                        if(isPointDefinitelyInPolygon(point, regions.get(i)))
                        {
                            contained = false;
                        }
                    }

                    if(contained && regions.get(j).size() >= regions.get(i).size())
                    {
                        regions.remove(i);
                    }
                }
            }
        }

        for(int i = regions.size() - 1 ; i >= 0 ; i--)
        {
            for(int j = regions.size() - 1 ; j >= 0 ; j--)
            {
                if(i > regions.size() - 1)
                {
                    i = regions.size() - 1;
                }

                if(j > regions.size() - 1)
                {
                    j = regions.size() - 1;
                }

                if(i != j)
                {
                    boolean contained = true;
                    for(Point point : regions.get(j))
                    {
                        if(!isPointPrettyMuchInPolygon(point, regions.get(i)))
                        {
                            contained = false;
                        }
                        if(isPointDefinitelyInPolygon(point, regions.get(i)))
                        {
                            contained = false;
                        }
                    }

                    if(contained /*&& regions.get(j).size() >= regions.get(i).size()*/)
                    {
                        regions.remove(i);
                    }
                }
            }
        }

        return regions;
    }

    public static LinkedList<LinkedList<Point>> getRegions(LinkedList<Line> allLines)
    {
        LinkedList<Line[]> regions = new LinkedList<>();

        for(Line line1 : allLines)
        {
            for(Line line2 : allLines)
            {
                if(!line1.equals(line2)) {
                    if(line1.intersects(line2)) {
                        LinkedList<Line> allLinesClone = Line.cloneList(allLines);
                        Line.removeElement(allLinesClone, line1);
                        Line.removeElement(allLinesClone, line2);
                        LinkedList<Line> shapeLines = new LinkedList<>();
                        shapeLines.add(line1);
                        shapeLines.add(line2);

                        getRegionsRecursive(shapeLines, allLinesClone, regions);
                    }
                }
            }
        }

        return getPointsFromRegions(regions);
    }

    public static void getRegionsRecursive(LinkedList<Line> shapeLines, LinkedList<Line> allLines, LinkedList<Line[]> regions)
    {
        //TODO Maybe, add a base case.
        for(Line line : allLines)
        {
            if(line.intersects(shapeLines.getLast()))
            {
                if(line.intersects(shapeLines.getFirst()))
                {
                    shapeLines.add(line);
                    Line[] shapeLinesAsArray = shapeLines.toArray(new Line[shapeLines.size() - 1]);
                    regions.add(shapeLinesAsArray);
                    shapeLines.removeLast();
                }

                shapeLines.add(line);
                LinkedList<Line> allLinesClone = Line.cloneList(allLines);
                Line.removeElement(allLinesClone, line);
                getRegionsRecursive(shapeLines, allLinesClone, regions);
                shapeLines.removeLast();
            }
        }
    }

    public static LinkedList<LinkedList<Point>> getPointsFromRegions(LinkedList<Line[]> regions)
    {
        LinkedList<LinkedList<Point>> pointsLists = new LinkedList<>();
        for(Line[] region : regions)
        {
            pointsLists.add(getPointsFromLines(region));
        }

        return  pointsLists;
    }

    public static LinkedList<Point> getPointsFromLines(Line[] region)
    {
        LinkedList<Point> points = new LinkedList<>();
        for (int i = region.length - 1; i >= 0; i--) {
            if(i >= 1)
            {
                points.add(region[i].getIntersectionWith(region[i - 1]));
            }
        }
        points.add(region[0].getIntersectionWith(region[region.length - 1]));

        return points;
    }
}
