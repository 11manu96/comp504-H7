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
    int size;
    int jail;
    boolean flash;
    String color;

    public Ghost(Point loc,IUpdateStrategy updateStrategy,String color){
        super(loc,"ghost",new Point(0,0),updateStrategy);
        this.updateStrategy=updateStrategy;
        this.interactStrategy=null;
        this.size=SIZE;
        this.flash=false;
        this.jail=3;
        this.color=color;
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

    public String getColor() {
        return color;
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

    public int getSize() {
        return size;
    }

    public void update(Observable obs, Object o){}
}
