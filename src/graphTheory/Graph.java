package graphTheory;

import shapes.Circle;
import shapes.Line;
import shapes.LineShape;
import shapes.Point;

import java.util.LinkedList;

import static arrangement.Arrangement.circles;
import static java.lang.Math.atan2;

public class Graph {
    public static LinkedList<LinkedList<Node>> getCircleNodesForSections()
    {
        LinkedList<LinkedList<Node>> circleNodes = new LinkedList<LinkedList<Node>>();

        for(Circle circle : circles)
        {
            circleNodes.add(new LinkedList<>());
        }

        for(int x = 0 ; x < circles.size() ; x++)
        {
            LinkedList<Node> nodesList = new LinkedList<>();
            for (int y = x + 1 ; y < circles.size() ; y++)
            {
                if(circles.get(x).intersects(circles.get(y)))
                {
                    LinkedList<Point> circleIntersections = circles.get(x).getIntersectionsWith(circles.get(y));
                    String name1 = x + "," + y + "," + "A";
                    String name2 = x + "," + y + "," + "B";

                    Node node1 = new Node(circleIntersections.get(0), name1, circles.get(x), circles.get(y));
                    Node node2 = new Node(circleIntersections.get(1), name2, circles.get(x), circles.get(y));

                    circleNodes.get(x).add(node1);
                    circleNodes.get(x).add(node2);
                    circleNodes.get(y).add(node1);
                    circleNodes.get(y).add(node2);
                }
            }
        }

        sortCircleNodes(circleNodes);

        return circleNodes;
    }

    //Gets list of nodes, each of which correspond to a circle
    public static LinkedList<LinkedList<Node>> getCircleNodes(LinkedList<Circle> circleCluster)
    {
        LinkedList<LinkedList<Node>> circleNodes = new LinkedList<LinkedList<Node>>();

        for(Circle circle : circleCluster)
        {
            circleNodes.add(new LinkedList<>());
        }

        for(int x = 0 ; x < circleCluster.size() ; x++)
        {
            for (int y = x + 1 ; y < circleCluster.size() ; y++)
            {
                if(x != y) {
                    if (circleCluster.get(x).intersects(circleCluster.get(y))) {
                        LinkedList<Point> circleIntersections = circleCluster.get(x).getIntersectionsWith(circleCluster.get(y));
                        String name1 = x + "," + y + "," + "A";
                        String name2 = x + "," + y + "," + "B";

                        Node node1 = new Node(circleIntersections.get(0), name1, circleCluster.get(x), circleCluster.get(y));
                        Node node2 = new Node(circleIntersections.get(1), name2, circleCluster.get(x), circleCluster.get(y));

                        circleNodes.get(x).add(node1);
                        circleNodes.get(x).add(node2);
                        circleNodes.get(y).add(node1);
                        circleNodes.get(y).add(node2);
                    }
                }
            }
        }

        sortCircleNodes(circleNodes);

        return circleNodes;
    }

    //Gets list of nodes, each of which correspond to a triangle or square
    public static LinkedList<LinkedList<Node>> getTriangleAndSquareNodes(LinkedList<LineShape> lineShapeCluster)
    {
        LinkedList<LinkedList<Node>> lineShapeNodes = new LinkedList<LinkedList<Node>>();

        for(int x = 0 ; x < lineShapeCluster.size() ; x++)
        {
            LineShape lineShape = lineShapeCluster.get(x);
            LinkedList<Node> lineShapePointNodes = new LinkedList<Node>();
            for(int y = 0 ; y < lineShape.getPoints().length ; y++)
            {
                String name1 = x + "," + y + "," + "Point";
                Node lineShapeNode = new Node(lineShape.getPoints()[y], name1, lineShape);
                lineShapeNode.type = "point";
                lineShapePointNodes.add(lineShapeNode);
            }

            lineShapeNodes.add(lineShapePointNodes);
        }

        for(int x = 0 ; x < lineShapeCluster.size() ; x++)
        {
            for (int y = x + 1 ; y < lineShapeCluster.size() ; y++)
            {
                if(x != y) {
                    if (lineShapeCluster.get(x).intersects(lineShapeCluster.get(y))) {
                        LinkedList<Point> intersections = lineShapeCluster.get(x).getIntersectionsWith(lineShapeCluster.get(y));
                        for(int a = 0 ; a < intersections.size() ; a++)
                        {
                            Point intersection = intersections.get(a);
                            String name = x + "," + y + "" + a;


                            Node node = new Node(intersection, name, lineShapeCluster.get(x), lineShapeCluster.get(y));
                            node.type = "intersection";

                            lineShapeNodes.get(x).add(node);
                            lineShapeNodes.get(y).add(node);
                        }
                    }
                }
            }
        }

        sortTriangleAndSquareNodes(lineShapeNodes, lineShapeCluster);

        return lineShapeNodes;
    }

