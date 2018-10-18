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


    }
}
