package edu.rice.comp504.model.gameObjects.AFood;

import java.awt.*;
import java.util.Observable;

public class BigDot extends AFood {
    String type;
    Point location;
    public BigDot(Point loc,String type){
        super(loc,type);
        this.location=loc;
        this.type="BigDot";
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
