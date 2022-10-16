package arrangement;

import graphTheory.Graph;
import graphTheory.Node;
import shapes.*;

import java.util.LinkedList;

import static arrangement.Arrangement.*;

public class GraphArrangementPropertyFunctions {
    public static int getNumberOfTriangleSectionsForCircleClusters(LinkedList<LinkedList<Circle>> circleClusters)
    {
        int numberOfTriangleSections = 0;

        for(LinkedList<Circle> circleCluster : circleClusters)
        {
            numberOfTriangleSections += getNumberOfTriangleSectionsFromCircleCluster(circleCluster);
        }

        return numberOfTriangleSections;
    }

    public static int getNumberOfQuadrilateralSectionsForCircles()
    {
        int numberOfQuadrilateralSections = 0;

        numberOfQuadrilateralSections += getNumberOfQuadrilateralSectionsFromCircles();

        return numberOfQuadrilateralSections;
    }

    public static int getNumberOfOutsideEdgesForCircles()
    {
        int numberOfOutsideEdges = 0;

        LinkedList<LinkedList<Node>> nodeChains = getNodeChainsForCircleCluster(circles);

        for(int x = 0 ; x < nodeChains.size() ; x++)
        {

            LinkedList<Node> nodeChain = nodeChains.get(x);

            boolean nodeChainNotCompletelyContained = false;

            for(Node node : nodeChain)
            {
                if(!pointContainedByRaysMethod(node.point, circles))
                {
                    nodeChainNotCompletelyContained = true;
                }
            }

            if(nodeChainNotCompletelyContained)
            {
                numberOfOutsideEdges += nodeChain.size();
            }
        }

        numberOfOutsideEdges += ComplexArrangementPropertyFunctions.getNumberOfNotContainedSoloCircles();

        return numberOfOutsideEdges;
    }

    public static int getNumberOfOutsideEdgesForTrianglesAndSquares()
    {
        int numberOfOutsideEdges = 0;

        LinkedList<LinkedList<Node>> nodeChains = getNodeChainsForTriangleAndSquareCluster(getTrianglesAndSquares());

        for(int x = 0 ; x < nodeChains.size() ; x++)
        {

            LinkedList<Node> nodeChain = nodeChains.get(x);

            boolean nodeChainNotCompletelyContained = false;

            for(Node node : nodeChain)
            {
                if(!pointContainedByRaysMethodForTriangleAndSquareCluster(node.point, getTrianglesAndSquares()))
                {
                    nodeChainNotCompletelyContained = true;
                }
            }

            if(nodeChainNotCompletelyContained)
            {
                numberOfOutsideEdges += nodeChain.size();
            }
        }

        return numberOfOutsideEdges;
    }

    public static int getNumberOfIntersectionsOnOutsideEdgesForTrianglesAndSquares()
    {
        int numberOfIntersectionsOnOutsideEdges = 0;
        //int numberOfOutsideEdges = 0;

        LinkedList<LinkedList<Node>> nodeChains = getNodeChainsForTriangleAndSquareCluster(getTrianglesAndSquares());

        for(int x = 0 ; x < nodeChains.size() ; x++)
        {

            LinkedList<Node> nodeChain = nodeChains.get(x);

            boolean nodeChainNotCompletelyContained = false;

            for(Node node : nodeChain)
            {
                if(!pointContainedByRaysMethodForTriangleAndSquareCluster(node.point, getTrianglesAndSquares()))
                {
                    nodeChainNotCompletelyContained = true;
                }
            }

            if(nodeChainNotCompletelyContained)
            {
                for(Node node : nodeChain)
                {
                    if(node.type.equals("intersection"))
                    {
                        numberOfIntersectionsOnOutsideEdges++;
                    }
                }
                //numberOfOutsideEdges += nodeChain.size();
            }
        }

        return  numberOfIntersectionsOnOutsideEdges;
        //return numberOfOutsideEdges;
    }

