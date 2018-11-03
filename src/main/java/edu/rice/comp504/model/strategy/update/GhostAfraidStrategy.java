package edu.rice.comp504.model.strategy.update;

import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Ghost;
import edu.rice.comp504.model.gameobjects.character.Pacman;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This strategy implementing IUpdateStrategy is for ghost afraid.
 */
public class GhostAfraidStrategy implements IUpdateStrategy {
    private static GhostAfraidStrategy singletonGhostAfraid;
    private String name;

    /**
     * Ghost Afraid Strategy constructor.
     */
    private GhostAfraidStrategy() {
        this.name = "ghost_afraid";
    }

    /**
     * Get a singleton of ghost afraid strategy.
     * @return a singleton.
     */
    public static IUpdateStrategy makeStrategy() {
        if (singletonGhostAfraid == null) {
            singletonGhostAfraid = new GhostAfraidStrategy();
        }
        return singletonGhostAfraid;
    }

    /**
     * Get the strategy name.
     * @return the strategy name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Update the ghost status.
     * @param gameObj the ghost.
     */
    public void update(AGameObject gameObj) {
        Ghost ghost = (Ghost) gameObj;
        // move ghost to new location
        Point vel = ghost.getVel();
        // if ghost moves then reset open spaces
        if (vel.x != 0 || vel.y != 0) {
            ghost.setOpenSpaces();
        }
        Point loc = ghost.getLocation();
        loc.x = vel.x + loc.x;
        loc.y = vel.y + loc.y;
        ghost.setLocation(loc);

        // pick next spot that maximizes manhattan distance
        loc = ghost.getLocation();
        Point pacLoc = Pacman.getInstance().getLocation();
        int maxDist = 0;
        Point maxDir = new Point(0, 0);
        int dist;
        // possible directions
        List<Point> directions = ghost.getOpenSpaces();
        // randomize directions list
        Collections.shuffle(directions);
        // find best direction
        for (Point dir : directions) {
            dist = Math.abs(loc.x + dir.x - pacLoc.x) + Math.abs(loc.y + dir.y - pacLoc.y);
            if (dist > maxDist) {
                maxDist = dist;
                maxDir = dir;
            }
        }
        ghost.setVel(maxDir);
    }

}
