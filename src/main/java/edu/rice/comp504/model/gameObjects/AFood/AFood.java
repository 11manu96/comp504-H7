package edu.rice.comp504.model.gameObjects.AFood;

import edu.rice.comp504.model.gameObjects.AGameObject;

import java.awt.*;
import java.util.Observable;

public abstract class AFood extends AGameObject {
    String type;
    Point location;
    public AFood(Point loc,String type){
        super(loc,type);
        this.location=loc;
        this.type=type;
    }
    public Point getLocation(){
        return this.location;
    }

    public String getType() {
        return type;
    }

    public void setLocation(Point location) {
        this.location = location;
    }


    public void update(Observable obs, Object o){}

}
