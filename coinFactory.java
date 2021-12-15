import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class coinFactory extends TreasureHunter{
    
    List<Coin> coinList = new LinkedList<>();
    // List<Double> offset = new LinkedList<>();
    // double[] offset;
    HashMap<Point, Coin> pointCoin = new HashMap<>();

    public void addCoin(Point pt){
        Coin cn = new Coin();
        cn.posX = pt.x;
        cn.posY = pt.y;

        coinList.add(cn);
        pointCoin.put(pt, cn);
    }

}
