package edu.rice.comp504.model.gameobjects.food;

import edu.rice.comp504.model.DispatchAdapter;

import java.awt.*;

/**
 * This concrete class extends AFood and represents small dots.
 */
public class SmallDot extends AFood {

    /**
     * Constructor for SmallDot.
     * @param loc small dot location
     */
    public SmallDot(Point loc) {
        super(loc, "small_dot", DispatchAdapter.getGridSize() / 4, 50);
    }
}