    public static int getNumberOfPointsOnOutsideEdgesForTrianglesAndSquares()
    {
        int numberOfPointsOnOutsideEdges = 0;
        //int numberOfOutsideEdges = 0;

        LinkedList<LinkedList<Node>> nodeChains = getNodeChainsForTriangleAndSquareCluster(getTrianglesAndSquares());

        for(int x = 0 ; x < nodeChains.size() ; x++)
        {

            LinkedList<Node> nodeChain = nodeChains.get(x);

            boolean nodeChainNotCompletelyContained = false;

            for(Node node : nodeChain)
            {
                if(!pointContainedByRaysMethodForTriangleAndSquareCluster(node.point, getTrianglesAndSquares()))
                {
                    nodeChainNotCompletelyContained = true;
                }
            }

            if(nodeChainNotCompletelyContained)
            {
                for(Node node : nodeChain)
                {
                    if(node.type.equals("point"))
                    {
                        numberOfPointsOnOutsideEdges++;
                    }
                }
                //numberOfOutsideEdges += nodeChain.size();
            }
        }

        return  numberOfPointsOnOutsideEdges;
        //return numberOfOutsideEdges;
    }

    public static int getNumberOfOutsideEdgesForLines(LinkedList<Point> containedPoints)
    {
        int numberOfOutsideEdges = 0;

        LinkedList<LinkedList<Node>> nodeChains = getNodeChainsForLineClusters(lines, containedPoints);

        for(int x = 0 ; x < nodeChains.size() ; x++)
        {

            LinkedList<Node> nodeChain = nodeChains.get(x);

            boolean nodeChainNotCompletelyContained = false;

            for(Node node : nodeChain)
            {
                if(!pointContainedByRaysMethodForCircleCluster(node.point, circles))
                {
                    nodeChainNotCompletelyContained = true;
                }
            }

            /*
            for(Node node : nodeChain)
            {
                if(sectionPoints != null) {
                    if (!sectionPoints.contains(node.point)) {
                        nodeChainNotCompletelyContained = false;
                    }
                }
            }
             */


            if(nodeChainNotCompletelyContained)
            {
                numberOfOutsideEdges += nodeChain.size();
            }
        }

        return numberOfOutsideEdges;
    }

    /*
    public static int getNumberOfOutsideEdgesForLines(LinkedList<Point> containedPoints, LinkedList<LinkedList<Point>> lineRegions)
    {
        int numberOfOutsideEdges = 0;

        LinkedList<LinkedList<Node>> nodeChains = getNodeChainsForLineClusters(lines, containedPoints);

        for(int x = 0 ; x < nodeChains.size() ; x++)
        {

            LinkedList<Node> nodeChain = nodeChains.get(x);

            boolean nodeChainNotCompletelyContained = false;

            for(Node node : nodeChain)
            {
                if(!pointContainedByRaysMethodForCircleCluster(node.point, circles))
                {
                    nodeChainNotCompletelyContained = true;
                }
            }

            /*
            for(Node node : nodeChain)
            {
                if(sectionPoints != null) {
                    if (!sectionPoints.contains(node.point)) {
                        nodeChainNotCompletelyContained = false;
                    }
                }
            }
             */


            /*
            if(nodeChainNotCompletelyContained)
            {
                for(Node node: nodeChain)
                {
                    int numberOfTimesContained = 0;
                    for(LinkedList<Point> region : lineRegions)
                    {
                        if(PointFunctions.isPointPrettyMuchInPolygon(node.point, region))
                        {
                            numberOfTimesContained++;
                        }
                    }

                    if(numberOfTimesContained >= 2)
                    {
                        numberOfOutsideEdges += 2;
                    }
                    else if(numberOfTimesContained >= 1)
                    {
                        numberOfOutsideEdges++;
                    }
                }
                //numberOfOutsideEdges += nodeChain.size();
            }
        }

        return numberOfOutsideEdges;
    }
    */

