package edu.rice.comp504.model.strategy.Update;

import edu.rice.comp504.model.gameObjects.AGameObject;

public interface IUpdateStrategy  {
    public String getName();
    public void Update(AGameObject context);
}
