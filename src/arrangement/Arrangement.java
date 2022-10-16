package arrangement;

import game.Display;
import shapes.*;

import java.util.LinkedList;
import java.util.Stack;

import static game.Display.*;

public class Arrangement {
    public static Stack<LinkedList<Shape>> arrangementsFromAllActions = new Stack<LinkedList<Shape>>();

    public static LinkedList<Circle> circles = new LinkedList<Circle>();
    public static LinkedList<Line> lines = new LinkedList<Line>();
    public static LinkedList<Square> squares = new LinkedList<Square>();
    public static LinkedList<Triangle> triangles = new LinkedList<Triangle>();

    public static Stack<Shape> lastAddedShapes = new Stack<>();

    public static boolean removeLastAddedShape = false;

    static final int CLOSENESS_FACTOR = 8;

    public static LinkedList<Shape> getShapes()
    {
        LinkedList<Shape> shapes = new LinkedList<>();
        shapes.addAll(lines);
        shapes.addAll(triangles);
        shapes.addAll(squares);
        shapes.addAll(circles);

        return shapes;
    }

    public  static  LinkedList<Shape> getCirclesAsShapes()
    {
        LinkedList<Shape> shapes = new LinkedList<>();
        shapes.addAll(circles);

        return shapes;
    }

    public static LinkedList<Shape> getWholeShapes()
    {
        LinkedList<Shape> shapes = new LinkedList<>();
        shapes.addAll(circles);
        shapes.addAll(squares);
        shapes.addAll(triangles);

        return shapes;
    }

    public static LinkedList<LineShape> getLineShapes()
    {
        LinkedList<LineShape> lineShapes = new LinkedList<>();
        lineShapes.addAll(lines);
        lineShapes.addAll(squares);
        lineShapes.addAll(triangles);

        return lineShapes;
    }

    public static LinkedList<LineShape> getTrianglesAndSquares()
    {
        LinkedList<LineShape> lineShapes = new LinkedList<>();
        lineShapes.addAll(squares);
        lineShapes.addAll(triangles);

        return lineShapes;
    }

    public static LinkedList<Point> getPoints()
    {
        LinkedList<LineShape> lineShapes = getLineShapes();
        LinkedList<Point> points = new LinkedList<>();
        for(LineShape lineShape : lineShapes)
        {
            points.addAll(java.util.List.of(lineShape.getPoints()));
        }

        return points;
    }

    public static LinkedList<Line> getAllLines()
    {
        LinkedList<LineShape> lineShapes = getLineShapes();
        LinkedList<Line> lines = new LinkedList<>();
        for(LineShape lineShape : lineShapes)
        {
            lines.addAll(java.util.List.of(lineShape.getLines()));
        }
        return lines;
    }

    public static LinkedList<Line> getTriangleAndSquareLines()
    {
        LinkedList<Line> lines = new LinkedList<>();

        for(Triangle triangle : triangles)
        {
            lines.addAll(java.util.List.of(triangle.getLines()));
        }

        for(Square square : squares)
        {
            lines.addAll(java.util.List.of(square.getLines()));
        }

        return lines;
    }

    public static void clear()
    {
        circles.clear();
        squares.clear();
        triangles.clear();
        lines.clear();
        lastAddedShapes.clear();
        shapeResizing = false;
        currentShape = null;

        ArrangementProperties.update();
    }

    public static void removeLastAddedShape()
    {
        removeLastAddedShape = true;
        ArrangementProperties.update();
    }

