package edu.rice.comp504.model.gameobjects.character;

import edu.rice.comp504.model.strategy.update.IUpdateStrategy;

import java.awt.*;

/**
 * This concrete class extends ACharacter and represents ghost game objects.
 */
public class Ghost extends ACharacter {
    private int jailTimer;
    private String color;

    /**
     * Constructor for Ghost.
     * @param loc ghost location
     * @param updateStrategy update strategy
     * @param color ghost color
     */
    public Ghost(Point loc, IUpdateStrategy updateStrategy, String color) {
        super(loc, "ghost", new Point(0,0), updateStrategy,null,20);
        this.jailTimer = 3;
        this.color = color;
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
}