    public static LinkedList<Point> getPointsFromNodeChainsForCircles()
    {
        LinkedList<Point> points = new LinkedList<>();

        LinkedList<LinkedList<Node>> nodeChains = getNodeChainsForCircleCluster(circles);

        for(int x = 0 ; x < nodeChains.size() ; x++)
        {
            LinkedList<Node> nodeChain = nodeChains.get(x);

            for(Node node : nodeChain)
            {
                points.add(node.point);
            }
        }

        return points;
    }

    public static LinkedList<Point> getPointsFromNodeChainsForLineShapes()
    {
        LinkedList<Point> points = new LinkedList<>();

        LinkedList<LinkedList<Node>> nodeChains = getNodeChainsForTriangleAndSquareCluster(getLineShapes());

        for(int x = 0 ; x < nodeChains.size() ; x++)
        {
            LinkedList<Node> nodeChain = nodeChains.get(x);

            for(Node node : nodeChain)
            {
                points.add(node.point);
            }
        }

        return points;
    }

    public static LinkedList<Point> getPointsFromNodeChainsForLines()
    {
        LinkedList<Point> points = new LinkedList<>();

        LinkedList<LinkedList<Node>> nodeChains = getNodeChainsForLineClusters(lines, ComplexArrangementPropertyFunctions.getDefinitelyContainedPointsForRegions(ArrangementProperties.intersections, ArrangementProperties.lineSections));

        for(int x = 0 ; x < nodeChains.size() ; x++)
        {
            LinkedList<Node> nodeChain = nodeChains.get(x);

            for(Node node : nodeChain)
            {
                points.add(node.point);
            }
        }

        return points;
    }

    public static LinkedList<LinkedList<Node>> getNodeChainsForCircleCluster(LinkedList<Circle> circleCluster)
    {
        LinkedList<LinkedList<Node>> circleNodes = Graph.getCircleNodes(circleCluster);
        LinkedList<Node> nodes = Graph.getNodes(circleNodes);
        boolean[][] booleanNodesAsGraph = Graph.getCircleNodesAsBooleanGraph(circleCluster, circleNodes, nodes);

        LinkedList<LinkedList<Node>> chainsOfNodesNotContainedByCircles = new LinkedList<>();

        for(int x  = 0 ; x < nodes.size() ; x++)
        {
            nodes.get(x).setFlag(false);
        }

        //Loop through all the nodes.
        for(int x  = 0 ; x < nodes.size() ; x++)
        {
            if(!nodes.get(x).getFlag()) {
                LinkedList<Node> chainOfNodes = new LinkedList<>();

                chainOfNodes.add(nodes.get(x));
                nodes.get(x).setFlag(true);

                boolean pointContained1 = false;

                for (Circle circle : circleCluster) {
                    if (circle.containsPoint(nodes.get(x).point)) {
                        pointContained1 = true;
                    }
                }

                //If node is a point not contained in a circle by another circle.
                if (!pointContained1) {
                    int currentIndex = x;

                    while (true) {
                        boolean addedNode = false;

                        for (int a = 0; a < nodes.size(); a++) {
                            if (a != currentIndex && !nodes.get(a).getFlag()) {
                                boolean pointContained2 = false;

                                for (Circle circle : circleCluster) {
                                    if (circle.containsPoint(nodes.get(a).point)) {
                                        pointContained2 = true;
                                    }
                                }

                                if (!pointContained2) {
                                    if (booleanNodesAsGraph[currentIndex][a] || booleanNodesAsGraph[a][currentIndex]) {
                                        chainOfNodes.add(nodes.get(a));
                                        nodes.get(a).setFlag(true);

                                        currentIndex = a;
                                        addedNode = true;
                                        break;
                                    }
                                }
                            }
                        }

                        if (!addedNode) {
                            break;
                        }
                    }

                    chainsOfNodesNotContainedByCircles.add(chainOfNodes);


                }
                else
                {
                    nodes.get(x).setFlag(false);
                }
            }
        }

        return chainsOfNodesNotContainedByCircles;
    }

