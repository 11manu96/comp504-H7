package edu.rice.comp504.model.gameObjects;

import java.awt.*;
import java.util.Observable;

public class Wall extends AGameObject {


    public Wall(Point loc){
        super(loc,"wall",20);

    }

    public void update(Observable obs, Object o){}
}
