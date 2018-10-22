package edu.rice.comp504.model.gameObjects.food;

import java.awt.*;

/**
 * A concrete class that extends AFood to represent big dots
 */
public class BigDot extends AFood {

    /**
     * Constructor for BigDot
     * @param loc location
     */
    public BigDot(Point loc){
        super(loc, "big_dot", 10);
    }
}
