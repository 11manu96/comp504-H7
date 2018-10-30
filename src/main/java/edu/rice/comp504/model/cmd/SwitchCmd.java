package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.ACharacter;

import java.awt.*;

public class SwitchCmd implements IGameObjectCmd {

    String direction;

    /**
     * Constructor
     * @param direction switch to which direction
     */
    public SwitchCmd(String direction) {
        this.direction = direction;
    }

    /**
     * Execute the command.
     * @param context The receiver gameobj.
     */
    public void execute(AGameObject context) {
        String type = context.getType();
        if (!(type.equals("pacman") || type.equals("ghost")))
            return;

        ACharacter character = (ACharacter)context;
        switch (direction) {
            case "left":
                character.setVel(new Point(0, -1));
                break;
            case "right":
                character.setVel(new Point(0, 1));
                break;
            case "up":
                character.setVel(new Point(-1, 0));
                break;
            case "down":
                character.setVel(new Point(1, 0));
                break;
            default:
                break;

        }

    }
}
