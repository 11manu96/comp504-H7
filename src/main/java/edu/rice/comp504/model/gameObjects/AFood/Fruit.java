package edu.rice.comp504.model.gameObjects.AFood;

import java.awt.*;
import java.util.Observable;

public class Fruit extends AFood {

    public Fruit(Point loc){
        super(loc,"fruit");
        this.size=20;
    }

    public void update(Observable obs, Object o){}
}
