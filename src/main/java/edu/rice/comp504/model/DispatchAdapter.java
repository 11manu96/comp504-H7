package edu.rice.comp504.model;

import java.awt.*;
import java.util.List;
import java.util.Observable;

/**
 * Note: Balls are only accessed through using the observable-observer design pattern.  You should NOT create
 * a separate list of balls.
 */
public class DispatchAdapter extends Observable {
    private Point dims;
    /**
     * Constructor
     */
    public DispatchAdapter() { }

     /**
     * Set the canvas dimensions
     * @param dims The canvas width (x) and height (y)
     */
    public void setCanvasDims(Point dims) {
        this.dims = dims;
    }

    /**
     * Get the canvas dimensions
     * @return The canvas dimensions
     */
    public Point getCanvasDims() {
        return this.dims;
    }

    /**
     * Call the update method on all the paint observers to update their position in the paint world
     */
    public void updatePacWorld() {

    }

}
