import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import processing.core.PApplet;
import processing.core.PFont;

public class Game extends TreasureHunter{

    public static int drawCounter = 0, frameCounter = 0, slagCount = 0, stage = 0;
    public boolean lookingRight = true;
    private List<slag> slaggedGround = new LinkedList<>();
    
    public static ObstacleFactory obsFactory = new ObstacleFactory();
    private coinFactory cnFac = new coinFactory();
    private EnemyFactory enFac = new EnemyFactory();
    
    private HashMap<Point, Enemy> tempMap = new HashMap<>();
    private HashSet<slag> tempSlags = new HashSet<>();

    private PathingStrategy aStar = new AStarPathingStrategy();
    private PathingStrategy single = new SingleStepPathingStrategy();
    private PathingStrategy basic = new basicAlgo();

    boolean playHover = false;
    boolean portalPlace = false;
        
    int newX;
    int newY;

    int enemyTempX;
    int enemyTempY;

    Point tempPt =  new Point(0,0);
    Point enCurPt = new Point(0,0);
    
    public void settings(){
        size(winWidth, winHeight);
    }

    public void setup(){
        background(stonebkgd);
        drawCounter = 0;
        frameCount = 0;


        enemies.add("NINJA");
        enemies.add("SAMURAI");
        enemies.add("VIKING");
        

        mapSETUP();
        mapGEN();
        coinSETUP();
        coinGEN();
        enemySETUP();
        enemyGEN();
        imageMode(CENTER);
        textAlign(CENTER);
        PFont title = createFont("SupportingFiles/titleFont.ttf", 128);
        textFont(title);

        TreasureHunter.mainCharacter.posX = newX = 8*gridLen + gridLen/2;
        TreasureHunter.mainCharacter.posY = newY = 6*gridLen + gridLen/2;

        tempPt = new Point(newX, newY);

    }

    private void mapSETUP(){

        // left lake
        obsFactory.addObs("water", new Point(3,5));
        obsFactory.addObs("water", new Point(3,4));
        obsFactory.addObs("water", new Point(4,4));
        obsFactory.addObs("water", new Point(5,4));

        // right lake
        obsFactory.addObs("water", new Point(11,4));
        obsFactory.addObs("water", new Point(12,4));
        obsFactory.addObs("water", new Point(13,4));
        obsFactory.addObs("water", new Point(13,5));

        // center cage
        obsFactory.addObs("obsidian", new Point(6,5));
        obsFactory.addObs("obsidian", new Point(6,6));
        obsFactory.addObs("obsidian", new Point(6,7));
        obsFactory.addObs("obsidian", new Point(10,5));
        obsFactory.addObs("obsidian", new Point(10,6));
        obsFactory.addObs("obsidian", new Point(10,7));
        obsFactory.addObs("obsidian", new Point(7,7));
        obsFactory.addObs("obsidian", new Point(8,7));
        obsFactory.addObs("obsidian", new Point(9,7));

        // bottom lake
        obsFactory.addObs("water", new Point(5,10));
        obsFactory.addObs("water", new Point(6,10));
        obsFactory.addObs("water", new Point(7,10));
        obsFactory.addObs("water", new Point(8,10));
        obsFactory.addObs("water", new Point(9,10));
        obsFactory.addObs("water", new Point(10,10));
        obsFactory.addObs("water", new Point(11,10));

        // corners
        // bottom left
        obsFactory.addObs("obsidian", new Point(0,10));
        obsFactory.addObs("obsidian", new Point(0,11));
        obsFactory.addObs("obsidian", new Point(0,12));
        obsFactory.addObs("obsidian", new Point(1,12));
        obsFactory.addObs("obsidian", new Point(2,12));

        // bottom right
        obsFactory.addObs("obsidian", new Point(16,10));
        obsFactory.addObs("obsidian", new Point(16,11));
        obsFactory.addObs("obsidian", new Point(16,12));
        obsFactory.addObs("obsidian", new Point(15,12));
        obsFactory.addObs("obsidian", new Point(14,12));

        // top left
        obsFactory.addObs("obsidian", new Point(2,0));
        obsFactory.addObs("obsidian", new Point(1,0));
        obsFactory.addObs("obsidian", new Point(0,0));
        obsFactory.addObs("obsidian", new Point(0,1));
        obsFactory.addObs("obsidian", new Point(0,2));

        // top right
        obsFactory.addObs("obsidian", new Point(14,0));
        obsFactory.addObs("obsidian", new Point(15,0));
        obsFactory.addObs("obsidian", new Point(16,0));
        obsFactory.addObs("obsidian", new Point(16,1));
        obsFactory.addObs("obsidian", new Point(16,2));

        // walls
        obsFactory.addObs("water", new Point(1,4));
        obsFactory.addObs("water", new Point(1,5));
        obsFactory.addObs("water", new Point(1,6));
        obsFactory.addObs("water", new Point(1,7));
        obsFactory.addObs("water", new Point(15,4));
        obsFactory.addObs("water", new Point(15,5));
        obsFactory.addObs("water", new Point(15,6));
        obsFactory.addObs("water", new Point(15,7));

        // top lake
        obsFactory.addObs("water", new Point(7,2));
        obsFactory.addObs("water", new Point(8,2));
        obsFactory.addObs("water", new Point(9,2));

    }

