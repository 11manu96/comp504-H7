package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.ACharacter;
import edu.rice.comp504.model.gameobjects.character.Ghost;
import edu.rice.comp504.model.gameobjects.character.Pacman;
import edu.rice.comp504.model.strategy.update.IUpdateStrategy;

import java.awt.*;

public class SwitchCmd implements IGameObjectCmd {

    Object dirOrStrat;

    /**
     * Constructor
     * @param dirOrStrat switch to which direction
     */
    public SwitchCmd(Object dirOrStrat) {
        this.dirOrStrat = dirOrStrat;
    }

    /**
     * Execute the command.
     * @param context The receiver gameobj.
     */
    public void execute(AGameObject context) {

        String type = context.getType();


        if (type.equals("pacman")) {
            System.out.println("pacman");
            Pacman pacman = (Pacman) context;

            switch ((String)dirOrStrat) {
                case "left":
                    pacman.setVel(new Point(0, -1));
                    break;
                case "right":
                    pacman.setVel(new Point(0, 1));
                    break;
                case "up":
                    pacman.setVel(new Point(-1, 0));
                    break;
                case "down":
                    pacman.setVel(new Point(1, 0));
                    break;
                default:
                    break;
            }

            //System.out.println(pacman.getVel().getX() + "    " + pacman.getVel().getY());
        }
        else if (type.equals("ghost")) {
            Ghost ghost = (Ghost) context;
            ghost.setUpdateStrategy((IUpdateStrategy)dirOrStrat);

        }

    }
}