    //Gets list of nodes, each of which correspond to a line
    public static LinkedList<LinkedList<Node>> getLineNodes(LinkedList<Line> lineCluster)
    {
        LinkedList<LinkedList<Node>> lineShapeNodes = new LinkedList<LinkedList<Node>>();

        for(int x = 0 ; x < lineCluster.size() ; x++)
        {
            lineShapeNodes.add(new LinkedList<>());
        }

        for(int x = 0 ; x < lineCluster.size() ; x++)
        {
            for (int y = x + 1 ; y < lineCluster.size() ; y++)
            {
                if(x != y) {
                    if (lineCluster.get(x).intersects(lineCluster.get(y))) {
                        LinkedList<Point> intersections = lineCluster.get(x).getIntersectionsWith(lineCluster.get(y));
                        for(int a = 0 ; a < intersections.size() ; a++)
                        {
                            Point intersection = intersections.get(a);
                            String name = x + "," + y + "" + a;

                            Node node = new Node(intersection, name, lineCluster.get(x), lineCluster.get(y));

                            lineShapeNodes.get(x).add(node);
                            lineShapeNodes.get(y).add(node);
                        }
                    }
                }
            }
        }

        sortLineNodes(lineShapeNodes, lineCluster);

        return lineShapeNodes;
    }

    public static void sortCircleNodes(LinkedList<LinkedList<Node>> circleNodes)
    {
        for(int x = 0 ; x < circleNodes.size() ; x++)
        {
            LinkedList<Node> circleNodeList = circleNodes.get(x);
            if(circleNodeList != null) {
                for (int y = 0; y < circleNodeList.size(); y++) {
                    Node node = circleNodeList.get(y);
                    node.degreesFromCenter = circles.get(x).getDegreesFromCenter(node.point);
                }

                circleNodeList.sort(new NodeComparator());

                /*
                System.out.println("A:");
                for (int y = 0; y < circleNodeList.size(); y++) {
                    Node node = circleNodeList.get(y);
                    System.out.println(node.degreesFromCenter);
                }
                 */
            }
        }
    }

    public static void sortTriangleAndSquareNodes(LinkedList<LinkedList<Node>> lineShapeNodes, LinkedList<LineShape> lineShapeCluster)
    {
        for(int x = 0 ; x < lineShapeNodes.size() ; x++)
        {
            LinkedList<Node> lineShapeList = lineShapeNodes.get(x);
            if(lineShapeList != null) {
                for (int y = 0; y < lineShapeList.size(); y++) {
                    Node node = lineShapeList.get(y);
                    node.degreesFromCenter = getDegreesFromAnotherPoint(node.point, new Point(lineShapeCluster.get(x).getX1(), lineShapeCluster.get(x).getY1()));
                }

                lineShapeList.sort(new NodeComparator());
            }
        }
    }

    public static void sortLineNodes(LinkedList<LinkedList<Node>> lineShapeNodes, LinkedList<Line> lineCluster)
    {
        for(int x = 0 ; x < lineShapeNodes.size() ; x++)
        {
            LinkedList<Node> lineShapeList = lineShapeNodes.get(x);
            if(lineShapeList != null) {
                for (int y = 0; y < lineShapeList.size(); y++) {
                    Node node = lineShapeList.get(y);
                    node.degreesFromCenter = getDegreesFromAnotherPoint(node.point, new Point((lineCluster.get(x).getX1() + 200), lineCluster.get(x).getY1()));
                }

                lineShapeList.sort(new NodeComparator());
            }
        }
    }

    //Gets a list of all nodes
    public static LinkedList<Node> getNodes(LinkedList<LinkedList<Node>> nodesLists)
    {
        LinkedList<Node> nodes = new LinkedList<>();

        for(LinkedList<Node> nodesList : nodesLists)
        {
            for(Node node : nodesList)
            {
                if(!nodes.contains(node))
                {
                    nodes.add(node);
                }
            }
        }

        return nodes;
    }

    public static double getDegreesFromAnotherPoint(Point point1, Point point2)
    {
        return atan2(point1.getY() - point2.getY(), point1.getX() - point2.getX());
    }

