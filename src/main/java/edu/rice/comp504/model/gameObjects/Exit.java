package edu.rice.comp504.model.gameObjects;

import java.awt.*;
import java.util.Observable;

public class Exit extends AGameObject {

    public Exit(Point loc){
        super(loc,"exit",10);
    }

    public void update(Observable obs, Object o){}
}
