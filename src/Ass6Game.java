/*
 * Mor Siman Tov
 * ID: 208682484
 */

import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import level.DirectHitLevel;
import level.FinalFourLevel;
import level.Green3Level;
import level.LevelInformation;
import level.WideEasyLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mor Siman Tov
 * Ass6Game class, creates a new game acorrding to given parameters and runs it.
 */

public class Ass6Game {

    /**
     * The main function that creates the game according to the levels given, initializes it and runs it.
     *
     * @param args command line arguments, the number of levels to run
     */
    public static void main(String[] args) {

        // The sizes of the window
        final int windowWidth = 800;
        final int windowHeight = 600;

        // Create a new gui animation in the sizes of the window
        GUI gui = new GUI("Arkanoid", windowWidth, windowHeight);

        // Create a keyboard sensor
        KeyboardSensor keyboard = gui.getKeyboardSensor();

        // Create a new animation runner that will run the animations
        AnimationRunner runner = new AnimationRunner(gui);

        // Create a new gameFlow object the will run the different levels
        GameFlow gameFlow = new GameFlow(runner, keyboard, gui);

        // Create a new list of levels
        List<LevelInformation> levels = new ArrayList<>();

        /*
        If one of the level numbers from 1-4 was given as a parameter, add the specific level to the list of levels to
        run.
         */
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("1")) {
                levels.add(new DirectHitLevel());
            }
            if (args[i].equals("2")) {
                levels.add(new WideEasyLevel());
            }
            if (args[i].equals("3")) {
                levels.add(new Green3Level());
            }
            if (args[i].equals("4")) {
                levels.add(new FinalFourLevel());
            }
        }

        // If there are no valid arguments, add all levels to the list
        if (args.length == 0 || levels.isEmpty()) {
            levels.add(new DirectHitLevel());
            levels.add(new WideEasyLevel());
            levels.add(new Green3Level());
            levels.add(new FinalFourLevel());
        }

        // Run all the levels through the gameFlow object
        gameFlow.runLevels(levels);
    }
}