    //Gets a 2D Array of the number of sides between each node in the graph
    public static int[][] getCircleNodesAsGraph(LinkedList<LinkedList<Node>> circleNodes, LinkedList<Node> nodes)
    {
        int[][] nodesAsGraph = new int[nodes.size()][nodes.size()];

        for(int x = 0 ; x < nodesAsGraph.length ; x++)
        {
            for(int y = 0 ; y < nodesAsGraph[x].length ; y++)
            {
                nodesAsGraph[x][y] = 0;
            }
        }

        for(LinkedList<Node> nodesList : circleNodes)
        {
            if(nodesList != null) {
                for(int a = 0 ; a < nodesList.size() - 1 ; a++)
                {
                    int index1 = nodes.indexOf(nodesList.get(a));
                    int index2 = nodes.indexOf(nodesList.get(a + 1));

                    if(index1 != -1 && index2 != -1) {
                        nodesAsGraph[index1][index2]++;
                        nodesAsGraph[index2][index1]++;
                    }
                }
                if(nodesList.size() > 0) {
                    int index1 = nodes.indexOf(nodesList.get(nodesList.size() - 1));
                    int index2 = nodes.indexOf(nodesList.get(0));

                    if(index1 != -1 && index2 != -1) {
                        nodesAsGraph[index1][index2]++;
                        nodesAsGraph[index2][index1]++;
                    }
                }
            }
        }

        return nodesAsGraph;
    }

    //Gets a 2D Array of the whether at least one of the sides between each node in the graph are not contained by a circle.
    //The boolean index is set to true if at least one edge between the nodes is not contained by a circle.
    public static boolean[][] getCircleNodesAsBooleanGraph(LinkedList<Circle> circleCluster, LinkedList<LinkedList<Node>> circleNodes, LinkedList<Node> nodes)
    {
        LinkedList<Node> booleanNodes = new LinkedList<>();

        boolean[][] nodesAsBooleanGraph = new boolean[nodes.size()][nodes.size()];

        for(int x = 0 ; x < nodesAsBooleanGraph.length ; x++)
        {
            for(int y = 0 ; y < nodesAsBooleanGraph[x].length ; y++)
            {
                nodesAsBooleanGraph[x][y] = false;
            }
        }

        for (int x = 0; x < circleNodes.size(); x++) {
            LinkedList<Node> nodesList = circleNodes.get(x);

            if(nodesList != null) {
                for(int a = 0 ; a < nodesList.size() - 1 ; a++)
                {
                    int index1 = nodes.indexOf(nodesList.get(a));
                    int index2 = nodes.indexOf(nodesList.get(a + 1));

                    if(index1 != -1 && index2 != -1) {
                        Node node0 = nodesList.get(a);
                        Node node1 = nodesList.get(a + 1);

                        Circle circle = circleCluster.get(x);

                        double degreesFromCenter0 = circleCluster.get(x).getDegreesFromCenter(node0.point);
                        double degreesFromCenter1 = circleCluster.get(x).getDegreesFromCenter(node1.point);

                        if(degreesFromCenter0 < 0)
                        {
                            degreesFromCenter0 += 2 * Math.PI;
                        }
                        if(degreesFromCenter0 > 2 * Math.PI)
                        {
                            degreesFromCenter0 -= 2 * Math.PI;
                        }

                        if(degreesFromCenter1 < 0)
                        {
                            degreesFromCenter1 += 2 * Math.PI;
                        }
                        if(degreesFromCenter1 > 2 * Math.PI)
                        {
                            degreesFromCenter1 -= 2 * Math.PI;
                        }

                        if(degreesFromCenter1 < degreesFromCenter0)
                        {
                            degreesFromCenter1 += 2 * Math.PI;
                        }

                        double middleDegreesFromCenter = degreesFromCenter0 + ((degreesFromCenter1 - degreesFromCenter0) / 2);

                        Point pointToCheck = new Point((int) (circle.getCenterX() + (circle.getRadius() * Math.cos(middleDegreesFromCenter))), (int) (circle.getCenterY() + (circle.getRadius() * Math.sin(middleDegreesFromCenter))));

                        boolean contained = false;

                        for(Circle circle1 : circleCluster)
                        {
                            if(!circle1.equals(circle)) {
                                if (circle1.containsPointForGraph(pointToCheck)) {
                                    contained = true;
                                }
                            }
                        }

                        if(!contained)
                        {
                            nodesAsBooleanGraph[index1][index2] = true;
                            nodesAsBooleanGraph[index2][index1] = true;
                        }

                        String name = "contained";

                        if(!contained)
                        {
                            name = "not";
                        }

                        booleanNodes.add(new Node(pointToCheck, name, new Circle(), new Circle()));
                    }
                }

                if(nodesList.size() > 0) {
                    int index1 = nodes.indexOf(nodesList.get(nodesList.size() - 1));
                    int index2 = nodes.indexOf(nodesList.get(0));

                    if(index1 != -1 && index2 != -1) {
                        Node node0 = nodesList.get(nodesList.size() - 1);
                        Node node1 = nodesList.get(0);

                        Circle circle = circleCluster.get(x);

                        double degreesFromCenter0 = circles.get(x).getDegreesFromCenter(node0.point);
                        double degreesFromCenter1 = circles.get(x).getDegreesFromCenter(node1.point);

                        if(degreesFromCenter0 < 0)
                        {
                            degreesFromCenter0 += 2 * Math.PI;
                        }
                        if(degreesFromCenter0 > 2 * Math.PI)
                        {
                            degreesFromCenter0 -= 2 * Math.PI;
                        }

                        if(degreesFromCenter1 < 0)
                        {
                            degreesFromCenter1 += 2 * Math.PI;
                        }
                        if(degreesFromCenter1 > 2 * Math.PI)
                        {
                            degreesFromCenter1 -= 2 * Math.PI;
                        }

                        if(degreesFromCenter1 < degreesFromCenter0)
                        {
                            degreesFromCenter1 += 2 * Math.PI;
                        }

                        double middleDegreesFromCenter = degreesFromCenter0 + ((degreesFromCenter1 - degreesFromCenter0) / 2);

                        Point pointToCheck = new Point((int) (circle.getCenterX() + (circle.getRadius() * Math.cos(middleDegreesFromCenter))), (int) (circle.getCenterY() + (circle.getRadius() * Math.sin(middleDegreesFromCenter))));

                        boolean contained = false;

                        for(Circle circle1 : circleCluster)
                        {
                            if(circle1.containsPoint(pointToCheck))
                            {
                                contained = true;
                            }
                        }

                        if(!contained)
                        {
                            nodesAsBooleanGraph[index1][index2] = true;
                            nodesAsBooleanGraph[index2][index1] = true;
                        }

                        String name = "contained";

                        if(!contained)
                        {
                            name = "not";
                        }

                        booleanNodes.add(new Node(pointToCheck, name, new Circle(), new Circle()));
                    }
                }
            }
        }

        return nodesAsBooleanGraph;
        //return booleanNodes;
    }