    private void coinSETUP(){
        cnFac.addCoin(new Point(4,5));
        cnFac.addCoin(new Point(5,5));
        cnFac.addCoin(new Point(11,5));
        cnFac.addCoin(new Point(12,5));
        cnFac.addCoin(new Point(1,11));
        cnFac.addCoin(new Point(15,11));
        cnFac.addCoin(new Point(15, 1));
        cnFac.addCoin(new Point(1, 1));
        cnFac.addCoin(new Point(0, 4));
        cnFac.addCoin(new Point(0, 5));
        cnFac.addCoin(new Point(0, 6));
        cnFac.addCoin(new Point(0, 7));
        cnFac.addCoin(new Point(16, 4));
        cnFac.addCoin(new Point(16, 5));
        cnFac.addCoin(new Point(16, 6));
        cnFac.addCoin(new Point(7, 3));
        cnFac.addCoin(new Point(8, 3));
        cnFac.addCoin(new Point(9, 3));
    }

    private void enemySETUP(){
        enFac.addEnemy(enemies.get(0), new Point(0 ,9), aStar);
        // enFac.addEnemy(enemies.get(0), new Point(2,10), aStar);
        // enFac.addEnemy(enemies.get(0), new Point(4,12), aStar);

        enFac.addEnemy(enemies.get(1), new Point(13,12), single);
        // enFac.addEnemy(enemies.get(1), new Point(14,10), aStar);
        // enFac.addEnemy(enemies.get(1), new Point(16,9), aStar);

        enFac.addEnemy(enemies.get(2), new Point(7,1), basic);
        // enFac.addEnemy(enemies.get(2), new Point(8,1), aStar);
        // enFac.addEnemy(enemies.get(2), new Point(9,1), aStar);
    }

    private void mapGEN(){
        for(Obstacle ob : obsFactory.Obstacles){
            image(ob.img, gridLen*ob.posX + gridLen/2, gridLen*ob.posY + gridLen/2);
        }
    }

    private void coinGEN(){
        for(Coin cn : cnFac.pointCoin.values()){
            image(cn.setFrame(frameCounter, cn.idleFrames), gridLen * cn.posX + gridLen/2, gridLen * cn.posY + gridLen/2);
        }
    }

    private void enemyGEN(){
        for(Point pt : enFac.enemyHash.keySet()){
            if(!(enFac.enemyHash.get(pt)== null)){
            Enemy en = enFac.enemyHash.get(pt);
            image(en.setFrame(frameCounter, en.currentFrames), en.posX, en.posY);
            }
        }
    }

    private void frameCount(){
        drawCounter++;
        if(drawCounter % 12 == 0){ // arbitrary update time; updates 5 times per second
            frameCounter++;
        }
    }

    private void pollDirection(){
        if(mouseX <= TreasureHunter.mainCharacter.posX){
            lookingRight = false;
        }
        else{
            lookingRight = true;
        }

    }

