package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Ghost;
import edu.rice.comp504.model.gameobjects.character.Pacman;

import java.awt.*;

public class UpdateCmd implements IGameObjectCmd{

    private DispatchAdapter dispatchAdapter;
    public UpdateCmd(DispatchAdapter dispatchAdapter){
        this.dispatchAdapter=dispatchAdapter;

    }
    public void execute(AGameObject context){

        switch (context.getType()){

            case "pacman":

                ((Pacman) context).getUpdateStrategy().update(context);
                dispatchAdapter.sendCollisionCmd(context);
                break;
            /*case "ghost":
                ((Ghost) context).getUpdateStrategy().update(context);
                dispatchAdapter.sendCollisionCmd(context);
                break;*/
            default:

                break;
        }
    }

}
