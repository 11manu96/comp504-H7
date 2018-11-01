package edu.rice.comp504.model.strategy.interact;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.cmd.SwitchCmd;
import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.Exit;
import edu.rice.comp504.model.gameobjects.character.ACharacter;
import edu.rice.comp504.model.gameobjects.character.Pacman;
import edu.rice.comp504.model.gameobjects.food.AFood;

import java.awt.*;

/**
 * This strategy implementing IInteractStrategy is for interact strategy when two objects meets.
 */
public class PacmanInteraction implements IInteractStrategy {
    private static IInteractStrategy singleton;
    private static DispatchAdapter dis;

    /**
     * Constructor.
     */
    public PacmanInteraction(DispatchAdapter dis) {
        this.dis = dis;
    }

    /**
     * Initialize or retrieve Pacman interaction strategy singleton.
     * @return an interact strategy
     */
    public static IInteractStrategy makeStrategy(DispatchAdapter dis) {
        if (singleton == null) {
            singleton = new PacmanInteraction(dis);
        } else {
            PacmanInteraction.dis = dis;
        }

        return singleton;
    }

    /**
     * Get the interaction strategy name.
     * @return the interaction strategy name
     */
    public String getName() {
        return "PacmanInteraction";
    }

    /**
     * Handle the interaction when two objects meet.
     * @param src the src object will impose the interaction strategy on the dest object
     * @param dest the dest object behavior will be affected by the src object interaction strategy
     */
    public void interact(AGameObject src, AGameObject dest) {
        Pacman pacman = (Pacman) src;
        switch (dest.getType()) {
            case "wall":
                pacman.setVel(new Point(0,0));
                break;
            case "exit":
                Point newLoc = ((Exit) dest).getExitTo();
                pacman.setLocation(new Point(newLoc.x + DispatchAdapter.getGridSize() / 2,
                        newLoc.y + DispatchAdapter.getGridSize() / 2));
                break;
            case "small_dot":
                dis.deleteObserver(dest);
                AFood smallDot = (AFood)dest;
                dis.setScore(dis.getScore() + smallDot.getPoints());
                break;
            case "big_dot":
                dis.deleteObserver(dest);
                AFood bigDot = (AFood)dest;
                dis.setScore(dis.getScore() + bigDot.getPoints());
                dis.setAfraidTimer(100);
                dis.sendSwitchCmd("afraid");
                break;
            case "fruit":
                dis.deleteObserver(dest);
                AFood fruit = (AFood)dest;
                dis.setScore(dis.getScore() + fruit.getPoints());
                break;
        }
    }
}
