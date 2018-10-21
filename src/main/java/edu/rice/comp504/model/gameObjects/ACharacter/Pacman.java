package edu.rice.comp504.model.gameObjects.ACharacter;

import java.awt.*;
import java.util.Observable;

public class Pacman extends ACharacter {

    public Pacman(Point loc){
        super(loc,"pacman",new Point(0,0),null,null,20);
    }


    public void update(Observable obs, Object o){}
}
