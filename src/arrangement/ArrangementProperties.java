package arrangement;

import game.Display;
import game.GraphicsDisplay;
import graphTheory.Graph;
import graphTheory.Node;
import shapes.*;

import java.util.LinkedList;

import static arrangement.Arrangement.*;
import static shapes.PointFunctions.isPointPrettyMuchInPolygon;

public class ArrangementProperties {
    public static boolean shapeMoved = false;
    public static boolean shapeDrawn = false;
    public static boolean shapeDeleted = false;
    public static boolean actionPerformed = false;
    public static boolean undoPerformed = false;
    public static boolean clearPerformed = false;

    public static int numberOfShapes = 0;
    public static int numberOfPoints = 0;
    public static LinkedList<Point> intersections = new LinkedList<>();
    public static int numberOfCompleteOverlaps = 0;
    public static int numberOfCompleteOverlapsForDistinctCircles = 0;

    public static int totalNumberOfEdges = 0;
    public static LinkedList<LinkedList<Point>> lineRegions = new LinkedList<>();
    public static LinkedList<LinkedList<Point>> lineSections = new LinkedList<>();
    public static int numberOfSections = 0;
    public static int numberOfTriangleSections = 0;
    public static int numberOfQuadrilateralSections = 0;
    public static int numberOfOutsideEdges = 0;
    public static int numberOfInsideEdges = 0;
    public static int numberOfContainedIntersections = 0;
    public static int numberOfContainedPoints = 0;
    public static LinkedList<Integer> numberOfIntersectionsPerSide = new LinkedList<>();
    public static boolean testedVariableTrue;

