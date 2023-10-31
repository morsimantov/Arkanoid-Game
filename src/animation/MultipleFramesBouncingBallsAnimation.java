/*
 * Mor Siman Tov
 * ID: 208682484
 */

package animation;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprite.Ball;

import java.util.Arrays;
import java.awt.Color;

/**
 * @author Mor Siman Tov
 * MultipleFramesBouncingBallsAnimation class, creates animation of multiple moving balls in two different frames.
 */

public class MultipleFramesBouncingBallsAnimation {

    // The sizes of the window
    private static final int WIDTH = 650;
    private static final int HEIGHT = 650;

    // Sizes of the frames
    private static final int BIG_FRAME = 450;
    private static final int SMALL_FRAME = 150;

    // Upper and lower bounds of the big frame
    private static final int UPPER_BIG_FRAME = 50;
    private static final int LOWER_BIG_FRAME = 500;

    // Upper and lower bounds of the small frame
    private static final int UPPER_SMALL_FRAME = 450;
    private static final int LOWER_SMALL_FRAME = 600;

    /**
     * Creates the balls and invokes the animation of them moving inside two frames.
     *
     * @param args command line arguments, contain the radii of the balls
     * @param numBalls the numbers of balls received
     */
    public static void multipleFrames(String[] args, int numBalls) {

        // If one of the radii received is negative or 0
        for (int i = 0; i < numBalls; i++) {
            if (Integer.parseInt(args[i]) <= 0) {
                return;
            }
        }

        /*
        Create two arrays of balls, one for the first half of the balls and one for the second half. Each array
        of balls is for a different frame.
         */
        Ball[] array1 = MultipleBouncingBallsAnimation.createBallsArray(args, numBalls / 2, UPPER_BIG_FRAME,
                LOWER_BIG_FRAME, BIG_FRAME, BIG_FRAME);
        Ball[] array2 = MultipleBouncingBallsAnimation.createBallsArray(Arrays.copyOfRange(args, numBalls / 2,
                numBalls), numBalls / 2, UPPER_SMALL_FRAME, LOWER_SMALL_FRAME, SMALL_FRAME, SMALL_FRAME);

        // Draw the animation of the moving balls inside the frames
        drawAnimationInFrames(array1, array2, numBalls / 2);
    }

    /**
     * Draws the moving balls, half of them inside the bigger frame and the other half inside the smaller frame.
     *
     * @param array1 the first array of balls
     * @param array2 the second array of balls
     * @param numBalls the numbers of balls received
     */
    private static void drawAnimationInFrames(Ball[] array1, Ball[] array2, int numBalls) {

        // Time between frames in milliseconds
        final int sleepTime = 50;

        // Create a new window animation
        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", WIDTH, HEIGHT);
        DrawSurface d;

        // Set the velocity and radii of the balls in each array
        MultipleBouncingBallsAnimation.setBalls(array1, numBalls);
        MultipleBouncingBallsAnimation.setBalls(array2, numBalls);
        biuoop.Sleeper sleeper = new Sleeper();
        while (true) {

            // Loop through two arrays and move the balls inside the bounds of the window
            for (int i = 0; i < numBalls; i++) {
                array1[i].setWindowBounds(UPPER_BIG_FRAME, UPPER_BIG_FRAME, LOWER_BIG_FRAME, LOWER_BIG_FRAME);
                array2[i].setWindowBounds(UPPER_SMALL_FRAME, UPPER_SMALL_FRAME, LOWER_SMALL_FRAME, LOWER_SMALL_FRAME);
                array1[i].moveOneStep();
                array2[i].moveOneStep();
            }
            d = gui.getDrawSurface();

            // Set the color of the first frame
            d.setColor(Color.GRAY);

            // Create the big frame
            d.fillRectangle(UPPER_BIG_FRAME, UPPER_BIG_FRAME, BIG_FRAME, BIG_FRAME);

            // Set the color of the second frame
            d.setColor(Color.YELLOW);

            // Create the small frame
            d.fillRectangle(UPPER_SMALL_FRAME, UPPER_SMALL_FRAME, SMALL_FRAME, SMALL_FRAME);

            // Draw the balls on the surface
            for (int i = 0; i < numBalls; i++) {
                array1[i].drawOn(d);
                array2[i].drawOn(d);
            }
            gui.show(d);

            // wait for 50 milliseconds.
            sleeper.sleepFor(sleepTime);
        }
    }

    /**
     * The main function that calls multipleFrames to create the animation of multiple balls inside the frames.
     *
     * @param args command line arguments, contain the radii of the balls
     */
    public static void main(String[] args) {

        // The number of balls received
        final int numBalls = args.length;

        // If number of arguments is 0 or an empty string received
        if (args.length == 0 || args[0].equals("")) {
            System.out.println("Invalid input");
            return;
        }

        // If one of the radii received is negative or 0
        for (int i = 0; i < numBalls; i++) {
            if (Integer.parseInt(args[i]) <= 0) {
                System.out.println("Invalid input");
                return;
            }
        }

        // Call multipleFrames to create the animation of the balls inside the frames
        multipleFrames(args, args.length);
    }
}