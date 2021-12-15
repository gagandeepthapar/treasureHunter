import processing.core.PImage;

public class slag extends gamePiece{
    
    private int counter = 0;

    public PImage curSlag = slag;
    public Point closer;
    public Point extended;
    private int offset = -1*gridLen/2;

    public slag(Point pos, int counter, boolean lookingRight){
        this.counter = counter;
        
        if(lookingRight){
            offset *= -1;
        }
        
        closer = new Point(pos.x + offset, pos.y);
        extended = new Point(this.closer.x + offset, this.closer.y);

    }

    public boolean checkTime(){
        if(Game.drawCounter - this.counter >= 120){
            curSlag = noSlag;
            return true;
        }
        return false;
    }

}
