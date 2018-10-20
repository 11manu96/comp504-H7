package edu.rice.comp504.model.gameObjects;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public abstract class AGameObject implements Observer {
   private Point location;
   private String type;

   protected int size;
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

    public int getSize(){return this.size;}

    public void update(Observable obs, Object o){}
}
