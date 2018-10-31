package edu.rice.comp504.model.cmd;


import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.ACharacter;
import edu.rice.comp504.model.gameobjects.character.Ghost;

/**
 * This command implementing IGameObjectCmd executes the strategy when two objects meet.
 */
public class CollisionCmd implements IGameObjectCmd {
    private  AGameObject object;

    public CollisionCmd(AGameObject object){
        this.object = object;
    }

    public void execute(AGameObject context){
        if (context != object) {
            if (object instanceof ACharacter){
                ACharacter character = (ACharacter) object;
                if (character.collision(context)){
                    character.getInteractStrategy().interact(character,context);
                }
        }

        }
    }
}
