import processing.core.PImage;

public class Obstacle extends gamePiece{
    PImage img;

    public Obstacle(PImage img, Point pt){
        this.img = img;
        this.posX = pt.x;
        this.posY = pt.y;
    }

}