    public static LinkedList<LinkedList<Node>> getNodeChainsForTriangleAndSquareCluster(LinkedList<LineShape> lineShapeCluster)
    {
        LinkedList<LinkedList<Node>> lineShapeNodes = Graph.getTriangleAndSquareNodes(lineShapeCluster);
        LinkedList<Node> nodes = Graph.getNodes(lineShapeNodes);
        boolean[][] booleanNodesAsGraph = Graph.getTriangleAndSquareNodesAsBooleanGraph(lineShapeCluster, lineShapeNodes, nodes);

        LinkedList<LinkedList<Node>> chainsOfNodesNotContainedByCircles = new LinkedList<>();

        for(int x  = 0 ; x < nodes.size() ; x++)
        {
            nodes.get(x).setFlag(false);
        }

        //Loop through all the nodes.
        for(int x  = 0 ; x < nodes.size() ; x++)
        {
            if(!nodes.get(x).getFlag()) {
                LinkedList<Integer> indices = new LinkedList<Integer>();

                LinkedList<Node> chainOfNodes = new LinkedList<>();



                boolean pointContained1 = false;

                for (LineShape lineShape : lineShapeCluster) {
                    if (lineShape.containsPointForGraph(nodes.get(x).point)) {
                        pointContained1 = false;
                    }
                }

                //If node is a point not contained in a circle by another circle.
                if (!pointContained1) {
                    chainOfNodes.add(nodes.get(x));
                    nodes.get(x).setFlag(true);

                    int currentIndex = x;

                    indices.add(currentIndex);

                    while (true) {
                        boolean addedNode = false;

                        for (int a = 0; a < nodes.size(); a++) {
                            for(int o = 0; o < indices.size() ; o++) {
                                currentIndex = indices.get(o);
                                if (a != currentIndex && !nodes.get(a).getFlag()) {
                                    boolean pointContained2 = false;

                                    for (LineShape lineShape : lineShapeCluster) {
                                        if (lineShape.containsPointForGraph(nodes.get(a).point)) {
                                            pointContained2 = false;
                                        }
                                    }

                                    if (!pointContained2) {
                                        if (booleanNodesAsGraph[currentIndex][a] || booleanNodesAsGraph[a][currentIndex]) {
                                            chainOfNodes.add(nodes.get(a));
                                            nodes.get(a).setFlag(true);
                                            currentIndex = a;

                                            indices.add(currentIndex);
                                            addedNode = true;
                                            //break;
                                        }
                                    }
                                }
                            }
                        }

                        if (!addedNode) {
                            break;
                        }
                    }

                    chainsOfNodesNotContainedByCircles.add(chainOfNodes);


                }
                else
                {
                    nodes.get(x).setFlag(false);
                }
            }
        }

        return chainsOfNodesNotContainedByCircles;
    }


    /*
    public static LinkedList<LinkedList<Node>> getNodeChainsForLineClusters(LinkedList<Line> lineCluster, LinkedList<Point> containedPoints)
    {
        LinkedList<LinkedList<Node>> lineNodes = Graph.getLineNodes(lineCluster);
        LinkedList<Node> nodes = Graph.getNodes(lineNodes);
        boolean[][] booleanNodesAsGraph = Graph.getLineNodesAsBooleanGraph(lineCluster, lineNodes, nodes, containedPoints);

        LinkedList<LinkedList<Node>> chainsOfNodesNotContainedByCircles = new LinkedList<>();

        for(int x  = 0 ; x < nodes.size() ; x++)
        {
            nodes.get(x).setFlag(false);
        }

        //Loop through all the nodes.
        for(int x  = 0 ; x < nodes.size() ; x++)
        {
            if(nodes.get(x).getFlagCount() < 2) {
                LinkedList<Node> chainOfNodes = new LinkedList<>();

                chainOfNodes.add(nodes.get(x));
                nodes.get(x).augmentFlagCount();

                boolean pointContained1 = false;

                for(Point point : containedPoints)
                {
                    if(point.equals(nodes.get(x).point))
                    {
                        pointContained1 = true;
                    }
                }

                //If node is a point not contained in a circle by another circle.
                if (!pointContained1) {
                    int currentIndex = x;

                    while (true) {
                        boolean addedNode = false;

                        for (int a = 0; a < nodes.size(); a++) {
                            if (a != currentIndex && nodes.get(a).getFlagCount() < 2 && !chainOfNodes.contains(nodes.get(a))) {
                                boolean pointContained2 = false;

                                for(Point point : containedPoints)
                                {
                                    if(point.equals(nodes.get(a).point))
                                    {
                                        pointContained2 = true;
                                    }
                                }

                                if (!pointContained2) {
                                    if (booleanNodesAsGraph[currentIndex][a] || booleanNodesAsGraph[a][currentIndex]) {
                                        chainOfNodes.add(nodes.get(a));
                                        nodes.get(a).augmentFlagCount();

                                        currentIndex = a;
                                        addedNode = true;
                                        break;
                                    }
                                }
                            }
                        }

                        if (!addedNode) {
                            break;
                        }
                    }

                    chainsOfNodesNotContainedByCircles.add(chainOfNodes);


                }
                else
                {
                    nodes.get(x).setFlag(false);
                }
            }
        }

        return chainsOfNodesNotContainedByCircles;
    }
    */


