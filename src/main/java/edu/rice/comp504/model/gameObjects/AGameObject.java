package edu.rice.comp504.model.gameObjects;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public abstract class AGameObject implements Observer {
   Point location;
   String type;
   protected static final int SIZE=10;
   protected static final int BIGDOTRADIUS=5;
   protected static final int SMALLDOTRADIUS=2;
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
