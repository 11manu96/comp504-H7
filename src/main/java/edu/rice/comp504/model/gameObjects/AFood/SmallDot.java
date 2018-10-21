package edu.rice.comp504.model.gameObjects.AFood;

import java.awt.*;
import java.util.Observable;

/**
 * A concrete class extends AFood for the Small dot
 */
public class SmallDot extends AFood {

    /**
     * Constructor for the SmallDot
     * @param loc location
     */
    public SmallDot(Point loc){
        super(loc,"small_dot",10);
    }

    /**
     * Update the SmallDot
     * @param obs observable
     * @param o object
     */
    public void update(Observable obs, Object o){}
}
