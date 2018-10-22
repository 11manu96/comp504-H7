package edu.rice.comp504.model.gameobjects.food;

import java.awt.*;

/**
 * This concrete class extends AFood to represent fruit game objects.
 */
public class Fruit extends AFood {

    /**
     * Constructor for Fruit.
     * @param loc fruit location
     */
    public Fruit(Point loc) {
        super(loc, "fruit", 20);
    }
}