    //Gets a 2D Array of the whether at least one of the sides between each node in the graph are not contained by a circle.
    //The boolean index is set to true if at least one edge between the nodes is not contained by a circle.
    public static boolean[][] getTriangleAndSquareNodesAsBooleanGraph(LinkedList<LineShape> lineShapeCluster, LinkedList<LinkedList<Node>> lineShapeNodes, LinkedList<Node> nodes)
    {
        LinkedList<Node> booleanNodes = new LinkedList<>();

        boolean[][] nodesAsBooleanGraph = new boolean[nodes.size()][nodes.size()];

        for(int x = 0 ; x < nodesAsBooleanGraph.length ; x++)
        {
            for(int y = 0 ; y < nodesAsBooleanGraph[x].length ; y++)
            {
                nodesAsBooleanGraph[x][y] = false;
            }
        }

        for (int x = 0; x < lineShapeNodes.size(); x++) {
            LinkedList<Node> nodesList = lineShapeNodes.get(x);

            if(nodesList != null) {
                for(int a = 0 ; a < nodesList.size() - 1 ; a++)
                {
                    int index1 = nodes.indexOf(nodesList.get(a));
                    int index2 = nodes.indexOf(nodesList.get(a + 1));

                    if(index1 != -1 && index2 != -1) {
                        Node node0 = nodesList.get(a);
                        Node node1 = nodesList.get(a + 1);

                        LineShape lineShape = lineShapeCluster.get(x);

                        //double degreesFromCenter0 = circleCluster.get(x).getDegreesFromCenter(node0.point);
                        //double degreesFromCenter1 = circleCluster.get(x).getDegreesFromCenter(node1.point);

                        Point pointToCheck = new Point(((node0.point.getX() + node1.point.getX()) / 2), ((node0.point.getY() + node1.point.getY()) / 2));

                        //Point pointToCheck = new Point((int) (circle.getCenterX() + (circle.getRadius() * Math.cos(middleDegreesFromCenter))), (int) (circle.getCenterY() + (circle.getRadius() * Math.sin(middleDegreesFromCenter))));

                        boolean contained = false;

                        for(LineShape lineShape1 : lineShapeCluster)
                        {
                            if(!lineShape1.equals(lineShape)) {
                                if (lineShape1.containsPointForGraph(pointToCheck)) {
                                    contained = true;
                                }
                            }
                        }

                        if(!contained)
                        {
                            nodesAsBooleanGraph[index1][index2] = true;
                            nodesAsBooleanGraph[index2][index1] = true;
                        }

                        String name = "contained";

                        if(!contained)
                        {
                            name = "not";
                        }

                        booleanNodes.add(new Node(pointToCheck, name, new Circle(), new Circle()));
                    }
                }

                if(nodesList.size() > 0) {
                    int index1 = nodes.indexOf(nodesList.get(nodesList.size() - 1));
                    int index2 = nodes.indexOf(nodesList.get(0));

                    if(index1 != -1 && index2 != -1) {
                        Node node0 = nodesList.get(nodesList.size() - 1);
                        Node node1 = nodesList.get(0);

                        LineShape lineShape = lineShapeCluster.get(x);

                        //double degreesFromCenter0 = circleCluster.get(x).getDegreesFromCenter(node0.point);
                        //double degreesFromCenter1 = circleCluster.get(x).getDegreesFromCenter(node1.point);

                        Point pointToCheck = new Point(((node0.point.getX() + node1.point.getX()) / 2), ((node0.point.getY() + node1.point.getY()) / 2));

                        //Point pointToCheck = new Point((int) (circle.getCenterX() + (circle.getRadius() * Math.cos(middleDegreesFromCenter))), (int) (circle.getCenterY() + (circle.getRadius() * Math.sin(middleDegreesFromCenter))));

                        boolean contained = false;

                        for(LineShape lineShape1 : lineShapeCluster)
                        {
                            if(!lineShape1.equals(lineShape)) {
                                if (lineShape1.containsPointForGraph(pointToCheck)) {
                                    contained = true;
                                }
                            }
                        }

                        if(!contained)
                        {
                            nodesAsBooleanGraph[index1][index2] = true;
                            nodesAsBooleanGraph[index2][index1] = true;
                        }

                        String name = "contained";

                        if(!contained)
                        {
                            name = "not";
                        }

                        booleanNodes.add(new Node(pointToCheck, name, new Circle(), new Circle()));
                    }
                }
            }
        }

        return nodesAsBooleanGraph;
        //return booleanNodes;
    }

