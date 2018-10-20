package edu.rice.comp504.model.gameObjects.ACharacter;

import edu.rice.comp504.model.gameObjects.AGameObject;
import edu.rice.comp504.model.strategy.Interact.IInteractStrategy;
import edu.rice.comp504.model.strategy.Update.IUpdateStrategy;

import java.awt.*;
import java.util.Observable;

public abstract class ACharacter extends AGameObject {
    private Point vel;
    private IUpdateStrategy updateStrategy;
    private IInteractStrategy interactStrategy;


    public ACharacter(Point loc,String type, Point vel, IUpdateStrategy updateStrategy, IInteractStrategy interactStrategy) {
        super(loc, type);
        this.vel = vel;
        this.updateStrategy=updateStrategy;
        this.interactStrategy=interactStrategy;
    }




    public IInteractStrategy getInteractStrategy() {
        return this.interactStrategy;
    }

    public IUpdateStrategy getUpdateStrategy() {
        return this.updateStrategy;
    }

    public void setInteractStrategy(IInteractStrategy interactStrategy) {
        this.interactStrategy = interactStrategy;
    }

    public void setUpdateStrategy(IUpdateStrategy updateStrategy) {
        this.updateStrategy = updateStrategy;
    }

    public Point getVel() {
        return this.vel;
    }

    public void setVel(Point vel) {
        this.vel = vel;
    }




    public void update(Observable obs, Object o){}
}
