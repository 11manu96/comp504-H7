package edu.rice.comp504.model.strategy.update;

import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Ghost;
import edu.rice.comp504.model.gameobjects.character.Pacman;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This strategy implementing IUpdateStrategy is for ghost random walk.
 */
public class GhostRandomStrategy implements IUpdateStrategy {
    private static GhostRandomStrategy singletonghostrandom;
    private String name;

    /**
     * Ghost random strategy constructor.
     */
    private GhostRandomStrategy() {
        this.name = "ghost_random";
    }

    /**
     * Get the ghost random strategy singleton.
     * @return a singleton.
     */
    public static IUpdateStrategy makeStrategy() {
        if (singletonghostrandom == null) {
            singletonghostrandom = new GhostRandomStrategy();
        }
        return singletonghostrandom;
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
        Point vel = ghost.getVel();
        if (vel.x != 0 || vel.y != 0) {
            ghost.setOpenSpaces();
        }
        Point loc = ghost.getLocation();
        loc.x = vel.x + loc.x;
        loc.y = vel.y + loc.y;
        ghost.setLocation(loc);

        Point pacLoc = Pacman.getInstance().getLocation();
        // random if far from Pacman and chase if nearby
        int dist = Math.abs(loc.x - pacLoc.x) + Math.abs(loc.y - pacLoc.y);
        if (dist < 200) {
            int minDist=Integer.MAX_VALUE;
            Point minDir = new Point(0, 0);
            // possible directions
            List<Point> directions = ghost.getOpenSpaces();
            // randomize directions list
            Collections.shuffle(directions);
            // find best direction
            for (Point dir : directions) {
                dist = Math.abs(loc.x + dir.x - pacLoc.x) + Math.abs(loc.y + dir.y - pacLoc.y);
                if (dist < minDist) {
                    minDist = dist;
                    minDir = dir;
                }
            }
            ghost.setVel(minDir);
        } else {
            // possible directions
            List<Point> directions = ghost.getOpenSpaces();
            // randomize directions list
            Collections.shuffle(directions);
            ghost.setVel(directions.get(0));
        }
    }
}