    public static boolean addShape()
    {
        if (Display.currentShapeType.equals("circle")) {
            circles.add((Circle) currentShape);
        } else if (Display.currentShapeType.equals("line")) {
            lines.add((Line) currentShape);
        } else if (Display.currentShapeType.equals("square")) {
            squares.add((Square) currentShape);
        } else if (Display.currentShapeType.equals("triangle")) {
            triangles.add((Triangle) currentShape);
        }
        lastAddedShapes.add(currentShape);

        if(isLastAddedShapeTooCloseToOtherShapes())
        {
            removeLastAddedShape();
            return false;
        }

        //Check if shapes are almost overlapping.
        if(currentShapeType.equals("circle"))
        {
            LinkedList<Point> intersectionsBefore = BasicArrangementPropertyFunctions.getIntersections();

            removeLastAddedShape();

            Circle largerCurrentShape = new Circle();
            largerCurrentShape.setX1(currentShape.getX1());
            largerCurrentShape.setX2(currentShape.getX2());
            largerCurrentShape.setY1(currentShape.getY1());
            largerCurrentShape.setY2(currentShape.getY2());
            largerCurrentShape.update();

            largerCurrentShape.setRadius(largerCurrentShape.getRadius() + CLOSENESS_FACTOR) ;

            circles.add(largerCurrentShape);
            lastAddedShapes.add(largerCurrentShape);


            LinkedList<Point> intersectionsAfter = BasicArrangementPropertyFunctions.getIntersections();

            removeLastAddedShape();

            if(intersectionsAfter.size() != intersectionsBefore.size())
            {
                return false;
            }
            else {
                largerCurrentShape = new Circle();
                largerCurrentShape.setX1(currentShape.getX1());
                largerCurrentShape.setX2(currentShape.getX2());
                largerCurrentShape.setY1(currentShape.getY1());
                largerCurrentShape.setY2(currentShape.getY2());
                largerCurrentShape.update();

                largerCurrentShape.setRadius(largerCurrentShape.getRadius() - CLOSENESS_FACTOR) ;

                circles.add(largerCurrentShape);
                lastAddedShapes.add(largerCurrentShape);


                intersectionsAfter = BasicArrangementPropertyFunctions.getIntersections();

                removeLastAddedShape();

                if(intersectionsAfter.size() != intersectionsBefore.size())
                {
                    return false;
                }
                else {
                    circles.add((Circle) currentShape);
                    lastAddedShapes.add(currentShape);
                }
            }
        }

        if(currentShapeType.equals("triangle"))
        {
            LinkedList<Point> intersectionsBefore = BasicArrangementPropertyFunctions.getIntersections();

            removeLastAddedShape();

            Triangle largerCurrentShape = new Triangle();
            largerCurrentShape.setX1(currentShape.getX1());
            largerCurrentShape.setX2(currentShape.getX2());
            largerCurrentShape.setY1(currentShape.getY1());
            largerCurrentShape.setY2(currentShape.getY2());

            if(largerCurrentShape.getX2() > largerCurrentShape.getX1())
            {
                largerCurrentShape.setX2(largerCurrentShape.getX2() + CLOSENESS_FACTOR / 2);
            }
            else {
                largerCurrentShape.setX2(largerCurrentShape.getX2() - CLOSENESS_FACTOR / 2);
            }

            if(largerCurrentShape.getY2() > largerCurrentShape.getY1())
            {
                largerCurrentShape.setY2(largerCurrentShape.getY2() + CLOSENESS_FACTOR / 2);
            }
            else {
                largerCurrentShape.setY2(largerCurrentShape.getY2() - CLOSENESS_FACTOR / 2);
            }

            largerCurrentShape.update();


            triangles.add(largerCurrentShape);
            lastAddedShapes.add(largerCurrentShape);


            LinkedList<Point> intersectionsAfter = BasicArrangementPropertyFunctions.getIntersections();

            removeLastAddedShape();

            if(intersectionsAfter.size() != intersectionsBefore.size())
            {
                return false;
            }
            else {
                triangles.add((Triangle) currentShape);
                lastAddedShapes.add(currentShape);
            }
        }

        if(currentShapeType.equals("square"))
        {
            LinkedList<Point> intersectionsBefore = BasicArrangementPropertyFunctions.getIntersections();

            removeLastAddedShape();

            Square largerCurrentShape = new Square();
            largerCurrentShape.setX1(currentShape.getX1());
            largerCurrentShape.setX2(currentShape.getX2());
            largerCurrentShape.setY1(currentShape.getY1());
            largerCurrentShape.setY2(currentShape.getY2());

            if(largerCurrentShape.getX2() > largerCurrentShape.getX1())
            {
                largerCurrentShape.setX2(largerCurrentShape.getX2() + CLOSENESS_FACTOR / 2);
            }
            else {
                largerCurrentShape.setX2(largerCurrentShape.getX2() - CLOSENESS_FACTOR / 2);
            }

            if(largerCurrentShape.getY2() > largerCurrentShape.getY1())
            {
                largerCurrentShape.setY2(largerCurrentShape.getY2() + CLOSENESS_FACTOR / 2);
            }
            else {
                largerCurrentShape.setY2(largerCurrentShape.getY2() - CLOSENESS_FACTOR / 2);
            }

            largerCurrentShape.update();


            squares.add(largerCurrentShape);
            lastAddedShapes.add(largerCurrentShape);


            LinkedList<Point> intersectionsAfter = BasicArrangementPropertyFunctions.getIntersections();

            removeLastAddedShape();

            if(intersectionsAfter.size() != intersectionsBefore.size())
            {
                return false;
            }
            else {
                squares.add((Square) currentShape);
                lastAddedShapes.add(currentShape);
            }
        }

        if(currentShapeType.equals("line"))
        {
            LinkedList<Point> intersectionsBefore = BasicArrangementPropertyFunctions.getIntersections();

            removeLastAddedShape();

            Line largerCurrentShape = new Line();
            largerCurrentShape.setX1(currentShape.getX1());
            largerCurrentShape.setX2(currentShape.getX2());
            largerCurrentShape.setY1(currentShape.getY1());
            largerCurrentShape.setY2(currentShape.getY2());

            if(largerCurrentShape.getX2() > largerCurrentShape.getX1())
            {
                largerCurrentShape.setX2(largerCurrentShape.getX2() + CLOSENESS_FACTOR / 2);
            }
            else {
                largerCurrentShape.setX2(largerCurrentShape.getX2() - CLOSENESS_FACTOR / 2);
            }

            if(largerCurrentShape.getY2() > largerCurrentShape.getY1())
            {
                largerCurrentShape.setY2(largerCurrentShape.getY2() + CLOSENESS_FACTOR / 2);
            }
            else {
                largerCurrentShape.setY2(largerCurrentShape.getY2() - CLOSENESS_FACTOR / 2);
            }

            largerCurrentShape.update();


            lines.add(largerCurrentShape);
            lastAddedShapes.add(largerCurrentShape);


            LinkedList<Point> intersectionsAfter = BasicArrangementPropertyFunctions.getIntersections();

            removeLastAddedShape();

            if(intersectionsAfter.size() != intersectionsBefore.size())
            {
                return false;
            }
            else {
                lines.add((Line) currentShape);
                lastAddedShapes.add(currentShape);
            }
        }

        return true;
    }

