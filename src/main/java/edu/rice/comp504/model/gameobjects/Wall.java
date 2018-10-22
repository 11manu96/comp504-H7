package edu.rice.comp504.model.gameobjects;

import java.awt.*;

/**
 * This concrete class extends AGameObject and represents wall game objects.
 */
public class Wall extends AGameObject {

    /**
     * Constructor for Wall.
     * @param loc wall location
     */
    public Wall(Point loc) {
        super(loc,"wall",20);
    }
}
