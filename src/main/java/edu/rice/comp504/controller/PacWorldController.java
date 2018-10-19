package edu.rice.comp504.controller;

import edu.rice.comp504.model.DispatchAdapter;

import com.google.gson.Gson;

import java.awt.*;

import static spark.Spark.*;

/**
 * The controller that creates the dispatch adapter and defines the REST end points
 */
public class PacWorldController {

    public static void main(String[] args) {
        staticFiles.location("/public");

        Gson gson = new Gson();
        DispatchAdapter dis = new DispatchAdapter();

/**
 * load the object
 */
        post("/loadObject", (request, response) -> {
            return dis.loadObject(request.body());
        });

        /**
         * clears observers and adds all observers for a new game
         */
        post("/resetGame", (request, response) -> {
            dis.initializeGame();
            return gson.toJson(dis);
        });

        /**
         * update positions at each time step
         */
        get("/updateGame", (request, response) -> {
            dis.updateGame();
            return gson.toJson(dis);
        });

        /**
         * switch Pacman direction
         */
        get("/switchDirection", (request, response) -> {
            dis.switchDirection(request.body());
            return gson.toJson(dis);
        });

        /**
         * get canvas dimensions
         */
        get("/canvasDims", (request, response) -> {
            int height = Integer.parseInt(request.queryParams("height"));
            int width = Integer.parseInt(request.queryParams("width"));
            Point dims = new Point(height, width);
            dis.setCanvasDims(dims);
            return gson.toJson(dis);
        });
    }
}