    private void pollMovement(){
        TreasureHunter.mainCharacter.currentFrames = TreasureHunter.mainCharacter.idleFrames;

        if(keyPressed){
            if(keyCode == UP || 
                keyCode == DOWN ||
                keyCode == LEFT || 
                keyCode == RIGHT ||
                key == 'w' ||
                key == 'a' || 
                key == 's' || 
                key == 'd'){
                    if(lookingRight){
                        TreasureHunter.mainCharacter.currentFrames = TreasureHunter.mainCharacter.moveRightFrames;
                    }
                    else{
                        TreasureHunter.mainCharacter.currentFrames = TreasureHunter.mainCharacter.moveLeftFrames;
                    }
                }

            
            if(keyCode == UP || key == 'w'){
                newY = TreasureHunter.mainCharacter.posY - 2;
                tempPt.x = Math.floorDiv(TreasureHunter.mainCharacter.posX, gridLen);
                tempPt.y = Math.floorDiv(newY, gridLen);
                if((newY) >= 0 && !obsFactory.pts.contains(tempPt))
                TreasureHunter.mainCharacter.posY = newY;
            }
            else if(keyCode == LEFT || key == 'a'){
                newX = TreasureHunter.mainCharacter.posX - 2;
                tempPt.x = Math.floorDiv(newX, gridLen);
                tempPt.y = Math.floorDiv(TreasureHunter.mainCharacter.posY, gridLen);
                if((TreasureHunter.mainCharacter.posX - 2) >= 0 && !obsFactory.pts.contains(tempPt))
                TreasureHunter.mainCharacter.posX = newX;
            }
            else if(keyCode == DOWN || key == 's'){
                newY = TreasureHunter.mainCharacter.posY + 2;
                tempPt.x = Math.floorDiv(TreasureHunter.mainCharacter.posX, gridLen);
                tempPt.y = Math.floorDiv(newY, gridLen);
                if((newY) <= winHeight - gridLen/2 && !obsFactory.pts.contains(tempPt))
                TreasureHunter.mainCharacter.posY = newY;
            }
            else if(keyCode == RIGHT || key == 'd'){
                newX = TreasureHunter.mainCharacter.posX + 2;
                tempPt.x = Math.floorDiv(newX, gridLen);
                tempPt.y = Math.floorDiv(TreasureHunter.mainCharacter.posY, gridLen);
                if((newX) <= winWidth - gridLen/2 && !obsFactory.pts.contains(tempPt))
                TreasureHunter.mainCharacter.posX = newX;
            }
        }

        if(mousePressed){
            if(lookingRight){
                TreasureHunter.mainCharacter.currentFrames = TreasureHunter.mainCharacter.attackRightFrames;
            }
            else{
                TreasureHunter.mainCharacter.currentFrames = TreasureHunter.mainCharacter.attackLeftFrames;
            }
        }

        image(TreasureHunter.mainCharacter.setFrame(frameCounter, TreasureHunter.mainCharacter.currentFrames), TreasureHunter.mainCharacter.posX, TreasureHunter.mainCharacter.posY);

    }

    private void enemyMOVE(){

        tempMap.clear();

        for(Point pt : enFac.enemyHash.keySet()){
            Enemy en = enFac.enemyHash.get(pt);

            en.moveToTarget(new Point(en.posX, en.posY), new Point(mainCharacter.posX, mainCharacter.posY), en.strategy);
            // System.out.println("PATH SIZE: " + String.valueOf(en.path.size()));

            en.posX = en.path.get(0).x;
            en.posY = en.path.get(0).y;

            if(mainCharacter.posX <= en.posX){
                en.currentFrames = en.moveLeftFrames;
            }
            else{
                en.currentFrames = en.moveRightFrames;
            }

            tempMap.put(new Point(Math.floorDiv(en.posX, gridLen), Math.floorDiv(en.posY, gridLen)), en);

        }

        enFac.enemyHash.clear();

        if(!tempMap.isEmpty()){
            for(Point pt : tempMap.keySet()){
                Enemy en = tempMap.get(pt);
                enFac.enemyHash.put(pt, en);
            }
        }
        
    }

    private void pollCollection(){

        Point MCPOINTNOW = new Point(Math.floorDiv(TreasureHunter.mainCharacter.posX, gridLen), Math.floorDiv(TreasureHunter.mainCharacter.posY, gridLen));

        // System.out.println(MCPOINTNOW);
        if(cnFac.pointCoin.keySet().contains(MCPOINTNOW)){
            cnFac.pointCoin.remove(MCPOINTNOW);
            // System.out.println("REMOVED COIN");
        }
    }

    private void pollWinCondition(){
        if(cnFac.pointCoin.isEmpty()){
            stage = 1;
        }
    }

    private void pollLoseCondition(){
        // System.out.println(TreasureHunter.mainCharacter.posX);
        // System.out.println(TreasureHunter.mainCharacter.posY);
        for(Point pt : enFac.enemyHash.keySet()){
            // if(!(enFac.enemyHash.get(pt) == null)){
            int xP = enFac.enemyHash.get(pt).posX;
            int yP = enFac.enemyHash.get(pt).posY;
            int xComp = abs(xP - TreasureHunter.mainCharacter.posX);
            int yComp = abs(yP - TreasureHunter.mainCharacter.posY);

            if(xComp <= 16 && yComp <= 16){
                stage = 2;
            }
        // }
        }
    }

    private void won(){
        fill(Black[0], Black[1], Black[2]);
        textSize(100);
        text("YOU WON!", centerX + gridLen/2, 150);
        text("YOUR TIME:", centerX + gridLen/2, 250);

        printTime(Black, centerX + gridLen/2, 350, 100);

        playAgain();
    }

    private void lost(){
        fill(Red[0], Red[1], Red[2]);
        textSize(200);
        text("YOU LOST!", centerX + gridLen/2, centerY + gridLen/2);
        playAgain();
    }

