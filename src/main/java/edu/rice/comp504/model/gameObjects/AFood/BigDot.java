package edu.rice.comp504.model.gameObjects.AFood;

import java.awt.*;
import java.util.Observable;

/**
 * A concrete class extends AFood for the BigDot
 */
public class BigDot extends AFood {

    /**
     * Constructor for the BigDot
     * @param loc location
     */
    public BigDot(Point loc){
        super(loc,"big_dot",18);
    }

    /**
     * Update the BigDot
     * @param obs observable
     * @param o object
     */
    public void update(Observable obs, Object o){}
}