    public static LinkedList<LinkedList<Node>> getNodeChainsForLineClusters(LinkedList<Line> lineCluster, LinkedList<Point> containedPoints)
    {
        LinkedList<LinkedList<Node>> lineNodes = Graph.getLineNodes(lineCluster);
        LinkedList<Node> nodes = Graph.getNodes(lineNodes);
        boolean[][] booleanNodesAsGraph = Graph.getLineNodesAsBooleanGraph(lineCluster, lineNodes, nodes, containedPoints);

        LinkedList<LinkedList<Node>> chainsOfNodesNotContainedByCircles = new LinkedList<>();

        for(int x  = 0 ; x < nodes.size() ; x++)
        {
            nodes.get(x).setFlag(false);
        }

        //Loop through all the nodes.
        for(int x  = 0 ; x < nodes.size() ; x++)
        {
            if(!nodes.get(x).getFlag()) {
                LinkedList<Node> chainOfNodes = new LinkedList<>();

                chainOfNodes.add(nodes.get(x));
                nodes.get(x).setFlag(true);

                boolean pointContained1 = false;

                for(Point point : containedPoints)
                {
                    if(point.equals(nodes.get(x).point))
                    {
                        pointContained1 = true;
                    }
                }

                //If node is a point not contained in a circle by another circle.
                if (!pointContained1) {
                    int currentIndex = x;

                    while (true) {
                        boolean addedNode = false;

                        for (int a = 0; a < nodes.size(); a++) {
                            if (a != currentIndex && !nodes.get(a).getFlag()) {
                                boolean pointContained2 = false;

                                for(Point point : containedPoints)
                                {
                                    if(point.equals(nodes.get(a).point))
                                    {
                                        pointContained2 = true;
                                    }
                                }

                                if (!pointContained2) {
                                    if (booleanNodesAsGraph[currentIndex][a] || booleanNodesAsGraph[a][currentIndex]) {
                                        chainOfNodes.add(nodes.get(a));
                                        nodes.get(a).setFlag(true);

                                        currentIndex = a;
                                        addedNode = true;
                                        break;
                                    }
                                }
                            }
                        }

                        if (!addedNode) {
                            break;
                        }
                    }

                    chainsOfNodesNotContainedByCircles.add(chainOfNodes);


                }
                else
                {
                    nodes.get(x).setFlag(false);
                }
            }
        }

        return chainsOfNodesNotContainedByCircles;
    }

