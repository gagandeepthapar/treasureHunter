import processing.core.PApplet;
import processing.core.PFont;

public class mainMenu extends TreasureHunter{
    
    // flags to check for mouse click
    boolean playHover = false;
    boolean exitHover = false;
    boolean instClick = false;

    boolean pirateHover = false;
    boolean ninjaHover = false;
    boolean samuraiHover = false;
    boolean vikingHover = false;
    boolean runGame;

    // set stage
    int stage = 0;

    // used sprites
    Pirate pirate = new Pirate();
    Ninja ninja = new Ninja();
    Samurai samurai = new Samurai();
    Viking viking = new Viking();
    Coin coin = new Coin();

    EnemyFactory enFac = new EnemyFactory();
    
    
    public void settings(){
        size(winWidth, winHeight);
    }

    public void setup(){
        background(sandbkgd);
        PFont title = createFont("SupportingFiles/titleFont.ttf", 128);
        textFont(title);
        imageMode(CENTER);
        textAlign(CENTER);
        rectMode(CENTER);
        strokeWeight(4);
        // noLoop();

        enemies.add("PIRATE");
        enemies.add("NINJA");
        enemies.add("SAMURAI");
        enemies.add("VIKING");

    }

    public void draw(){
        background(sandbkgd); // reset screen
        frameCount();
        
        // title screen
        if(stage == 0){
            titleScreen();
        }

        // character selection
        if(stage == 1){
            charSelection();

        }

        // instructions
        if(stage == 2){
            backStoryScreen();
        }

        if(stage == 3){
            instructionScreen();
        }

    }

    @Override
    public void mouseClicked() {
        // stage 0 options
        if(stage == 0){
            if(playHover){
                stage = 1;
            }
            else if(exitHover){
                exit();
            }
        }

        // stage 1 options
        int enIDX = 4;
        if(stage == 1){

            if(pirateHover){
                enIDX = 0;
                TreasureHunter.mainCharacter = new Pirate();
                enemy1 = new Enemy(new Ninja(), new AStarPathingStrategy());
                enemy2 = new Enemy(new Samurai(), new AStarPathingStrategy());
                enemy3 = new Enemy(new Viking(), new AStarPathingStrategy());
            }

            else if(ninjaHover){
                enIDX = 1;
                TreasureHunter.mainCharacter = new Ninja();
                enemy1 = new Enemy(new Pirate(), new AStarPathingStrategy());
                enemy2 = new Enemy(new Samurai(), new AStarPathingStrategy());
                enemy3 = new Enemy(new Viking(), new AStarPathingStrategy());
            }

            else if(samuraiHover){
                enIDX = 2;
                TreasureHunter.mainCharacter = new Samurai();
                enemy1 = new Enemy(new Pirate(), new AStarPathingStrategy());
                enemy2 = new Enemy(new Ninja(), new AStarPathingStrategy());
                enemy3 = new Enemy(new Viking(), new AStarPathingStrategy());
            }

            else if(vikingHover){
                enIDX = 3;
                TreasureHunter.mainCharacter = new Viking();
                enemy3 = new Enemy(new Pirate(), new AStarPathingStrategy());
                enemy1 = new Enemy(new Ninja(), new AStarPathingStrategy());
                enemy2 = new Enemy(new Samurai(), new AStarPathingStrategy());
            }

            if(!(enIDX == 4)){
                mainC = enemies.get(enIDX);
                enemies.remove(enIDX);

                stage = 2;
            }

        }

        if(stage == 2){
            if(instClick){
                stage = 3;
            }
        }

        // stage 3 options
        if(stage == 3){
            if(runGame){
                PApplet.main("Game");
            }
        }
    }

    private void frameCount(){
        drawCounter++;
        if(drawCounter == 12){ // arbitrary update time; updates 5 times per second
            drawCounter = 0;
            frameCounter++;
        }
    }

