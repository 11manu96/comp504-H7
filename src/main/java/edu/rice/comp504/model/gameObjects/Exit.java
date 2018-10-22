package edu.rice.comp504.model.gameObjects;

import java.awt.*;

/**
 * A concrete class that extends AGameObject to represent exits
 */
public class Exit extends AGameObject {

    /**
     * Constructor for Exit
     * @param loc location
     */
    public Exit(Point loc){
        super(loc,"exit",10);
    }
}
