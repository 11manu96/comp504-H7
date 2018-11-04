package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.Wall;
import edu.rice.comp504.model.gameobjects.character.ACharacter;
import edu.rice.comp504.model.gameobjects.character.Pacman;

/**
 * This command implementing IGameObjectCmd executes the strategy when two objects meet.
 */
public class CollisionCmd implements IGameObjectCmd {
    private  AGameObject object;

    /**
     * Collision command constructor.
     * @param object an AGameObject to check collision against
     */
    public CollisionCmd(AGameObject object) {
        this.object = object;
    }

    /**
     * Execute the command.
     * @param context The receiver that will execute the command
     */
    public void execute(AGameObject context) {
        if (context != object) {
            if (object instanceof ACharacter) {
                if (object instanceof Pacman) {
                    Pacman pacman = ((Pacman) object);
                    if (context instanceof Wall && !pacman.isSwitchDirectionCollision()) {
                        // if pacman collides with any wall with updated velocity
                        // set velocity to velocity before switching
                        if (pacman.collision(context)) {
                            pacman.setSwitchDirectionCollision(true);
                        }
                    }
                }
                ACharacter character = (ACharacter) object;
                if (character.collision(context)) {
                    character.getInteractStrategy().interact(character, context);
                }
            }
        }
    }
}
