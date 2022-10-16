package shapes;

import java.awt.*;

public abstract class Shape {
    public int x1;
    public int y1;
    public int x2;
    public int y2;

    boolean largeEnough = true;

    public String type;

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public boolean isLargeEnough() {return largeEnough; }

    public String getType()
    {
        return type;
    }

    public abstract void draw(Graphics g);

    public abstract void update();

    public abstract boolean completelyOverlaps(Shape shape2);

    public abstract int getNumberOfIntersections(Shape shape);

    public void makeLargeEnough()
    {
        if(!largeEnough) {
            x2 = x1 + 6;
            y2 = y1 + 6;
        }
    }

}
