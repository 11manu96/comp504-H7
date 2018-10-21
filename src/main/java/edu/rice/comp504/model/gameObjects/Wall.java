package edu.rice.comp504.model.gameObjects;

import java.awt.*;
import java.util.Observable;

/**
 * A concrete class extends the AGameObject for the wall
 */
public class Wall extends AGameObject {

    /**
     * Constructor for the wall
     * @param loc location
     */
    public Wall(Point loc){
        super(loc,"wall",20);
    }

    /**
     * Update the wall, the wall keep still in a game.
     * @param obs observable
     * @param o object
     */
    public void update(Observable obs, Object o){}
}
