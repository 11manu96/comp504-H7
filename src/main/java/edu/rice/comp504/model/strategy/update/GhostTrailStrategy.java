package edu.rice.comp504.model.strategy.update;

import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Ghost;
import edu.rice.comp504.model.gameobjects.character.Pacman;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * This strategy implementing IUpdateStrategy is for ghost trail.
 */
public class GhostTrailStrategy implements IUpdateStrategy {
    private static GhostTrailStrategy singletonGhostTrail;
    private String name;

    /**
     * Ghost trail strategy constructor.
     */
    private GhostTrailStrategy() { this.name="ghost_trail"; }

    /**
     * Get a singleton of ghost trail strategy.
     * @return a singleton.
     */
    public static IUpdateStrategy makeStrategy() {
        if (singletonGhostTrail == null) {
            singletonGhostTrail = new GhostTrailStrategy();
        }
        return singletonGhostTrail;
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

        // pick next spot that minimizes manhattan distance to 4 steps ahead of pacman
        loc = ghost.getLocation();
        Point pacLoc = Pacman.getInstance().getLocation();
        Point pacVel = Pacman.getInstance().getVel();
        Point pacDest = new Point(pacLoc.x - 4 * pacVel.x,pacLoc.y - 4 * pacVel.y);

        int minDist = Integer.MAX_VALUE;
        Point minDir = new Point(0, 0);
        int dist;
        // possible directions
        List<Point> directions = ghost.getOpenSpaces();
        // randomize directions list
        Collections.shuffle(directions);
        // find best direction
        for (Point dir : directions) {
            dist = Math.abs(loc.x + dir.x - pacDest.x) + Math.abs(loc.y + dir.y - pacDest.y);
            if (dist < minDist) {
                minDist = dist;
                minDir = dir;
            }
        }
        ghost.setVel(minDir);
    }
}
