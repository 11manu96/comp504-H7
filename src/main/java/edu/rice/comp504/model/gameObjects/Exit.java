package edu.rice.comp504.model.gameObjects;

import java.awt.*;
import java.util.Observable;

/**
 * A concrete class extends AGameObject for the exit
 */
public class Exit extends AGameObject {

    /**
     * Constructor for the exit
     * @param loc location
     */
    public Exit(Point loc){
        super(loc,"exit",10);
    }

    /**
     * Update the exit, the exit keeps still in a game
     * @param obs observable
     * @param o object
     */
    public void update(Observable obs, Object o){}
}