    public static boolean isLastAddedShapeTooCloseToOtherShapes()
    {
        LinkedList<Point> intersections = BasicArrangementPropertyFunctions.getIntersections();
        LinkedList<Point> points = getPoints();

        LinkedList<Point> allPoints =  new LinkedList<Point>();
        allPoints.addAll(intersections);
        allPoints.addAll(points);

        for(Point point : allPoints)
        {
            for(Point point1 : allPoints)
            {
                if(!point.equals(point1))
                {
                    if(point.distance(point1) <= CLOSENESS_FACTOR)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static void undoLastAction()
    {
        if(arrangementsFromAllActions.size() > 1) {
            LinkedList<Shape> currentShapes = arrangementsFromAllActions.pop();
            currentShapes = arrangementsFromAllActions.pop();

            LinkedList<Line> newLines = new LinkedList<>();
            LinkedList<Triangle> newTriangles = new LinkedList<>();
            LinkedList<Square> newSquares = new LinkedList<>();
            LinkedList<Circle> newCircles = new LinkedList<>();

            for (Shape shape : currentShapes) {
                if (shape.type.equals("line")) {
                    newLines.add((Line) shape);
                }
                if (shape.type.equals("triangle")) {
                    newTriangles.add((Triangle) shape);
                }
                if (shape.type.equals("square")) {
                    newSquares.add((Square) shape);
                }
                if (shape.type.equals("circle")) {
                    newCircles.add((Circle) shape);
                }
            }

            lines.clear();
            triangles.clear();
            squares.clear();
            circles.clear();

            lines = newLines;
            triangles = newTriangles;
            squares = newSquares;
            circles = newCircles;
        }
        else {

            lines.clear();
            triangles.clear();
            squares.clear();
            circles.clear();

            arrangementsFromAllActions.clear();
        }

        shapeResizing = false;
        currentShape = null;

        undoLastAction = false;
    }

    public static void addLastAction()
    {
        LinkedList<Shape> currentShapes = new LinkedList<>();

        LinkedList<Line> linesClone = new LinkedList<>();
        LinkedList<Triangle> trianglesClone = new LinkedList<>();
        LinkedList<Square> squaresClone = new LinkedList<>();
        LinkedList<Circle> circlesClone = new LinkedList<>();

        for(Line line : lines)
        {
            linesClone.add(new Line(line));
        }
        for(Triangle triangle : triangles)
        {
            trianglesClone.add(new Triangle(triangle));
        }
        for(Square square : squares)
        {
            squaresClone.add(new Square(square));
        }
        for(Circle circle : circles)
        {
            circlesClone.add(new Circle(circle));
        }


        currentShapes.addAll(linesClone);
        currentShapes.addAll(trianglesClone);
        currentShapes.addAll(squaresClone);
        currentShapes.addAll(circlesClone);

        //Check if shapes are identical as the previous batch so that we don't add a new set. We may want to make a .equals function for specifically this unless checking x1 x2 and y1 y2 works fine.
        //for(Shape shape : arrangementsFromAllActions.peek())
        //{
        //    if()
        //}

        arrangementsFromAllActions.add(currentShapes);
    }
}
