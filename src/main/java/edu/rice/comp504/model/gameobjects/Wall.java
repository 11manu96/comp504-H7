package edu.rice.comp504.model.gameobjects;

import edu.rice.comp504.model.DispatchAdapter;

import java.awt.*;

/**
 * This concrete class extends AGameObject and represents wall game objects.
 */
public class Wall extends AGameObject {

    /**
     * Constructor for Wall.
     * @param loc wall location.
     */
    public Wall(Point loc) {
        super(loc, "wall", DispatchAdapter.getGridSize());
    }
}
