package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Ghost;
import edu.rice.comp504.model.gameobjects.character.Pacman;

import java.awt.*;

public class UpdateCmd implements IGameObjectCmd{
    private Point dims;
    private DispatchAdapter dispatchAdapter;
    public UpdateCmd(Point dims,DispatchAdapter dispatchAdapter){
        this.dispatchAdapter=dispatchAdapter;
        this.dims=dims;
    }
    public void execute(AGameObject context){
        switch (context.getType()){
            case "pacman":((Pacman) context).getUpdateStrategy().update(context);break;
            case "ghost":((Ghost) context).getUpdateStrategy().update(context);break;
            default:
                break;
        }
        dispatchAdapter.setChangedPublic();
        dispatchAdapter.notifyObservers(new CollisonCmd(context));

    }
}
