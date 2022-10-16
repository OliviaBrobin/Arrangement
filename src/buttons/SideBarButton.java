package buttons;

import game.GraphicsDisplay;
import game.MyColor;

import java.awt.*;

public class SideBarButton extends Button {
    public SideBarButton(int x, int y, int xLength, int yLength, String name) {
        super(x, y, xLength, yLength, name);
    }

    @Override
    public void display(Graphics2D g)
    {
        if(!getHovered())
        {
            g.setColor(MyColor.getColor("sideBarUnselected"));
        }
        else
        {
            g.setColor(MyColor.getColor("sideBarSelected"));
        }

        if(getName().equals("P") || getName().equals("S"))
        {
            g.setColor(MyColor.getColor("sideBarUnavailable"));
        }

        g.setStroke(new BasicStroke(1));
        displayRectangle(g);
        g.setColor(MyColor.getColor("textBlack"));
        //displayText(g);

        String imageName = getName() + ".png";
        g.drawImage(Toolkit.getDefaultToolkit().getImage("Arrangement_Images/" + imageName), (int) (getX() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (getY() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (getXLength() * GraphicsDisplay.scalingFactor), (int) (getYLength() * GraphicsDisplay.scalingFactor), null);

    }
}
