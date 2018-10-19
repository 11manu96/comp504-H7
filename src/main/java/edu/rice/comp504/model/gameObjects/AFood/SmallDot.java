package edu.rice.comp504.model.gameObjects.AFood;

import java.awt.*;
import java.util.Observable;

public class SmallDot extends AFood {
    String type;
    Point location;
    public SmallDot(Point loc,String type){
        super(loc,type);
        this.location=loc;
        this.type="SmallDot";
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
