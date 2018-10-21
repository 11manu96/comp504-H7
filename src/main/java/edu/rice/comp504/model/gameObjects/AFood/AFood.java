package edu.rice.comp504.model.gameObjects.AFood;

import edu.rice.comp504.model.gameObjects.AGameObject;

import java.awt.*;
import java.util.Observable;

public abstract class AFood extends AGameObject {

    public AFood(Point loc,String type, int size){
        super(loc,type,size);
    }


    public void update(Observable obs, Object o){}

}
