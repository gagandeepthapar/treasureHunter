import java.util.LinkedList;

import processing.core.PImage;
import java.util.List;

public class Coin extends Collectible{

    List<PImage> titleFrames = new LinkedList<>();

    public Coin(){

        for(Integer i = 1; i < 9; i++){
            idleFrames.add(loadImage("SupportingFiles/Sprites/Coin/2x/coin_0" + i.toString() + ".png"));
        }

        for(Integer i = 1; i < 9; i++){
            titleFrames.add(loadImage("SupportingFiles/Sprites/Coin/5x/coin_0" + i.toString() + ".png"));
        }

    }
    
}
