package edu.rice.comp504.model.strategy.update;

import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Ghost;

import java.awt.*;
import java.util.Random;


public class GhostRandomStrategy implements IUpdateStrategy {
    private static GhostRandomStrategy singletonghostrandom;

    private GhostRandomStrategy(){}

    public static IUpdateStrategy makeStrategy(){
        if (singletonghostrandom == null){
            singletonghostrandom = new GhostRandomStrategy();

        }
        return singletonghostrandom;
    }

    public String getName() {
        return "ghost_random";
    }

    public void update(AGameObject ghost) {
        int veldirection = generateVelDirection();
        Point vel = null;
        switch (veldirection) {
            case 1:
                vel = new Point(20,0);
                break;
            case 2:
                vel = new Point(-20,0);
                break;
            case 3:
                vel = new Point(0,20);
                break;
            case 4:
                vel = new Point(0,-20);
                break;
            default:
                break;
        }
        ((Ghost) ghost).setVel(vel);
        Point loc = ghost.getLocation();
        loc.x = vel.x + loc.x;
        loc.y = vel.y + loc.y;
        ghost.setLocation(loc);
    }
    private int generateVelDirection(){
        Random random=new Random();
        return random.nextInt(4)+1;
    }

}