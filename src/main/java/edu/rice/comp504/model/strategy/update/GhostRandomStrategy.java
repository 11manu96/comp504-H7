package edu.rice.comp504.model.strategy.update;

import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Ghost;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class GhostRandomStrategy implements IUpdateStrategy {
    private static GhostRandomStrategy singletonghostrandom;
    private String name;

    private GhostRandomStrategy() {
        this.name = "ghost_random";
    }

    public static IUpdateStrategy makeStrategy() {
        if (singletonghostrandom == null) {
            singletonghostrandom = new GhostRandomStrategy();

        }
        return singletonghostrandom;
    }

    public String getName() {
        return this.name;
    }

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
