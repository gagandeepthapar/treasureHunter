import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;


public class ObstacleFactory extends TreasureHunter{

    List<Obstacle> Obstacles = new LinkedList<>();
    HashSet<Point> pts = new HashSet<>();

    public void addObs(String ObstacleType, Point pt){

        if(ObstacleType.equalsIgnoreCase("obsidian")){
            Obstacles.add(new Obstacle(obsidian, pt));
            pts.add(pt);
        }
        else if(ObstacleType.equalsIgnoreCase("water")){
            Obstacles.add(new Obstacle(water, pt));
            pts.add(pt);
        }

    }

}
