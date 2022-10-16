package oldCode;

import shapes.Point;

import java.util.LinkedList;

import static arrangement.Arrangement.lines;
import static shapes.PointFunctions.isPointDefinitelyInPolygon;

public class OldCode3 {


    public static int getNumberOfInsideEdges(int numberOfLineSegments, int numberOfOutsideEdges)
    {
        return numberOfLineSegments - numberOfOutsideEdges - lines.size() * 2;
    }

    public static int getNumberOfOutsideEdgesForLines(LinkedList<Point> intersections, LinkedList<LinkedList<Point>> lineSections, LinkedList<LinkedList<Point>> lineRegions)
    {
        int containedIntersections = 0;

        for(Point point : intersections)
        {
            for(LinkedList<Point> lineRegion : lineRegions)
            {
                if(isPointDefinitelyInPolygon(point, lineRegion))
                {
                    containedIntersections++;
                    break;
                }
            }
        }

        //return  getNumberOfPointsFromSections(lineSections) - containedIntersections;
        return 0;
    }

    //TODO Count the number of points in sections then subtract 2 x the number of intersections. Test this on paper first.
    public static int getNumberOfOutsideEdgesForLines2(LinkedList<Point> intersections, LinkedList<LinkedList<Point>> lineRegions, LinkedList<LinkedList<Point>> sections)
    {
        int containedIntersections = 0;

        for(Point point : intersections)
        {
            for(LinkedList<Point> lineRegion : lineRegions)
            {
                if(isPointDefinitelyInPolygon(point, lineRegion))
                {
                    containedIntersections++;
                    break;
                }
            }
        }

        return  intersections.size() - containedIntersections;
    }
}
