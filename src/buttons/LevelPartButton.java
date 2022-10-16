package buttons;

import game.Display;
import game.GraphicsDisplay;
import game.MyColor;
import level.Level;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LevelPartButton extends Button {
    public int distinctArrangementNumber = -1;

    public LevelPartButton(int x, int y, int xLength, int yLength, int number) {
        super(x, y, xLength, yLength, number);
    }


    public void display(Graphics g2, Graphics2D g)
    {
        g.setColor(MyColor.getColor("levelUnselected"));

        if(getNumber() != -1 && getNumber() <= Level.getLevelParts().length - 1)
        {
            if(Level.getLevelParts()[getNumber()].isSolved())
            {
                g.setColor(MyColor.getColor("levelPartiallySolvedUnselected"));

                if(Level.isTwoThirdsSolved(Level.levelName))
                {
                    g.setColor(MyColor.getColor("levelTwoThirdsSolvedUnselected"));
                }

                if(Level.isLevelSolved(Level.levelName))
                {
                    g.setColor(MyColor.getColor("levelSolvedUnselected"));
                }

                /*
                g.setColor(MyColor.getColor("levelPartSolvedUnselected"));




                 */
            }
        }

        if(getHovered())
        {
            g.setColor(MyColor.getColor("levelSelected"));

            if(getNumber() != -1 && getNumber() <= Level.getLevelParts().length - 1)
            {

                if(Level.getLevelParts()[getNumber()].isSolved())
                {

                    g.setColor(MyColor.getColor("levelPartiallySolvedSelected"));

                    if(Level.isTwoThirdsSolved(Level.levelName))
                    {
                        g.setColor(MyColor.getColor("levelTwoThirdsSolvedSelected"));
                    }

                    if(Level.isLevelSolved(Level.levelName))
                    {
                        g.setColor(MyColor.getColor("levelSolvedSelected"));
                    }

                    /*
                    g.setColor(MyColor.getColor("levelPartSolvedSelected"));





                     */
                }
            }
        }

        g.setStroke(new BasicStroke(3));

        if(getNumber() > Level.getLevelParts().length - 1) {
            g.setColor(MyColor.getColor("levelPartUnavailable"));
        }

        displayRectangle(g);

        if (getNumber() != -1) {
            if(getNumber() <= Level.getLevelParts().length - 1)
            {
                if(getNumber() == Level.levelPartNumber)
                {
                    g.setColor(MyColor.getColor("currentLevelPartText"));
                }
                else
                {
                    g.setColor(MyColor.getColor("notCurrentLevelPartText"));
                }

                displayNumber(g);
            }
        }

        if(getNumber() != -1 && getNumber() <= Level.getLevelParts().length - 1) {
            if (Level.getLevelParts()[getNumber()].isSolved() && Level.getLevelParts()[getNumber()].hasDistinctProperty()) {
                //Image logoImage = Toolkit.getDefaultToolkit().getImage("Arrangement_Images/" + "intersectingCircles.png");
                //g.drawImage(Toolkit.getDefaultToolkit().getImage("Arrangement_Images/" + "levelSolvedBlank.png"), (int) (getX() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (getY() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (getXLength() * GraphicsDisplay.scalingFactor), (int) (getYLength() * GraphicsDisplay.scalingFactor), null);

                if(distinctArrangementNumber != -1)
                {
                    Map<RenderingHints.Key, Object> hints = new HashMap<RenderingHints.Key, Object>();
                    hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    hints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
                    hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //hints.put(RenderingHints.KEY_RESOLUTION_VARIANT, RenderingHints.)
                    g.setRenderingHints(hints);

                    String imageName = Level.levelName + "-" + distinctArrangementNumber + ".png";
                    g.drawImage(Toolkit.getDefaultToolkit().getImage("Arrangement_Images/" + imageName), (int) (getX() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (getY() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (getXLength() * GraphicsDisplay.scalingFactor), (int) (getYLength() * GraphicsDisplay.scalingFactor), null);
                    //g.drawImage(Toolkit.getDefaultToolkit().getImage("Arrangement_Images/" + imageName), (int) (getX() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (getY() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, null);
                }


            }
        }
    }
}