    //Gets a 2D Array of the whether at least one of the sides between each node in the graph are not contained by a circle.
    //The boolean index is set to true if at least one edge between the nodes is not contained by a circle.
    public static boolean[][] getLineNodesAsBooleanGraph(LinkedList<Line> lineCluster, LinkedList<LinkedList<Node>> lineShapeNodes, LinkedList<Node> nodes, LinkedList<Point> containedPoints)
    {
        LinkedList<Node> booleanNodes = new LinkedList<>();

        boolean[][] nodesAsBooleanGraph = new boolean[nodes.size()][nodes.size()];

        for(int x = 0 ; x < nodesAsBooleanGraph.length ; x++)
        {
            for(int y = 0 ; y < nodesAsBooleanGraph[x].length ; y++)
            {
                nodesAsBooleanGraph[x][y] = false;
            }
        }

        for (int x = 0; x < lineShapeNodes.size(); x++) {
            LinkedList<Node> nodesList = lineShapeNodes.get(x);

            if(nodesList != null) {
                for(int a = 0 ; a < nodesList.size() - 1 ; a++)
                {
                    int index1 = nodes.indexOf(nodesList.get(a));
                    int index2 = nodes.indexOf(nodesList.get(a + 1));

                    if(index1 != -1 && index2 != -1) {
                        Node node0 = nodesList.get(a);
                        Node node1 = nodesList.get(a + 1);

                        LineShape lineShape = lineCluster.get(x);

                        //double degreesFromCenter0 = circleCluster.get(x).getDegreesFromCenter(node0.point);
                        //double degreesFromCenter1 = circleCluster.get(x).getDegreesFromCenter(node1.point);

                        Point pointToCheck = new Point(((node0.point.getX() + node1.point.getX()) / 2), ((node0.point.getY() + node1.point.getY()) / 2));

                        //Point pointToCheck = new Point((int) (circle.getCenterX() + (circle.getRadius() * Math.cos(middleDegreesFromCenter))), (int) (circle.getCenterY() + (circle.getRadius() * Math.sin(middleDegreesFromCenter))));

                        boolean contained = false;

                        for(Point point : containedPoints) {
                            if (point.equals(pointToCheck)) {
                                contained = true;
                            }
                        }
                        if(!contained)
                        {
                            nodesAsBooleanGraph[index1][index2] = true;
                            nodesAsBooleanGraph[index2][index1] = true;
                        }

                        String name = "contained";

                        if(!contained)
                        {
                            name = "not";
                        }

                        booleanNodes.add(new Node(pointToCheck, name, new Circle(), new Circle()));
                    }
                }
            }
        }

        return nodesAsBooleanGraph;
        //return booleanNodes;
    }

