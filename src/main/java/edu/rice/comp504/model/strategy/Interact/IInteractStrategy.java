package edu.rice.comp504.model.strategy.Interact;

import edu.rice.comp504.model.gameObjects.AGameObject;

public interface IInteractStrategy {
    public String getName();
    public void interact(AGameObject src, AGameObject dest);
}
