package buttons;

import game.GraphicsDisplay;
import game.MyColor;

import java.awt.*;

public class ClearUndoButton extends Button {
    public ClearUndoButton(int x, int y, int xLength, int yLength, String name) {
        super(x, y, xLength, yLength, name);
    }

    @Override
    public void display(Graphics2D g)
    {
        if(!getHovered())
        {
            g.setColor(MyColor.getColor("clearUndoUnselected"));
        }
        else
        {
            g.setColor(MyColor.getColor("clearUndoSelected"));
        }

        g.setStroke(new BasicStroke(1));
        displayRectangle(g);
        //g.setColor(MyColor.getColor("textBlack"));
        //displayText(g);

        //g.setStroke(new BasicStroke(1));
        //displayRectangle(g);

        String imageName = getName() + ".png";
        g.drawImage(Toolkit.getDefaultToolkit().getImage("Arrangement_Images/" + imageName), (int) (getX() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (getY() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (getXLength() * GraphicsDisplay.scalingFactor), (int) (getYLength() * GraphicsDisplay.scalingFactor), null);

    }
}
