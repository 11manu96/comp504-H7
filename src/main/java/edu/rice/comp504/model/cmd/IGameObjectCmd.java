package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.gameObjects.AGameObject;

public interface IGameObjectCmd {

    public void execute(AGameObject context);
}
