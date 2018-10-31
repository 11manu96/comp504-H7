package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Ghost;
import edu.rice.comp504.model.gameobjects.character.Pacman;

import java.awt.*;

public class UpdateCmd implements IGameObjectCmd{

    private DispatchAdapter dispatchAdapter;
    public UpdateCmd(DispatchAdapter dispatchAdapter) {
        this.dispatchAdapter = dispatchAdapter;

    }
    public void execute(AGameObject context) {

        switch(context.getType()) {

            case "pacman":
                dispatchAdapter.sendCollisionCmd(context);
                ((Pacman) context).getUpdateStrategy().update(context);
                break;

            case "ghost":
                dispatchAdapter.sendCollisionCmd(context);
                ((Ghost) context).getUpdateStrategy().update(context);
                break;

            default:
                break;
        }
    }

}
