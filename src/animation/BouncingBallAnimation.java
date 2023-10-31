/*
 * Mor Siman Tov
 * ID: 208682484
 */

package animation;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import geometry.Point;
import sprite.Ball;

/**
 * @author Mor Siman Tov
 * BouncingBallAnimation class that creates animation of a moving ball.
 */

public class BouncingBallAnimation {

    // The sizes of the window
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int UPPER_BOUND = 0;
    private static final int LEFT_BOUND = 0;

    // The radius of a ball
    private static final int RADIUS = 50;

    /**
     * Creates an empty drawing surface and a ball and draws it on the surface.
     *
     * @param start the starting center point of the ball
     * @param dx the first position of the ball
     * @param dy the second position of the ball
     */
    private static void drawAnimation(Point start, double dx, double dy) {

        // Time between frames in milliseconds
        final int sleepTime = 50;
        double x = start.getX();
        double y = start.getY();

        // Create a new window animation
        GUI gui = new GUI("Bouncing Ball Animation", WIDTH, HEIGHT);
        biuoop.Sleeper sleeper = new Sleeper();

        /*
        While the radius of the ball is bigger than the sizes of the frames, change x and y coordinates
        (the starting point of the ball), so it would fit inside the frame.
        */
        while (x - RADIUS <= LEFT_BOUND) {
            x++;
        }
        while (x + RADIUS >= WIDTH) {
            x--;
        }
        while (y - RADIUS <= UPPER_BOUND) {
            y++;
        }
        while (y + RADIUS >= HEIGHT) {
            y--;
        }

        // Create a ball
        Ball ball = new Ball((int) x, (int) y, RADIUS, java.awt.Color.BLACK);
        ball.setWindowBounds(UPPER_BOUND, LEFT_BOUND, WIDTH, HEIGHT);

        // Set the velocity of the ball
        ball.setVelocity(dx, dy);

        /*
        A while loop that creates an empty drawing surface, creates a ball with a random location and draws it on
        the surface.
         */
        while (true) {

            // Move the ball inside the bounds of the window
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();

            // Draw the ball on the surface
            ball.drawOn(d);
            gui.show(d);

            // wait for 50 milliseconds
            sleeper.sleepFor(sleepTime);
        }
    }

    /**
     * The main function that calls drawAnimations with the parameters of the ball.
     *
     * @param args command line arguments, contain x and y of the starting point of the ball and dx and dy positions.
     */
    public static void main(String[] args) {
        final int numArguments = 4;

        // If the number of arguments is different than numArguments
        if (args.length != numArguments) {
            System.out.println("Invalid input");
            return;
        }

        // Call drawAnimation with the parameters received
        drawAnimation(new Point(Double.parseDouble(args[0]), Double.parseDouble(args[1])), Double.parseDouble(args[2]),
                Double.parseDouble(args[3]));
    }
}