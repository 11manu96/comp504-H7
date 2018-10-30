package edu.rice.comp504.model.strategy.interact;

import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.Exit;
import edu.rice.comp504.model.gameobjects.character.ACharacter;

import java.awt.*;

public class PacmanInteraction implements IInteractStrategy {
    private static IInteractStrategy singleton;

    /**
     * Constructor
     */
    public PacmanInteraction() {}

    /**
     * Make a strategy.  There is only one (singleton).
     * @return An strategy
     */
    public static IInteractStrategy makeStrategy() {
        if (singleton == null) {
            singleton = new PacmanInteraction();
        }

        return singleton;
    }

    /**
     * Get the interaction strategy name.
     * @return The interaction strategy name
     */
    public String getName() {
        return "PacmanInteraction";
    }

    /**
     * Handle the interaction when two objects meet.
     * @param src The src object will impose the interaction strategy on the dest object
     * @param dest The dest object behavior will be affected by the src object interaction strategy
     */
    public void interact(AGameObject src, AGameObject dest){
        ACharacter pacman = (ACharacter) src;
        switch (dest.getType()) {
            case "wall":
                pacman.setVel(new Point(0,0));
                break;
            case "exit":
                pacman.setLocation(((Exit)dest).getExitTo());
                break;
        }
    }
}
