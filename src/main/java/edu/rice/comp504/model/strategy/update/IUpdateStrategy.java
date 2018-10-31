package edu.rice.comp504.model.strategy.update;

import edu.rice.comp504.model.gameobjects.AGameObject;

/**
 * This interface is used for game object updat behavior.
 */
public interface IUpdateStrategy {
    /**
     * Get the interaction update name.
     * @return The interaction update name.
     */
    public String getName();

    /**
     * Update the state of a game object.
     * @param context game object to update.
     */
    public void update(AGameObject context);
}
