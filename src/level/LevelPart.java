package level;

import arrangement.ArrangementProperties;
import game.GraphicsDisplay;

import java.util.Arrays;
import java.util.LinkedList;

public class LevelPart {
    //String property;
    private static Boolean[] twoDistinctLines = new Boolean[]{false, false};
    private static Boolean[] threeDistinctLines = new Boolean[]{false, false, false, false};
    private static Boolean[] fourDistinctLines = new Boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    private static Boolean[] twoDistinctTriangles = new Boolean[]{false, false, false, false, false, false, false, false};
    private static Boolean[] twoDistinctSquares = new Boolean[]{false, false, false, false, false, false, false, false, false, false, false, false};
    private static Boolean[] twoDistinctCircles = new Boolean[]{false, false, false};
    private static Boolean[] threeDistinctCircles = new Boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    private static Boolean[] distinctTriangleCircles = new Boolean[]{false, false, false, false};

    public LinkedList<Property> properties;

    private String shapeType;

    private boolean isSolved = false;

    public LevelPart(String shapeType, int numberOfShapes, String propertyAsString)
    {
        this.shapeType = shapeType;
        properties = new LinkedList<Property>();

        properties.add(new Property("sh-" + numberOfShapes));
        properties.add(new Property(propertyAsString));
    }

    public LevelPart(String shapeType, String[] propertiesArray)
    {
        this.shapeType = shapeType;
        properties = new LinkedList<Property>();

        for(String propertyAsString : propertiesArray) {
            properties.add(new Property(propertyAsString));
        }
    }

    public String getShapeType() {
        return shapeType;
    }

    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public LinkedList<Property> getProperties()
    {
        return properties;
    }