    public static void update()
    {
        if(Display.undoLastAction)
        {
            Arrangement.undoLastAction();
            setActionPerformed(true);
        }

        if(removeLastAddedShape)
        {
            if(lastAddedShapes.size() > 0) {
                Shape shape = lastAddedShapes.pop();

                if (shape.getType().equals("line")) {
                    lines.removeLast();
                }
                if (shape.getType().equals("triangle")) {
                    triangles.removeLast();
                }
                if (shape.getType().equals("square")) {
                    squares.removeLast();
                }
                if (shape.getType().equals("circle")) {
                    circles.removeLast();
                }
            }

            removeLastAddedShape = false;
        }

        //long timeOriginal = System.currentTimeMillis();
        //int a = 0;
        //System.out.println(a++ + " " + (timeOriginal - System.currentTimeMillis()));
        numberOfShapes = Arrangement.getShapes().size();
        //System.out.println(a++ + " " + (timeOriginal - System.currentTimeMillis()));
        LinkedList<Point> points = Arrangement.getPoints();

        numberOfPoints = points.size();
        //System.out.println(a++ + " " + (timeOriginal - System.currentTimeMillis()));
        intersections = BasicArrangementPropertyFunctions.getIntersections();
        //System.out.println(a++ + " " + (timeOriginal - System.currentTimeMillis()));
        numberOfIntersectionsPerSide = BasicArrangementPropertyFunctions.getIntersectionsPerSide();
        //System.out.println(a++ + " " + (timeOriginal - System.currentTimeMillis()));
        numberOfCompleteOverlaps = BasicArrangementPropertyFunctions.getNumberOfCompleteOverlaps();
        //System.out.println(a++ + " " + (timeOriginal - System.currentTimeMillis()));
        numberOfCompleteOverlapsForDistinctCircles = BasicArrangementPropertyFunctions.getNumberOfCompleteOverlapsForDistinctCircles();

        totalNumberOfEdges = BasicArrangementPropertyFunctions.getTotalNumberOfEdges(intersections.size());
        //System.out.println(a++ + " " + (timeOriginal - System.currentTimeMillis()));



        lineRegions = RegionArrangementPropertyFunctions.getRegions(lines);
        //System.out.println(a++ + " " + (timeOriginal - System.currentTimeMillis()));

        LinkedList<LinkedList<Point>> triangleAndSquareRegions = RegionArrangementPropertyFunctions.getRegions(Arrangement.getTriangleAndSquareLines());

        LinkedList<Point> containedIntersectionsForLines = new LinkedList<>();
        containedIntersectionsForLines.clear();
        containedIntersectionsForLines.addAll(ComplexArrangementPropertyFunctions.getContainedPoints(intersections, lineRegions));

        lineSections = RegionArrangementPropertyFunctions.getSections(lineRegions);
        //System.out.println(a++ + " " + (timeOriginal - System.currentTimeMillis()));

        numberOfSections = lineSections.size() + BasicArrangementPropertyFunctions.getNumberOfWholeShapeSections();
        //System.out.println(a++ + " " + (timeOriginal - System.currentTimeMillis()));

        //int numberOfPointsFromSections = ComplexArrangementPropertyFunctions.getNumberOfPointsFromSections(lineSections);

        numberOfOutsideEdges = 0;
        numberOfInsideEdges = 0;
        numberOfContainedIntersections = 0;

        if(circles.size() > 0) {
            numberOfOutsideEdges = GraphArrangementPropertyFunctions.getNumberOfOutsideEdgesForCircles();
            numberOfInsideEdges = totalNumberOfEdges - numberOfOutsideEdges;
            numberOfContainedIntersections = intersections.size() - numberOfOutsideEdges + ComplexArrangementPropertyFunctions.getNumberOfNotContainedSoloCircles();
        }
        if(getTrianglesAndSquares().size() > 0) {
            numberOfOutsideEdges = GraphArrangementPropertyFunctions.getNumberOfOutsideEdgesForTrianglesAndSquares();
            numberOfInsideEdges = totalNumberOfEdges - numberOfOutsideEdges;
            numberOfContainedIntersections = intersections.size() - GraphArrangementPropertyFunctions.getNumberOfIntersectionsOnOutsideEdgesForTrianglesAndSquares();
            numberOfContainedPoints = points.size() - GraphArrangementPropertyFunctions.getNumberOfPointsOnOutsideEdgesForTrianglesAndSquares();
        }
        if(lines.size() > 0) {
            LinkedList<Point> pointsFromLineSections = new LinkedList<>();
            for (LinkedList<Point> sectionPoints : lineSections) {
                for (Point point : sectionPoints) {
                    if (!pointsFromLineSections.contains(point)) {
                        pointsFromLineSections.add(point);
                    }
                }
            }

            //pointsFromLineSections = ComplexArrangementPropertyFunctions.getPrettyMuchContainedPointsForRegions(intersections, lineRegions);
            LinkedList<LinkedList<Node>> lineNodes = Graph.getLineNodes(lines);
            LinkedList<Node> nodes = Graph.getNodes(lineNodes);
            LinkedList<Node> booleanNodes = Graph.getBooleanNodesForLineGraph(lines, lineNodes, nodes, ComplexArrangementPropertyFunctions.getDefinitelyContainedPointsForRegions(ArrangementProperties.intersections, ArrangementProperties.lineSections));

            numberOfOutsideEdges = 0;
            numberOfInsideEdges = 0;
            int numberOfSectionEdges = 0;

            for(Node node : booleanNodes)
            {
                if(ComplexArrangementPropertyFunctions.isPointPrettyMuchContainedInRegions(node.point, lineRegions))
                {
                    numberOfSectionEdges++;
                }
            }

            for(Node node : booleanNodes)
            {
                int numberOfTimesContained = 0;
                for(LinkedList<Point> region : lineRegions)
                {
                    if(isPointPrettyMuchInPolygon(node.point, region))
                    {
                        numberOfTimesContained++;
                    }
                }


                if(numberOfTimesContained == 1)
                {
                    numberOfOutsideEdges ++;
                }
                /*
                if(numberOfTimesContained > 1)
                {
                    numberOfInsideEdges++;
                }
                 */
            }

            numberOfInsideEdges = numberOfSectionEdges - numberOfOutsideEdges;
            numberOfContainedIntersections = containedIntersectionsForLines.size();
            numberOfContainedPoints = ComplexArrangementPropertyFunctions.getDefinitelyContainedPointsForRegions(points, lineRegions).size();
        }
        //System.out.println(a++ + " " + (timeOriginal - System.currentTimeMillis()));

        LinkedList<LinkedList<Circle>> circleClusters = BasicArrangementPropertyFunctions.getCircleClusters();

        numberOfTriangleSections = RegionArrangementPropertyFunctions.getNumberOfOrderLineSections(3, lineSections) + nSidedSectionArrangementPropertyFunctions.getTriangleAndSquareTriangleSections().size() + GraphArrangementPropertyFunctions.getNumberOfTriangleSectionsForCircleClusters(circleClusters);
        //System.out.println(a++ + " " + (timeOriginal - System.currentTimeMillis()));
        numberOfQuadrilateralSections = RegionArrangementPropertyFunctions.getNumberOfOrderLineSections(4, lineSections) + nSidedSectionArrangementPropertyFunctions.getTriangleAndSquareQuadrilateralSections().size() + GraphArrangementPropertyFunctions.getNumberOfQuadrilateralSectionsForCircles();
        //System.out.println(a++ + " " + (timeOriginal - System.currentTimeMillis()));

        testedVariableTrue = ComplexArrangementPropertyFunctions.isTestedVariableTrue();






        GraphicsDisplay.currentArrangementDistinctAndUnmodified = false;
    }

