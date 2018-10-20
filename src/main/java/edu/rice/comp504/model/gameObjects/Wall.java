package edu.rice.comp504.model.gameObjects;

import java.awt.*;
import java.util.Observable;

public class Wall extends AGameObject {

    Point location;
    String type;
    int length;
    public Wall(Point loc){
        super(loc,"wall");
        this.location=loc;
        this.length=SIZE;
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

    public int getLength() {
        return length;
    }

    public void update(Observable obs, Object o){}
}
