package edu.rice.comp504.model.gameobjects.character;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.strategy.interact.PacmanInteraction;
import edu.rice.comp504.model.strategy.update.PacmanUpdateStrategy;

import java.awt.*;

/**
 * This concrete class extends ACharacter and represents the Pacman game object.
 */
public class Pacman extends ACharacter {

    private static Pacman pacman;
    private Point initialLoc;
    /**
     * Constructor for Pacman.
     * @param loc pacman location
     */
    private Pacman(Point loc,DispatchAdapter dis) {
        super(loc, "pacman", new Point(0,0),
                PacmanUpdateStrategy.makeStrategy(), PacmanInteraction.makeStrategy(dis), DispatchAdapter.getGridSize());
        this.initialLoc=new Point(loc);
    }

    public static Pacman makePacman(Point loc,DispatchAdapter dis) {
        if (pacman == null) {
            pacman = new Pacman(loc,dis);
        } else {
            pacman.setLocation(loc);
        }
        return pacman;
    }

    public static Pacman getInstance() {
        return pacman;
    }

    public Point getInitialLoc(){return this.initialLoc;}

    /**
     * Handle collision between Pacman and game object.
     * @param gameObject object the check collision against
     * @return whether there was a collision
     */
    public boolean collision(AGameObject gameObject) {

        Point pacmanLoc = this.getLocation();
        int pacmanSize = this.getSize() / 2;
        Point pacmanVel = this.getVel();

        Point gameObjLoc = gameObject.getLocation();
        int gameObjSize = gameObject.getSize() / 2;

        int distX = pacmanLoc.x + pacmanVel.x - (gameObjLoc.x + DispatchAdapter.getGridSize() / 2);
        int distY = pacmanLoc.y + pacmanVel.y - (gameObjLoc.y + DispatchAdapter.getGridSize() / 2);
        if ((Math.abs(distX) < pacmanSize + gameObjSize) && (Math.abs(distY) < pacmanSize + gameObjSize)) {
            return true;
        }
        return false;
    }
}