    public static boolean wasShapeDrawn() {
        return shapeDrawn;
    }

    public static void setShapeDrawn(boolean shapeDrawn) {
        ArrangementProperties.shapeDrawn = shapeDrawn;
    }

    public static boolean hasShapeMoved() {
        return shapeMoved;
    }

    public static void setShapeMoved(boolean shapeMoved) {
        ArrangementProperties.shapeMoved = shapeMoved;
    }

    public static boolean hasShapeDeleted() {
        return shapeDeleted;
    }

    public static void setShapeDeleted(boolean shapeDeleted) {
        ArrangementProperties.shapeDeleted = shapeDeleted;
    }

    public static boolean hasActionPerformed() {
        return actionPerformed;
    }

    public static void setActionPerformed(boolean actionPerformed) {
        ArrangementProperties.actionPerformed = actionPerformed;
    }

    public static boolean hasUndoPerformed() {
        return undoPerformed;
    }

    public static void setUndoPerformed(boolean undoPerformed) {
        ArrangementProperties.undoPerformed = undoPerformed;
    }

    public static boolean hasClearPerformed() {
        return clearPerformed;
    }

    public static void setClearPerformed(boolean clearPerformed) {
        ArrangementProperties.clearPerformed = clearPerformed;
    }

    public static int getNumberOfShapes() {
        return numberOfShapes;
    }

    public static int getNumberOfPoints() {
        return numberOfPoints;
    }

    public static int getNumberOfIntersections() {
        return intersections.size();
    }

    public static int getNumberOfCompleteOverlaps() {
        return numberOfCompleteOverlaps;
    }

    public static int getNumberOfCompleteOverlapsForDistinctCircles() {
        return numberOfCompleteOverlapsForDistinctCircles;
    }

    public static int getNumberOfContainedIntersections() {
        return numberOfContainedIntersections;
    }

    public static int getNumberOfContainedPoints() {
        return numberOfContainedPoints;
    }

    public static int getTotalNumberOfEdges() {
        return totalNumberOfEdges;
    }

    public static int getNumberOfTriangleSections() {
        return numberOfTriangleSections;
    }

    public static int getNumberOfQuadrilateralSections() {
        return numberOfQuadrilateralSections;
    }

    public static int getNumberOfOutsideEdges() {
        return numberOfOutsideEdges;
    }

    public static int getNumberOfInsideEdges() {
        return numberOfInsideEdges;
    }

    public static int getNumberOfSections() {
        return numberOfSections;
    }

    public  static  boolean isTestedVariableTrue() { return testedVariableTrue;}

    public static LinkedList<Integer> getNumberOfIntersectionsPerSide() {
        return numberOfIntersectionsPerSide;
    }

    public static LinkedList<Point> getIntersections() {
        return intersections;
    }
}


