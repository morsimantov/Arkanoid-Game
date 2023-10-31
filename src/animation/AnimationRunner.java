/*
 * Mor Siman Tov
 * ID: 208682484
 */

package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Mor Siman Tov
 * AnimationRunner class, that takes an Animation object and runs it.
 */

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Construct an AnimationRunner.
     *
     * @param gui the gui animation
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * Run an animation object.
     *
     * @param animation the animation object
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;

        // Preform the loop as long should stop is false
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();

            // Do one frame of the animation
            animation.doOneFrame(d);

            // Show the animation on the screen
            this.gui.show(d);

            // Timing of the game
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}