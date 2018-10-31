package edu.rice.comp504.model;

import edu.rice.comp504.model.gameobjects.Exit;
import edu.rice.comp504.model.gameobjects.Wall;
import edu.rice.comp504.model.gameobjects.character.Ghost;
import edu.rice.comp504.model.gameobjects.character.Pacman;
import edu.rice.comp504.model.strategy.update.GhostRandomStrategy;
import junit.framework.TestCase;

import java.awt.*;

public class DispatchAdapterTest extends TestCase {

    public void testUpdatePacWorld() {
        DispatchAdapter adapter = new DispatchAdapter();
        adapter.setCanvasDims(new Point(540, 370));

        // add objects for testing
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


        adapter.deleteObservers();
        Ghost ghost = new Ghost(new Point(220, 100), GhostRandomStrategy.makeStrategy(), "red");
        adapter.addObserver(ghost);

        // test ghost movement
        int ghostX = ghost.getLocation().x;
        int ghostY = ghost.getLocation().y;
        ghost.setVel(new Point(20, 0));
        adapter.updatePacWorld();
        assertEquals("Check ghost x location after move",
                ghostX + ghost.getVel().x, ghost.getLocation().x);
        assertEquals("Check ghost y location after move",
                ghostY + ghost.getVel().y, ghost.getLocation().y);

        // test ghost wall collision
        ghostX = ghost.getLocation().x;
        ghostY = ghost.getLocation().y;
        ghost.setVel(new Point(20, 0));

        // add walls around ghost since ghost moves randomly
        adapter.addObserver(new Wall(new Point(ghostX + 20, ghostY)));
        adapter.addObserver(new Wall(new Point(ghostX - 20, ghostY)));
        adapter.addObserver(new Wall(new Point(ghostX, ghostY + 20)));
        adapter.addObserver(new Wall(new Point(ghostX, ghostY - 20)));

        adapter.updatePacWorld();
//        assertEquals("Check ghost x location after wall collision", ghostX, ghost.getLocation().x);
//        assertEquals("Check ghost y location after wall collision", ghostY, ghost.getLocation().y);

        // test ghost exit collision
        adapter.deleteObservers();
        ghost = new Ghost(new Point(220, 100), GhostRandomStrategy.makeStrategy(), "red");
        adapter.addObserver(ghost);
        ghostX = ghost.getLocation().x;
        ghostY = ghost.getLocation().y;
        ghost.setVel(new Point(20, 0));

        // add exits around ghost since ghost moves randomly
        adapter.addObserver(new Exit(new Point(ghostX + 20, ghostY), new Point(0, 160)));
        adapter.addObserver(new Exit(new Point(ghostX - 20, ghostY), new Point(0, 160)));
        adapter.addObserver(new Exit(new Point(ghostX, ghostY + 20), new Point(0, 160)));
        adapter.addObserver(new Exit(new Point(ghostX, ghostY - 20), new Point(0, 160)));

        adapter.updatePacWorld();
//        assertEquals("Check ghost x location after exit collision", 20, ghost.getLocation().x);
//        assertEquals("Check ghost y location after exit collision", 160, ghost.getLocation().y);
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