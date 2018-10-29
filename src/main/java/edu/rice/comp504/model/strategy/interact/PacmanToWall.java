package edu.rice.comp504.model.strategy.interact;

import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.ACharacter;

import java.awt.*;

public class PacmanToWall implements IInteractStrategy {
    private static IInteractStrategy singleton;

    /**
     * Constructor
     */
    public PacmanToWall(){}

    /**
     * Make a strategy.  There is only one (singleton).
     * @return An exchange color strategy
     */
    public static IInteractStrategy makeStrategy() {
        if (singleton == null) {
            singleton = new PacmanToWall();
        }

        return singleton;
    }

    /**
     * Get the interaction strategy name.
     * @return The interaction strategy name
     */
    @Override
    public String getName() {
        return "PacmanToWall";
    }

    /**
     * Handle the interaction when two objects meet.
     * @param src The src object will impose the interaction strategy on the dest object
     * @param dest The dest object behavior will be affected by the src object interaction strategy
     */
    public void interact(AGameObject src, AGameObject dest){
        ACharacter pacman=(ACharacter)src;
        pacman.setVel(new Point(0,0));
    }
}
