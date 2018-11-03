package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.gameobjects.AGameObject;

/**
 * This interface is used for commands that are excuted by game objects.
 */
public interface IGameObjectCmd {

    /**
     * Execute the command by the receiver (context).
     * @param context  The receiver that will execute the command
     */
    public void execute(AGameObject context);
}
