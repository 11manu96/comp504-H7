package edu.rice.comp504.model.gameobjects;

import edu.rice.comp504.model.DispatchAdapter;

import java.awt.*;

/**
 * This concrete class extends AGameObject and represents exit game objects.
 */
public class Exit extends AGameObject {

    /**
     * Constructor for Exit.
     * @param loc exit location
     */
    public Exit(Point loc) {
        super(loc,"exit", DispatchAdapter.getGridSize());
    }
}
