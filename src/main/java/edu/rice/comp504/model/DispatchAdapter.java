package edu.rice.comp504.model;

import edu.rice.comp504.model.gameobjects.*;
import edu.rice.comp504.model.gameobjects.character.*;
import edu.rice.comp504.model.gameobjects.food.*;

import java.awt.*;
import java.util.Observable;

/**
 * DispatchAdapter class acts as the game host.
 * It handles requests from the views and updates all game objects.
 */
public class DispatchAdapter extends Observable {
    private Point dims;
    private int gridSize;
    private int score;
    private int lives;
    private static int fruitTimer;
    private static int afraidTimer;

    /**
     * Constructor.
     */
    public DispatchAdapter() {
        this.gridSize = 20;
    }

     /**
     * Set the canvas dimensions.
     * @param dims The canvas width (x) and height (y)
     */
    public void setCanvasDims(Point dims) {
        this.dims = dims;
    }

    /**
     * Get the canvas dimensions.
     * @return The canvas dimensions
     */
    public Point getCanvasDims() {
        return this.dims;
    }

    /**
     * Read initialization from a 2D array and add all necessary objects as observers.
     */
    public void initializeGame() {
        int[][] map = initializeMap();

        // clear any observers before resetting game
        deleteObservers();

        // set default game values
        this.score = 0;
        this.lives = 3;
        DispatchAdapter.fruitTimer = 30;
        DispatchAdapter.afraidTimer = 0;

        // add observers at locations specified in layout
        int height = map.length;
        int width = map[0].length;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                AGameObject object = null;

                switch (map[y][x]) {

                    case 1: // wall
                        object = new Wall(new Point(x * this.gridSize, y * this.gridSize));
                        break;
                    case 2: // exit
                        object = new Exit(new Point(x * this.gridSize, y * this.gridSize));
                        break;
                    case 3: // pacman
                        object = new Pacman(new Point(x * this.gridSize, y * this.gridSize));
                        break;

                    case 4: // ghost1
                        object = new Ghost(new Point(x * this.gridSize, y * this.gridSize), null, "red");
                        break;
                    case 5: // ghost2
                        object = new Ghost(new Point(x * this.gridSize, y * this.gridSize), null, "pink");
                        break;
                    case 6: // ghost3
                        object = new Ghost(new Point(x * this.gridSize, y * this.gridSize), null, "orange");
                        break;
                    case 7: // ghost4
                        object = new Ghost(new Point(x * this.gridSize, y * this.gridSize), null, "blue");
                        break;

                    case 8: // smallDot
                        object = new SmallDot(new Point(x * this.gridSize, y * this.gridSize));
                        break;
                    case 9: // bigDot
                        object = new BigDot(new Point(x * this.gridSize, y * this.gridSize));
                        break;
                    default:
                        break;
                }

                if (object != null) {
                    addObserver(object);
                }
            }
        }
    }

    /**
     * Initialize the map layout.
     * @return 2D array of map layout
     */
    private int[][] initializeMap() {
        // 216 small dots
        int[][] map = new int[][] {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
                {1, 8, 1, 1, 1, 1, 1, 1, 1, 8, 1, 1, 1, 0, 1, 1, 1, 8, 1, 8, 1, 8, 1, 8, 1, 8, 1},
                {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1, 8, 8, 0, 8, 8, 1, 8, 1, 8, 1, 8, 1, 8, 1, 8, 1},
                {1, 8, 1, 8, 1, 8, 1, 1, 1, 8, 1, 1, 1, 0, 1, 1, 1, 8, 1, 8, 1, 9, 1, 8, 1, 8, 1},
                {1, 8, 1, 8, 8, 8, 8, 9, 1, 8, 1, 4, 5, 0, 6, 7, 1, 8, 1, 8, 1, 8, 1, 8, 1, 8, 1},
                {1, 8, 1, 8, 1, 8, 1, 1, 1, 8, 1, 1, 1, 1, 1, 1, 1, 8, 1, 8, 1, 8, 1, 8, 1, 8, 1},
                {1, 8, 1, 8, 8, 8, 8, 8, 8, 8, 1, 8, 8, 8, 8, 8, 1, 8, 1, 8, 8, 8, 8, 8, 1, 8, 1},
                {2, 8, 1, 1, 1, 8, 1, 1, 1, 8, 1, 8, 1, 1, 1, 8, 1, 8, 1, 1, 1, 8, 1, 1, 1, 8, 2},
                {1, 8, 8, 8, 8, 8, 8, 8, 1, 8, 1, 8, 8, 8, 8, 8, 1, 8, 8, 8, 8, 8, 8, 8, 1, 8, 1},
                {1, 8, 1, 1, 1, 8, 1, 8, 1, 8, 1, 8, 1, 1, 1, 8, 1, 8, 1, 1, 1, 8, 1, 8, 1, 8, 1},
                {1, 8, 1, 9, 8, 8, 8, 8, 8, 8, 8, 8, 1, 3, 1, 8, 8, 8, 8, 8, 8, 8, 1, 8, 1, 8, 1},
                {1, 8, 1, 1, 1, 8, 1, 8, 1, 8, 1, 8, 1, 8, 1, 8, 1, 8, 1, 1, 1, 8, 1, 8, 1, 8, 1},
                {1, 8, 8, 8, 8, 8, 8, 8, 1, 8, 1, 8, 8, 8, 8, 8, 1, 8, 1, 9, 8, 8, 1, 8, 1, 8, 1},
                {1, 8, 1, 1, 1, 1, 1, 1, 1, 8, 1, 1, 1, 8, 1, 1, 1, 8, 1, 1, 1, 8, 1, 8, 1, 8, 1},
                {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };

        return map;
    }

    /**
     * Call the update method on all observers to update their position in the game.
     */
    public void updatePacWorld() {

    }

    /**
     * Load a fruit into the game.
     * @return A fruit object
     */
    public Fruit loadFruit() {
        return null;
    }

    /**
     * Switch Pacman direction.
     * @param body The request body
     */
    public void switchDirection(String body) {

    }

}
