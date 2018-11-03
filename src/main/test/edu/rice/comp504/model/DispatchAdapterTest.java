package edu.rice.comp504.model;

import edu.rice.comp504.model.gameobjects.Exit;
import edu.rice.comp504.model.gameobjects.Wall;
import edu.rice.comp504.model.gameobjects.character.Ghost;
import edu.rice.comp504.model.gameobjects.character.Pacman;
import edu.rice.comp504.model.gameobjects.food.BigDot;
import edu.rice.comp504.model.gameobjects.food.Fruit;
import edu.rice.comp504.model.gameobjects.food.SmallDot;
import edu.rice.comp504.model.strategy.update.GhostRandomStrategy;
import junit.framework.TestCase;

import java.awt.*;

/**
 * Test the basic functions in DispatchAdapter.
 */
public class DispatchAdapterTest extends TestCase {

    /**
     * Test update Pacman.
     */
    public void testUpdatePacman() {
        DispatchAdapter adapter = new DispatchAdapter();
        adapter.setCanvasDims(new Point(540, 370));

        // add objects for testing
        Pacman pacman = Pacman.makePacman(new Point(270, 230), adapter);
        adapter.addObserver(pacman);
        adapter.addObserver(new Wall(new Point(280, 240)));
        adapter.addObserver(new Exit(new Point(520, 160), new Point(0, 160)));
        // prevent game from ending
        adapter.setLives(1);
        adapter.setDotsLeft(1);

        // test Pacman movement
        int currentX = pacman.getLocation().x;
        int currentY = pacman.getLocation().y;
        pacman.setVel(new Point(0, 20));
        adapter.updatePacWorld();
        assertEquals("Check Pacman x location after move",
                currentX + pacman.getVel().x, pacman.getLocation().x);
        assertEquals("Check Pacman y location after move",
                currentY + pacman.getVel().y, pacman.getLocation().y);

        // test Pacman wall collision
        currentX = pacman.getLocation().x;
        currentY = pacman.getLocation().y;
        pacman.setVel(new Point(20, 0));
        adapter.updatePacWorld();
        assertEquals("Check Pacman x location after wall collision", currentX, pacman.getLocation().x);
        assertEquals("Check Pacman y location after wall collision", currentY, pacman.getLocation().y);
        assertEquals("Check Pacman x velocity after wall collision", 0, pacman.getVel().x);
        assertEquals("Check Pacman y velocity after wall collision", 0, pacman.getVel().y);

        // test Pacman exit collision
        pacman.setLocation(new Point(510, 170));
        currentY = pacman.getLocation().y;
        pacman.setSwitchdirection(new Point(20, 0));
        adapter.updatePacWorld();
        assertEquals("Check Pacman x location after exit collision", 30, pacman.getLocation().x);
        assertEquals("Check Pacman y location after exit collision", currentY, pacman.getLocation().y);
    }

    /**
     * Test pacman eats food.
     */
    public void testEatFood() {
        DispatchAdapter adapter = new DispatchAdapter();
        adapter.setCanvasDims(new Point(540, 370));
        // prevent game from ending
        adapter.setLives(1);
        adapter.setDotsLeft(3);

        // add objects for testing
        Pacman pacman = Pacman.makePacman(new Point(270, 230), adapter);
        adapter.addObserver(pacman);
        SmallDot smallDot = new SmallDot(new Point(280, 220));
        adapter.addObserver(smallDot);
        BigDot bigDot = new BigDot(new Point(300, 220));
        adapter.addObserver(bigDot);
        Fruit fruit = Fruit.makeFruit(new Point(320, 220));
        adapter.addObserver(fruit);

        pacman.setSwitchdirection(new Point(20, 0));
        assertEquals("Check all objects added", 4, adapter.countObservers());
        adapter.updatePacWorld();
        assertEquals("Check small dot was destroyed", 3, adapter.countObservers());
        assertEquals("Check small dot updated score", smallDot.getPoints(), adapter.getScore());
        adapter.setScore(0);
        adapter.updatePacWorld();
        assertEquals("Check big dot was destroyed", 2, adapter.countObservers());
        assertEquals("Check small dot updated score", bigDot.getPoints(), adapter.getScore());
        assertEquals("Check big dot updated afraid timer", 99, adapter.getAfraidTimer());
        adapter.setScore(0);
        adapter.updatePacWorld();
        assertEquals("Check fruit was destroyed", 1, adapter.countObservers());
        assertEquals("Check fruit updated score", fruit.getPoints(), adapter.getScore());
    }

