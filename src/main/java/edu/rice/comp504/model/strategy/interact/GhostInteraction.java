package edu.rice.comp504.model.strategy.interact;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.Exit;
import edu.rice.comp504.model.gameobjects.character.ACharacter;
import edu.rice.comp504.model.gameobjects.character.Ghost;
import edu.rice.comp504.model.gameobjects.character.Pacman;

import java.awt.*;

/**
 * This strategy implementing IInteractStrategy is for interact strategy when two objects meets.
 */
public class GhostInteraction implements IInteractStrategy {
    private static IInteractStrategy singleton;
    private static DispatchAdapter dis;

    /**
     * Ghost Interaction Constructor.
     */
    public GhostInteraction(DispatchAdapter dis) {
        GhostInteraction.dis = dis;
    }

    /**
     * Make a strategy. This is a singleton.
     * @return an interact strategy.
     */
    public static IInteractStrategy makeStrategy(DispatchAdapter dis) {
        if (singleton == null) {
            singleton = new GhostInteraction(dis);
        } else {
            GhostInteraction.dis = dis;
        }

        return singleton;
    }

    /**
     * Get the interaction strategy name.
     * @return the interaction strategy name.
     */
    public String getName() {
        return "GhostInteraction";
    }

    /**
     * Handle the interaction when two objects meet.
     * @param src the src object will impose the interaction strategy on the dest object.
     * @param dest the dest object behavior will be affected by the src object interaction strategy.
     */
    public void interact(AGameObject src, AGameObject dest) {
        Ghost ghost = (Ghost) src;
        switch (dest.getType()) {
            case "wall":
                // we know which direction wall is in based on ghost velocity
                ghost.removeOpenSpace(ghost.getVel());

                ghost.setVel(new Point(0,0));
                break;
            case "exit":
                Point newLoc = ((Exit) dest).getExitTo();
                ghost.setLocation(new Point(newLoc.x, newLoc.y));
                break;
            case "pacman":
                Pacman pacman = (Pacman) dest;
                // check if ghost is afraid
                if (ghost.getUpdateStrategy().getName().equals("ghost_afraid")) {
                    ghost.setLocation(ghost.getInitialLoc());
                    dis.setScore(dis.getScore() + ghost.getPoints());
                    dis.sendSwitchCmd(ghost.getColor());
                } else {
                    dis.setLives(dis.getLives() - 1);
                    pacman.setLocation(pacman.getInitialLoc());
                    pacman.setVel(new Point(0, 0));
                    ghost.setLocation(ghost.getInitialLoc());
                    ghost.setVel(new Point(0, 0));
                }
                break;
        }
    }
}
