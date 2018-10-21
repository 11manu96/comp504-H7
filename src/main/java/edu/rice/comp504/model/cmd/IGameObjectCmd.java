package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.gameObjects.AGameObject;

/**
 * A command that needs to be excuted by an object
 */
public interface IGameObjectCmd {

    /**
     * Execute the command by the receiver (context).
     * @param context  The receiver that will execute the command.
     */
    public void execute(AGameObject context);
}
