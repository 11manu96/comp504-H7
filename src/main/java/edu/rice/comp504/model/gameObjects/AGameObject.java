package edu.rice.comp504.model.gameObjects;

import com.sun.corba.se.spi.ior.ObjectKey;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public abstract class AGameObject implements Observer {
   Point location;
   String type;
   public AGameObject(Point location,String type){
       this.location=location;
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