    public static boolean isCurrentArrangementDistinct()
    {
        Integer[] numberOfIntersectionsPerSide = ArrangementProperties.getNumberOfIntersectionsPerSide().toArray(new Integer[ArrangementProperties.getNumberOfIntersectionsPerSide().size()]);

        if(Level.levelName.equals("dL1"))
        {
            if(ArrangementProperties.getNumberOfShapes() == 2) {
                if (ArrangementProperties.getNumberOfIntersections() == 0) {
                    if(!twoDistinctLines[0])
                    {
                        twoDistinctLines[0] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 0;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 1) {
                    if(!twoDistinctLines[1])
                    {
                        twoDistinctLines[1] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 1;
                        return true;
                    }
                }
            }
        }


        if(Level.levelName.equals("dL2"))
        {
            if(ArrangementProperties.getNumberOfShapes() == 3) {
                if (ArrangementProperties.getNumberOfIntersections() == 0) {
                    if(!threeDistinctLines[0])
                    {
                        threeDistinctLines[0] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 0;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 1) {
                    if(!threeDistinctLines[1])
                    {
                        threeDistinctLines[1] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 1;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 2) {
                    if(!threeDistinctLines[2])
                    {
                        threeDistinctLines[2] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 2;
                        return true;
                    }
                }else if (ArrangementProperties.getNumberOfIntersections() == 3) {
                    if(!threeDistinctLines[3])
                    {
                        threeDistinctLines[3] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 3;
                        return true;
                    }
                }
            }
        }


        if(Level.levelName.equals("dL3")) {
            if (ArrangementProperties.getNumberOfShapes() == 4) {
                if (Arrays.equals(numberOfIntersectionsPerSide,new Integer[]{0, 0, 0, 0})) {
                    if (!fourDistinctLines[0]) {
                        fourDistinctLines[0] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 0;
                        return true;
                    }
                } else if (Arrays.equals(numberOfIntersectionsPerSide,new Integer[]{1, 1, 0, 0})) {
                    if (!fourDistinctLines[1]) {
                        fourDistinctLines[1] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 1;
                        return true;
                    }
                } else if (Arrays.equals(numberOfIntersectionsPerSide,new Integer[]{1, 1, 1, 1})) {
                    if (!fourDistinctLines[2]) {
                        fourDistinctLines[2] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 2;
                        return true;
                    }
                } else if (Arrays.equals(numberOfIntersectionsPerSide,new Integer[]{2, 1, 1, 0})) {
                    if (!fourDistinctLines[3]) {
                        fourDistinctLines[3] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 3;
                        return true;
                    }
                } else if (Arrays.equals(numberOfIntersectionsPerSide,new Integer[]{3, 1, 1, 1})) {
                    if (!fourDistinctLines[4]) {
                        fourDistinctLines[4] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 4;
                        return true;
                    }
                } else if (Arrays.equals(numberOfIntersectionsPerSide,new Integer[]{2, 2, 2, 0}) && ArrangementProperties.getNumberOfContainedPoints() == 0) {
                    if (!fourDistinctLines[5]) {
                        fourDistinctLines[5] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 5;
                        return true;
                    }
                } else if (Arrays.equals(numberOfIntersectionsPerSide,new Integer[]{2, 2, 2, 0}) && ArrangementProperties.getNumberOfContainedPoints() == 2) {
                    if (!fourDistinctLines[14]) {
                        fourDistinctLines[14] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 14;
                        return true;
                    }
                }
                else if (Arrays.equals(numberOfIntersectionsPerSide,new Integer[]{2, 2, 1, 1})) {
                    if (!fourDistinctLines[6]) {
                        fourDistinctLines[6] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 6;
                        return true;
                    }
                } else if (Arrays.equals(numberOfIntersectionsPerSide,new Integer[]{2, 2, 2, 2})) {
                    if (!fourDistinctLines[7]) {
                        fourDistinctLines[7] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 7;
                        return true;
                    }
                } else if (Arrays.equals(numberOfIntersectionsPerSide,new Integer[]{3, 2, 2, 1}) && ArrangementProperties.getNumberOfContainedPoints() == 0) {
                    if (!fourDistinctLines[8]) {
                        fourDistinctLines[8] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 8;
                        return true;
                    }
                } else if (Arrays.equals(numberOfIntersectionsPerSide,new Integer[]{3, 2, 2, 1}) && ArrangementProperties.getNumberOfContainedPoints() == 1) {
                    if (!fourDistinctLines[9]) {
                        fourDistinctLines[9] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 9;
                        return true;
                    }
                } else if (Arrays.equals(numberOfIntersectionsPerSide,new Integer[]{3, 3, 2, 2}) && ArrangementProperties.getNumberOfContainedPoints() == 1 && ArrangementProperties.getNumberOfTriangleSections() == 2) {
                    if (!fourDistinctLines[10]) {
                        fourDistinctLines[10] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 10;
                        return true;
                    }
                } else if (Arrays.equals(numberOfIntersectionsPerSide,new Integer[]{3, 3, 2, 2}) && ArrangementProperties.getNumberOfContainedPoints() == 0 && ArrangementProperties.getNumberOfTriangleSections() == 2) {
                    if (!fourDistinctLines[11]) {
                        fourDistinctLines[11] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 11;
                        return true;
                    }
                } else if (Arrays.equals(numberOfIntersectionsPerSide,new Integer[]{3, 3, 2, 2}) && ArrangementProperties.getNumberOfContainedPoints() == 0 && ArrangementProperties.getNumberOfTriangleSections() == 1) {
                    if (!fourDistinctLines[12]) {
                        fourDistinctLines[12] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 12;
                        return true;
                    }
                } else if (Arrays.equals(numberOfIntersectionsPerSide,new Integer[]{3, 3, 3, 3})) {
                    if (!fourDistinctLines[13]) {
                        fourDistinctLines[13] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 13;
                        return true;
                    }
                }
            }
        }


        if(Level.levelName.equals("dT1"))
        {
            if(ArrangementProperties.getNumberOfShapes() == 2) {
                if (ArrangementProperties.getNumberOfIntersections() == 0 && ArrangementProperties.getNumberOfContainedPoints() == 0) {
                    if(!twoDistinctTriangles[0])
                    {
                        twoDistinctTriangles[0] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 0;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 0 && ArrangementProperties.getNumberOfContainedPoints() == 3) {
                    if(!twoDistinctTriangles[1])
                    {
                        twoDistinctTriangles[1] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 1;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 2 && ArrangementProperties.getNumberOfContainedPoints() == 1) {
                    if(!twoDistinctTriangles[2])
                    {
                        twoDistinctTriangles[2] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 2;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 2 && ArrangementProperties.getNumberOfContainedPoints() == 2 && Arrays.equals(numberOfIntersectionsPerSide, new Integer[] {2, 1, 1, 0, 0, 0})) {
                    if(!twoDistinctTriangles[3])
                    {
                        twoDistinctTriangles[3] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 3;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 4 && ArrangementProperties.getNumberOfContainedPoints() == 1) {
                    if(!twoDistinctTriangles[4])
                    {
                        twoDistinctTriangles[4] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 4;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 2 && ArrangementProperties.getNumberOfContainedPoints() == 2 && Arrays.equals(numberOfIntersectionsPerSide, new Integer[] {1, 1, 1, 1, 0, 0})) {
                    if(!twoDistinctTriangles[5])
                    {
                        twoDistinctTriangles[5] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 5;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 4 && ArrangementProperties.getNumberOfContainedPoints() == 0) {
                    if(!twoDistinctTriangles[6])
                    {
                        twoDistinctTriangles[6] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 6;
                        return true;
                    }
                }
                else if (ArrangementProperties.getNumberOfIntersections() == 6 && ArrangementProperties.getNumberOfContainedPoints() == 0) {
                    if(!twoDistinctTriangles[7])
                    {
                        twoDistinctTriangles[7] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 7;
                        return true;
                    }
                }
            }
        }


        if(Level.levelName.equals("dS1"))
        {
            if(ArrangementProperties.getNumberOfShapes() == 2) {
                if (ArrangementProperties.getNumberOfIntersections() == 0 && ArrangementProperties.getNumberOfContainedPoints() == 0) {
                    if(!twoDistinctSquares[0])
                    {
                        twoDistinctSquares[0] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 0;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 0 && ArrangementProperties.getNumberOfContainedPoints() == 4) {
                    if(!twoDistinctSquares[1])
                    {
                        twoDistinctSquares[1] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 1;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 2 && ArrangementProperties.getNumberOfContainedPoints() == 1) {
                    if(!twoDistinctSquares[2])
                    {
                        twoDistinctSquares[2] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 2;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 2 && ArrangementProperties.getNumberOfContainedPoints() == 2 && Arrays.equals(numberOfIntersectionsPerSide, new Integer[] {2, 1, 1, 0, 0, 0, 0, 0})) {
                    if(!twoDistinctSquares[3])
                    {
                        twoDistinctSquares[3] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 3;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 2 && ArrangementProperties.getNumberOfContainedPoints() == 3  && Arrays.equals(numberOfIntersectionsPerSide, new Integer[] {2, 1, 1, 0, 0, 0, 0, 0})) {
                    if(!twoDistinctSquares[4])
                    {
                        twoDistinctSquares[4] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 4;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 2 && ArrangementProperties.getNumberOfContainedPoints() == 2 && Arrays.equals(numberOfIntersectionsPerSide, new Integer[] {1, 1, 1, 1, 0, 0, 0, 0})) {
                    if(!twoDistinctSquares[5])
                    {
                        twoDistinctSquares[5] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 5;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 4 && ArrangementProperties.getNumberOfContainedPoints() == 2 && Arrays.equals(numberOfIntersectionsPerSide, new Integer[] {2, 2, 2, 1, 1, 0, 0, 0})) {
                    if(!twoDistinctSquares[6])
                    {
                        twoDistinctSquares[6] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 6;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 2 && ArrangementProperties.getNumberOfContainedPoints() == 3 && Arrays.equals(numberOfIntersectionsPerSide, new Integer[] {1, 1, 1, 1, 0, 0, 0, 0})) {
                    if(!twoDistinctSquares[7])
                    {
                        twoDistinctSquares[7] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 7;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 6 && ArrangementProperties.getNumberOfContainedPoints() == 1) {
                    if(!twoDistinctSquares[8])
                    {
                        twoDistinctSquares[8] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 8;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 4 && ArrangementProperties.getNumberOfContainedPoints() == 2 && Arrays.equals(numberOfIntersectionsPerSide, new Integer[] {2, 2, 1, 1, 1, 1, 0, 0})) {
                    if(!twoDistinctSquares[9])
                    {
                        twoDistinctSquares[9] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 9;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 4 && ArrangementProperties.getNumberOfContainedPoints() == 1) {
                    if(!twoDistinctSquares[10])
                    {
                        twoDistinctSquares[10] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 10;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 8 && ArrangementProperties.getNumberOfContainedPoints() == 0) {
                    if(!twoDistinctSquares[11])
                    {
                        twoDistinctSquares[11] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 11;
                        return true;
                    }
                }
            }
        }

        if(Level.levelName.equals("dC1"))
        {
            if(ArrangementProperties.getNumberOfShapes() == 2) {
                if (ArrangementProperties.getNumberOfIntersections() == 0 && ArrangementProperties.getNumberOfCompleteOverlaps() == 0) {
                    if(!twoDistinctCircles[0])
                    {
                        twoDistinctCircles[0] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 0;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 0 && ArrangementProperties.getNumberOfCompleteOverlaps() == 1) {
                    if(!twoDistinctCircles[1])
                    {
                        twoDistinctCircles[1] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 1;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 2 && ArrangementProperties.getNumberOfCompleteOverlaps() == 0) {
                    if(!twoDistinctCircles[2])
                    {
                        twoDistinctCircles[2] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 2;
                        return true;
                    }
                }
            }
        }

        if(Level.levelName.equals("dC2"))
        {
            if(ArrangementProperties.getNumberOfShapes() == 3) {
                if (ArrangementProperties.getNumberOfIntersections() == 0 && ArrangementProperties.getNumberOfCompleteOverlapsForDistinctCircles() == 0 && ArrangementProperties.getNumberOfContainedIntersections() == 0) {
                    if(!threeDistinctCircles[0])
                    {
                        threeDistinctCircles[0] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 0;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 0 && ArrangementProperties.getNumberOfCompleteOverlapsForDistinctCircles() == 1 && ArrangementProperties.getNumberOfContainedIntersections() == 0) {
                    if(!threeDistinctCircles[1])
                    {
                        threeDistinctCircles[1] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 1;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 0 && ArrangementProperties.getNumberOfCompleteOverlapsForDistinctCircles() == 2 && ArrangementProperties.getNumberOfContainedIntersections() == 0) {
                    if(!threeDistinctCircles[2])
                    {
                        threeDistinctCircles[2] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 2;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 0 && ArrangementProperties.getNumberOfCompleteOverlapsForDistinctCircles() == 3 && ArrangementProperties.getNumberOfContainedIntersections() == 0) {
                    if(!threeDistinctCircles[3])
                    {
                        threeDistinctCircles[3] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 3;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 2 && ArrangementProperties.getNumberOfCompleteOverlapsForDistinctCircles() == 0 && ArrangementProperties.getNumberOfContainedIntersections() == 0) {
                    if(!threeDistinctCircles[4])
                    {
                        threeDistinctCircles[4] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 4;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 4 && ArrangementProperties.getNumberOfCompleteOverlapsForDistinctCircles() == 0 && ArrangementProperties.getNumberOfContainedIntersections() == 0) {
                    if(!threeDistinctCircles[5])
                    {
                        threeDistinctCircles[5] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 5;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 2 && ArrangementProperties.getNumberOfCompleteOverlapsForDistinctCircles() == 2 && ArrangementProperties.getNumberOfContainedIntersections() == 0) {
                    if(!threeDistinctCircles[6])
                    {
                        threeDistinctCircles[6] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 6;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 2 && ArrangementProperties.getNumberOfCompleteOverlapsForDistinctCircles() == 1 && ArrangementProperties.getNumberOfContainedIntersections() == 0) {
                    if(!threeDistinctCircles[7])
                    {
                        threeDistinctCircles[7] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 7;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 6 && ArrangementProperties.getNumberOfCompleteOverlapsForDistinctCircles() == 0 && ArrangementProperties.getNumberOfContainedIntersections() == 3 && ArrangementProperties.getNumberOfTriangleSections() == 7) {
                    if(!threeDistinctCircles[8])
                    {
                        threeDistinctCircles[8] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 8;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 4 && ArrangementProperties.getNumberOfCompleteOverlapsForDistinctCircles() == 1 && ArrangementProperties.getNumberOfContainedIntersections() == 2) {
                    if(!threeDistinctCircles[9])
                    {
                        threeDistinctCircles[9] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 9;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 6 && ArrangementProperties.getNumberOfCompleteOverlapsForDistinctCircles() == 0 && ArrangementProperties.getNumberOfContainedIntersections() == 4) {
                    if(!threeDistinctCircles[10])
                    {
                        threeDistinctCircles[10] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 10;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 2 && ArrangementProperties.getNumberOfCompleteOverlapsForDistinctCircles() == 2 && ArrangementProperties.getNumberOfContainedIntersections() == 2) {
                    if(!threeDistinctCircles[11])
                    {
                        threeDistinctCircles[11] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 11;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 6 && ArrangementProperties.getNumberOfCompleteOverlapsForDistinctCircles() == 0 && ArrangementProperties.getNumberOfContainedIntersections() == 3 && ArrangementProperties.getNumberOfTriangleSections() == 1) {
                    if(!threeDistinctCircles[12])
                    {
                        threeDistinctCircles[12] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 12;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfIntersections() == 6 && ArrangementProperties.getNumberOfCompleteOverlapsForDistinctCircles() == 0 && ArrangementProperties.getNumberOfContainedIntersections() == 2) {
                    if(!threeDistinctCircles[13])
                    {
                        threeDistinctCircles[13] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 13;
                        return true;
                    }
                }
            }
        }

        if(Level.levelName.equals("d,tC"))
        {
            if(ArrangementProperties.getNumberOfShapes() == 3) {
                if (ArrangementProperties.getNumberOfTriangleSections() == 1) {
                    if(!distinctTriangleCircles[0])
                    {
                        distinctTriangleCircles[0] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 0;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfTriangleSections() == 2 && ArrangementProperties.getNumberOfContainedIntersections() == 2) {
                    if(!distinctTriangleCircles[1])
                    {
                        distinctTriangleCircles[1] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 1;
                        return true;
                    }
                } else if (ArrangementProperties.getNumberOfTriangleSections() == 2  && ArrangementProperties.getNumberOfContainedIntersections() == 4) {
                    if(!distinctTriangleCircles[2])
                    {
                        distinctTriangleCircles[2] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 2;
                        return true;
                    }
                }else if (ArrangementProperties.getNumberOfTriangleSections() == 7) {
                    if(!distinctTriangleCircles[3])
                    {
                        distinctTriangleCircles[3] = true;
                        GraphicsDisplay.levelPartButtons[Level.levelPartNumber].distinctArrangementNumber = 3;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean hasDistinctProperty()
    {
        for(Property property : properties)
        {
            if(property.propertyAsString.equals("d"))
            {
                return true;
            }
        }

        return false;
    }
}
