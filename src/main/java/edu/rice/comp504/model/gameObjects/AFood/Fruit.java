package edu.rice.comp504.model.gameObjects.AFood;

import java.awt.*;
import java.util.Observable;

/**
 * A concrete class extends AFood for the Fruit
 */
public class Fruit extends AFood {

    /**
     * Constructor for the fruit
     * @param loc location
     */
    public Fruit(Point loc){
        super(loc,"fruit",20);
    }

    /**
     * Update the fruit
     * @param obs observable
     * @param o object
     */
    public void update(Observable obs, Object o){}
}
