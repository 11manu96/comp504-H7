package edu.rice.comp504.model.cmd;


import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.ACharacter;
import edu.rice.comp504.model.gameobjects.character.Ghost;


public class CollisionCmd implements IGameObjectCmd {
    private  AGameObject object;

    public CollisionCmd(AGameObject object){
        this.object = object;
    }

    public void execute(AGameObject context){
        if(object.getType().equals("pacman")) {
            ACharacter pacman = (ACharacter) object;
            if (pacman.collision(context)) {
                pacman.getInteractStrategy().interact(pacman, context);
            }
        }
        else if (object.getType().equals("ghost")){
            Ghost ghost=(Ghost) object;
            if (ghost.collision(context)){
                ghost.getInteractStrategy().interact(ghost,context);
            }
        }
    }
}
