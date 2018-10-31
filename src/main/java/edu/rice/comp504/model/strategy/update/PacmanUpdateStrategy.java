package edu.rice.comp504.model.strategy.update;


import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Pacman;


import java.awt.*;

/**
 * This strategy implementing IUpdateStrategy is for updating of a single objects
 */
public class PacmanUpdateStrategy implements IUpdateStrategy{
    private static PacmanUpdateStrategy SingletonPacmanUpdate;

    /**
     * Constructor
     */
    private PacmanUpdateStrategy(){}

    /**
     * Make a strategy.  There is a singleton.
     * @return an update strategy
     */
    public static IUpdateStrategy makeStrategy(){
        if (SingletonPacmanUpdate==null){
            SingletonPacmanUpdate=new PacmanUpdateStrategy();
        }
        return SingletonPacmanUpdate;
    }

    /**
     * Get the strategy name.
     * @return the strategy name
     */
    public String getName(){
        return "pacman_update";
    }

    /**
     * Update the status of the object
     * @param pacman the object to be updated
     */
    public void update(AGameObject pacman){
        Point vel=((Pacman)pacman).getVel();
        Point loc=pacman.getLocation();
        loc.x=vel.x+loc.x;
        loc.y=vel.y+loc.y;
        pacman.setLocation(loc);
    }

}
