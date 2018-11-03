package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Ghost;
import edu.rice.comp504.model.gameobjects.character.Pacman;
import edu.rice.comp504.model.strategy.update.*;

import java.awt.*;

/**
 * This command implementing IGameObjectCmd switches the strategy for a pacman or a ghost.
  */
public class SwitchCmd implements IGameObjectCmd {

    String switchInfo;
    DispatchAdapter dis;

    /**
     * Switch Command Constructor.
     * @param switchInfo information on what to switch to.
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
        // switch ghost strategy
        else if (type.equals("ghost")) {
            Ghost ghost = (Ghost) context;
            switch (switchInfo) {
                // switch ghosts to afraid strategy
                case "afraid":
                    ghost.setUpdateStrategy(GhostAfraidStrategy.makeStrategy());
                    break;
                // switch ghosts to attack strategy
                case "red":
                    if (ghost.getColor().equals("red")) {
                        ghost.setUpdateStrategy(GhostChaseStrategy.makeStrategy());
                    }
                    break;
                case "pink":
                    if (ghost.getColor().equals("pink")) {
                        ghost.setUpdateStrategy(GhostTrapStrategy.makeStrategy());
                    }
                    break;
                case "orange":
                    if (ghost.getColor().equals("orange")) {
                        ghost.setUpdateStrategy(GhostTrailStrategy.makeStrategy());
                    }
                    break;
                case "blue":
                    if (ghost.getColor().equals("blue")) {
                        ghost.setUpdateStrategy(GhostRandomStrategy.makeStrategy());
                    }
                    break;
                default:
                    break;
            }

        }

    }
}
