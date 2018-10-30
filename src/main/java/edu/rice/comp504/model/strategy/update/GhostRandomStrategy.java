package edu.rice.comp504.model.strategy.update;

import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Ghost;

import java.awt.*;
import java.util.Random;


public class GhostRandomStrategy implements IUpdateStrategy {
    private static GhostRandomStrategy Singletonghostrandom;
    private GhostRandomStrategy(){}
    public static IUpdateStrategy makeStrategy(){
        if (Singletonghostrandom==null){
            Singletonghostrandom=new GhostRandomStrategy();

        }
        return Singletonghostrandom;
    }

    @Override
    public String getName() {
        return "ghost_random";
    }

    @Override
    public void update(AGameObject ghost) {
        int veldirection=GenerateVelDirection();
        Point ghostvel=((Ghost) ghost).getVel();
        Point vel=null;
        switch (veldirection){
            case 1:vel=new Point(ghostvel.x,0);break;
            case 2:vel=new Point(-ghostvel.x,0);break;
            case 3:vel=new Point(0,ghostvel.y);break;
            case 4:vel=new Point(0,-ghostvel.y);break;
            default:break;
        }
        Point loc=ghost.getLocation();
        loc.x=vel.x+loc.x;
        loc.y=vel.y+loc.y;
        ghost.setLocation(loc);
    }
    private int GenerateVelDirection(){
        Random random=new Random();
        return random.nextInt(4)+1;
    }

}
