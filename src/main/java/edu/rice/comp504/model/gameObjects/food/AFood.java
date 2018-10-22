package edu.rice.comp504.model.gameObjects.food;

import edu.rice.comp504.model.gameObjects.AGameObject;

import java.awt.*;

/**
 * An abstract class that extends AGameObject for all food objects in the Pac world
 */
public abstract class AFood extends AGameObject {

    /**
     * Constructor for AFood
     * @param loc location
     * @param type type big_dot or fruit or small_dot
     * @param size diameter
     */
    public AFood(Point loc, String type, int size) { super(loc, type, size); }
}
