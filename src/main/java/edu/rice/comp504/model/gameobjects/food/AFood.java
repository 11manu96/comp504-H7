package edu.rice.comp504.model.gameobjects.food;

import edu.rice.comp504.model.gameobjects.AGameObject;

import java.awt.*;

/**
 * This abstract class extends AGameObject for all food objects in the Pac world.
 */
public abstract class AFood extends AGameObject {

    private int points;
    /**
     * Constructor for AFood.
     * @param loc food location
     * @param type type big_dot or fruit or small_dot
     * @param size food size
     */
    public AFood(Point loc, String type, int size, int points) {
        super(loc, type, size);
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }
}
