package edu.rice.comp504.model.strategy.update;

import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Ghost;

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

        // possible directions
        List<Point> directions = ghost.getOpenSpaces();
        // randomize directions list
        Collections.shuffle(directions);
        ghost.setVel(directions.get(0));

    }
}
