package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Ghost;
import edu.rice.comp504.model.gameobjects.character.Pacman;

import java.awt.*;

/**
 * This command implementing IGameObjectCmd updates the state of a pacman or ghost.
 */
public class UpdateCmd implements IGameObjectCmd{

    private DispatchAdapter dispatchAdapter;

    /**
     * Constructor.
     * @param dispatchAdapter communicate with dispatchAdapter
     */
    public UpdateCmd(DispatchAdapter dispatchAdapter) {
        this.dispatchAdapter = dispatchAdapter;

    }

    /**
     * Excute the command.
     * @param context The receiver that will execute the command
     */
    public void execute(AGameObject context) {

        switch (context.getType()) {

            case "pacman":
                dispatchAdapter.sendCollisionCmd(context);
                ((Pacman) context).getUpdateStrategy().update(context);
                ((Pacman) context).setChangedircollision(false);
                break;

            case "ghost":
                dispatchAdapter.sendCollisionCmd(context);
                ((Ghost) context).getUpdateStrategy().update(context);
                break;

            default:
                break;
        }
    }

}
