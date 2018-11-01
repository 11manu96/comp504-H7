package edu.rice.comp504.model.gameobjects.food;

import edu.rice.comp504.model.DispatchAdapter;

import java.awt.*;
import java.util.*;
import java.util.List;


/**
 * This concrete class extends AFood to represent fruit game objects.
 */
public class Fruit extends AFood {

    private static Fruit fruit;
    private static int fruitTimer;
    private List<Point> emptyPositionsList;
    /**
     * Constructor for Fruit.
     * @param loc fruit location
     */
    private Fruit(Point loc) {
        super(loc, "fruit", DispatchAdapter.getGridSize(), 100);
        this.fruitTimer = 30;
        this.emptyPositionsList = new ArrayList<Point>();
    }

    public static Fruit makeFruit(Point loc) {
        if (fruit == null) {
            fruit = new Fruit(loc);
        } else {
            fruit.setLocation(loc);
        }
        return fruit;
    }

    /**
     * Get the fruit timer.
     * @return fruit timer
     */
    public int getFruitTimer() {
        return this.fruitTimer;
    }

    /**
     * Set the fruit timer.
     * @param fruitTimer fruit timer
     */
    public void setFruitTimer(int fruitTimer) {
        this.fruitTimer = fruitTimer;
    }
}
