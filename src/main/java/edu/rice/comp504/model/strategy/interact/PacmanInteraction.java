package edu.rice.comp504.model.strategy.interact;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.Exit;
import edu.rice.comp504.model.gameobjects.character.ACharacter;

import java.awt.*;

/**
 * This strategy implementing IInteractStrategy is for interact strategy when two objects meets.
 */
public class PacmanInteraction implements IInteractStrategy {
    private static IInteractStrategy singleton;

    /**
     * Constructor.
     */
    public PacmanInteraction() {}

    /**
     * Make a strategy. This is a singleton.
     * @return an interact strategy.
     */
    public static IInteractStrategy makeStrategy() {
        if (singleton == null) {
            singleton = new PacmanInteraction();
        }

        return singleton;
    }

    /**
     * Get the interaction strategy name.
     * @return the interaction strategy name.
     */
    public String getName() {
        return "PacmanInteraction";
    }

    /**
     * Handle the interaction when two objects meet.
     * @param src the src object will impose the interaction strategy on the dest object.
     * @param dest the dest object behavior will be affected by the src object interaction strategy.
     */
    public void interact(AGameObject src, AGameObject dest) {
        ACharacter pacman = (ACharacter) src;
        switch (dest.getType()) {
            case "wall":
                pacman.setVel(new Point(0,0));
                break;
            case "exit":
                Point newLoc = ((Exit) dest).getExitTo();
                pacman.setLocation(new Point(newLoc.x + DispatchAdapter.getGridSize() / 2,
                        newLoc.y + DispatchAdapter.getGridSize() / 2));
                break;
        }
    }
}
