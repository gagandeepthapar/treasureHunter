public class Ninja extends Sprite{

    public Ninja(){
        String defpath = "SupportingFiles/Sprites/Ninja/";
        String ext = ".png";

        addFrames(titleIdle, defpath + "titleBlock/idle/", "idle_", ext, 4);
        addFrames(titlePick, defpath + "titleBlock/jump/", "jump_", ext, 4);

        addFrames(idleFrames, defpath + "idle/", "idle_", ext, 4);
        addFrames(moveRightFrames, defpath + "right/run/", "run_", ext, 6);
        addFrames(moveLeftFrames, defpath + "left/run/", "run_", ext, 6);
        addFrames(attackRightFrames, defpath + "right/attack/", "attack_", ext, 3);
        addFrames(attackLeftFrames, defpath + "left/attack/", "attack_", ext, 3);
        
    }
    
}
