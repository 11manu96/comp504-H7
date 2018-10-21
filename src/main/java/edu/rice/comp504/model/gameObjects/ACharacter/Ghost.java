package edu.rice.comp504.model.gameObjects.ACharacter;


import edu.rice.comp504.model.strategy.Update.IUpdateStrategy;

import java.awt.*;
import java.util.Observable;

public class Ghost extends ACharacter {


    private int jail;
    private boolean flash;
    private String color;

    public Ghost(Point loc,IUpdateStrategy updateStrategy,String color){
        super(loc,"ghost",new Point(0,0),updateStrategy,null,20);
        this.flash=false;
        this.jail=3;
        this.color=color;
    }



    public String getColor() {
        return color;
    }
    public void setColor(String color){
        this.color=color;
    }

    public boolean isFlash() {
        return flash;
    }

    public void setFlash(boolean flash) {
        this.flash = flash;
    }

    public int getJail() {
        return jail;
    }

    public void setJail(int jail) {
        this.jail = jail;
    }

    public void update(Observable obs, Object o){}
}