    public static int getNumberOfTriangleSectionsFromCircleCluster(LinkedList<Circle> circleCluster)
    {
        LinkedList<LinkedList<Node>> circleNodes = Graph.getCircleNodesForSections();
        LinkedList<Node> nodes = Graph.getNodes(circleNodes);
        int[][] nodesAsGraph = Graph.getCircleNodesAsGraph(circleNodes, nodes);

        int numberOfTriangleSections = 0;

        for(int a = 0 ; a < nodesAsGraph.length ; a++)
        {
            for(int b = a + 1 ; b < nodesAsGraph.length ; b++)
            {
                for(int c = b + 1 ; c < nodesAsGraph.length ; c++)
                {
                    if(a != b && a != c && b != c)
                    {
                        if(nodesAsGraph[a][b] > 0 && nodesAsGraph[b][c] > 0 && nodesAsGraph[c][a] > 0)
                        {
                            Point point1 = nodes.get(a).point;
                            Point point2 = nodes.get(b).point;
                            Point point3 = nodes.get(c).point;

                            LinkedList<Point> points = new LinkedList<>();
                            points.add(point1);
                            points.add(point2);
                            points.add(point3);

                            if(hasAContainedPoint(points, circleCluster)) {
                                numberOfTriangleSections++;
                            }
                            else
                            {
                                if(allPointsContainedByRaysMethodForCircles(points, circleCluster))
                                {
                                    numberOfTriangleSections++;
                                }
                            }
                        }
                    }
                }
            }
        }

        return numberOfTriangleSections;
    }

    //TODO We could instead implement with the sides contained method.
    public static int getNumberOfQuadrilateralSectionsFromCircles()
    {
        LinkedList<LinkedList<Node>> circleNodes = Graph.getCircleNodesForSections();
        LinkedList<Node> nodes = Graph.getNodes(circleNodes);
        int[][] nodesAsGraph = Graph.getCircleNodesAsGraph(circleNodes, nodes);

        int numberOfQuadrilateralSections = 0;

        for(int a = 0 ; a < nodesAsGraph.length ; a++)
        {
            for(int b = 0 ; b < nodesAsGraph.length ; b++)
            {
                for(int c = 0 ; c < nodesAsGraph.length ; c++)
                {
                    for(int d = 0 ; d < nodesAsGraph.length ; d++)
                    {
                        if (a != b && b != c && c != d && d!= a && a != c && b != d) {
                            if (nodesAsGraph[a][b] > 0 && nodesAsGraph[b][c] > 0 && nodesAsGraph[c][d] > 0 && nodesAsGraph[d][a] > 0 && nodesAsGraph[a][c] < 1 && nodesAsGraph[b][d] < 1) {
                                boolean canAddMore = true;
                                if (nodesAsGraph[a][b] > 2 || nodesAsGraph[b][c] > 2 || nodesAsGraph[c][d] > 2 || nodesAsGraph[d][a] > 2) {
                                    numberOfQuadrilateralSections++;
                                    canAddMore = false;
                                }

                                Point point1 = nodes.get(a).point;
                                Point point2 = nodes.get(b).point;
                                Point point3 = nodes.get(c).point;
                                Point point4 = nodes.get(d).point;

                                LinkedList<Point> points = new LinkedList<>();
                                points.add(point1);
                                points.add(point2);
                                points.add(point3);
                                points.add(point4);

                                if (hasAContainedPoint(points, circles)) {

                                    if(circleFromNodesContainsOddNumberOfPoints(a, b, c, d, nodes, getIntersectionsForCircleCluster(circles))) {
                                        System.out.println("1");
                                        numberOfQuadrilateralSections++;
                                    }
                                } else {
                                    System.out.println("2");

                                    if (allPointsContainedByRaysMethodForCircles(points, circles)) {
                                        System.out.println("3");
                                        numberOfQuadrilateralSections++;
                                    } else {
                                        System.out.println("4");


                                        if (canAddMore && ((nodesAsGraph[a][b] > 1 && nodesAsGraph[c][d] > 1) || (nodesAsGraph[a][d] > 1 && nodesAsGraph[b][c] > 1))) {
                                            if (circleFromNodesContainsNoPoints(a, b, c, d, nodes, getIntersectionsForCircleCluster(circles))) {
                                                System.out.println("5");
                                                numberOfQuadrilateralSections++;
                                            }
                                        } else {
                                            System.out.println("6");
                                        }

                                    }
                                    //}
                                }
                            }
                        }
                    }
                }
            }
        }

        return numberOfQuadrilateralSections / 8;
    }

