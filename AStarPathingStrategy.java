import java.util.List;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;


import java.util.HashMap;

class AStarPathingStrategy
        implements PathingStrategy
{

            // initialization of tools

            private static List<Point> path = new LinkedList<>();

            private static double inf = Double.POSITIVE_INFINITY;
            private static HashMap<Point, Point> cameFrom = new HashMap<>();
            private static HashMap<Point, Double> gScore = new HashMap<>();
            private static HashMap<Point, Double> fScore = new HashMap<>();
            private static PriorityQueue<Point> openQ = new PriorityQueue<>(new Comparator<Point>() {
                // priority queue will order points by minimum value
                    // first fScore will be checked (want to minimize fScore therefore greater fScore == lower on Queue_
                        // then check grid position (pushing in numerical order)
                public int compare(Point a, Point b){
                    if(fScore.get(a) > fScore.get(b)){
                        return 1;
                    }
                    else if(fScore.get(a) < fScore.get(b)){
                        return -1;
                    }
                    else{
                        if(a.y > b.y){
                            return 1;
                        }
                        else if(a.y < b.y){
                            return -1;
                        }
                        else{
                            if(a.x > b.x){
                                return 1;
                            }
                            else if(a.x < b.x){
                                return -1;
                            }
                            else{
                                return 0;
                            }
                            }
                        }
                    }
                });
            private double tempGScore;

    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors)
    {


        cameFrom.clear();
        gScore.clear();
        fScore.clear();
        openQ.clear();
        path.clear();
        
        // initializing known values
        openQ.add(start);

        // gScore == cost from start to current node
        // hScore == cost from current node to target
        // fScore == gScore + hScore
        gScore.put(start, cost(start, start));
        fScore.put(start, cost(start, start) + cost(start, end));
        
  
        
        while(!openQ.isEmpty()){
        // actual Astar Algo
        Point current = openQ.poll();
        if(withinReach.test(current, end)){
            createPath(current);
            // for(Point pt : path){
            //     System.out.println(pt);
            // }
            return path;
        }

        // valid neighbors
        Stream<Point> adjs = potentialNeighbors.apply(current).filter(canPassThrough);

        // for each valid neighbor...
        adjs.forEach(pt -> {
            // System.out.println(pt);
            tempGScore = gScore.get(current) + cost(current, pt); // check temp gScore
            if(!gScore.containsKey(pt)){    // if point was not previously recorded
                gScore.put(pt, inf);    // add point with gscore of Inf (want to default to worst option so far)
            }
            if(tempGScore < gScore.get(pt)){    // if temp gscore is less than current gscore
                cameFrom.put(pt, current);  // add this to paths
                gScore.put(pt, tempGScore); // update gscore
                fScore.put(pt, tempGScore + cost(pt, end)); // update fscore
                if(!openQ.contains(pt)){    // if not in openQ
                    openQ.add(pt);  // add to openQ (don't want to rule out grids if we already passed it)
                }
            }

            
        });
    }

        System.out.println("No Path Found - AStar Strategy");
        return path;
    }

    public double cost(Point p1, Point p2){     // euclidean cost analysis
        return (Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y));
    }

    public static void createPath(Point current){
        path.add(0, current);
        while(cameFrom.containsKey(current)){
            current = cameFrom.get(current);
            path.add(0, current);
        }

        path.remove(0);

    }

}
