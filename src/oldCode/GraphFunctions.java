package oldCode;

// Java program to find number
// of triangles in an Undirected
// Graph. The program is for
// adjacency matrix representation
// of the graph
import arrangement.BasicArrangementPropertyFunctions;
import shapes.Circle;
import shapes.Point;

import java.io.*;
import java.util.LinkedList;

import static arrangement.Arrangement.circles;
import static arrangement.Arrangement.getCirclesAsShapes;

public class GraphFunctions
{
    // Number of vertices in
    // the graph
    int V = 4;

    //  Utility function for
    // matrix multiplication
    static void multiply(int A[][], int B[][],
                         int C[][])
    {
        for (int i = 0; i < A.length; i++)
        {
            for (int j = 0; j < A.length; j++)
            {
                C[i][j] = 0;
                for (int k = 0; k < A.length;
                     k++)
                {
                    C[i][j] += A[i][k]*
                            B[k][j];
                }
            }
        }
    }

    // Utility function to calculate
    // trace of a matrix (sum of
    // diagonal elements)
    static int getTrace(int graph[][])
    {
        int trace = 0;

        for (int i = 0; i < graph.length; i++)
        {
            trace += graph[i][i];
        }
        return trace;
    }

    // Utility function for
    // calculating number of
    // triangles in graph
    public static int triangleInGraph(int graph[][])
    {
        // To Store graph^2
        int[][] aux2 = new int[graph.length][graph.length];

        // To Store graph^3
        int[][] aux3 = new int[graph.length][graph.length];

        // Initialising aux matrices
        // with 0
        for (int i = 0; i < graph.length; ++i)
        {
            for (int j = 0; j < graph.length; ++j)
            {
                aux2[i][j] = aux3[i][j] = 0;
            }
        }

        // aux2 is graph^2 now
        // printMatrix(aux2)
        multiply(graph, graph, aux2);

        // after this multiplication aux3
        // is graph^3 printMatrix(aux3)
        multiply(graph, aux2, aux3);

        /*
        for (int i = 0; i < aux3.length; i++) {
            for (int j = 0; j < aux3.length; j++) {
                System.out.print(aux3[i][j] + "   ");
            }

            System.out.println();
        }
         */

        int trace = getTrace(aux3);

        return trace / 6;
    }

    public static int[][] getTriangleInGraphTraceGraph(int graph[][])
    {
// To Store graph^2
        int[][] aux2 = new int[graph.length][graph.length];

        // To Store graph^3
        int[][] aux3 = new int[graph.length][graph.length];

        // Initialising aux matrices
        // with 0
        for (int i = 0; i < graph.length; ++i)
        {
            for (int j = 0; j < graph.length; ++j)
            {
                aux2[i][j] = aux3[i][j] = 0;
            }
        }

        // aux2 is graph^2 now
        // printMatrix(aux2)
        multiply(graph, graph, aux2);

        // after this multiplication aux3
        // is graph^3 printMatrix(aux3)
        multiply(graph, aux2, aux3);

        return  aux3;
    }

    /*
    public static int getNumberOfTriangleSectionsFromCircleGraph()
    {
        int[][] circleNodesAsGraph = getCircleNodesAsGraph();

        int[] numberOfOutsideEdgesForCircleClusters = getNumberOfOutsideEdgesForCircleClusters();

        int numberOfOutsideEdgeTriangles = 0;

        for(Integer numberOfOutsideEdges : numberOfOutsideEdgesForCircleClusters)
        {
            if(numberOfOutsideEdges == 3)
            {
                numberOfOutsideEdgeTriangles++;
            }
        }



        return GraphFunctions.triangleInGraph(circleNodesAsGraph) - numberOfOutsideEdgeTriangles;
    }
     */
}

// This code is from GeeksForGeeks.com