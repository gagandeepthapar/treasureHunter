import processing.core.PApplet;
import processing.core.PImage;

import java.util.LinkedList;
import java.util.List;

abstract public class TreasureHunter extends PApplet {

    public final int gridLen = 64;
    public final int centerX = 8*gridLen;
    public final int centerY = 6*gridLen;
    

    public final int winWidth = 1088;
    public final int winHeight = 832;


    public static int drawCounter = 0, frameCounter = 0;

    public PImage stonebkgd = loadImage("SupportingFiles/Tiles/stoneBackground.png");
    public PImage sandbkgd = loadImage("SupportingFiles/Tiles/sandBackground.png");

    public PImage obsidian = loadImage("SupportingFiles/Tiles/obsidian.png");
    public PImage water = loadImage("SupportingFiles/Tiles/water.png");
    public PImage slag = loadImage("SupportingFiles/Tiles/slag.png");
    public PImage noSlag = loadImage("SupportingFiles/Tiles/noSlag.png");
    public PImage portal = loadImage("SupportingFiles/Tiles/nether.png");

    static Sprite mainCharacter;
    Enemy enemy1;
    Enemy enemy2;
    Enemy enemy3;

    // color scheme
    int[] Navy = {4, 54, 82};
    int[] White = {255, 255, 255};
    int[] Black = {0,0,0};
    int[] Gold = {255, 215, 0};
    int[] Red = {148, 6, 6};
    int[] Green = {9, 145, 9};

    static List<String> enemies = new LinkedList<>();
    static String mainC = new String();

    public static void main(String[] args) {
        PApplet.main("mainMenu");
    }
    
}
