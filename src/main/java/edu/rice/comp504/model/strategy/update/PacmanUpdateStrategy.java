package edu.rice.comp504.model.strategy.update;


import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Pacman;


import java.awt.*;

/**
 * This strategy implementing IUpdateStrategy is for updating Pacman.
 */
public class PacmanUpdateStrategy implements IUpdateStrategy{
    private static PacmanUpdateStrategy singletonPacmanUpdate;

    /**
     * Constructor.
     */
    private PacmanUpdateStrategy(){}

    /**
     * Initialize or retrieve Pacman update strategy singleton.
     * @return an update strategy
     */
    public static IUpdateStrategy makeStrategy() {
        if (singletonPacmanUpdate == null){
            singletonPacmanUpdate = new PacmanUpdateStrategy();
        }
        return singletonPacmanUpdate;
    }

    /**
     * Get the strategy name.
     * @return the strategy name
     */
    public String getName() {
        return "pacman_update";
    }

    /**
     * Update Pacman state.
     * @param pacman the object to be updated
     */
    public void update(AGameObject pacman) {
        Point vel = ((Pacman) pacman).getVel();
        if (!((Pacman) pacman).isChangedircollision()){
            ((Pacman) pacman).setVel(((Pacman) pacman).getSwitchdirection());
            vel=((Pacman) pacman).getSwitchdirection();
        }
        Point loc = pacman.getLocation();
        loc.x = vel.x + loc.x;
        loc.y = vel.y + loc.y;
        pacman.setLocation(loc);



    }

}
