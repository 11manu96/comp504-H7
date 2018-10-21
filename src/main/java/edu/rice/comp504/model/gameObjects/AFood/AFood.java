package edu.rice.comp504.model.gameObjects.AFood;

import edu.rice.comp504.model.gameObjects.AGameObject;

import java.awt.*;
import java.util.Observable;

/**
 * An abstract class extends AGameObject for all food objects in the Pac world.
 * The Object that will be drawn and updated on the canvas.
 */
public abstract class AFood extends AGameObject {

    /**
     * Constructor for AFood
     * @param loc location
     * @param type type big_dot or fruit or small_dot
     * @param size diameter
     */
    public AFood(Point loc,String type, int size){
        super(loc,type,size);
    }

    /**
     * Update the state of the object using strategies associated with the object.
     * @param obs observable
     * @param o object
     */
    public void update(Observable obs, Object o){}

}
