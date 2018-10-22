package edu.rice.comp504.model.gameObjects;

import java.awt.*;

/**
 * A concrete class that extends AGameObject to represent walls
 */
public class Wall extends AGameObject {

    /**
     * Constructor for Wall
     * @param loc location
     */
    public Wall(Point loc){
        super(loc,"wall",20);
    }
}
