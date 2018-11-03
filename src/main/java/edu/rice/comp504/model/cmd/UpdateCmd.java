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
     * Update command Constructor.
     * @param dispatchAdapter communicate with dispatchAdapter.
     */
    public UpdateCmd(DispatchAdapter dispatchAdapter) {
        this.dispatchAdapter = dispatchAdapter;

    }

    /**
     * Excute the command.
     * @param context The receiver that will execute the command.
     */
    public void execute(AGameObject context) {

        switch (context.getType()) {

            case "pacman":
                ((Pacman) context).setSwitchDirectionCollision(false);


                Point tempvel=((Pacman) context).getVel();
                Point switchVelocity = ((Pacman) context).getSwitchdirection();
                //set pacman velocity to key pressed velocity if key pressed
                ((Pacman) context).setVel(new Point(switchVelocity.x, switchVelocity.y));
                dispatchAdapter.sendCollisionCmd(context);

                //collision when switching direction, isSwitchDirectionCollision only true when pacman collides with wall
                if(((Pacman) context).isSwitchDirectionCollision()){
                    ((Pacman) context).setVel(tempvel);
                    dispatchAdapter.sendCollisionCmd(context);
                }



                ((Pacman) context).getUpdateStrategy().update(context);
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
