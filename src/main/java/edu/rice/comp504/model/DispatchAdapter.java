package edu.rice.comp504.model;

import edu.rice.comp504.model.gameObjects.*;
import edu.rice.comp504.model.gameObjects.ACharacter.*;
import edu.rice.comp504.model.gameObjects.AFood.*;

import java.awt.*;
import java.util.List;
import java.util.Observable;

/**
 * Note: Balls are only accessed through using the observable-observer design pattern.  You should NOT create
 * a separate list of balls.
 */
public class DispatchAdapter extends Observable {
    private Point dims;
    private static int score;
    private static int lives;
    private static int fruitTimer;
    private static int afraidTimer;
    private static int[][] map;

    /**
     * Constructor
     */
    public DispatchAdapter() {
        initializeMap();
    }

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
     * read initialization from a 2D array and add all necessary objects as observers
     * 1000*500 10
     */
    public void initializeGame() {
        deleteObservers();
        score = 0;
        lives = 3;
        fruitTimer = 30;
        afraidTimer = 0;

        int height = map.length;
        int width = map[0].length;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                AGameObject object = null;

                switch (map[x][y]) {
                    case 1://wall
                        object = new Wall(new Point(x, y));
                        break;
                    case 2://exit
                        object = new Exit(new Point(x, y));
                        break;
                    case 3://pacman
                        object = new Pacman(new Point(x, y));
                        break;

                    case 4://ghost1
                        object = new Ghost(new Point(x, y), null, "red");
                        break;
                    case 5://ghost2
                        object = new Ghost(new Point(x, y), null, "pink");
                        break;
                    case 6://ghost3
                        object = new Ghost(new Point(x, y), null, "orange");
                        break;
                    case 7://ghost4
                        object = new Ghost(new Point(x, y), null, "blue");
                        break;

                    case 8://smallDot
                        object = new SmallDot(new Point(x, y));
                        break;
                    case 9://bigDot
                        object = new BigDot(new Point(x, y));
                        break;
                }

                this.addObserver(object);
            }
        }
    }

    /**
     * Initialize the map
     */
    private void initializeMap() {
        int height = dims.x / 20;
        int width = dims.y / 20;
        map = new int[height][width];

        //outer wall and exit
        for (int y = 0; y < width; y++) {
            map[0][y] = 1;
            map[9][y] = 1;
        }
        for (int x = 0; x < height; x++) {
            map[x][0] = 1;
            map[x][19] = 1;
        }
    }

    /**
     * Call the update method on all the paint observers to update their position in the paint world
     */
    public void updatePacWorld() {

    }

    /**
     * Load a paint into the paint world
     * @return A new ball
     */
    public Fruit loadFruit() {
        return null;
    }

    /**
     * switch Pacman direction
     */
    public void switchDirection(String body) {

    }

}