    public static LinkedList<Node> getBooleanNodesForCircleGraph(LinkedList<Circle> circleCluster, LinkedList<LinkedList<Node>> circleNodes, LinkedList<Node> nodes)
    {
        LinkedList<Node> booleanNodes = new LinkedList<>();

        boolean[][] nodesAsBooleanGraph = new boolean[nodes.size()][nodes.size()];

        for(int x = 0 ; x < nodesAsBooleanGraph.length ; x++)
        {
            for(int y = 0 ; y < nodesAsBooleanGraph[x].length ; y++)
            {
                nodesAsBooleanGraph[x][y] = false;
            }
        }

        for (int x = 0; x < circleNodes.size(); x++) {
            LinkedList<Node> nodesList = circleNodes.get(x);

            if(nodesList != null) {
                for(int a = 0 ; a < nodesList.size() - 1 ; a++)
                {
                    int index1 = nodes.indexOf(nodesList.get(a));
                    int index2 = nodes.indexOf(nodesList.get(a + 1));

                    if(index1 != -1 && index2 != -1) {
                        Node node0 = nodesList.get(a);
                        Node node1 = nodesList.get(a + 1);

                        Circle circle = circleCluster.get(x);

                        double degreesFromCenter0 = circleCluster.get(x).getDegreesFromCenter(node0.point);
                        double degreesFromCenter1 = circleCluster.get(x).getDegreesFromCenter(node1.point);

                        if(degreesFromCenter0 < 0)
                        {
                            degreesFromCenter0 += 2 * Math.PI;
                        }
                        if(degreesFromCenter0 > 2 * Math.PI)
                        {
                            degreesFromCenter0 -= 2 * Math.PI;
                        }

                        if(degreesFromCenter1 < 0)
                        {
                            degreesFromCenter1 += 2 * Math.PI;
                        }
                        if(degreesFromCenter1 > 2 * Math.PI)
                        {
                            degreesFromCenter1 -= 2 * Math.PI;
                        }

                        if(degreesFromCenter1 < degreesFromCenter0)
                        {
                            degreesFromCenter1 += 2 * Math.PI;
                        }

                        double middleDegreesFromCenter = degreesFromCenter0 + ((degreesFromCenter1 - degreesFromCenter0) / 2);

                        Point pointToCheck = new Point((int) (circle.getCenterX() + (circle.getRadius() * Math.cos(middleDegreesFromCenter))), (int) (circle.getCenterY() + (circle.getRadius() * Math.sin(middleDegreesFromCenter))));

                        boolean contained = false;

                        for(Circle circle1 : circleCluster)
                        {
                            if(!circle1.equals(circle)) {
                                if (circle1.containsPointForGraph(pointToCheck)) {
                                    contained = true;
                                }
                            }
                        }

                        if(!contained)
                        {
                            nodesAsBooleanGraph[index1][index2] = true;
                            nodesAsBooleanGraph[index2][index1] = true;
                        }

                        String name = "contained";

                        if(!contained)
                        {
                            name = "not";
                        }

                        booleanNodes.add(new Node(pointToCheck, name, new Circle(), new Circle()));
                    }
                }

                if(nodesList.size() > 0) {
                    int index1 = nodes.indexOf(nodesList.get(nodesList.size() - 1));
                    int index2 = nodes.indexOf(nodesList.get(0));

                    if(index1 != -1 && index2 != -1) {
                        Node node0 = nodesList.get(nodesList.size() - 1);
                        Node node1 = nodesList.get(0);

                        Circle circle = circleCluster.get(x);

                        double degreesFromCenter0 = circles.get(x).getDegreesFromCenter(node0.point);
                        double degreesFromCenter1 = circles.get(x).getDegreesFromCenter(node1.point);

                        if(degreesFromCenter0 < 0)
                        {
                            degreesFromCenter0 += 2 * Math.PI;
                        }
                        if(degreesFromCenter0 > 2 * Math.PI)
                        {
                            degreesFromCenter0 -= 2 * Math.PI;
                        }

                        if(degreesFromCenter1 < 0)
                        {
                            degreesFromCenter1 += 2 * Math.PI;
                        }
                        if(degreesFromCenter1 > 2 * Math.PI)
                        {
                            degreesFromCenter1 -= 2 * Math.PI;
                        }

                        if(degreesFromCenter1 < degreesFromCenter0)
                        {
                            degreesFromCenter1 += 2 * Math.PI;
                        }

                        double middleDegreesFromCenter = degreesFromCenter0 + ((degreesFromCenter1 - degreesFromCenter0) / 2);

                        Point pointToCheck = new Point((int) (circle.getCenterX() + (circle.getRadius() * Math.cos(middleDegreesFromCenter))), (int) (circle.getCenterY() + (circle.getRadius() * Math.sin(middleDegreesFromCenter))));

                        boolean contained = false;

                        for(Circle circle1 : circleCluster)
                        {
                            if(circle1.containsPoint(pointToCheck))
                            {
                                contained = true;
                            }
                        }

                        if(!contained)
                        {
                            nodesAsBooleanGraph[index1][index2] = true;
                            nodesAsBooleanGraph[index2][index1] = true;
                        }

                        String name = "contained";

                        if(!contained)
                        {
                            name = "not";
                        }

                        booleanNodes.add(new Node(pointToCheck, name, new Circle(), new Circle()));
                    }
                }
            }
        }

        //return nodesAsBooleanGraph;
        return booleanNodes;
    }

