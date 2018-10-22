package edu.rice.comp504.model.strategy.update;

import edu.rice.comp504.model.gameobjects.AGameObject;

/**
 * This interface is used for object update behavior strategies.
 */
public interface IUpdateStrategy  {

    /**
     * Get the name of the strategy.
     * @return The strategy name
     */
    public String getName();

    /**
     * Update the state of the object.
     * @param context The object to update
     */
    public void update(AGameObject context);
}
