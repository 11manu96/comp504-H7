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
    private static int timer;
    private static int[][] map;

    /**
     * Constructor
     */
    public DispatchAdapter() {
        score = 0;
        lives = 3;
        timer = 0;

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
        int height = map.length;
        int width = map[0].length;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                AGameObject object = null;

                switch (map[x][y]) {
                    case 1://wall
                        object = new Wall(new Point(x, y), "Wall");
                        break;
                    case 2://exit
                        object = new Exit(new Point(x, y), "Exit");
                        break;
                    case 3://pacman
                        object = new Pacman(new Point(x, y), "Pacman", null, null, null);//don't need 'vel'
                        break;
                    case 4://ghost
                        object = new Ghost(new Point(x, y), "Ghost", null, null, null);//don't need 'vel'
                        break;
                    case 5://smallDot
                        object = new SmallDot(new Point(x, y), "SmallDot");
                        break;
                    case 6://bigDot
                        object = new BigDot(new Point(x, y), "BigDot");
                        break;
                    case 7://Fruit
                        object = new Fruit(new Point(x, y), "Fruit");
                        break;
                }

                this.addObserver(object);
            }
        }
    }

    /**
     * Call the update method on all the paint observers to update their position in the paint world
     */
    public void updatePacWorld() {

    }

    /**
     * calls all update strategies and checks collisions
     */
    public void updateGame() {

    }

    /**
     * Load a paint into the paint world
     * @param body  The REST request body has the strategy names.
     * @return A new ball
     */
    public AGameObject loadObject(String body) {

        return null;
    }

    /**
     * switch Pacman direction
     */
    public void switchDirection(String body) {

    }

    /**
     * Initialize the map
     */
    private void initializeMap() {
        map = new int[50][100];
        //outer wall and exit
        for (int y = 0; y < 100; y++) {
            map[0][y] = 1;
            map[49][y] = 1;
        }
        for (int x = 0; x < 50; x++) {
            if (x < 20 || x >= 30) {
                map[x][0] = 1;
                map[x][99] = 1;
            } else {
                map[x][0] = 2;
                map[x][99] = 2;
            }
        }

        //5
        //-
        for (int x = 10; x < 15; x++)
            for (int y = 10; y < 30; y++)
                map[x][y] = 1;
        //|
        for (int x = 15; x < 20; x++)
            for (int y = 10; y < 15; y++)
                map[x][y] = 1;
        //-
        for (int x = 20; x < 25; x++)
            for (int y = 10; y < 30; y++)
                map[x][y] = 1;
        //|
        for (int x = 25; x < 40; x++)
            for (int y = 25; y < 30; y++)
                map[x][y] = 1;
        //-
        for (int x = 40; x < 45; x++)
            for (int y = 10; y < 30; y++)
                map[x][y] = 1;

        //0
        //-
        for (int x = 10; x < 15; x++)
            for (int y = 40; y < 60; y++)
                map[x][y] = 1;
        //|
        for (int x = 15; x < 40; x++)
            for (int y = 40; y < 45; y++)
                map[x][y] = 1;
        //-
        for (int x = 40; x < 45; x++)
            for (int y = 40; y < 60; y++)
                map[x][y] = 1;
        //|
        for (int x = 20; x < 40; x++)
            for (int y = 55; y < 60; y++)
                map[x][y] = 1;

        //4
        //|
        for (int x = 10; x < 30; x++)
            for (int y = 70; y < 75; y++)
                map[x][y] = 1;
        //-
        for (int x = 25; x < 30; x++)
            for (int y = 70; y < 85; y++)
                map[x][y] = 1;
        //|
        for (int x = 10; x < 45; x++)
            for (int y = 85; y < 90; y++)
                map[x][y] = 1;

        //small blocks
        for (int x = 30; x < 34; x++)
            for (int y = 6; y < 20; y++)
                map[x][y] = 1;
        for (int x = 20; x < 34; x++)
            for (int y = 48; y < 52; y++)
                map[x][y] = 1;
        for (int x = 35; x < 40; x++)
            for (int y = 65; y < 80; y++)
                map[x][y] = 1;
        for (int x = 5; x < 22; x++)
            for (int y = 78; y < 82; y++)
                map[x][y] = 1;

        //pacman
        map[47][49] = 3;

        //ghost
        map[15][15] = map[15][16] = map[15][17] = map[15][18] = 4;

        //smallDot
        //-
        for (int y = 5; y < 73; y++)
            map[6][y] = 5;
        for (int y = 87; y < 95; y++)
            map[6][y] = 5;
        //|
        for (int x = 7; x < 25; x++)
            map[x][5] = 5;
        for (int x = 39; x < 47; x++)
            map[x][5] = 5;
        //-
        for (int y = 5; y < 95; y++)
            map[46][y] = 5;
        //|
        for (int x = 10; x < 44; x++)
            map[x][35] = 5;
        //|
        for (int x = 10; x < 30; x++)
            map[x][64] = 5;
        for (int x = 7; x < 46; x++)
            map[x][94] = 5;

        //bigDot
        map[6][5] = map[46][5] = map[6][94] = map[46][94] = 6;

        //fruit
        map[32][82] = 7;

    }

}