    private void titleScreen(){
        titleBlock(150, 200); // show title
        frameCount();   // update frameCount AGAIN to make coin animation smoother
        coinAnimations(); // showcoin animations

        playHover = exitHover = false;
        
        // buttons();
        boolean playFlag = button(new Point(100, winHeight - 250), new Point(500, winHeight - 50), Navy,
                                    "PLAY!", White, 100, new Point(300, winHeight - 110));
        
        boolean exitFlag = button(new Point(winWidth - 500, winHeight - 250), new Point(winWidth - 100, winHeight - 50), Navy,
                                    "EXIT", White, 100, new Point(winWidth - 300, winHeight - 110));

        if(playFlag){
            playHover = button(new Point(100, winHeight - 250), new Point(500, winHeight - 50), Gold,
                                    "PLAY!", Black, 150, new Point(300, winHeight - 100));
        }

        if(exitFlag){
            exitHover = button(new Point(winWidth - 500, winHeight - 250), new Point(winWidth - 100, winHeight - 50), Red,
                                    "EXIT", Black, 150, new Point(winWidth - 300, winHeight - 100));
        }
    }

    private void charSelection(){
        titleBlock(100, 100);   // title block
        
        // choose your char text
        fill(0);
        textSize(60);
        text("Choose Your Character", centerX + gridLen/2, 200);

        // reset movetype to idle frames
        pirate.currentFrames = pirate.titleIdle;
        ninja.currentFrames = ninja.titleIdle;
        samurai.currentFrames = samurai.titleIdle;
        viking.currentFrames = viking.titleIdle;

        pirateHover = ninjaHover = samuraiHover = vikingHover = false;

        // create pirate button
        boolean pirateSelect = button(new Point(gridLen, centerY-2*gridLen), new Point(gridLen + 200, centerY-gridLen+400), Navy,
                                        "Roger\nthe Pirate", White, 40, new Point(5*gridLen/2, centerY + 250));

        // create ninja button
        boolean ninjaSelect = button(new Point(5*gridLen, centerY-2*gridLen), new Point(5*gridLen + 200, centerY-gridLen+400), Navy,
                                        "Raizo\nthe Ninja", White, 40, new Point(13*gridLen/2, centerY + 250));

        // create samurai button
        boolean samuraiSelect = button(new Point(9*gridLen, centerY-2*gridLen), new Point(9*gridLen + 200, centerY-gridLen+400), Navy,
                                        "Zoro\nthe Samurai", White, 40, new Point(21*gridLen/2, centerY + 250));

        // create viking button
        boolean vikingSelect = button(new Point(13*gridLen, centerY-2*gridLen), new Point(13*gridLen + 200, centerY-gridLen+400), Navy,
                                        "Franky\nthe Viking", White, 40, new Point(29*gridLen/2, centerY + 250));

        if(pirateSelect){
            pirate.currentFrames = pirate.titlePick;
            pirateHover = button(new Point(gridLen, centerY-2*gridLen), new Point(gridLen + 200, centerY-gridLen+400), Gold,
                                        "Roger\nthe Pirate", Black, 50, new Point(5*gridLen/2, centerY + 240));
        }
        if(ninjaSelect){
            ninja.currentFrames = ninja.titlePick;
            ninjaHover = button(new Point(5*gridLen, centerY-2*gridLen), new Point(5*gridLen + 200, centerY-gridLen+400), Gold,
                                         "Raizo\nthe Ninja", Black, 50, new Point(13*gridLen/2, centerY + 240));
        }
        if(samuraiSelect){
            samurai.currentFrames = samurai.titlePick;
            samuraiHover = button(new Point(9*gridLen, centerY-2*gridLen), new Point(9*gridLen + 200, centerY-gridLen+400), Gold,
                                        "Zoro\nthe Samurai", Black, 50, new Point(21*gridLen/2, centerY + 240));

        }
        if(vikingSelect){
            viking.currentFrames = viking.titlePick;
            vikingHover = button(new Point(13*gridLen, centerY-2*gridLen), new Point(13*gridLen + 200, centerY-gridLen+400), Gold,
                                        "Franky\nthe Viking", Black, 50, new Point(29*gridLen/2, centerY + 240));
        }

        // sprite animations
        image(pirate.setFrame(frameCounter, pirate.currentFrames), gridLen + 3*gridLen/2 + 20, centerY);
        image(ninja.setFrame(frameCounter, ninja.currentFrames),  2*gridLen + 3*3*gridLen/2 + 24, centerY);
        image(samurai.setFrame(frameCounter, samurai.currentFrames), 3*gridLen + 5*3*gridLen/2 + 32, centerY);
        image(viking.setFrame(frameCounter, viking.currentFrames), 4*gridLen + 7*3*gridLen/2 + 24, centerY);

    }

