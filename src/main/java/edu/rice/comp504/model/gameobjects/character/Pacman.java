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
    /**
     * Constructor for Pacman.
     * @param loc pacman location
     */
    private Pacman(Point loc) {
        super(loc, "pacman", new Point(0,1), PacmanUpdateStrategy.makeStrategy(), PacmanInteraction.makeStrategy(), DispatchAdapter.getGridSize());
    }

    /**
     * Handle collision between Pacman and game object.
     * @param gameObject object the check collision against
     * @return whether there was a collision
     */
    public boolean collision(AGameObject gameObject) {

        Point pacmanLoc = this.getLocation();
        int pacmanSize = this.getSize()/2;
        Point pacmanVel = this.getVel();

        Point gameObjLoc = gameObject.getLocation();
        int gameObjSize = gameObject.getSize();

        //O ---> []

        /**
         * O ---> [] no 1
         *
         * [] <--- O  no 2
         *
         * [ ]
         *  ^
         *  |        no 4
         *  O
         *
         *   O
         *   |     no 3
         *  [ ]
         */

        if(gameObjLoc.getX() + gameObjSize > pacmanLoc.getX() + pacmanSize
                && pacmanLoc.getX() + pacmanSize >  gameObjLoc.getX()
                && pacmanLoc.getY() - pacmanSize == gameObjLoc.getY()
                && pacmanLoc.getY() + pacmanSize == gameObjLoc.getY() + gameObjSize
                && pacmanVel.getX() > 0){

            return true;
        }else if(gameObjLoc.getX() < pacmanLoc.getX() - pacmanSize
                && pacmanLoc.getX() - pacmanSize < gameObjLoc.getX()+gameObjSize
                && pacmanLoc.getY() - pacmanSize == gameObjLoc.getY()
                && pacmanLoc.getY() + pacmanSize == gameObjLoc.getY() + gameObjSize
                && pacmanVel.getX() < 0){

            return true;
        }else if(pacmanLoc.getY() + pacmanSize < gameObjLoc.getY() + gameObjSize
                && pacmanLoc.getY() + pacmanSize > gameObjLoc.getY()
                && pacmanLoc.getX() + pacmanSize == gameObjLoc.getX() + gameObjSize
                &&  pacmanLoc.getX() - pacmanSize == gameObjLoc.getX()
                && pacmanVel.getY() > 0){
            //System.out.println(pacmanLoc.getY() +  " " + pacmanSize + "  " + gameObjLoc.getY() +  " " +gameObjSize  + gameObject.getType());
            return true;
        }else if(pacmanLoc.getY() - pacmanSize > gameObjLoc.getY()
                && pacmanLoc.getX() + pacmanSize == gameObjLoc.getX() + gameObjSize
                &&  pacmanLoc.getX() - pacmanSize == gameObjLoc.getX()
                && pacmanLoc.getY() - pacmanSize < gameObjLoc.getY() + gameObjSize
                && pacmanVel.getY() < 0){

            return true;
        }

        return false;
    }


    public static Pacman getInstance(Point loc){

        if (pacman == null)
            pacman = new Pacman(loc);

        return pacman;


    }
}