    public static LinkedList<Node> getBooleanNodesForLineShapeGraph(LinkedList<LineShape> lineShapeCluster, LinkedList<LinkedList<Node>> lineShapeNodes, LinkedList<Node> nodes)
    {
        LinkedList<Node> booleanNodes = new LinkedList<>();

        boolean[][] nodesAsBooleanGraph = new boolean[nodes.size()][nodes.size()];

        for(int x = 0 ; x < nodesAsBooleanGraph.length ; x++)
        {
            for(int y = 0 ; y < nodesAsBooleanGraph[x].length ; y++)
            {
                nodesAsBooleanGraph[x][y] = false;
            }
        }

        for (int x = 0; x < lineShapeNodes.size(); x++) {
            LinkedList<Node> nodesList = lineShapeNodes.get(x);

            if(nodesList != null) {
                for(int a = 0 ; a < nodesList.size() - 1 ; a++)
                {
                    int index1 = nodes.indexOf(nodesList.get(a));
                    int index2 = nodes.indexOf(nodesList.get(a + 1));

                    if(index1 != -1 && index2 != -1) {
                        Node node0 = nodesList.get(a);
                        Node node1 = nodesList.get(a + 1);

                        LineShape lineShape = lineShapeCluster.get(x);

                        //double degreesFromCenter0 = circleCluster.get(x).getDegreesFromCenter(node0.point);
                        //double degreesFromCenter1 = circleCluster.get(x).getDegreesFromCenter(node1.point);

                        Point pointToCheck = new Point(((node0.point.getX() + node1.point.getX()) / 2), ((node0.point.getY() + node1.point.getY()) / 2));

                        //Point pointToCheck = new Point((int) (circle.getCenterX() + (circle.getRadius() * Math.cos(middleDegreesFromCenter))), (int) (circle.getCenterY() + (circle.getRadius() * Math.sin(middleDegreesFromCenter))));

                        boolean contained = false;

                        for(LineShape lineShape1 : lineShapeCluster)
                        {
                            if(!lineShape1.equals(lineShape)) {
                                if (lineShape1.containsPointForGraph(pointToCheck)) {
                                    contained = true;
                                }
                            }
                        }

                        if(!contained)
                        {
                            nodesAsBooleanGraph[index1][index2] = true;
                            nodesAsBooleanGraph[index2][index1] = true;
                        }

                        String name = "contained";

                        if(!contained)
                        {
                            name = "not";
                        }

                        booleanNodes.add(new Node(pointToCheck, name, new Circle(), new Circle()));
                    }
                }

                if(nodesList.size() > 0) {
                    int index1 = nodes.indexOf(nodesList.get(nodesList.size() - 1));
                    int index2 = nodes.indexOf(nodesList.get(0));

                    if(index1 != -1 && index2 != -1) {
                        Node node0 = nodesList.get(nodesList.size() - 1);
                        Node node1 = nodesList.get(0);

                        LineShape lineShape = lineShapeCluster.get(x);

                        //double degreesFromCenter0 = circleCluster.get(x).getDegreesFromCenter(node0.point);
                        //double degreesFromCenter1 = circleCluster.get(x).getDegreesFromCenter(node1.point);

                        Point pointToCheck = new Point(((node0.point.getX() + node1.point.getX()) / 2), ((node0.point.getY() + node1.point.getY()) / 2));

                        //Point pointToCheck = new Point((int) (circle.getCenterX() + (circle.getRadius() * Math.cos(middleDegreesFromCenter))), (int) (circle.getCenterY() + (circle.getRadius() * Math.sin(middleDegreesFromCenter))));

                        boolean contained = false;

                        for(LineShape lineShape1 : lineShapeCluster)
                        {
                            if(!lineShape1.equals(lineShape)) {
                                if (lineShape1.containsPointForGraph(pointToCheck)) {
                                    contained = true;
                                }
                            }
                        }

                        if(!contained)
                        {
                            nodesAsBooleanGraph[index1][index2] = true;
                            nodesAsBooleanGraph[index2][index1] = true;
                        }

                        String name = "contained";

                        if(!contained)
                        {
                            name = "not";
                        }

                        booleanNodes.add(new Node(pointToCheck, name, new Circle(), new Circle()));
                    }
                }
            }
        }

        //return nodesAsBooleanGraph;
        return booleanNodes;
    }

