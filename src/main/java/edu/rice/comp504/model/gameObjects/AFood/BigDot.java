package edu.rice.comp504.model.gameObjects.AFood;

import java.awt.*;
import java.util.Observable;

public class BigDot extends AFood {
    String type;
    Point location;
    int radius;
    public BigDot(Point loc){
        super(loc,"big_dot");
        this.location=loc;
        this.radius=BIGDOTRADIUS;
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

    public int getRadius() {
        return radius;
    }

    public void update(Observable obs, Object o){}
}
