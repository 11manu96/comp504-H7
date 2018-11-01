package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Ghost;
import edu.rice.comp504.model.gameobjects.character.Pacman;

import java.awt.*;

/**
 * This command implementing IGameObjectCmd switches the strategy for a pacman or a ghost.
  */
public class SwitchCmd implements IGameObjectCmd {

    String switchInfo;
    DispatchAdapter dis;

    /**
     * Constructor.
     * @param switchInfo information on what to switch to
     */
    public SwitchCmd(String switchInfo, DispatchAdapter dis) {
        this.switchInfo = switchInfo;
        this.dis = dis;
    }

    /**
     * Execute the command.
     * @param context The receiver gameobj
     */
    public void execute(AGameObject context) {
        String type = context.getType();
        if (type.equals("pacman")) {
            Pacman pacman = (Pacman) context;
            // receive from the front end
            switch (switchInfo) {
                case "left":
                    pacman.setSwitchdirection(new Point(-20,0));
                    break;
                case "right":
                    pacman.setSwitchdirection(new Point(20,0));
                    break;
                case "up":
                    pacman.setSwitchdirection(new Point(0,-20));
                    break;
                case "down":
                    pacman.setSwitchdirection(new Point(0,20));
                    break;
                default:
                    break;
            }
        }
        else if (type.equals("ghost")) {
            Ghost ghost = (Ghost) context;

        }

    }
}