    //TODO This function doesn't do like anything.
    public static LinkedList<Node> getBooleanNodesForLineGraph(LinkedList<Line> lineCluster, LinkedList<LinkedList<Node>> lineShapeNodes, LinkedList<Node> nodes, LinkedList<Point> containedPoints)
    {
        LinkedList<Node> booleanNodes = new LinkedList<>();

        boolean[][] nodesAsBooleanGraph = new boolean[nodes.size()][nodes.size()];

        for(int x = 0 ; x < nodesAsBooleanGraph.length ; x++)
        {
            for(int y = 0 ; y < nodesAsBooleanGraph[x].length ; y++)
            {
                nodesAsBooleanGraph[x][y] = false;
            }
        }

        for (int x = 0; x < lineShapeNodes.size(); x++) {
            LinkedList<Node> nodesList = lineShapeNodes.get(x);

            if(nodesList != null) {
                for(int a = 0 ; a < nodesList.size() - 1 ; a++)
                {
                    int index1 = nodes.indexOf(nodesList.get(a));
                    int index2 = nodes.indexOf(nodesList.get(a + 1));

                    if(index1 != -1 && index2 != -1) {
                        Node node0 = nodesList.get(a);
                        Node node1 = nodesList.get(a + 1);

                        LineShape lineShape = lineCluster.get(x);

                        //double degreesFromCenter0 = circleCluster.get(x).getDegreesFromCenter(node0.point);
                        //double degreesFromCenter1 = circleCluster.get(x).getDegreesFromCenter(node1.point);

                        Point pointToCheck = new Point(((node0.point.getX() + node1.point.getX()) / 2), ((node0.point.getY() + node1.point.getY()) / 2));

                        //Point pointToCheck = new Point((int) (circle.getCenterX() + (circle.getRadius() * Math.cos(middleDegreesFromCenter))), (int) (circle.getCenterY() + (circle.getRadius() * Math.sin(middleDegreesFromCenter))));

                        boolean contained = false;

                        //This doesn't make sense.
                        for(Point point : containedPoints) {
                            if (point.equals(pointToCheck)) {
                                contained = true;
                            }
                        }
                        if(!contained)
                        {
                            nodesAsBooleanGraph[index1][index2] = true;
                            nodesAsBooleanGraph[index2][index1] = true;
                        }

                        String name = "contained";

                        if(!contained)
                        {
                            name = "not";
                        }

                        booleanNodes.add(new Node(pointToCheck, name, new Circle(), new Circle()));
                    }
                }
            }
        }

        //return nodesAsBooleanGraph;
        return booleanNodes;
    }
}
