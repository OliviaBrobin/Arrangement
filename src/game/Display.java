package game;

import arrangement.ComplexArrangementPropertyFunctions;
import level.Level;
import arrangement.Arrangement;
import arrangement.ArrangementProperties;
import buttons.Button;
import shapes.*;
import shapes.Shape;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static arrangement.Arrangement.*;
import static game.GraphicsDisplay.*;


public class Display extends JFrame implements Runnable {
	//Double buffering variables
	private Image dbImage;
	private Graphics dbg;

	static public boolean testingMode = false;
	public static boolean levelsLock = true;

	static String currentScreen = "startScreen";


	public static LinkedList<Line> linesFromRaysMethod = new LinkedList<>();



	public static int MAX_LINES = 6;
	public static int MAX_TRIANGLES = 5;
	public static int MAX_SQUARES = 5;
	public static int MAX_CIRCLES = 10;


	public Mouse mouse = new Mouse();

	boolean holdingControl = false;

	boolean holdingMouseButton1 = false;

	boolean holdingMouseButton2 = false;

	boolean holdingMouseButton3 = false;

	static boolean shapeBeingDragged = false;

	static boolean shapeBeingDeleted = false;


	int dragInitialX = 0;
	int dragInitialY = 0;

	int initialX1 = 0;
	int initialX2 = 0;
	int initialY1 = 0;
	int initialY2 = 0;

	public static String currentShapeType = "circle";
	public static Shape currentShape;

	public static boolean shapeResizing = false;

	public static boolean undoLastAction = false;

	public static Image start;

	//Constructor
	public Display(){
		//Title above screen
		setTitle("Arrangement - A Game About Arranging Shapes");

		//How many pixels by how many
		setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50);
		//setSize((int) 1536, (int) 814);

		//System.out.println((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		//System.out.println((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50);

		//GraphicsDisplay.setScalingFactor(getWidth(), getHeight());

		GraphicsDisplay.declarePlacementOfStuff();


		start = Toolkit.getDefaultToolkit().getImage("Arrangement_Images/" + "start.png");



		//If it can be resized
		setResizable(true);
		//setResizable(false);


		//If you can see it
		setVisible(true);

