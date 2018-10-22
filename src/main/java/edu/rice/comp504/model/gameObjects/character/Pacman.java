package edu.rice.comp504.model.gameObjects.character;

import java.awt.*;

/**
 * A concrete class that extends ACharacter to represent Pacman
 */
public class Pacman extends ACharacter {

    /**
     * Constructor for Pacman
     * @param loc location
     */
    public Pacman(Point loc){
        super(loc, "pacman", new Point(0,0),null,null,20);
    }
}
