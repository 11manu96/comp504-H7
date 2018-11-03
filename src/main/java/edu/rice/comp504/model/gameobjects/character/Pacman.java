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
    private boolean switchDirectionCollision;

    private Point switchdirection;

    /**
     * Constructor for Pacman.
     * @param loc pacman location
     */
    private Pacman(Point loc, DispatchAdapter dis) {
        super(loc, "pacman", new Point(0,0),
                PacmanUpdateStrategy.makeStrategy(), PacmanInteraction.makeStrategy(dis), DispatchAdapter.getGridSize());
        this.switchDirectionCollision =false;
        this.switchdirection=new Point(0,0);
    }

    /**
     * Get a singleton Pacman.
     * @param loc pacman location.
     * @param dis dispatchAdapter.
     * @return a pacman.
     */
    public static Pacman makePacman(Point loc, DispatchAdapter dis) {
        if (pacman == null) {
            pacman = new Pacman(loc, dis);
        } else {
            pacman.setLocation(loc);
            pacman.setInteractStrategy(PacmanInteraction.makeStrategy(dis));
        }
        return pacman;
    }

    /**
     * Get a singleton Pacman.
     * @return a pacman.
     */
    public static Pacman getInstance() {
        return pacman;
    }

    /**
     * Check whether the Pacman is switch direction collision.
     * @return boolean whether it is switch direction collision.
     */
    public boolean isSwitchDirectionCollision() {
        return switchDirectionCollision;
    }

    /**
     * Set switch direction collision.
     * @param switchDirectionCollision boolean.
     */
    public void setSwitchDirectionCollision(boolean switchDirectionCollision) {
        this.switchDirectionCollision = switchDirectionCollision;
    }

    /**
     * Get the next direction for the Pacman.
     * @return a Point direction.
     */
    public Point getSwitchdirection() {
        return switchdirection;
    }

    /**
     * Set the next direction for the Pacman.
     * @param switchdirection a Point direction.
     */
    public void setSwitchdirection(Point switchdirection) {
        this.switchdirection = switchdirection;
    }

    /**
     * Handle collision between Pacman and game object.
     * @param gameObject object the check collision against
     * @return whether there was a collision.
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
