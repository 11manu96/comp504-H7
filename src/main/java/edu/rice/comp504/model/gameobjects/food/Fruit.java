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
    private static int fruitTimer = 100;
    private static List<Point> emptyPositionsList = new ArrayList<Point>();

    /**
     * Constructor for Fruit.
     * @param loc fruit location
     */
    private Fruit(Point loc) {
        super(loc, "fruit", DispatchAdapter.getGridSize(), 200);
    }

    /**
     * Instantiate or update singleton fruit instance.
     * @param loc fruit location
     * @return fruit instance
     */
    public static Fruit makeFruit(Point loc) {
        if (fruit == null) {
            fruit = new Fruit(loc);
        } else {
            fruit.setLocation(loc);
        }
        return fruit;
    }

    /**
     * Get singleton instance of fruit.
     * @return fruit instance
     */
    public static Fruit getInstance() {
        return fruit;
    }

    /**
     * Get the fruit timer.
     * @return fruit timer
     */
    public static int getFruitTimer() {
        return Fruit.fruitTimer;
    }

    /**
     * Set the fruit timer.
     * @param fruitTimer fruit timer
     */
    public static void setFruitTimer(int fruitTimer) {
        Fruit.fruitTimer = fruitTimer;
    }

    /**
     * Get the list of open positions.
     * @return position list
     */
    public static List<Point> getPositionList() {
        return Fruit.emptyPositionsList;
    }

    /**
     * Set the position list.
     * @param position a possible position for fruit
     */
    public static void setPositionList(Point position) {
        emptyPositionsList.add(position);
    }
}
