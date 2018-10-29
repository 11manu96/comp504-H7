package edu.rice.comp504.model.cmd;


import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.ACharacter;
import edu.rice.comp504.model.strategy.interact.PacmanToExit;
import edu.rice.comp504.model.strategy.interact.PacmanToWall;

public class CollisionCmd implements IGameObjectCmd {
    private  AGameObject object;

    public CollisionCmd(AGameObject object){
        this.object=object;
    }

    public void execute(AGameObject context){
        if(object.getType().equals("pacman")){
            ACharacter pacman=(ACharacter)object;
            if(context.getType().equals("wall")&&pacman.collision(context)){
                pacman.setInteractStrategy(PacmanToWall.makeStrategy());
                pacman.getInteractStrategy().interact(pacman,context);
            }
            else if(context.getType().equals("exit")&&pacman.collision(context)){
                pacman.setInteractStrategy(PacmanToExit.makeStrategy());
                pacman.getInteractStrategy().interact(pacman,context);
            }
        }
    }
}
