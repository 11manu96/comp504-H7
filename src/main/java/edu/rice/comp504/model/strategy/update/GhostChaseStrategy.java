package edu.rice.comp504.model.strategy.update;

import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Ghost;
import edu.rice.comp504.model.gameobjects.character.Pacman;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class GhostChaseStrategy implements IUpdateStrategy {
    private static GhostChaseStrategy singletonGhostChase;
    private String name;

    private GhostChaseStrategy() {
        this.name = "ghost_chase";
    }

    public static IUpdateStrategy makeStrategy() {
        if (singletonGhostChase == null) {
            singletonGhostChase = new GhostChaseStrategy();
        }
        return singletonGhostChase;
    }

    public String getName() {
        return this.name;
    }

    public void update(AGameObject gameObj) {
        Ghost ghost = (Ghost) gameObj;
        // move ghost to new location
        Point vel = ghost.getVel();
        // if ghost moves then reset open spaces
        if (vel.x != 0 || vel.y != 0) {
            ghost.setOpenSpaces();
        }
        Point loc = ghost.getLocation();
        vel = ghost.getVel();
        loc.x = vel.x + loc.x;
        loc.y = vel.y + loc.y;
        ghost.setLocation(loc);

        // pick next spot that minimizes manhattan distance
        Point pacLoc = Pacman.getInstance().getLocation();
        int minDist = Integer.MAX_VALUE;
        Point minDir = new Point(0, 0);
        int dist;
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

    }
}
