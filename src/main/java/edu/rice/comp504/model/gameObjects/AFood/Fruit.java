package edu.rice.comp504.model.gameObjects.AFood;

import java.awt.*;
import java.util.Observable;

public class Fruit extends AFood {
    String type;
    Point location;
    int size;
    public Fruit(Point loc){
        super(loc,"fruit");
        this.location=loc;
        this.size=SIZE;
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

    public int getSize() {
        return size;
    }

    public void update(Observable obs, Object o){}
}
