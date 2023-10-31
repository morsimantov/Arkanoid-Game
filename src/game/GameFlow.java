/*
 * Mor Siman Tov
 * ID: 208682484
 */

package game;

import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import level.LevelInformation;
import screen.GameOverScreen;
import screen.YouWinScreen;

import java.util.List;

/**
 * @author Mor Siman Tov
 * GameFlow class, in charge of creating the different levels, and moving from one level to the next.
 */

public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private GUI gui;
    private Counter scoreCounter;
    private boolean winGame;

    /**
     * Construct an GameFlow object, given an animation runner, a keyboard sensor and a gui.
     *
     * @param ar the animation runner
     * @param ks the keyboard sensor
     * @param gui the gui animation
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.scoreCounter = new Counter(0);
        this.winGame = true;
    }

    /**
     * Run the all levels according to the given list of levels, one after the other.
     *
     * @param levels the list of levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        final String key = "space";

        // Loop through all the levels in the given list
        for (LevelInformation levelInfo : levels) {

            // Create a new game level
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.gui,
                    this.scoreCounter);

            // Initialize the level
            level.initialize();

            // While there are balls and blocks in the game level, run the level
            while (level.getRemainingBalls() != 0 && level.getRemainingBlocks() != 0) {
                level.run();
            }

            // If there are no balls left, meaning the player lost
            if (level.getRemainingBalls() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, key,
                        new GameOverScreen(this.scoreCounter)));
                this.winGame = false;
                break;
            }
        }

        // When the player cleared all the blocks of all levels and won the game
        if (this.winGame) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, key,
                    new YouWinScreen(this.scoreCounter)));
        }

        // Close the gui animation, i.e finish the game
        gui.close();
    }
}