import java.util.HashMap;

public class EnemyFactory extends TreasureHunter{

    HashMap<Point, Enemy> enemyHash = new HashMap<>();

    public void addEnemy(String enemyType, Point pt, PathingStrategy strategy){

        if(enemyType.equalsIgnoreCase("NINJA")){
            Ninja ninja = new Ninja();
            ninja.posX = pt.x * gridLen + gridLen/2;
            ninja.posY = pt.y * gridLen + gridLen/2;
            enemyHash.put(pt, new Enemy(ninja, strategy));
        }
        
        if(enemyType.equalsIgnoreCase("SAMURAI")){
            Samurai samurai = new Samurai();
            samurai.posX = pt.x * gridLen + gridLen/2;
            samurai.posY = pt.y * gridLen + gridLen/2;
            enemyHash.put(pt, new Enemy(samurai, strategy));
        }

        if(enemyType.equalsIgnoreCase("PIRATE")){
            Pirate pirate = new Pirate();
            pirate.posX = pt.x * gridLen + gridLen/2;
            pirate.posY = pt.y * gridLen + gridLen/2;
            enemyHash.put(pt, new Enemy(pirate, strategy));
        }

        if(enemyType.equalsIgnoreCase("VIKING")){
            Viking viking = new Viking();
            viking.posX = pt.x * gridLen + gridLen/2;
            viking.posY = pt.y * gridLen + gridLen/2;
            enemyHash.put(pt, new Enemy(viking, strategy));
        }
    }

}
