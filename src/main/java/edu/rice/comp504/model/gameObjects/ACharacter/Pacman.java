package edu.rice.comp504.model.gameObjects.ACharacter;

import java.awt.*;
import java.util.Observable;

/**
 * A concrete class extends ACharacter for the Pacman
 */
public class Pacman extends ACharacter {

    /**
     * Constructor for the Pacman
     * @param loc location
     */
    public Pacman(Point loc){
        super(loc,"pacman",new Point(0,0),null,null,20);
    }


    /**
     * Update the Pacman
     * @param obs observable
     * @param o object
     */
    public void update(Observable obs, Object o){}
}
