/*
 * Mor Siman Tov
 * ID: 208682484
 */

package animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import geometry.Point;
import sprite.Ball;

import java.awt.Color;
import java.util.Random;

/**
 * @author Mor Siman Tov
 * MultipleBouncingBallsAnimation class that creates animation of multiple moving balls.
 */

public class MultipleBouncingBallsAnimation {

    // The sizes of the window
    private static final int WIDTH = 450;
    private static final int HEIGHT = 450;
    private static final int UPPER_BOUND = 0;
    private static final int LEFT_BOUND = 0;

    /**
     * Creates an array of balls.
     *
     * @param args command line arguments, contain the radii of the balls
     * @param numBalls the numbers of balls received
     * @param startRange the starting range of the ball within the frame limits
     * @param endRange the ending range of the ball within the frame limits
     * @param width the width of the frame
     * @param height the height of the frame
     * @return array of balls
     */
    public static Ball[] createBallsArray(String[] args, int numBalls, int startRange, int endRange, int width,
                                          int height) {
        // Create a random-number generator
        Random rand = new Random();

        // minimalSize will help determine whether the ball is too big for the frame
        int minimalSize = 5;

        // Create a new array of balls
        Ball[] ballsArray = new Ball[numBalls];

        // Loop through all the radii of balls received, and for each radius make a ball
        for (int i = 0; i < numBalls; i++) {
            int radius = Integer.parseInt(args[i]);

            // If the size of the ball is too big for the width or height of the window, minimize the ball
            if (radius >= width / 2 - minimalSize || radius >= height / 2 - minimalSize) {

                // Create a new smaller radius according to the size of the window
                radius = Math.min(width / 2, height / 2) - minimalSize;
            }

            // Set random x and y coordinates for the starting point of the ball, in the bounds of the window
            int x = rand.nextInt(endRange - startRange - 2 * radius) + startRange + radius;
            int y = rand.nextInt(endRange - startRange - 2 * radius) + startRange + radius;

            // Create the starting point of the ball
            Point start = new Point(x, y);

            // r, g, b will set the color of the ball
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();

            // Generate a random color for the ball
            Color randomColor = new Color(r, g, b);

            // Create a new ball and add it to the array
            Ball ball = new Ball((int) start.getX(), (int) start.getY(), radius, randomColor);
            ballsArray[i] = ball;
        }
        return ballsArray;
    }

    /**
     * Set the velocity of each ball in array of balls and check if each radius is valid.
     *
     * @param ballsArray array of balls
     * @param numBalls the numbers of balls received
     */
    public static void setBalls(Ball[] ballsArray, int numBalls) {

        // Balls above the maximum radius size have a default speed
        final int maxRadius = 50;

        // The range of the random integers
        final int startRange = 75;
        final int endRange = 150;

        // Create a random-number generator
        Random rand = new Random();

        // Get dx and dy random integers in range 75-200
        int dx = rand.nextInt(endRange) + startRange;
        int dy = rand.nextInt(endRange) + startRange;

        // Loop through array of balls to set the velocity of each ball and check its radius
        for (int i = 0; i < numBalls; i++) {

            /*
            If the radius of the ball is bigger than maxRadius, set the velocity so that the ball will be slower.
             */
            if (ballsArray[i].getSize() >= maxRadius) {
                ballsArray[i].setVelocity(dx / (double) maxRadius, dy / (double) maxRadius);
            } else {
                ballsArray[i].setVelocity(dx / (double) ballsArray[i].getSize(),
                        dy / (double) ballsArray[i].getSize());
            }
        }
    }

    /**
     * Creates the animation of the moving balls.
     *
     * @param ballsArray array of balls
     * @param numBalls the numbers of balls received
     */
    private static void movingBallsAnimation(Ball[] ballsArray, int numBalls) {

        // Time between frames in milliseconds
        final int sleepTime = 50;

        // Create a new window animation
        GUI gui = new GUI("Multiple Bouncing Balls Animation", WIDTH, HEIGHT);
        biuoop.Sleeper sleeper = new Sleeper();

        /*
        A while loop that creates an empty drawing surface and draws the moving balls on the surface.
         */
        while (true) {

            // Move the balls inside the bounds of the window
            for (int i = 0; i < numBalls; i++) {
                ballsArray[i].setWindowBounds(UPPER_BOUND, LEFT_BOUND, WIDTH, HEIGHT);
                ballsArray[i].moveOneStep();
            }
            DrawSurface d = gui.getDrawSurface();

            // A for loop to draw all the balls on the surface
            for (int i = 0; i < numBalls; i++) {
                ballsArray[i].drawOn(d);
            }
            gui.show(d);

            // wait for 50 milliseconds.
            sleeper.sleepFor(sleepTime);
        }
    }

    /**
     * The main function that calls movingBallsAnimation to create the animation of the balls.
     *
     * @param args command line arguments, contain the radii of the balls
     */
    public static void main(String[] args) {

        // The number of balls received
        final int numBalls = args.length;
        final int startRange = 0;
        final int endRange = 450;

        // If number of arguments is 0 or an empty string received
        if (args.length == 0 || args[0].equals("")) {
            System.out.println("Invalid input");
            return;
        }

        // Create array of balls
        Ball[] ballsArray = createBallsArray(args, numBalls, startRange, endRange, WIDTH, HEIGHT);

        // Go through array of balls to check if all the balls have a valid radius
        for (int i = 0; i < numBalls; i++) {

            // If the radius is negative or 0, return
            if (ballsArray[i].getSize() <= 0) {
                System.out.println("Invalid input");
                return;
            }
        }

        // Set the velocity of the balls
        setBalls(ballsArray, numBalls);

        // Call a movingBallAnimation to create the animation of the balls
        movingBallsAnimation(ballsArray, numBalls);
    }
}