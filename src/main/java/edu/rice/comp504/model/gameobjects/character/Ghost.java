package edu.rice.comp504.model.gameobjects.character;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.strategy.interact.GhostInteraction;
import edu.rice.comp504.model.strategy.update.IUpdateStrategy;

import java.awt.*;

/**
 * This concrete class extends ACharacter and represents ghost game objects.
 */
public class Ghost extends ACharacter {
    private int jailTimer;
    private String color;
    private int points;
    private Point initialLocation;

    /**
     * Constructor for Ghost.
     * @param loc ghost location
     * @param updateStrategy update strategy
     * @param color ghost color
     */
    public Ghost(Point loc, IUpdateStrategy updateStrategy, String color) {
        super(loc, "ghost", new Point(0,0), updateStrategy, GhostInteraction.makeStrategy(), DispatchAdapter.getGridSize());
        this.jailTimer = 3;
        this.color = color;
        this.points = 200;
        this.initialLocation = loc;

    }

    /**
     * Get the color of the ghost.
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set the color of the ghost.
     * @param color ghost color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Get the jail timer.
     * @return time ghost will stay in jail
     */
    public int getJailTimer() {
        return this.jailTimer;
    }

    /**
     * Set the jail timer.
     * @param jailTimer time ghost will stay in jail
     */
    public void setJailTimer(int jailTimer) {
        this.jailTimer = jailTimer;
    }

    /**
     * Handle collision between ghost and game object.
     * @param gameObject object to check collision against
     * @return whether there was collision
     */
    public boolean collision(AGameObject gameObject) {

        Point ghostLoc = this.getLocation();
        int ghostSize = this.getSize() / 2;
        Point ghostVel = this.getVel();

        Point gameObjLoc = gameObject.getLocation();
        int gameObjSize = gameObject.getSize() / 2;

        int distX = ghostLoc.x + DispatchAdapter.getGridSize() / 2 + ghostVel.x - (gameObjLoc.x + DispatchAdapter.getGridSize() / 2);
        int distY = ghostLoc.y + DispatchAdapter.getGridSize() / 2 + ghostVel.y - (gameObjLoc.y + DispatchAdapter.getGridSize() / 2);
        if ((Math.abs(distX) < ghostSize + gameObjSize) && (Math.abs(distY) < ghostSize + gameObjSize)) {
            return true;
        }
        return false;
    }
}
