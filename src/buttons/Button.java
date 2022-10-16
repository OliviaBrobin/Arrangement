package buttons;

import game.Display;
import game.GraphicsDisplay;
import game.MyColor;

import java.awt.*;

public abstract class Button {
    private boolean hovered = false;

    private int x;
    private int y;
    private int xLength;
    private int yLength;

    private int number = -1;

    private String name = "";

    private boolean isPressed = false;

    public Button (int x, int y, int xLength, int yLength, int number)
    {
        this.x = (int) (x);
        this.y = (int) (y);
        this.xLength = (int) (xLength);
        this.yLength = (int) (yLength);
        this.number = number;
    }

    public Button (int x, int y, int xLength, int yLength, String name)
    {
        this.x = (int) (x);
        this.y = (int) (y);
        this.xLength = (int) xLength;
        this.yLength = (int) yLength;
        this.name = name;
    }

    private boolean mouseInButton(int mouseX, int mouseY)
    {
        return mouseX > x && mouseX < (x + xLength) && mouseY > y && mouseY < (y + yLength);
    }


    public void setHovered(int mouseX, int mouseY)
    {
        if(mouseInButton(mouseX, mouseY))
        {
            hovered = true;
        }
        else
        {
            hovered = false;
        }
    }

    public boolean getHovered()
    {
        return  hovered;
    }

    public boolean isClicked(int mouseX, int mouseY)
    {
        //if(isPressed) {
            return mouseInButton(mouseX, mouseY);
        //}
        //isPressed = false;
        //return false;
    }

    public void setPressed(int mouseX, int mouseY)
    {
        isPressed = mouseInButton(mouseX, mouseY);
    }

    public boolean isPressed() {
        return  isPressed;
    }

    public void display(Graphics2D g)
    {
        g.setColor(MyColor.getColor("rectangleBlack"));
        displayRectangle(g);
    }

    protected void fillRectangle(Graphics2D g)
    {
        g.fillRect((int) (x * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (y * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (xLength * GraphicsDisplay.scalingFactor), (int) (yLength * GraphicsDisplay.scalingFactor));
    }

    protected void displayRectangle(Graphics2D g)
    {
        g.fillRect((int) (x * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (y * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (xLength * GraphicsDisplay.scalingFactor), (int) (yLength * GraphicsDisplay.scalingFactor));

        g.setColor(MyColor.getColor("outlineBlack"));

        g.drawRect((int) (x * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (y * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER, (int) (xLength * GraphicsDisplay.scalingFactor), (int) (yLength * GraphicsDisplay.scalingFactor));

    }

    protected void displayText(Graphics2D g)
    {
        g.setFont(new Font("TimesRoman", Font.BOLD, (int) (30 * GraphicsDisplay.scalingFactor)));
        g.drawString(name, (int) (x * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (y * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER + g.getFont().getSize());

    }

    protected void displayNumber(Graphics2D g)
    {
        g.setFont(new Font("TimesRoman", Font.BOLD, (int) (30 * GraphicsDisplay.scalingFactor)));
        g.drawString(number + "", (int) ((x + 3) * GraphicsDisplay.scalingFactor) + GraphicsDisplay.LEFT_BUFFER, (int) (y * GraphicsDisplay.scalingFactor) + GraphicsDisplay.TOP_BUFFER + g.getFont().getSize());

    }


    public int getNumber()
    {
        return number;
    }

    public String getName()
    {
        return name;
    }

    public int getX() { return  x; }

    public int getY() { return  y; }

    public int getXLength() { return  xLength; }

    public int getYLength() { return  yLength; }

    public void setY(int y)
    {
        this.y = y;
    }
}