    public static boolean hasAContainedPoint(LinkedList<Point> points, LinkedList<Circle> circleCluster)
    {
        for(Circle circle : circleCluster)
        {
            for(Point point : points)
            {
                if(circle.containsPoint(point))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean allPointsContainedByRaysMethodForCircles(LinkedList<Point> points, LinkedList<Circle> circleCluster)
    {
        for(Point point : points)
        {
            if(!pointContainedByRaysMethodForCircleCluster(point, circleCluster))
            {
                return false;
            }
        }

        return true;
    }

    public static boolean pointContainedByRaysMethodForCircleCluster(Point point, LinkedList<Circle> circleCluster)
    {
        for(int degree = 0 ; degree < 72 ; degree++) {
            boolean thisRayIntersectsALine = false;

            int startX = (int) (point.getX() + 6 * Math.sin(Math.toRadians(degree * 5)));
            int startY = (int) (point.getY() + 6 * Math.cos(Math.toRadians(degree * 5)));
            int endX = (int) (point.getX() + 10000 * Math.sin(Math.toRadians(degree * 5)));
            int endY = (int) (point.getY() + 10000 * Math.cos(Math.toRadians(degree * 5)));

            Line line = new Line(startX, startY, endX, endY);

            for(Circle circle : circleCluster)
            {
                if(ComplexArrangementPropertyFunctions.checkIntersectsCircleAndLine(circle, line))
                {
                    thisRayIntersectsALine = true;
                }
            }

            if(!thisRayIntersectsALine)
            {
                return false;
            }
        }
        return  true;
    }

    public static boolean pointContainedByRaysMethodForTriangleAndSquareCluster(Point point, LinkedList<LineShape> lineShapeCluster)
    {
        for(int degree = 0 ; degree < 72 ; degree++) {
            boolean thisRayIntersectsALine = false;

            int startX = (int) (point.getX() + 6 * Math.sin(Math.toRadians(degree * 5)));
            int startY = (int) (point.getY() + 6 * Math.cos(Math.toRadians(degree * 5)));
            int endX = (int) (point.getX() + 10000 * Math.sin(Math.toRadians(degree * 5)));
            int endY = (int) (point.getY() + 10000 * Math.cos(Math.toRadians(degree * 5)));

            Line line = new Line(startX, startY, endX, endY);

            for(LineShape lineShape : lineShapeCluster)
            {
                if(lineShape.intersects(line))
                {
                    thisRayIntersectsALine = true;
                }
            }

            if(!thisRayIntersectsALine)
            {
                return false;
            }
        }
        return  true;
    }

    public static boolean pointContainedByRaysMethodForLineCluster(Point point, LinkedList<Line> lineCluster)
    {
        for(int degree = 0 ; degree < 72 ; degree++) {
            boolean thisRayIntersectsALine = false;

            int startX = (int) (point.getX() + 6 * Math.sin(Math.toRadians(degree * 5)));
            int startY = (int) (point.getY() + 6 * Math.cos(Math.toRadians(degree * 5)));
            int endX = (int) (point.getX() + 10000 * Math.sin(Math.toRadians(degree * 5)));
            int endY = (int) (point.getY() + 10000 * Math.cos(Math.toRadians(degree * 5)));

            Line line = new Line(startX, startY, endX, endY);

            for(Line line1 : lineCluster)
            {
                if(line1.intersects(line))
                {
                    thisRayIntersectsALine = true;
                }
            }

            if(!thisRayIntersectsALine)
            {
                return false;
            }
        }
        return  true;
    }

    public static boolean circleFromNodesContainsNoPoints(int a, int b, int c, int d, LinkedList<Node> nodes, LinkedList<Point> intersections)
    {
        Node node0 = nodes.get(a);
        Node node1 = nodes.get(b);
        Node node2 = nodes.get(c);
        Node node3 = nodes.get(d);

        if(node0.circle1.equals(node1.circle1) || node0.circle1.equals(node1.circle2))
        {
            if(node0.circle1.equals(node2.circle1) || node0.circle1.equals(node2.circle2))
            {
                if(node0.circle1.equals(node3.circle1) || node0.circle1.equals(node3.circle2))
                {
                    for(Point intersection : intersections)
                    {
                        if(node0.circle1.containsPoint(intersection))
                        {
                            return false;
                        }
                    }
                }
            }
        }

        if(node0.circle2.equals(node1.circle1) || node0.circle2.equals(node1.circle2))
        {
            if(node0.circle2.equals(node2.circle1) || node0.circle2.equals(node2.circle2))
            {
                if(node0.circle2.equals(node3.circle1) || node0.circle2.equals(node3.circle2))
                {
                    for(Point intersection : intersections)
                    {
                        if(node0.circle2.containsPoint(intersection))
                        {
                            return false;
                        }
                    }
                }
            }
        }

        return  true;
    }

    public static boolean circleFromNodesContainsOddNumberOfPoints(int a, int b, int c, int d, LinkedList<Node> nodes, LinkedList<Point> intersections)
    {
        Node node0 = nodes.get(a);
        Node node1 = nodes.get(b);
        Node node2 = nodes.get(c);
        Node node3 = nodes.get(d);

        int numberOfPointsContained = 0;
        if(node0.circle1.equals(node1.circle1) || node0.circle1.equals(node1.circle2))
        {
            if(node0.circle1.equals(node2.circle1) || node0.circle1.equals(node2.circle2))
            {
                if(node0.circle1.equals(node3.circle1) || node0.circle1.equals(node3.circle2))
                {
                    for(Point intersection : intersections)
                    {
                        if(node0.circle1.containsPoint(intersection))
                        {
                            numberOfPointsContained++;
                        }
                    }
                }
            }
        }

        if(node0.circle2.equals(node1.circle1) || node0.circle2.equals(node1.circle2))
        {
            if(node0.circle2.equals(node2.circle1) || node0.circle2.equals(node2.circle2))
            {
                if(node0.circle2.equals(node3.circle1) || node0.circle2.equals(node3.circle2))
                {
                    for(Point intersection : intersections)
                    {
                        if(node0.circle2.containsPoint(intersection))
                        {
                            numberOfPointsContained++;
                        }
                    }
                }
            }
        }

        if(numberOfPointsContained % 2 == 0)
        {
            return  true;
        }
        else
        {
            return  false;
        }
    }

    public static LinkedList<Point> getIntersectionsForCircleCluster(LinkedList<Circle> cluster)
    {
        LinkedList<Point> intersections = new LinkedList<>();
        for(int x = 0 ; x < cluster.size() ; x++)
        {
            for (int y = x + 1 ; y < cluster.size() ; y++)
            {
                if(cluster.get(x).intersects(cluster.get(y)))
                {
                    intersections.addAll(cluster.get(x).getIntersectionsWith(cluster.get(y)));
                }
            }
        }

        return intersections;
    }

    public static boolean pointContainedByRaysMethod(Point point, LinkedList<Circle> circleCluster)
    {
        for(int degree = 0 ; degree < 72 ; degree++) {
            boolean thisRayIntersectsALine = false;

            int startX = (int) (point.getX() + 6 * Math.sin(Math.toRadians(degree * 5)));
            int startY = (int) (point.getY() + 6 * Math.cos(Math.toRadians(degree * 5)));
            int endX = (int) (point.getX() + 10000 * Math.sin(Math.toRadians(degree * 5)));
            int endY = (int) (point.getY() + 10000 * Math.cos(Math.toRadians(degree * 5)));

            Line line = new Line(startX, startY, endX, endY);

            for(Circle circle : circleCluster)
            {
                if(ComplexArrangementPropertyFunctions.checkIntersectsCircleAndLine(circle, line))
                {
                    thisRayIntersectsALine = true;
                }
            }

            if(!thisRayIntersectsALine)
            {
                return false;
            }
        }
        return  true;
    }
}