		//What will happen when the screen is closed. Right now it is to be exited
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//Do when player closes window
			}
		});

		if(testingMode)
		{
			currentScreen = "level";
			Level.levelName = "bL";
		}

		//Listeners
		addKeyListener(new keyInput());
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	//Double buffering function
	public void paint(Graphics g){
		dbImage=createImage(getWidth(), getHeight());
		dbg=dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	};

	//Paint graphics area
	public void paintComponent(Graphics g2){
		GraphicsDisplay.displayEverything(g2, getWidth(), getHeight());


		repaint();
	}

	public class Mouse extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			//Create shape where player clicks.
			int mouseX = (int) ((e.getX() - LEFT_BUFFER) / GraphicsDisplay.scalingFactor);
			int mouseY = (int) ((e.getY() - TOP_BUFFER) / GraphicsDisplay.scalingFactor);


			if(e.getButton() == MouseEvent.BUTTON1)
			{
				holdingMouseButton1 = true;
			}
			if(e.getButton() == MouseEvent.BUTTON2)
			{
				holdingMouseButton2 = true;
			}
			if(e.getButton() == MouseEvent.BUTTON3)
			{
				holdingMouseButton3 = true;
			}

			if (currentScreen.equals("startScreen")) {
				playButton.setPressed(mouseX, mouseY);
			}
			if(currentScreen.equals("levelMap")) {
				for (buttons.Button levelButton : levelButtons) {
					levelButton.setPressed(mouseX, mouseY);
				}
			}
			if(currentScreen.equals("level")) {
				for (buttons.Button levelPartButton : levelPartButtons) {
					levelPartButton.setPressed(mouseX, mouseY);
				}

				if(isMouseOnScreen(mouseX, mouseY)) {
					if(e.getButton() == MouseEvent.BUTTON1) {
						if (currentShapeType.equals("circle")) {
							currentShape = new Circle();
						} else if (currentShapeType.equals("line")) {
							currentShape = new Line();
						} else if (currentShapeType.equals("square")) {
							currentShape = new Square();
						} else if (currentShapeType.equals("triangle")) {
							currentShape = new Triangle();
						}

						currentShape.setX1((int) Math.floor(mouseX));
						currentShape.setY1((int) Math.floor(mouseY));
						currentShape.setX2((int) Math.floor(mouseX));
						currentShape.setY2((int) Math.floor(mouseY));

						currentShape.update();

						shapeResizing = true;
					}
					else if(e.getButton() == MouseEvent.BUTTON3 || e.getButton() == MouseEvent.BUTTON2 && !holdingMouseButton1) {
						Circle mouseCircle = new Circle();
						mouseCircle.setX1(mouseX);
						mouseCircle.setY1(mouseY);
						mouseCircle.setX2(mouseX + 10);
						mouseCircle.setY2(mouseY + 10);
						mouseCircle.update();

						for(LineShape lineShape : getLineShapes()) {
							boolean doBreak = false;
							for (Line line : lineShape.getLines()) {
								if (ComplexArrangementPropertyFunctions.checkIntersectsCircleAndLine(mouseCircle, line)) {
									if (lineShape.getType().equals("line")) {
										lines.remove((Line) lineShape);
									}
									if (lineShape.getType().equals("triangle")) {
										triangles.remove((Triangle) lineShape);
									}
									if (lineShape.getType().equals("square")) {
										squares.remove((Square) lineShape);
									}
									currentShape = lineShape;
									currentShapeType = lineShape.getType();
									shapeBeingDragged = true;
									if(e.getButton() == MouseEvent.BUTTON2) {
										shapeBeingDeleted = true;
									}
									dragInitialX = mouseX;
									dragInitialY = mouseY;

									initialX1 = lineShape.getX1();
									initialX2 = lineShape.getX2();
									initialY1 = lineShape.getY1();
									initialY2 = lineShape.getY2();

									doBreak = true;
								}
							}

							if (doBreak) {
								break;
							}
						}


						for (Circle circle : circles) {
							if (mouseCircle.intersects(circle)) {
								circles.remove(circle);
								currentShape = circle;
								currentShapeType = "circle";
								shapeBeingDragged = true;
								if(e.getButton() == MouseEvent.BUTTON2) {
									shapeBeingDeleted = true;
								}
								dragInitialX = mouseX;
								dragInitialY = mouseY;

								initialX1 = circle.getX1();
								initialX2 = circle.getX2();
								initialY1 = circle.getY1();
								initialY2 = circle.getY2();

								break;
							}
						}
					}
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e){
			//Put shape into list when player releases mouse.
			shapeResizing = false;

			int mouseX = (int) ((e.getX() - LEFT_BUFFER) / scalingFactor);
			int mouseY = (int) ((e.getY() - TOP_BUFFER) / scalingFactor);


			if(e.getButton() == MouseEvent.BUTTON1)
			{
				holdingMouseButton1 = false;
			}
			if(e.getButton() == MouseEvent.BUTTON1)
			{
				holdingMouseButton2 = false;
			}
			if(e.getButton() == MouseEvent.BUTTON3)
			{
				holdingMouseButton3 = false;
			}

			if (currentScreen.equals("startScreen")) {
				if (playButton.isClicked(mouseX, mouseY)) {
					currentScreen = "levelMap";
				}
			} else if (currentScreen.equals("levelMap")) {
				resetCountdowns();

				if(homeButton.isClicked(mouseX, mouseY)) {
					currentScreen = "startScreen";
				}
				if (backButton.isClicked(mouseX, mouseY)) {
					currentScreen = "startScreen";
				}
				if(questionButton.isClicked(mouseX, mouseY)) {
					arrangementsFromAllActions.clear();
					clear();
					resetCountdowns();

					Level.levelName = "question";

					currentScreen = "level";
					Level.levelPartNumber = 0;
				}

				for (Button levelButton : levelButtons) {
					if (levelButton.isClicked(mouseX, mouseY)) {
						if(Level.isLevelUnlocked(levelButton.getName()) || !levelsLock) {
							arrangementsFromAllActions.clear();
							clear();
							resetCountdowns();

							Level.levelName = levelButton.getName();

							currentScreen = "level";
							Level.levelPartNumber = 0;
						}
					}
				}
			}
			else if(currentScreen.equals("level")) {
				if(shapeBeingDeleted)
				{
					ArrangementProperties.setShapeDeleted(true);

					currentShape = null;
					shapeBeingDragged = false;
					shapeBeingDeleted = false;
					shapeResizing = false;

					Arrangement.addLastAction();
				}

				/*
				if((holdingMouseButton3 && (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON2))) {
					currentShape = null;
					if(shapeBeingDragged)
					{
						ArrangementProperties.setShapeMoved(true);
					}
					shapeBeingDragged = false;
					shapeResizing = false;

					Arrangement.addLastAction();
				}

				if((holdingMouseButton1 && (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3)))
				{
					currentShape = null;
					shapeBeingDragged = false;
					shapeResizing = false;
				}
				 */


				if (homeButton.isClicked(mouseX, mouseY)) {
					currentScreen = "startScreen";
				}
				if (backButton.isClicked(mouseX, mouseY)) {
					currentScreen = "levelMap";
				}
				if(questionButton.isClicked(mouseX, mouseY)) {
					arrangementsFromAllActions.clear();
					clear();
					resetCountdowns();

					Level.levelName = "question";

					currentScreen = "level";
					Level.levelPartNumber = 0;
				}

				for (Button levelPartButton : levelPartButtons) {
					if (levelPartButton.isClicked(mouseX, mouseY)) {
						int newLevelPartNumber = levelPartButton.getNumber();
						if (newLevelPartNumber <= Level.getLevelParts().length - 1) {
							Level.levelPartNumber = newLevelPartNumber;
							arrangementsFromAllActions.clear();
							clear();
							resetCountdowns();

							ArrangementProperties.setShapeDrawn(false);
							ArrangementProperties.setShapeMoved(false);
							ArrangementProperties.setShapeDeleted(false);
							ArrangementProperties.setActionPerformed(false);
							ArrangementProperties.setUndoPerformed(false);
							ArrangementProperties.setClearPerformed(false);
						}
					}
				}

				if (currentShape != null) {
					if (currentShape.getX1() == -1 || currentShape.getX2() == -1 || currentShape.getY1() == -1 || currentShape.getY2() == -1) {

						currentShape.setX1((int) Math.floor(mouseX));
						currentShape.setY1((int) Math.floor(mouseY));
						currentShape.setX2((int) Math.floor(mouseX));
						currentShape.setY2((int) Math.floor(mouseY));
					}

					currentShape.update();
				}

				boolean addedShape = false;

				if (currentShape != null) {
					if (lines.size() < MAX_LINES && triangles.size() < MAX_TRIANGLES && squares.size() < MAX_SQUARES && circles.size() < MAX_CIRCLES) {
						if (isMouseOnScreen(mouseX, mouseY)) {
							if (currentShape.isLargeEnough()) {
								if (!addShape()) {
									lastAddedShapeTooCloseCountdown = 500;
								} else {
									addedShape = true;
								}
							} else {
								lastAddedShapeTooSmallCountdown = 500;
							}
						} else {
							lastAddedShapeOutsideWhiteAreaCountdown = 500;
						}
					} else {
						tooManyShapesCountdown = 500;
					}
				}

				shapeResizing = false;

				currentShape = null;

				boolean cleared = false;
				boolean undidLastAction = false;

				if(addedShape && shapeBeingDragged)
				{
					ArrangementProperties.setShapeMoved(true);
				}

				if (clearButton.isClicked(mouseX, mouseY)) {
					ArrangementProperties.setActionPerformed(true);
					ArrangementProperties.setClearPerformed(true);

					clear();
					cleared = true;
				}

				if (undoButton.isClicked(mouseX, mouseY)) {
					ArrangementProperties.setActionPerformed(true);
					ArrangementProperties.setUndoPerformed(true);

					undoLastAction = true;
					undidLastAction = true;
				}

				if (addedShape || cleared || shapeBeingDragged) {
					Arrangement.addLastAction();
					undidLastAction = false;
				}

				shapeBeingDragged = false;

				ArrangementProperties.update();


				if (undidLastAction) {
					Arrangement.addLastAction();
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e){
			//As player moves mouse, resize shape.
			int mouseX = (int) ((e.getX() - LEFT_BUFFER) / GraphicsDisplay.scalingFactor);
			int mouseY = (int) ((e.getY() - TOP_BUFFER) / GraphicsDisplay.scalingFactor);

			setButtonsHovered(mouseX, mouseY);

			if(currentScreen.equals("level"))
			{
				if(shapeBeingDragged) {
					int xDifference = mouseX - dragInitialX;
					int yDifference = mouseY - dragInitialY;

					currentShape.setX1(initialX1 + xDifference);
					currentShape.setY1(initialY1 + yDifference);
					currentShape.setX2(initialX2 + xDifference);
					currentShape.setY2(initialY2 + yDifference);

					currentShape.update();
				}
				else {
					if(shapeResizing) {
						currentShape.setX2((int) Math.floor(mouseX));
						currentShape.setY2((int) Math.floor(mouseY));

						currentShape.update();
					}
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e){
			//As player moves mouse, resize shape.
			int mouseX = (int) ((e.getX() - LEFT_BUFFER) / GraphicsDisplay.scalingFactor);
			int mouseY = (int) ((e.getY() - TOP_BUFFER) / GraphicsDisplay.scalingFactor);

			setButtonsHovered(mouseX, mouseY);
		}
	}

	public class keyInput extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e){
			//Use keys to select current shape and clear shapes
			int keyCode = e.getKeyCode();
			if(keyCode == KeyEvent.VK_1)
			{
				shapeResizing = false;
				currentShapeType = "line";
			}
			if(keyCode == KeyEvent.VK_2)
			{
				shapeResizing = false;
				currentShapeType = "triangle";
			}
			if(keyCode == KeyEvent.VK_3)
			{
				shapeResizing = false;
				currentShapeType = "square";
			}
			if(keyCode == KeyEvent.VK_4)
			{
				shapeResizing = false;
				currentShapeType = "circle";
			}

			if(keyCode == KeyEvent.VK_CONTROL)
			{
				holdingControl = true;
			}

			if(keyCode == KeyEvent.VK_C)
			{
				if(currentScreen.equals("level")) {
					ArrangementProperties.setActionPerformed(true);
					ArrangementProperties.setClearPerformed(true);
					Arrangement.clear();
					Arrangement.addLastAction();
				}
			}

			if(holdingControl && keyCode == KeyEvent.VK_Z)
			{
				if(currentScreen.equals("level")) {
					ArrangementProperties.setActionPerformed(true);
					ArrangementProperties.setUndoPerformed(true);
					undoLastAction = true;
					ArrangementProperties.update();
					Arrangement.addLastAction();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e){
			int keyCode = e.getKeyCode();

			if(keyCode == KeyEvent.VK_CONTROL)
			{
				holdingControl = false;
			}
		}
	}

	@Override
	public void run() {
		while(true) {
			lastAddedShapeTooCloseCountdown--;
			lastAddedShapeTooSmallCountdown--;
			lastAddedShapeOutsideWhiteAreaCountdown--;
			tooManyShapesCountdown--;


			try {
				Thread.sleep(4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static boolean isMouseOnScreen(int mouseX, int mouseY)
	{
		return testingMode || (!isALevelPartButtonHovered() && mouseY > symbolsBarRectangle.getY() + symbolsBarRectangle.getHeight() && mouseX > sideBarRectangle.getX() + sideBarRectangle.getWidth() && (!(mouseX > levelBarRectangle.getX() && (mouseY > levelBarRectangle.getY() && mouseY < levelBarRectangle.getY() + levelBarRectangle.getHeight())))
				&& (mouseX > 0 && mouseX < screenWhiteWidth && (mouseY > 0 && mouseY < screenWhiteHeight))
		);
	}

	public void setButtonsHovered(int mouseX, int mouseY)
	{
		homeButton.setHovered(mouseX, mouseY);
		backButton.setHovered(mouseX, mouseY);
		questionButton.setHovered(mouseX, mouseY);

		playButton.setHovered(mouseX, mouseY);

		clearButton.setHovered(mouseX, mouseY);
		undoButton.setHovered(mouseX, mouseY);

		for(buttons.Button levelButton : levelButtons)
		{
			levelButton.setHovered(mouseX, mouseY);
		}

		for(Button levelPartButton : levelPartButtons)
		{
			levelPartButton.setHovered(mouseX, mouseY);
		}
	}

	public static boolean isALevelPartButtonHovered()
	{
		for (buttons.Button levelPartButton : levelPartButtons) {
			if(levelPartButton.getHovered())
			{
				return true;
			}
		}

		return  false;
	}

	public int getCurrentWidth()
	{
		return getWidth();
	}

	public int getCurrentHeight()
	{
		return getHeight();
	}
}