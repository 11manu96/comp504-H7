package edu.rice.comp504.model.gameObjects.character;

import edu.rice.comp504.model.gameObjects.AGameObject;
import edu.rice.comp504.model.strategy.interact.IInteractStrategy;
import edu.rice.comp504.model.strategy.update.IUpdateStrategy;

import java.awt.*;

/**
 * An abstract class that extends AGameObject for all moving objects in the Pac world.
 */
public abstract class ACharacter extends AGameObject {
    private Point vel;
    private IUpdateStrategy updateStrategy;
    private IInteractStrategy interactStrategy;

    /**
     * Constructor for ACharacter
     * @param loc location
     * @param type type pacman or ghost
     * @param vel velocity
     * @param updateStrategy update strategy
     * @param interactStrategy interact strategy
     * @param size size
     */
    public ACharacter(Point loc, String type, Point vel, IUpdateStrategy updateStrategy, IInteractStrategy interactStrategy, int size) {
        super(loc, type, size);
        this.vel = vel;
        this.updateStrategy = updateStrategy;
        this.interactStrategy = interactStrategy;
    }

    /**
     * Get the interact strategy
     * @return interactStrategy
     */
    public IInteractStrategy getInteractStrategy() {
        return this.interactStrategy;
    }

    /**
     * Get the update strategy
     * @return updateStrategy
     */
    public IUpdateStrategy getUpdateStrategy() {
        return this.updateStrategy;
    }

    /**
     * Set interact strategy
     * @param interactStrategy
     */
    public void setInteractStrategy(IInteractStrategy interactStrategy) {
        this.interactStrategy = interactStrategy;
    }

    /**
     * Set update strategy
     * @param updateStrategy
     */
    public void setUpdateStrategy(IUpdateStrategy updateStrategy) {
        this.updateStrategy = updateStrategy;
    }

    /**
     * Get velocity
     * @return velocity
     */
    public Point getVel() {
        return this.vel;
    }

    /**
     * Set velocity
     * @param vel velocity
     */
    public void setVel(Point vel) {
        this.vel = vel;
    }
}