    private void playAgain(){
        rectMode(CENTER);
        boolean playAgain = button(new Point(centerX + gridLen/2 - 200, 500), new Point(centerX + gridLen/2 + 200, 750), Gold,
                                    "PLAY\nAGAIN?", Black, 80, new Point(centerX + gridLen/2, 590));

        if(playAgain){
            playHover = button(new Point(centerX + gridLen/2 - 200, 500), new Point(centerX + gridLen/2 + 200, 750), Green,
                                    "PLAY\nAGAIN?", White, 100, new Point(centerX + gridLen/2, 590));
        }

    }

    private void pollAttacks(){
        if(mousePressed){

            for(Point pt : enFac.enemyHash.keySet()){
                Enemy en = enFac.enemyHash.get(pt);

                int delX = abs(mainCharacter.posX - en.posX);
                int delY = abs(mainCharacter.posY - en.posY);

                if(delX <= 2*gridLen && delY <= 10){
                    en.posX = 8*gridLen + gridLen/2;
                    en.posY = 5*gridLen + gridLen/2;
                    portalPlace = true;
                }

            }

        }
    }

    private void portalGEN(){

        image(portal, 8*gridLen + gridLen/2, 5*gridLen + gridLen/2);

    }

    private boolean button(Point tl, Point br, int[] rColor, String text, int[] tColor, int tSize, Point tPoint){

        int xPt = Math.floorDiv((br.x + tl.x),2);
        int yPt = Math.floorDiv((br.y + tl.y),2);
        int delX = br.x - tl.x;
        int delY = br.y - tl.y;

        fill(rColor[0], rColor[1], rColor[2]);
        rect(xPt, yPt, delX, delY, 20);
        
        fill(tColor[0], tColor[1], tColor[2]);
        textSize(tSize);
        text(text, tPoint.x, tPoint.y);

        if(tl.y <= mouseY && mouseY <= br.y){
            if(tl.x <= mouseX && mouseX <= br.x){
                return true;
            }
        }

        return false;

    }

    private void checkSlags(){
        tempSlags.clear();

        if(!slaggedGround.isEmpty()){
            for(slag slg : slaggedGround){
                image(slg.curSlag, slg.closer.x, slg.closer.y);
                image(slg.curSlag, slg.extended.x, slg.extended.y);
                if(!(slg.checkTime())){
                    tempSlags.add(slg);
                }
            }

            slaggedGround.clear();

            for(slag slg : tempSlags){
                slaggedGround.add(slg);
            }

        }

    }

    public void mouseClicked(){
        if(playHover && (stage == 1 || stage == 2)){
            PApplet.main("mainMenu");
        }

        if(stage == 0){

            // checks if enemy was killed
            for(Point pt : enFac.enemyHash.keySet()){
                Enemy en = enFac.enemyHash.get(pt);

                int delX = abs(mainCharacter.posX - en.posX);
                int delY = abs(mainCharacter.posY - en.posY);

                if(delX <= 2*gridLen && delY <= 16){
                    en.posX = 8*gridLen + gridLen/2;
                    en.posY = 5*gridLen + gridLen/2;
                    portalPlace = true;
                }

            }

            // applies slag to ground
            slaggedGround.add(new slag(new Point(TreasureHunter.mainCharacter.posX, TreasureHunter.mainCharacter.posY), drawCounter, lookingRight));
        }

    }

    private void printTime(int[] tColor, int x, int y, int size){
        
        int sec = drawCounter / 60;
        int hr = Math.floorDiv(sec, 3600);
        int min = Math.floorDiv(sec - hr*3600, 60);
        int finSec = sec - hr*3600 - min*60;

        String time = String.valueOf(hr) + ":" + String.valueOf(min) + ":" + String.valueOf(finSec);

        fill(tColor[0], tColor[1], tColor[2]);
        textSize(size);
        text(time, x, y);
    }

    public void draw(){
        background(stonebkgd); // reset screen

        if(stage == 0){
            background(stonebkgd);
            frameCount(); // update framecounter
            mapGEN();   // generate terrain
            printTime(White, gridLen + gridLen/2, 48, 48); // show timer
            checkSlags(); // generate slagged ground
            coinGEN(); // generate coins
            pollDirection(); // check which direction mc is facing
            pollMovement(); // chekc for movement input
            // pollAttacks(); // check for attack input
            
            if(portalPlace){
                portalGEN();
            }

            enemyGEN(); // generate enemies
            // System.out.println(enFac.enemyHash.size());

            if(drawCounter % 3 == 0){
                enemyMOVE();
            } 

            pollCollection(); // check for coin collection

            pollWinCondition(); // check if won
            pollLoseCondition(); // check if lost


            // noLoop();
        }

        if(stage == 1){
            background(stonebkgd);
            won(); // run win screen
        }

        if(stage == 2){
            background(sandbkgd);
            lost(); // run lose screen
        }
    }

}