package edu.rice.comp504.model.gameObjects;

import java.awt.*;
import java.util.Observable;

public class Exit extends AGameObject {

    Point location;
    String type;
    public Exit(Point loc,String type){
        super(loc,type);
        this.location=loc;
        this.type="Exit";
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
