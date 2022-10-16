package graphTheory;

import shapes.Circle;
import shapes.LineShape;
import shapes.Point;

import static arrangement.Arrangement.circles;

public class Node {
    public Point point;
    public String name;

    public Circle circle1;
    public Circle circle2;
    public LineShape lineShape;
    public LineShape lineShape1;
    public LineShape lineShape2;

    public boolean flag = false;
    public int flagCount = 0;

    public double degreesFromCenter = -1;

    public String type = "";

    public Node(Point point, String name, Circle circle1, Circle circle2) {
        this.point = point;
        this.name = name;

        this.circle1 = circle1;
        this.circle2 = circle2;
    }

    public Node(Point point, String name, LineShape lineShape) {
        this.point = point;
        this.name = name;

        this.lineShape = lineShape;
    }

    public Node(Point point, String name, LineShape lineShape1, LineShape lineShape2) {
        this.point = point;
        this.name = name;

        this.lineShape1 = lineShape1;
        this.lineShape2 = lineShape2;
    }

    @Override
    public String toString()
    {
        return  name + " " + point.toString();
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return flag;
    }

    public void augmentFlagCount()
    {
        flagCount++;
    }

    public int getFlagCount()
    {
        return flagCount;
    }
}
