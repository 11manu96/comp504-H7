package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.Exit;
import edu.rice.comp504.model.gameobjects.Wall;
import edu.rice.comp504.model.gameobjects.character.ACharacter;
import edu.rice.comp504.model.gameobjects.character.Pacman;
import edu.rice.comp504.model.gameobjects.food.AFood;

import java.awt.*;

/**
 * This command implementing IGameObjectCmd executes the strategy when two objects meet.
 */
public class CollisionCmd implements IGameObjectCmd {
    private  AGameObject object;

    public CollisionCmd(AGameObject object){
        this.object = object;
    }

    public void execute(AGameObject context) {
        AFood temp = null;
        if (context != object) {
            if (object instanceof ACharacter) {
                ACharacter character = (ACharacter) object;
                if (character.collision(context)) {
                    if(context instanceof AFood)
                        temp = (AFood)context;

                    character.getInteractStrategy().interact(character, context);
                }
            }
                if (object instanceof Pacman) {
                    Pacman pacman=((Pacman) object);
                    Point tempvel=pacman.getVel();
                    pacman.setVel(pacman.getSwitchdirection());
                    if (pacman.collision(context) && context instanceof Wall) {
                        pacman.setChangedircollision(true);
                    }
                    if (pacman.collision(context) && context instanceof Exit) {
                        pacman.getInteractStrategy().interact(pacman,context);
                        pacman.setVel(pacman.getSwitchdirection());
                    }
                    else if (pacman.collision(context) && context instanceof AFood && temp != context) {

                        pacman.getInteractStrategy().interact(pacman,context);

                    }
                    else {
                        pacman.setVel(tempvel);
                    }

                }
        }


    }
}
