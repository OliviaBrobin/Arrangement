package game;

import java.awt.Color;
import java.util.Map;

import static java.util.Map.entry;

public class MyColor {
    private Color color;

    static Map<String, String> colors = Map.ofEntries(
            entry("lightColor1", "#33DEFD"),
            entry("lightColor2", "#FFFF00"),
            entry("lightColor3", "#A6FF00"),
            entry("lightColor4", "#30C100"),
            entry("lightColor5", "#FCB035"),

            entry("darkColor1", "#CAFBFF"),
            entry("darkColor2", "#FFFBB3"),
            entry("darkColor3", "#E1FF9F"),
            entry("darkColor4", "#AAF28C"),
            entry("darkColor5", "#F9D8A7")
    );


    public static Color getColor(String col) {
        Color color = Color.GREEN;

        switch (col) {
            case "rectangleBlack":
                color = Color.BLACK;
                break;
            case "outlineBlack":
                color = Color.BLACK;
                break;
            case "bar":
                color = Color.LIGHT_GRAY;
                break;
            case "barOutline":
                color = Color.BLACK;
                break;
            case "textBlack":
                color = Color.BLACK;
                break;
            case "shapeBlack":
                color = Color.BLACK;
                break;
            case "shapeBeingDragged":
                color = Color.CYAN;
                break;
            case "shapeBeingDeleted":
                color = Color.RED;
                break;
            case "point":
                color = Color.BLUE;
                break;
            case "intersection":
                color = Color.RED;
                break;
            case "gameBackground":
                color = Color.DARK_GRAY;
                break;
            case "screenBackground":
                color = Color.WHITE;
                break;
            case "sideBarUnselected":
                color = Color.decode(colors.get("lightColor1"));
                break;
            case "sideBarSelected":
                color = Color.decode(colors.get("darkColor1"));
                break;
            case "sideBarUnavailable":
                color = Color.LIGHT_GRAY;
                break;
            case "startUnselected":
                color = Color.decode(colors.get("lightColor1"));
                break;
            case "startSelected":
                color = Color.decode(colors.get("darkColor1"));
                break;
            case "clearUndoUnselected":
                color = Color.decode(colors.get("lightColor1"));
                break;
            case "clearUndoSelected":
                color = Color.decode(colors.get("darkColor1"));
                break;
            case "levelUnselected":
                color = Color.decode(colors.get("lightColor1"));
                break;
            case "levelSelected":
                color = Color.decode(colors.get("darkColor1"));
                break;
            case "levelLockedUnselected":
                color = Color.LIGHT_GRAY;
                break;
            case "levelLockedSelected":
                color = Color.LIGHT_GRAY;
                break;
            case "levelPartiallySolvedUnselected":
                color = Color.decode(colors.get("lightColor2"));
                break;
            case "levelPartiallySolvedSelected":
                color = Color.decode(colors.get("darkColor2"));
                break;
            case "levelTwoThirdsSolvedUnselected":
                color = Color.decode(colors.get("lightColor3"));
                break;
            case "levelTwoThirdsSolvedSelected":
                color = Color.decode(colors.get("darkColor3"));
                break;
            case "levelSolvedUnselected":
                color = Color.decode(colors.get("lightColor4"));
                break;
            case "levelSolvedSelected":
                color = Color.decode(colors.get("darkColor4"));
                break;
            case "levelPartUnavailable":
                color = Color.LIGHT_GRAY;
                break;
            case "currentLevelPartText":
                color = Color.RED;
                break;
            case "notCurrentLevelPartText":
                color = Color.BLACK;
                break;
            case "levelPropertySolved":
                color = Color.decode(colors.get("lightColor2"));;
                break;
            default:
                color = Color.PINK;



            /*
            case "editorBackground":
                color = Color.decode(colors.get("lightGray"));
                break;
            case "editorStatsText":
                color = Color.decode(colors.get("black"));
                break;
            case "editorTextBlack":
                color = Color.decode(colors.get("black"));
                break;
            case "editorTextViolet":
                color = Color.decode(colors.get("darkPurple"));
                break;
            case "editorTextWhite":
                color = Color.decode(colors.get("offWhite"));
                break;
            case "editorButtonWhite":
                color = Color.decode(colors.get("offWhite"));
                break;
            case "editorButtonGray":
                color = Color.LIGHT_GRAY;
                break;
            case "editorButtonColored":
                color = Color.Color2;
                break;
            case "basicScreen":
                color = Color.decode(colors.get("white"));
                break;
            case "gameBackground":
                color = Color.decode(colors.get("darkGray"));
                break;
            case "pauseTint":
                //TODO We'll need to modify this at a later time.
                //color = Color.decode(colors.get("black"));
                color = new Color(0,0,0,60);
                break;
            case "levelBackground":
                color = Color.decode(colors.get("offWhite"));
                break;
            case "levelTextBlack":
                color = Color.decode(colors.get("black"));
                break;
            case "levelTextWhite":
                color = Color.decode(colors.get("offWhite"));
                break;
            case "gridOutline":
                color = Color.decode(colors.get("black"));
                break;
            case "player":
                color = Color.decode(colors.get("black"));
                break;
            case "void":
                color = Color.decode(colors.get("offWhite"));
                break;
            case "solid":
                color = Color.decode(colors.get("offBlack"));
                break;
            case "bouncy":
                color = Color.decode(colors.get("blue"));
                break;
            case "sticky":
                color = Color.decode(colors.get("Color3"));
                break;
            case "playerSticky":
                color = Color.decode(colors.get("lightColor3"));
                break;
            case "grapple":
                color = Color.decode(colors.get("lightPurple"));
                break;
            case "playerDoubleJump":
                color = Color.decode(colors.get("lightBlue"));
                break;
            case "playerControlGravity":
                color = Color.decode(colors.get("green"));
                break;
            case "death":
                color = Color.decode(colors.get("darkGray"));
                break;
            case "finish":
                color = Color.decode(colors.get("Color4"));
                break;
             */
        }
        return color;
    }
}