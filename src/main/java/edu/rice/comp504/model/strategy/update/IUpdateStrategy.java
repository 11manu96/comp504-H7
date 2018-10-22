package edu.rice.comp504.model.strategy.update;

import edu.rice.comp504.model.gameObjects.AGameObject;

/**
 * This interface is used to add an object update behavior
 */
public interface IUpdateStrategy  {

    /**
     * Get the name of the strategy
     * @return The strategy name
     */
    public String getName();

    /**
     * update the state of the object
     * @param context The object to update
     */
    public void Update(AGameObject context);
}
