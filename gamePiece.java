import processing.core.PImage;
import java.util.List;

abstract public class gamePiece extends TreasureHunter{
    
    public int posX, posY;

    protected void addFrames(List<PImage> frameSet, String pathname, String filename, String ext, int length){
        
        for(Integer i = 0; i < length; i++){
            frameSet.add(loadImage(pathname + filename + i.toString() + ext));
        }

    }

    public PImage setFrame(int frameCounter, List<PImage> type){
        return type.get(frameCounter % type.size());
    }

}
