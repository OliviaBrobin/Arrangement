package buttons;

import buttons.Button;
import game.Display;
import game.GraphicsDisplay;
import game.MyColor;
import level.Level;

import java.awt.*;

public class StartButton extends Button {
    public StartButton(int x, int y, int xLength, int yLength, String name) {
        super(x, y, xLength, yLength, name);
    }

    @Override
    public void display(Graphics2D g)
    {
        if(!getHovered())
        {
            g.setColor(MyColor.getColor("startUnselected"));
        }
        else
        {
            g.setColor(MyColor.getColor("startSelected"));
        }

        g.setStroke(new BasicStroke(1));
        displayRectangle(g);
        g.setColor(MyColor.getColor("textBlack"));
        String imageName = getName() + ".png";
        g.drawImage(Toolkit.getDefaultToolkit().getImage("Arrangement_Images/" + imageName), (int) (getX() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (getY() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (getXLength() * GraphicsDisplay.scalingFactor), (int) (getYLength() * GraphicsDisplay.scalingFactor), null);
        //g.drawImage(Display.start, (int) (getX() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (getY() * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (getXLength() * GraphicsDisplay.scalingFactor), (int) (getYLength() * GraphicsDisplay.scalingFactor), null);


        //displayText(g);
    }
}
