package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.gameobjects.character.Ghost;
import edu.rice.comp504.model.gameobjects.character.Pacman;
import edu.rice.comp504.model.strategy.update.GhostAfraidStrategy;
import edu.rice.comp504.model.strategy.update.GhostRandomStrategy;

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
                    pacman.setVel(new Point(-20, 0));
                    break;
                case "right":
                    pacman.setVel(new Point(20, 0));
                    break;
                case "up":
                    pacman.setVel(new Point(0, -20));
                    break;
                case "down":
                    pacman.setVel(new Point(0, 20));
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
                case "attack":
                    switch (ghost.getColor()) {
                        case "red":
                            ghost.setUpdateStrategy(GhostRandomStrategy.makeStrategy());
                            break;
                        case "pink":
                            ghost.setUpdateStrategy(GhostRandomStrategy.makeStrategy());
                            break;
                        case "orange":
                            ghost.setUpdateStrategy(GhostRandomStrategy.makeStrategy());
                            break;
                        case "blue":
                            ghost.setUpdateStrategy(GhostRandomStrategy.makeStrategy());
                            break;
                        default:
                            break;
                    }
                default:
                    break;
            }

        }

    }
}
