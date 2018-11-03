package edu.rice.comp504.model.strategy.interact;

import edu.rice.comp504.model.gameobjects.AGameObject;

/**
 * This interface is used to determine the interaction strategy when objects collide.
 */
public interface IInteractStrategy {
    /**
     * Get the interaction strategy name.
     * @return The interaction strategy name
     */
    public String getName();

    /**
     * Handle the interaction when two objects meet.
     * @param src The src object will impose the interaction strategy on the dest object
     * @param dest The dest object behavior will be affected by the src object interaction strategy
     */
    public void interact(AGameObject src, AGameObject dest);
}
