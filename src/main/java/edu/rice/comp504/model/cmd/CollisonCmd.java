package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.gameobjects.AGameObject;

public class CollisonCmd implements IGameObjectCmd {
    private AGameObject gameObject;
    public CollisonCmd(AGameObject gameObject){
        this.gameObject=gameObject;
    }
    public void execute(AGameObject context){}
}
