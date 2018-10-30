package edu.rice.comp504.model.gameobjects;

import edu.rice.comp504.model.DispatchAdapter;

import java.awt.*;

/**
 * This concrete class extends AGameObject and represents exit game objects.
 */
public class Exit extends AGameObject {

    //this variable holds the value of the point from where the ghost/pacman appears after entering a a exit
    private Point exitTo;
    /**
     * Constructor for Exit.
     * @param loc exit location
     */
    public Exit(Point loc, Point exitTo) {
        super(loc,"exit", DispatchAdapter.getGridSize());
        this.exitTo = exitTo;
    }
    
    public Point getExitTo(){
        return this.exitTo;
    }
    
    public void setExitTo(Point exitTo){
        this.exitTo = exitTo;
    }
}
