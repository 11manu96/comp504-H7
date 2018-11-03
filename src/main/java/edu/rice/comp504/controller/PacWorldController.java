package edu.rice.comp504.controller;

import edu.rice.comp504.model.DispatchAdapter;

import com.google.gson.Gson;
import java.awt.*;

import static spark.Spark.*;

/**
 * The controller creates the dispatch adapter and defines the REST endpoints.
 */
public class PacWorldController {

    /**
     * Main function that runs the Pacman game.
     * @param args args.
     */
    public static void main(String[] args) {
        //set the port for Heroku
        port(getHerokuAssignedPort());
        staticFiles.location("/public");

        Gson gson = new Gson();
        DispatchAdapter dis = new DispatchAdapter();

        // get canvas dimensions and reset game objects
        get("/resetGame", (request, response) -> {
            int height = Integer.parseInt(request.queryParams("height"));
            int width = Integer.parseInt(request.queryParams("width"));
            Point dims = new Point(width, height);
            dis.setCanvasDims(dims);

            dis.initializeGame();
            return gson.toJson(dis);
        });

        // update game object locations
        get("/updateGame", (request, response) -> {
            dis.updatePacWorld();
            return gson.toJson(dis);
        });

        // switch Pacman direction
        post("/switchDirection", (request, response) -> {
            dis.switchDirection(request.body());
            return gson.toJson(dis);
        });
    }

    /**
     * Get the Heroku assigned port.
     * @return the port.
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
