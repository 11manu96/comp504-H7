package edu.rice.comp504.model.gameObjects.ACharacter;

import edu.rice.comp504.model.strategy.Interact.IInteractStrategy;
import edu.rice.comp504.model.strategy.Update.IUpdateStrategy;

import java.awt.*;
import java.util.Observable;

public class Pacman extends ACharacter {

    Point vel;
    IUpdateStrategy updateStrategy;
    IInteractStrategy interactStrategy;
    Point location;
    String type;
    int radius;
    public Pacman(Point loc,IUpdateStrategy updateStrategy){
        super(loc,"pacman",new Point(0,0),updateStrategy);
        this.location=loc;
        this.updateStrategy=updateStrategy;
        this.interactStrategy=null;
        this.radius=BIGDOTRADIUS;
    }

    public Point getLocation(){
        return this.location;
    }

    public String getType() {
        return this.type;
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

    public int getRadius() {
        return radius;
    }

    public void update(Observable obs, Object o){}
}