    private void backStoryScreen(){
        titleBlock(100, 100);
        fill(Navy[0], Navy[1], Navy[2]);
        rect(centerX + gridLen/2, centerY + 80, 900, 650, 20);

        fill(White[0],White[1],White[2]);
        textSize(30);

        String storyA = "You and your buddies go out to find the coveted treasure of the Pirate King";
        String storyB = "On the way, however, your crewmates get caught and are killed by the obstacles\nguarding the treasure";
        String storyC = "Your friends are now ghosts and are tracking you down to kill you too unless\nyou can collect all of the treasure!";
        String storyD = "You notice that, while slow, they can pass through walls and always know where you are!";
        String storyE = "You need to be careful! You try to attack back at them but they never stop spawning.";
        String storyF = "Collect all the treasure and escape the final resting place of your crewmates!";

        textAlign(CENTER);
        text(storyA, centerX + gridLen/2, 200);
        text(storyB, centerX + gridLen/2, 250);
        text(storyC, centerX + gridLen/2, 350);
        text(storyD, centerX + gridLen/2, 450);
        text(storyE, centerX + gridLen/2, 500);
        text(storyF, centerX + gridLen/2, 550);
        
        boolean instHover = button(new Point(centerX + gridLen/2 - 200, 650), new Point(centerX + gridLen/2 + 200, 750), Gold,
                            "LET'S GO!", Black, 80, new Point(centerX + gridLen/2, 730));
        
        if(instHover){
            instClick = button(new Point(centerX + gridLen/2 - 200, 650), new Point(centerX + gridLen/2 + 200, 750), Green,
            "LET'S GO!", White, 100, new Point(centerX + gridLen/2, 733));
        }


    }

    private void instructionScreen(){
        titleBlock(100, 100);
        fill(Navy[0], Navy[1], Navy[2]);
        rect(centerX + gridLen/2, centerY + 80, 900, 650, 20);

        fill(White[0],White[1],White[2]);
        textSize(40);
        String obj = "Collect these";
        String atk = "Attack these";
        String obs = "Can't pass through these";
        String conts = "Use 'WASD' to move around";
        String clk = "Use mouse to aim left/right and click to attack!";

        textAlign(LEFT);
        text(obj, 200, 220);
        text(atk, 200, 330);
        text(obs, 200, 440);
        text(conts, 200, 550);
        text(clk, 200, 620);
        textAlign(CENTER);

        fill(Gold[0], Gold[1], Gold[2]);
        rect(500, 200, 100, 100, 20);
        image(coin.setFrame(frameCounter, coin.idleFrames),500,200);

        rect(500, 310, 100, 100, 20);
        image(enemy1.setFrame(frameCounter, enemy1.idleFrames), 505, 310);
        rect(610, 310, 100, 100, 20);
        image(enemy2.setFrame(frameCounter, enemy2.idleFrames), 615, 310);
        rect(720, 310, 100, 100, 20);
        image(enemy3.setFrame(frameCounter, enemy3.idleFrames), 725, 310);

        rect(610, 420, 100, 100, 20);
        image(water, 610, 420);
        rect(720, 420, 100, 100, 20);
        image(obsidian, 720, 420);

        boolean goHover = button(new Point(centerX + gridLen/2 - 200, 650), new Point(centerX + gridLen/2 + 200, 750), Gold,
                            "LET'S GO!", Black, 80, new Point(centerX + gridLen/2, 730));
        
        if(goHover){
            runGame = button(new Point(centerX + gridLen/2 - 200, 650), new Point(centerX + gridLen/2 + 200, 750), Green,
            "LET'S GO!", White, 100, new Point(centerX + gridLen/2, 733));
        }
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

    private void titleBlock(int size, int Y){
        fill(Navy[0], Navy[1], Navy[2]);
        textSize(size);
        text("TREASURE HUNTER!",centerX + gridLen/2, Y);
    }

    private void coinAnimations(){

        image(coin.setFrame(frameCounter, coin.titleFrames),centerX+gridLen/2, centerY);
        image(coin.setFrame(frameCounter, coin.titleFrames),centerX+gridLen/2-300, centerY);
        image(coin.setFrame(frameCounter, coin.titleFrames),centerX+gridLen/2+300, centerY);
        
    }

}
