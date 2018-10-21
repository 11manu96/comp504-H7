package edu.rice.comp504.model.gameObjects.ACharacter;


import edu.rice.comp504.model.strategy.Update.IUpdateStrategy;

import java.awt.*;
import java.util.Observable;

/**
 * A concrete class extends ACharacter for the ghost
 */
public class Ghost extends ACharacter {

    private int jail;
    private boolean flash;
    private String color;

    /**
     * Constructor for the Ghost
     * @param loc location
     * @param updateStrategy update strategy
     * @param color color
     */
    public Ghost(Point loc,IUpdateStrategy updateStrategy,String color){
        super(loc,"ghost",new Point(0,0),updateStrategy,null,20);
        this.flash=false;
        this.jail=3;
        this.color=color;
    }

    /**
     * Get the color of the ghost
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set the color of the ghost
     * @param color
     */
    public void setColor(String color){
        this.color=color;
    }

    /**
     * Get whether the ghost is flash
     * @return flash
     */
    public boolean isFlash() {
        return flash;
    }

    /**
     * Make the ghost flash
     * @param flash
     */
    public void setFlash(boolean flash) {
        this.flash = flash;
    }

    /**
     * Get the jail
     * @return
     */
    public int getJail() {
        return jail;
    }

    /**
     * Set the jail
     * @param jail jail
     */
    public void setJail(int jail) {
        this.jail = jail;
    }

    /**
     * Update the ghost
     * @param obs observable
     * @param o object
     */
    public void update(Observable obs, Object o){}
}
