package edu.rice.comp504.model.gameobjects.food;

import java.awt.*;

/**
 * This concrete class extends AFood and represents big dots.
 */
public class BigDot extends AFood {

    /**
     * Constructor for BigDot.
     * @param loc big dot location
     */
    public BigDot(Point loc) {
        super(loc, "big_dot", 10);
    }
}
