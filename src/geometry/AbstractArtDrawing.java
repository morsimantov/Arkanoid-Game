/*
 * Mor Siman Tov
 * ID: 208682484
 */

package geometry;

import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * @author Mor Siman Tov
 * AbstractArtDrawing class that draws random lines and their intersection and middle points.
 */

public class AbstractArtDrawing {
    // The radius of the points
    private static final int RADIUS = 3;

    // The sizes of the window
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    /**
     * Creates the abstract drawing of the lines, by creating an array of lines and drawing them on the surface.
     *
     */
    public void drawAbstractArt() {

        // The numbers of lines that will be drawn
        final int linesNumber = 10;

        // Create a window which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Abstract Art Drawing", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        Line[] lines = new Line[linesNumber];

        // Create an array of 10 random-sized lines and draw them on the surface
        for (int i = 0; i < linesNumber; ++i) {
            lines[i] = generateRandomLine();
            drawLine(lines[i], d);

            // Draw the middle point of each line
            drawMidPoint(lines[i], d);
        }

        // Draw the intersection point of each line
        drawIntersectionPoint(lines, d);
        gui.show(d);
    }

    /**
     * Creates a line by four random coordinates (two random points).
     *
     * @return the random line created
     */
    private static Line generateRandomLine() {

        // create a random-number generator
        Random rand = new Random();

        // get integers in range 1-width and 1-height of the window
        int x1 = rand.nextInt(WIDTH) + 1;
        int y1 = rand.nextInt(HEIGHT) + 1;
        int x2 = rand.nextInt(WIDTH) + 1;
        int y2 = rand.nextInt(HEIGHT) + 1;

        // Return the new line created
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Receives a line and draws it on the surface.
     *
     * @param l the line
     * @param d the DrawSurface
     */
    private static void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
    }

    /**
     * Receives a line and draws its middle point.
     *
     * @param l the line
     * @param d the DrawSurface
     */
    private static void drawMidPoint(Line l, DrawSurface d) {
        Point midPoint = l.middle();
        d.setColor(Color.BLUE);
        d.fillCircle((int) midPoint.getX(), (int) midPoint.getY(), RADIUS);
    }

    /**
     * Receives an array of lines and draws all the intersection points of the lines.
     *
     * @param lines array of lines
     * @param d the DrawSurface
     */
    private static void drawIntersectionPoint(Line[] lines, DrawSurface d) {

        // Loop through array of lines and check intersection of each pair of lines
        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {

                // If the lines intersect, create the intersection point
                if (lines[i].isIntersecting(lines[j])) {
                    Point interPoint = lines[i].intersectionWith(lines[j]);
                    d.setColor(Color.RED);

                    // If the intersection point is different than null, draw it
                    if (interPoint != null) {
                        d.fillCircle((int) interPoint.getX(), (int) interPoint.getY(), RADIUS);
                    }
                }
            }
        }
    }

    /**
     * The main function that creates an abstract art drawing of the lines.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        AbstractArtDrawing linesDrawing = new AbstractArtDrawing();

        // Create a new abstract art drawing
        linesDrawing.drawAbstractArt();
    }
}