package buttons;

import game.GraphicsDisplay;
import game.MyColor;
import level.Level;

import java.awt.*;

public class LevelButton extends Button {
    public LevelButton(double x, double y, int xLength, int yLength, String name) {
        super((int) x, (int) y, xLength, yLength, name);
    }

    @Override
    public void display(Graphics2D g)
    {
        g.setColor(MyColor.getColor("levelUnselected"));

        if(getHovered())
        {
            g.setColor(MyColor.getColor("levelSelected"));
        }


        if(Level.isPartiallySolved(getName()))
        {
            g.setColor(MyColor.getColor("levelPartiallySolvedUnselected"));

            if(getHovered())
            {
                g.setColor(MyColor.getColor("levelPartiallySolvedSelected"));
            }
        }

        if(Level.isTwoThirdsSolved(getName()))
        {
            g.setColor(MyColor.getColor("levelTwoThirdsSolvedUnselected"));

            if(getHovered())
            {
                g.setColor(MyColor.getColor("levelTwoThirdsSolvedSelected"));
            }
        }


        if(Level.isLevelSolved(getName()))
        {
            g.setColor(MyColor.getColor("levelSolvedUnselected"));

            if(getHovered())
            {
                g.setColor(MyColor.getColor("levelSolvedSelected"));
            }
        }

        if(!Level.isLevelUnlocked(getName()))
        {
            g.setColor(MyColor.getColor("levelLockedUnselected"));

            if(getHovered())
            {
                g.setColor(MyColor.getColor("levelLockedSelected"));
            }
        }

        g.setStroke(new BasicStroke(1));
        displayRectangle(g);

        String imageName = getName() + ".png";
        g.drawImage(Toolkit.getDefaultToolkit().getImage("Arrangement_Images/" + imageName), (int) (getX() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (getY() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (getXLength() * GraphicsDisplay.scalingFactor), (int) (getYLength() * GraphicsDisplay.scalingFactor), null);

        /*
        Map<RenderingHints.Key, Object> hints = new HashMap<RenderingHints.Key, Object>();
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(hints);

        Image backgroundImage = Toolkit.getDefaultToolkit().getImage("Arrangement_Images/" + imageName);\
         */

        //Display image as square
        //g.drawImage(backgroundImage, (int) (getX() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (getY() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, 250, 250, null);
        //g.setColor(MyColor.getColor("textBlack"));
        //displayText(g);
    }
}
