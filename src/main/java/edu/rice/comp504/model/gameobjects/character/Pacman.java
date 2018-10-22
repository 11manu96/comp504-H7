package edu.rice.comp504.model.gameobjects.character;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.gameobjects.AGameObject;

import java.awt.*;

/**
 * This concrete class extends ACharacter and represents the Pacman game object.
 */
public class Pacman extends ACharacter {

    /**
     * Constructor for Pacman.
     * @param loc pacman location
     */
    public Pacman(Point loc) {
        super(loc, "pacman", new Point(0,0),null, null, DispatchAdapter.getGridSize());
    }

    /**
     * Handle collision between Pacman and game object.
     * @param gameObject object the check collision against
     * @return whether there was a collision
     */
    public boolean collision(AGameObject gameObject) {
        return false;
    }
}
