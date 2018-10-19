package edu.rice.comp504.model.gameObjects.ACharacter;

import edu.rice.comp504.model.strategy.Interact.IInteractStrategy;
import edu.rice.comp504.model.strategy.Update.IUpdateStrategy;

import java.awt.*;
import java.util.Observable;

public class Ghost extends ACharacter {

    Point vel;
    IUpdateStrategy updateStrategy;
    IInteractStrategy interactStrategy;
    Point location;
    String type;
    public Ghost(Point loc,String type, Point vel,IUpdateStrategy updateStrategy,IInteractStrategy interactStrategy){
        super(loc,type,vel,updateStrategy,interactStrategy);
        this.location=loc;
        this.vel=new Point(0,0);
        this.type="Ghost";
        this.updateStrategy=updateStrategy;
        this.interactStrategy=interactStrategy;
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


    public IInteractStrategy getInteractStrategy() {
        return interactStrategy;
    }

    public IUpdateStrategy getUpdateStrategy() {
        return updateStrategy;
    }

    public void setInteractStrategy(IInteractStrategy interactStrategy) {
        this.interactStrategy = interactStrategy;
    }

    public void setUpdateStrategy(IUpdateStrategy updateStrategy) {
        this.updateStrategy = updateStrategy;
    }

    public Point getVel() {
        return vel;
    }

    public void setVel(Point vel) {
        this.vel = vel;
    }

    public void update(Observable obs, Object o){}
}