    /**
     * Test update ghost.
     */
    public void testUpdateGhost() {
        DispatchAdapter adapter = new DispatchAdapter();
        adapter.setCanvasDims(new Point(540, 370));
        // prevent game from ending
        adapter.setLives(1);
        adapter.setDotsLeft(3);

        Pacman pacman = Pacman.makePacman(new Point(270, 230), adapter);
        adapter.addObserver(pacman);
        Ghost ghost = new Ghost(new Point(220, 100), GhostRandomStrategy.makeStrategy(), "red", adapter);
        adapter.addObserver(ghost);

        // test ghost movement
        int ghostX = ghost.getLocation().x;
        int ghostY = ghost.getLocation().y;
        ghost.setVel(new Point(20, 0));
        adapter.updatePacWorld();
        assertEquals("Check ghost x location after move",
                ghostX + 20, ghost.getLocation().x);
        assertEquals("Check ghost y location after move",
                ghostY, ghost.getLocation().y);

        // test ghost wall collision
        ghostX = ghost.getLocation().x;
        ghostY = ghost.getLocation().y;
        ghost.setVel(new Point(20, 0));
        adapter.addObserver(new Wall(new Point(ghostX + 20, ghostY)));
        adapter.updatePacWorld();
        assertEquals("Check ghost x location after wall collision", ghostX, ghost.getLocation().x);
        assertEquals("Check ghost y location after wall collision", ghostY, ghost.getLocation().y);

        // test ghost exit collision
        adapter.deleteObservers();
        ghost = new Ghost(new Point(220, 100), GhostRandomStrategy.makeStrategy(), "red", adapter);
        adapter.addObserver(ghost);
        ghostX = ghost.getLocation().x;
        ghostY = ghost.getLocation().y;
        ghost.setVel(new Point(20, 0));
        adapter.addObserver(new Exit(new Point(ghostX + 20, ghostY), new Point(0, 160)));
        adapter.updatePacWorld();
        assertEquals("Check ghost x location after exit collision", 20, ghost.getLocation().x);
        assertEquals("Check ghost y location after exit collision", 160, ghost.getLocation().y);
    }

    /**
     * Test ghost eats pacman.
     */
    public void testEatPacman() {
        DispatchAdapter adapter = new DispatchAdapter();
        adapter.setCanvasDims(new Point(540, 370));
        // prevent game from ending
        adapter.setLives(3);
        adapter.setDotsLeft(1);

        Ghost ghost = new Ghost(new Point(220, 100), GhostRandomStrategy.makeStrategy(), "red", adapter);
        adapter.addObserver(ghost);
        Pacman pacman = Pacman.makePacman(new Point(270, 230), adapter);
        adapter.addObserver(pacman);

        ghost.setVel(new Point(20, 0));
        pacman.setLocation(new Point(250, 110));
        pacman.setVel(new Point(0, 0));
        adapter.updatePacWorld();
        adapter.updatePacWorld();
        assertEquals("Check Pacman lives after being eaten", 2, adapter.getLives());
        assertEquals("Check Pacman x location after being eaten", 270, pacman.getLocation().x);
        assertEquals("Check Pacman y location after being eaten", 230, pacman.getLocation().y);
        assertEquals("Check ghost x location after eating Pacman", 220, ghost.getLocation().x);
        assertEquals("Check ghost y location after eating Pacman", 100, ghost.getLocation().y);
    }

    /**
     * Test switch pacman direction.
     */
    public void testSwitchDirection() {
        DispatchAdapter adapter = new DispatchAdapter();
        adapter.setCanvasDims(new Point(540, 370));
        // prevent game from ending
        adapter.setLives(3);
        adapter.setDotsLeft(1);

        Pacman pacman = Pacman.makePacman(new Point(270, 230), adapter);
        adapter.addObserver(pacman);

        // test switch direction
        adapter.switchDirection("keycode=40");
        assertEquals("Check Pacman x velocity after switching direction",
                0, pacman.getSwitchdirection().x);
        assertEquals("Check Pacman y velocity after switching direction",
                20, pacman.getSwitchdirection().y);

        adapter.addObserver(new Wall(new Point(280, 220)));
        pacman.setVel(new Point(0, 20));
        adapter.switchDirection("keycode=39");
        assertEquals("Check Pacman x velocity after switching into wall", 0, pacman.getVel().x);
        assertEquals("Check Pacman y velocity after switching into wall", 20, pacman.getVel().y);
    }
}