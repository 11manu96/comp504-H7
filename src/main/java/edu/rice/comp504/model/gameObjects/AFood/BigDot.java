package edu.rice.comp504.model.gameObjects.AFood;

import java.awt.*;
import java.util.Observable;

public class BigDot extends AFood {

    public BigDot(Point loc){
        super(loc,"big_dot");
        this.size=18;
    }


    public void update(Observable obs, Object o){}
}
