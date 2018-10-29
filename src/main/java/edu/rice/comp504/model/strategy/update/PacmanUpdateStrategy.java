package edu.rice.comp504.model.strategy.update;


import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Pacman;


import java.awt.*;

public class PacmanUpdateStrategy implements IUpdateStrategy{
    private static PacmanUpdateStrategy SingletonPacmanUpdate;
    private PacmanUpdateStrategy(){}
    public static IUpdateStrategy makeStrategy(){
        if (SingletonPacmanUpdate==null){
            SingletonPacmanUpdate=new PacmanUpdateStrategy();
        }
        return SingletonPacmanUpdate;
    }
    public String getName(){
        return "pacman_update";
    }

    public void update(AGameObject pacman){
        Point vel=((Pacman)pacman).getVel();
        Point loc=pacman.getLocation();
        loc.x=vel.x+loc.x;
        loc.y=vel.y+loc.y;
        pacman.setLocation(loc);
    }

}
