package shapes;

import shapes.Point;

import java.util.LinkedList;

public class PointFunctions {
    public static boolean isPointPrettyMuchInPolygon(Point point, LinkedList<Point> polygon)
    {
        double testx;
        double testy;
        int nvert;
        int i, j;
        boolean c;

        for(int a = 0 ; a < 72 ; a++) {

            testx = point.getX() + 10 * Math.cos(Math.toRadians(a * 5));
            testy = point.getY() + 10 * Math.sin(Math.toRadians(a * 5));
            nvert = polygon.size();
            i = 0;
            j = 0;
            c = false;
            for (i = 0, j = nvert - 1; i < nvert; j = i++) {
                if (((polygon.get(i).getY() > testy) != (polygon.get(j).getY() > testy)) &&
                        (testx < (polygon.get(j).getX() - polygon.get(i).getX()) * (testy - polygon.get(i).getY()) / (polygon.get(j).getY() - polygon.get(i).getY()) + polygon.get(i).getX()))
                    c = !c;
            }
            if(c)
            {
                return true;
            }

        }

        return false;
    }

    public static boolean isPointDefinitelyInPolygon(Point point, LinkedList<Point> polygon)
    {
        double testx;
        double testy;
        int nvert;
        int i, j;
        boolean c;

        for(int a = 0 ; a < 36 ; a++) {

            testx = point.getX() + 5 * Math.cos(Math.toRadians(a * 10));
            testy = point.getY() + 5 * Math.sin(Math.toRadians(a * 10));
            nvert = polygon.size();
            i = 0;
            j = 0;
            c = false;
            for (i = 0, j = nvert - 1; i < nvert; j = i++) {
                if (((polygon.get(i).getY() > testy) != (polygon.get(j).getY() > testy)) &&
                        (testx < (polygon.get(j).getX() - polygon.get(i).getX()) * (testy - polygon.get(i).getY()) / (polygon.get(j).getY() - polygon.get(i).getY()) + polygon.get(i).getX())) {
                    c = !c;
                }
            }
            if(!c)
            {
                return false;
            }

        }

        return true;
    }
}
