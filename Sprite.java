import processing.core.PImage;
import java.util.List;
import java.util.LinkedList;

abstract public class Sprite extends gamePiece{

    List<PImage> titleIdle = new LinkedList<>();
    List<PImage> titlePick = new LinkedList<>();
    
    List<PImage> idleFrames = new LinkedList<>();
    List<PImage> moveRightFrames = new LinkedList<>();
    List<PImage> moveLeftFrames = new LinkedList<>();
    List<PImage> attackRightFrames = new LinkedList<>();
    List<PImage> attackLeftFrames = new LinkedList<>();

    List<PImage> currentFrames = idleFrames;
    List<PImage> pointingFrames = moveRightFrames;
    
    
}
