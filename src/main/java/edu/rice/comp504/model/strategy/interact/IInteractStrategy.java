package edu.rice.comp504.model.strategy.interact;

import edu.rice.comp504.model.gameObjects.AGameObject;

/**
 * The interface used to determine the interaction strategy when objects collide
 */
public interface IInteractStrategy {
    /**
     * Get the interaction strategy name
     * @return The interaction strategy name
     */
    public String getName();

    /**
     * The interaction strategy when two objects meet
     * @param src The src object will impose the interaction strategy on the dest object
     * @param dest The dest object behavior will be affected by the src object interaction strategy
     */
    public void interact(AGameObject src, AGameObject dest);
}
