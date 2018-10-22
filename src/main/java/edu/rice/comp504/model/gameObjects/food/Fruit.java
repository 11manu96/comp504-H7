package edu.rice.comp504.model.gameObjects.food;

import java.awt.*;

/**
 * A concrete class that extends AFood to represent fruits
 */
public class Fruit extends AFood {

    /**
     * Constructor for Fruit
     * @param loc location
     */
    public Fruit(Point loc) { super(loc, "fruit", 20); }
}
