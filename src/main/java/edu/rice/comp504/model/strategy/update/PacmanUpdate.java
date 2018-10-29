package edu.rice.comp504.model.strategy.update;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Pacman;


import java.awt.*;

public class PacmanUpdate implements IUpdateStrategy{

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
