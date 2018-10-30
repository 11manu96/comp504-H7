package edu.rice.comp504.model;

import edu.rice.comp504.model.cmd.*;
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
    private static int gridSize;
    private int score;
    private int lives;

    private int afraidTimer;
    private int dotsLeft;



    /**
     * Constructor.
     */
    public DispatchAdapter() {
        DispatchAdapter.gridSize = 20;
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
     * Get the game grid size.
     * @return The grid size
     */
    public static int getGridSize() {
        return DispatchAdapter.gridSize;
    }

    /**
     * Get the game score.
     * @return The game score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Set the game score.
     * @param score game score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Get Pacman lives.
     * @return pacman lives
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Set Pacman lives.
     * @param lives pacman lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Get the ghosts afraid timer.
     * @return ghosts afraid timer
     */
    public int getAfraidTimer() {
        return this.afraidTimer;
    }

    /**
     * Set the ghosts afraid timer.
     * @param afraidTimer ghosts afraid timer
     */
    public void setAfraidTimer(int afraidTimer) {
        this.afraidTimer = afraidTimer;
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
        this.afraidTimer = 0;
        this.dotsLeft = 0;

        // add observers at locations specified in layout
        int height = map.length;
        int width = map[0].length;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                AGameObject object = null;

                Point objLoc = new Point(x * DispatchAdapter.gridSize, y * DispatchAdapter.gridSize);
                switch (map[y][x]) {
                    case 1: // wall
                        object = new Wall(objLoc);
                        break;
                    case 2: // exit
                        object = new Exit(objLoc, new Point(getCanvasDims().x - objLoc.x, objLoc.y));
                        break;
                    case 3: // pacman
                        object = Pacman.getInstance(objLoc);
                        break;

                    case 4: // ghost1
                        object = new Ghost(objLoc, null, "red");
                        break;
                    case 5: // ghost2
                        object = new Ghost(objLoc, null, "pink");
                        break;
                    case 6: // ghost3
                        object = new Ghost(objLoc, null, "orange");
                        break;
                    case 7: // ghost4
                        object = new Ghost(objLoc, null, "blue");
                        break;

                    case 8: // smallDot
                        object = new SmallDot(objLoc);
                        dotsLeft++;
                        break;
                    case 9: // bigDot
                        object = new BigDot(objLoc);
                        dotsLeft++;
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
     * Switch Pacman direction.
     * @param body The request body
     */
    public void switchDirection(String body) {
        SwitchCmd switchCmd = null;
        String keyCode = body.split("=")[1];
        switch(keyCode) {
            case "37":
                switchCmd = new SwitchCmd("left");
                break;
            case "38":
                switchCmd = new SwitchCmd("up");
                break;
            case "39":
                switchCmd = new SwitchCmd("right");
                break;
            case "40":
                switchCmd = new SwitchCmd("down");
                break;
            default:
                break;
        }

        setChanged();
        notifyObservers(switchCmd);
        clearChanged();
    }

}
