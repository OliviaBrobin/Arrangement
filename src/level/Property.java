package level;

public class Property {

    public String propertyAsString = "";

    private boolean shapeDrawn = false;
    private boolean shapeMoved = false;
    private boolean shapeDeleted = false;
    private boolean undoPerformed = false;
    private boolean clearPerformed = false;
    private boolean actionPerformed = false;
    private int numberOfShapes = -1;
    private boolean basic = false;
    private boolean formsTriangleSections = false;
    private int numberOfTriangleSections = -1;
    private int numberOfQuadrilateralSections = -1;
    private boolean distinct;
    private int numberOfIntersections = -1;
    private int numberOfSections = -1;
    private int numberOfOutsideEdges = -1;
    private int numberOfInsideEdges = -1;
    private int numberOfContainedIntersections = -1;
    private int totalNumberOfEdges = -1;
    private boolean theEnd = false;
    private boolean infoText = true;

    public Property(String propertyAsString)
    {

        String[] propertyParts = propertyAsString.split("-");

        this.propertyAsString = propertyParts[0];

        if(propertyParts[0].equals("tutorial0"))
        {
            shapeDrawn = Boolean.parseBoolean(propertyParts[1]);
        }
        if(propertyParts[0].equals("tutorial1"))
        {
            numberOfShapes = 10;
        }
        if(propertyParts[0].equals("tutorial2"))
        {
            shapeMoved = Boolean.parseBoolean(propertyParts[1]);
        }
        if(propertyParts[0].equals("tutorial3"))
        {
            shapeDeleted = Boolean.parseBoolean(propertyParts[1]);
        }
        if(propertyParts[0].equals("tutorial4"))
        {
            clearPerformed = Boolean.parseBoolean(propertyParts[1]);
        }
        if(propertyParts[0].equals("tutorial5"))
        {
            undoPerformed = Boolean.parseBoolean(propertyParts[1]);
        }
        if(propertyParts[0].equals("sh"))
        {
            numberOfShapes = Integer.parseInt(propertyParts[1]);
        }
        if (propertyParts[0].equals("b")) {
            basic = Boolean.parseBoolean(propertyParts[1]);
        }
        if (propertyParts[0].equals("d")) {
            distinct = Boolean.parseBoolean(propertyParts[1]);
        }
        if(propertyParts[0].equals("ft"))
        {
            formsTriangleSections = Boolean.parseBoolean(propertyParts[1]);
        }
        if (propertyParts[0].equals("ts")) {
            numberOfTriangleSections = Integer.parseInt(propertyParts[1]);
        }
        if (propertyParts[0].equals("qs")) {
            numberOfQuadrilateralSections = Integer.parseInt(propertyParts[1]);
        }
        if (propertyParts[0].equals("i")) {
            numberOfIntersections = Integer.parseInt(propertyParts[1]);
        }
        if (propertyParts[0].equals("s")) {
            numberOfSections = Integer.parseInt(propertyParts[1]);
        }
        if (propertyParts[0].equals("oe")) {
            numberOfOutsideEdges = Integer.parseInt(propertyParts[1]);
        }
        if (propertyParts[0].equals("ie")) {
            numberOfInsideEdges = Integer.parseInt(propertyParts[1]);
        }
        if (propertyParts[0].equals("ci")) {
            numberOfContainedIntersections = Integer.parseInt(propertyParts[1]);
        }
        if (propertyParts[0].equals("te")) {
            totalNumberOfEdges = Integer.parseInt(propertyParts[1]);
        }
        if(propertyParts[0].equals("the end"))
        {
            theEnd = Boolean.parseBoolean(propertyParts[1]);
        }
        if(propertyParts[0].equals("infoText"))
        {
            infoText = Boolean.parseBoolean(propertyParts[1]);
        }
    }

    public String getPropertyAsString() {return propertyAsString;}

    public int getNumberOfShapes() {
        return numberOfShapes;
    }

    public void setNumberOfShapes(int numberOfShapes) {
        this.numberOfShapes = numberOfShapes;
    }

    public boolean isBasic() {
        return basic;
    }

    public void setBasic(boolean basic) {
        this.basic = basic;
    }

    public boolean hasShapeDrawn() {
        return shapeDrawn;
    }

    public void setShapeDrawn(boolean shapeDrawn) {
        this.shapeDrawn = shapeDrawn;
    }

    public boolean hasShapeMoved() {
        return shapeMoved;
    }

    public void setShapeMoved(boolean shapeMoved) {
        this.shapeMoved = shapeMoved;
    }

    public boolean hasShapeDeleted() {
        return shapeDeleted;
    }

    public void setShapeDeleted(boolean shapeDeleted) {
        this.shapeDeleted = shapeDeleted;
    }

    public boolean hasActionPerformed() {
        return actionPerformed;
    }

    public void setActionPerformed(boolean actionPerformed) {
        this.actionPerformed = actionPerformed;
    }

    public boolean hasUndoPerformed() {
        return undoPerformed;
    }

    public void setUndoPerformed(boolean undoPerformed) {
        this.undoPerformed = undoPerformed;
    }

    public boolean hasClearPerformed() {
        return clearPerformed;
    }

    public void setClearPerformed(boolean clearPerformed) {
        this.clearPerformed = clearPerformed;
    }

    public boolean formsTriangleSections() {
        return formsTriangleSections;
    }

    public int getNumberOfTriangleSections() {
        return numberOfTriangleSections;
    }

    public void setNumberOfTriangleSections(int numberOfTriangleSections) {
        this.numberOfTriangleSections = numberOfTriangleSections;
    }

    public int getNumberOfQuadrilateralSections() {
        return numberOfQuadrilateralSections;
    }

    public void setNumberOfQuadrilateralSections(int numberOfQuadrilateralSections) {
        this.numberOfQuadrilateralSections = numberOfQuadrilateralSections;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public int getNumberOfIntersections() {
        return numberOfIntersections;
    }

    public void setNumberOfIntersections(int numberOfIntersections) {
        this.numberOfIntersections = numberOfIntersections;
    }

    public int getNumberOfSections() {
        return numberOfSections;
    }

    public void setNumberOfSections(int numberOfSections) {
        this.numberOfSections = numberOfSections;
    }

    public int getNumberOfOutsideEdges() {
        return numberOfOutsideEdges;
    }

    public void setNumberOfOutsideEdges(int numberOfOutsideEdges) {
        this.numberOfOutsideEdges = numberOfOutsideEdges;
    }

    public int getNumberOfInsideEdges() {
        return numberOfInsideEdges;
    }

    public void setNumberOfInsideEdges(int numberOfInsideEdges) {
        this.numberOfInsideEdges = numberOfInsideEdges;
    }

    public int getNumberOfContainedIntersections() {
        return numberOfContainedIntersections;
    }

    public int getTotalNumberOfEdges() {
        return totalNumberOfEdges;
    }

    public void setTotalNumberOfEdges(int totalNumberOfEdges) {
        this.totalNumberOfEdges = totalNumberOfEdges;
    }

    public boolean isTheEnd()
    {
        return theEnd;
    }
}
