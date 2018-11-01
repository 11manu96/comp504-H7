package edu.rice.comp504.model.gameobjects.character;

import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.gameobjects.AGameObject;
import edu.rice.comp504.model.strategy.interact.GhostInteraction;
import edu.rice.comp504.model.strategy.update.IUpdateStrategy;

import java.awt.*;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * This concrete class extends ACharacter and represents ghost game objects.
 */
public class Ghost extends ACharacter {
    private int jailTimer;
    private String color;
    private int points;
    private List<Point> openSpaces;

    /**
     * Constructor for Ghost.
     * @param loc ghost location
     * @param updateStrategy update strategy
     * @param color ghost color
     */
    public Ghost(Point loc, IUpdateStrategy updateStrategy, String color, DispatchAdapter dis) {
        super(loc, "ghost", new Point(0,0), updateStrategy, GhostInteraction.makeStrategy(dis), DispatchAdapter.getGridSize());
        this.jailTimer = 3;
        this.color = color;
        this.points = 200;
        this.setOpenSpaces();
    }

    /**
     * Get the color of the ghost.
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set the color of the ghost.
     * @param color ghost color
     */
    public void setColor(String color) {
        this.color = color;
    }

    public int getPoints() { return this.points; }

    /**
     * Get the jail timer.
     * @return time ghost will stay in jail
     */
    public int getJailTimer() {
        return this.jailTimer;
    }

    /**
     * Set the jail timer.
     * @param jailTimer time ghost will stay in jail
     */
    public void setJailTimer(int jailTimer) {
        this.jailTimer = jailTimer;
    }


    public void setOpenSpaces() {
        this.openSpaces = new LinkedList<Point>(Arrays.asList(
                new Point(-20, 0), new Point(20, 0),
                new Point(0, 20), new Point(0, -20)));
    }

    public void removeOpenSpace(Point direction) {
        this.openSpaces.remove(direction);
    }

    public List<Point> getOpenSpaces() { return this.openSpaces; }

    /**
     * Handle collision between ghost and game object.
     * @param gameObject object to check collision against
     * @return whether there was collision
     */
    public boolean collision(AGameObject gameObject) {

        Point ghostLoc = this.getLocation();
        int ghostSize = this.getSize() / 2;
        Point ghostVel = this.getVel();

        Point gameObjLoc = gameObject.getLocation();
        int gameObjSize = gameObject.getSize() / 2;

        int distX = ghostLoc.x + DispatchAdapter.getGridSize() / 2 + ghostVel.x - (gameObjLoc.x + DispatchAdapter.getGridSize() / 2);
        int distY = ghostLoc.y + DispatchAdapter.getGridSize() / 2 + ghostVel.y - (gameObjLoc.y + DispatchAdapter.getGridSize() / 2);

        if(gameObject.getType().equals("pacman")){

            Pacman pacman = (Pacman)gameObject;

            int distX1 = ghostLoc.x + DispatchAdapter.getGridSize() / 2 - gameObjLoc.x;
            int distY1 = ghostLoc.y + DispatchAdapter.getGridSize() / 2 - gameObjLoc.y;

            if(((ghostVel.x + pacman.getVel().x)==0) && ((ghostVel.y + pacman.getVel().y)==0)){
                if ((Math.abs(distX1) <= ghostSize + gameObjSize) && (Math.abs(distY1) <= ghostSize + gameObjSize)) {
                    return true;
                }
            }
            else if ((Math.abs(distX1) < ghostSize + gameObjSize) && (Math.abs(distY1) < ghostSize + gameObjSize)) {
                return true;
            }
        }
        else if ((Math.abs(distX) < ghostSize + gameObjSize) && (Math.abs(distY) < ghostSize + gameObjSize)) {
            return true;
        }
        return false;
    }
}
