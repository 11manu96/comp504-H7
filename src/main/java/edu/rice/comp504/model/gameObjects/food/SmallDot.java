package edu.rice.comp504.model.gameObjects.food;

import java.awt.*;

/**
 * A concrete class that extends AFood for to represent small dots
 */
public class SmallDot extends AFood {

    /**
     * Constructor for SmallDot
     * @param loc location
     */
    public SmallDot(Point loc){
        super(loc, "small_dot", 5);
    }
}
