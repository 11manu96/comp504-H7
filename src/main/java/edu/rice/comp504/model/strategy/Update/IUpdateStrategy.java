package edu.rice.comp504.model.strategy.Update;

import edu.rice.comp504.model.gameObjects.AGameObject;

/**
 * This interface is used to add an object behavior through the use of a strategy
 */
public interface IUpdateStrategy  {

    /**
     * Get the name of the strategy.
     * @return The strategy name.
     */
    public String getName();

    /**
     * Update the state of the object.
     * @param context The object to update.
     */
    public void Update(AGameObject context);
}
