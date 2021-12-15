import java.util.List;
import java.util.LinkedList;
import processing.core.PImage;

public class Enemy extends Sprite{

    List<PImage> moveRightFrames = new LinkedList<>();
    List<PImage> moveLeftFrames = new LinkedList<>();
    List<PImage> attackRightFrames = new LinkedList<>();
    List<PImage> attackLeftFrames = new LinkedList<>();
    List<PImage> idleFrames = new LinkedList<>();

    List<PImage> currentFrames = idleFrames;

    PathingStrategy strategy;
    private static Point tempPt = new Point(0,0);
    List<Point> path = new LinkedList<>();

    public void moveToTarget(Point current, Point end, PathingStrategy strategy){
        path.clear();
        path = strategy.computePath(current, end,
                              p ->  withinBounds(p),
                              (p1, p2) -> neighbors(p1,p2),
                              PathingStrategy.DIAG_CARD_NEIGHBORS);

    }

    private static boolean withinBounds(Point p){
        if(0 <= p.x && p.x <= 1088){
            if(0 <= p.y && p.y <= 832){
                tempPt.x = Math.floorDiv(p.x, 64);
                tempPt.y = Math.floorDiv(p.y, 64);

                // if(!Game.obsFactory.pts.contains(tempPt)){
                //     return true;
                // }

                return true;

            }
        }

        return false;
    }

    private static boolean neighbors(Point p1, Point p2){

        return p1.x+1 == p2.x && p1.y == p2.y ||
                p1.x-1 == p2.x && p1.y == p2.y ||
                p1.x == p2.x && p1.y+1 == p2.y ||
                p1.x == p2.x && p1.y-1 == p2.y;

    }

    public Enemy(Sprite s, PathingStrategy strategy){
        this.moveRightFrames = s.moveRightFrames;
        this.moveLeftFrames = s.moveLeftFrames;
        this.attackRightFrames = s.attackRightFrames;
        this.attackLeftFrames = s.attackLeftFrames;
        this.idleFrames = s.idleFrames;
        this.currentFrames = this.idleFrames;

        this.posX = s.posX;
        this.posY = s.posY;

        this.strategy = strategy;
    }
    
}
