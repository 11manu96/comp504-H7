package edu.rice.comp504.model;

import edu.rice.comp504.model.gameobjects.Exit;
import edu.rice.comp504.model.gameobjects.Wall;
import edu.rice.comp504.model.gameobjects.character.Pacman;
import junit.framework.TestCase;

import java.awt.*;

public class DispatchAdapterTest extends TestCase {

    public void testUpdatePacWorld() {
        DispatchAdapter adapter = new DispatchAdapter();
        adapter.setCanvasDims(new Point(540, 370));
        Pacman pacman = Pacman.makePacman(new Point(270, 230));
        adapter.addObserver(pacman);
        adapter.addObserver(new Wall(new Point(280, 240)));
        adapter.addObserver(new Exit(new Point(520, 160), new Point(0, 160)));

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
        pacman.setVel(new Point(20, 0));
        adapter.updatePacWorld();
        assertEquals("Check Pacman x location after exit collision", 30, pacman.getLocation().x);
        assertEquals("Check Pacman y location after exit collision", currentY, pacman.getLocation().y);
    }

    public void testSwitchDirection() {
        DispatchAdapter adapter = new DispatchAdapter();
        adapter.setCanvasDims(new Point(540, 370));
        adapter.initializeGame();
        Pacman pacman = Pacman.getInstance();

        // test switch direction
        adapter.switchDirection("keycode=40");
        assertEquals("Check Pacman x velocity after switch direction", 0, pacman.getVel().x);
        assertEquals("Check Pacman y velocity after switch direction", 20, pacman.getVel().y);
    }
}