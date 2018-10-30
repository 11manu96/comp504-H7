package edu.rice.comp504.model.gameobjects;

import edu.rice.comp504.model.cmd.IGameObjectCmd;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * This is an abstract class for all objects in the Pac world.
 * It represents all objects that will be drawn and updated on the canvas.
 */
public abstract class AGameObject implements Observer {
    private Point location;
    private String type;
    private int size;

    /**
     * Constructor for AGameObject.
     * @param location left upper conner of the object
     * @param type type of the object
     * @param size size of the object
     */
    public AGameObject(Point location, String type, int size) {
        this.location = location;
        this.type = type;
        this.size = size;
    }

    /**
     * Get the type of the object.
     * @return type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Get the location of the object.
     * @return location
     */
    public Point getLocation() {
        return this.location;
    }

    /**
     * Set the location of the object.
     * @param location a Point
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * Get the size of the object.
     * @return size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Update the state of the object using strategies associated with the object.
     * @param obs observable
     * @param o object
     */
    public void update(Observable obs, Object o) {
        IGameObjectCmd cmd=(IGameObjectCmd) o;

        cmd.execute(this);
    }


}
