import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class basicAlgo implements PathingStrategy{

    List<Point> path = new LinkedList<>();

    public List<Point> computePath(Point start, Point end, Predicate<Point> canPassThrough,
            BiPredicate<Point, Point> withinReach, Function<Point, Stream<Point>> potentialNeighbors) {
        
        path.clear();

        // extremely basic algorithm; checks for neighbors and path(0) prioritizes y-movement before x-movement

        if(end.y > start.y){
            path.add(new Point(start.x, start.y + 1));
        }
        if(end.y < start.y){
            path.add(new Point(start.x, start.y - 1));
        }

        if(end.x > start.x){
            path.add(new Point(start.x + 1, start.y));
        }
        if(end.x < start.x){
            path.add(new Point(start.x - 1, start.y));
        }

        return path;
      
    }
}
